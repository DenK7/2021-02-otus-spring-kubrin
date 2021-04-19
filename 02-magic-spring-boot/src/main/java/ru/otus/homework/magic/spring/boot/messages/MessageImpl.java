package ru.otus.homework.magic.spring.boot.messages;

import org.apache.commons.lang3.LocaleUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageImpl implements Message{
    private final MessageSource messageSource;
    private final String locale;

    public MessageImpl(MessageSource messageSource, @Value("${default.parameter.locale}") String locale) {
        this.messageSource = messageSource;
        this.locale = locale;
    }

    @Override
    public String getMessage(String messageName) {
        Locale locales = LocaleUtils.toLocale(locale);
        return messageSource.getMessage(messageName, null, locales);
    }
}
