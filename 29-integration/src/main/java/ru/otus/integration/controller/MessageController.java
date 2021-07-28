package ru.otus.integration.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.integration.service.api.MessageService;

@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @ApiOperation(value = "Отправка сообщений всем людям в бд")
    @PostMapping("/sendMessage")
    public ResponseEntity<String> listBooks(
            @ApiParam("Сообщение для рассылки, желательно чтоб содержало [name], которое будет заменяться на имя человека")
            @RequestBody String message) {
        return ResponseEntity.ok(messageService.sendMessages(message));
    }

}
