package com.project22.BeautyRoom_22Bot.handler;

import com.project22.BeautyRoom_22Bot.enums.Constants;
import com.project22.BeautyRoom_22Bot.sender.MessageSender;
import com.project22.BeautyRoom_22Bot.service.UserService;
import com.vdurmont.emoji.EmojiParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
@Component
@RequiredArgsConstructor
public class Handler {
    private final UserService userService;
    private final MessageSender messageSender;
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

        switch (callbackData){
            case Constants.YES_BUTTON:
                text = EmojiParser.parseToUnicode ("Вітаю , ви зареєструвалися! :tada: ");
                messageSender.executeEditMassageText(text , chatId , messageId);
                messageSender.chooseFavor(chatId);

                if (update.hasMessage()){
                    switch (callbackData){
                        case Constants.BROW_BUTTON :
                            text = "Ви обрали процедуру ламінування вій";
                            messageSender.executeEditMassageText(text, chatId, messageId);

                    }
                }
                break;
            case Constants.NO_BUTTON:
                text = EmojiParser.parseToUnicode ("Реєстрація скасована :x:");
                messageSender.executeEditMassageText(text , chatId , messageId);
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
            default:
                messageSender.sendMessage(chatId, "Вибачте , команди не існує");
        }
    }
}
