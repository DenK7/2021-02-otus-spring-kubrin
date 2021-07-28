package ru.otus.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.*;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import ru.otus.integration.model.PeopleModel;
import ru.otus.integration.service.api.TransformMessageService;

@Configuration
@IntegrationComponentScan
@ComponentScan
@EnableIntegration
public class IntegrationConfig {

    private final TransformMessageService transformMessageService;

    public IntegrationConfig(TransformMessageService transformMessageService) {
        this.transformMessageService = transformMessageService;
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate( 1000 ).get();
    }

    @Bean
    public PollableChannel requestPeopleChannel() {
        return MessageChannels.queue( 10 ).get();
    }

    @Bean
    public MessageChannel channelSMS() {
        return MessageChannels.publishSubscribe().minSubscribers(0).get();
    }

    @Bean
    public MessageChannel channelPush() {
        return MessageChannels.publishSubscribe().minSubscribers(0).get();
    }

    @Bean
    public MessageChannel channelEMail() {
        return MessageChannels.publishSubscribe().minSubscribers(0).get();
    }

    @Bean
    // подумать и в дальнейшем переделать на 1 метод каком то образом, может преобразовать основной тип в вертикальный
    // , вместо горизонтального (типы подписки не все в 1 строке -> 1 строка, один тип, тогда можно логи в 1 строку и 1 route
    // хотелось попробовать именно несколько)
    // добавить многопоточность
    // как вариант написать кастомный route
    public IntegrationFlow messageFlow() {
        return IntegrationFlows.from( "requestPeopleChannel" )
                .split()
                // менять содержимое сообщения не очень хорошо, но в данном случае имеет смысл
                // в противном случае эту трансформацию стоит вынести вообще и делать вне интеграции
                .transform(transformMessageService, "transformMessage")
                .channel(channelSMS())
                .channel(channelPush())
                .channel(channelEMail())
                .get();
    }

    @Bean
    public IntegrationFlow messageEMailFlow() {
        return IntegrationFlows.from("channelEMail")
                .route(PeopleModel.class, PeopleModel::isSendEMail,
                        subflow -> subflow.subFlowMapping(true,
                                sf -> sf.handle("serviceSendingEMailImpl", "sendEMail")
                                        .handle("logServiceImpl", "writeLogEMail")
                        )
                                .subFlowMapping(false, IntegrationFlowDefinition::nullChannel)
                )
                .get();
    }

        @Bean
    public IntegrationFlow messageSMSFlow() {
        return IntegrationFlows.from("channelSMS")
                .route(PeopleModel.class, PeopleModel::isSendSMSMessage,
                        subflow -> subflow.subFlowMapping(true,
                                sf -> sf.handle("serviceSendingSMSMessagesImpl", "sendSMSMessage")
                                .handle("logServiceImpl", "writeLogSMS")
                        )
                                .subFlowMapping(false, IntegrationFlowDefinition::nullChannel)
                )
                .get();
    }

    @Bean
    public IntegrationFlow messagePushFlow() {
        return IntegrationFlows.from("channelPush")
                .route(PeopleModel.class, PeopleModel::isSendPushMessage,
                        subflow -> subflow.subFlowMapping(true,
                                sf -> sf.handle("serviceSendingPushMessagesImpl", "sendPushMessage")
                                        .handle("logServiceImpl", "writeLogPush")
                        )
                                .subFlowMapping(false, IntegrationFlowDefinition::nullChannel)
                )
                .get();
    }
}
