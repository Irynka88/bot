package com.project22.BeautyRoom_22Bot.extension;


import com.project22.BeautyRoom_22Bot.handler.Handler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final Handler handler;

    {
        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start", "Розпочати чат"));
        listOfCommands.add(new BotCommand("/help", "Інформація з використання боту"));
        listOfCommands.add(new BotCommand("/register", "Зареєструватися"));
        listOfCommands.add(new BotCommand("/procedures", "Мої записи"));
        try {
            this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getBotUsername() {
        return "BeautyRoom_22Bot";
    }

    @Override
    public String getBotToken() {
        return "7074709024:AAFHfiD4uHxTOUs1R05nPLdCKd67gnF-Em4";
    }

    @Override
    public void onUpdateReceived(Update update) {
        handler.messageHandler(update);
    }
}
