package com.project22.BeautyRoom_22Bot.handler;

import com.project22.BeautyRoom_22Bot.enums.Constants;
import com.project22.BeautyRoom_22Bot.model.*;
import com.project22.BeautyRoom_22Bot.sender.MessageSender;
import com.project22.BeautyRoom_22Bot.service.*;
import com.vdurmont.emoji.EmojiParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
@Component
@RequiredArgsConstructor
public class Handler {
    private final UserService userService;
    private final MessageSender messageSender;
    private final ProcedureService procedureService;
    private final UserProcedureService userProcedureService;
    public void messageHandler(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            handleTextMessage(update);
        } else {
            handleCallbackQuery(update);
        }
    }

    private void handleCallbackQuery(Update update) {

        String callbackData = update.getCallbackQuery().getData();
        long messageId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        String text;
        UserProcedure userProcedure;

        switch (callbackData){
            case Constants.YES_BUTTON:
                text = EmojiParser.parseToUnicode ("Вітаю , ви зареєструвалися! :tada: ");
                messageSender.executeEditMassageText(text , chatId , messageId);
                messageSender.chooseFavor(chatId);
                break;
            case Constants.NO_BUTTON:
                text = EmojiParser.parseToUnicode ("Реєстрація скасована :x:");
                messageSender.executeEditMassageText(text , chatId , messageId);
                break;
            case Constants.PAINT_BROW:
                userProcedure = userProcedureService.createUserProcedure(chatId, "Фарбування брів");
                userProcedureService.saveData(userProcedure);
                messageSender.sendMessage(chatId, "Запис збережено");
                break;
            case Constants.LAM_BROW:
                userProcedure = userProcedureService.createUserProcedure(chatId, "Ламінування брів");
                userProcedureService.saveData(userProcedure);
                messageSender.sendMessage(chatId, "Запис збережено");
                break;
            case Constants.PAINT_LASH:
                userProcedure = userProcedureService.createUserProcedure(chatId, "Нарощення вій");
                userProcedureService.saveData(userProcedure);
                messageSender.sendMessage(chatId, "Запис збережено");
                break;
            case Constants.LAM_LASH:
                userProcedure = userProcedureService.createUserProcedure(chatId, "Ламінування вій");
                userProcedureService.saveData(userProcedure);
                messageSender.sendMessage(chatId, "Запис збережено");
                break;
            case Constants.NAIL_BUTTON:
                userProcedure = userProcedureService.createUserProcedure(chatId, "Манікюр");
                userProcedureService.saveData(userProcedure);
                messageSender.sendMessage(chatId, "Запис збережено");
                break;
            case Constants.MU_BUTTON:
                userProcedure = userProcedureService.createUserProcedure(chatId, "Мейкап");
                userProcedureService.saveData(userProcedure);
                messageSender.sendMessage(chatId, "Запис збережено");
                break;
        }
    }

    private void handleTextMessage(Update update) {
        String messageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();


        switch (messageText) {
            case "/start":
                userService.registerUser(update.getMessage());
                messageSender.startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                break;

            case "/help":
                messageSender.sendMessage(chatId, Constants.HELP_TEXT);
                break;

            case "/register":
                userService.register(chatId);
                break;
            case "/procedures":

                messageSender.sendMessage(chatId, messageSender.sendProcedures(chatId));
                break;
            default:
                messageSender.sendMessage(chatId, "Вибачте , команди не існує");
        }
    }
}
