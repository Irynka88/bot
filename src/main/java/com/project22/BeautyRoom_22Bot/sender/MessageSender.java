package com.project22.BeautyRoom_22Bot.sender;

import com.project22.BeautyRoom_22Bot.enums.Constants;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
@Component
public class MessageSender {
    public void startCommandReceived(long chatId , String name){

        String answer = EmojiParser.parseToUnicode("Привіт , " + name + " :blush:\n" + "Вітаю вас у салоні краси BeautyRoom :heart:");
        sendMessage(chatId, answer);

    }

    public void sendMessage(long chatId , String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        try {
            BotSender.getInstance().execute(message);

        }
        catch (TelegramApiException e){
            e.printStackTrace();
        }

    }


    public void executeEditMassageText(String text , long chatId , long messageId ){
        EditMessageText message = new EditMessageText();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        message.setMessageId((int) messageId);

        try {
            BotSender.getInstance().execute(message);

        }
        catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public void chooseFavor(long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Оберіть процедуру");
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> firstRowInLine = new ArrayList<>();
        List<InlineKeyboardButton> secondRowInLine = new ArrayList<>();
        List<InlineKeyboardButton> thirdRowInLine = new ArrayList<>();
        List<InlineKeyboardButton> fourthRowInLine = new ArrayList<>();


        var browButton = new InlineKeyboardButton();
        browButton.setText("Фарбування/ламінування брів");
        browButton.setCallbackData(Constants.BROW_BUTTON);

        var lashButton = new InlineKeyboardButton();
        lashButton.setText("Ламінування/нарощення вій");
        lashButton.setCallbackData(Constants.LASH_BUTTON);

        var muButton = new InlineKeyboardButton();
        muButton.setText("Макіяж");
        muButton.setCallbackData(Constants.MU_BUTTON);

        var nailButton = new InlineKeyboardButton();
        nailButton.setText("Манікюр");
        nailButton.setCallbackData(Constants.NAIL_BUTTON);

        firstRowInLine.add(browButton);
        secondRowInLine.add(lashButton);
        thirdRowInLine.add(muButton);
        fourthRowInLine.add(nailButton);

        rowsInLine.add(firstRowInLine);
        rowsInLine.add(secondRowInLine);
        rowsInLine.add(thirdRowInLine);
        rowsInLine.add(fourthRowInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        try {
            BotSender.getInstance().execute(message);

        }
        catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public void sendPrice(long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Натисність щоб дізнатися ціну");
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> firstRowInLine = new ArrayList<>();
        List<InlineKeyboardButton> secondRowInLine = new ArrayList<>();
        List<InlineKeyboardButton> thirdRowInLine = new ArrayList<>();
        List<InlineKeyboardButton> fourthRowInLine = new ArrayList<>();
        List<InlineKeyboardButton> fifthRowInLine = new ArrayList<>();
        List<InlineKeyboardButton> sixthRowInLine = new ArrayList<>();

        var paintButton = new InlineKeyboardButton();
        paintButton.setText("Фарбування брів");
        paintButton.setCallbackData(Constants.PAINT_BROW);

        var lamButton = new InlineKeyboardButton();
        lamButton.setText("Ламінування брів");
        lamButton.setCallbackData(Constants.LAM_BROW);

        var lamLButton = new InlineKeyboardButton();
        lamLButton.setText("Ламінування вій");
        lamLButton.setCallbackData(Constants.LAM_LASH);

        var lashButton = new InlineKeyboardButton();
        lashButton.setText("Нарощення вій");
        lashButton.setCallbackData(Constants.LASH_BUTTON);

        var muButton = new InlineKeyboardButton();
        muButton.setText("Макіяж");
        muButton.setCallbackData(Constants.MU_BUTTON);

        var nailButton = new InlineKeyboardButton();
        nailButton.setText("Манікюр");
        nailButton.setCallbackData(Constants.NAIL_BUTTON);

        firstRowInLine.add(paintButton);
        secondRowInLine.add(lamButton);
        thirdRowInLine.add(lamLButton);
        fourthRowInLine.add(lashButton);
        fifthRowInLine.add(muButton);
        sixthRowInLine.add(nailButton);

        rowsInLine.add(firstRowInLine);
        rowsInLine.add(secondRowInLine);
        rowsInLine.add(thirdRowInLine);
        rowsInLine.add(fourthRowInLine);
        rowsInLine.add(fifthRowInLine);
        rowsInLine.add(sixthRowInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        try {
            BotSender.getInstance().execute(message);

        }
        catch (TelegramApiException e){
            e.printStackTrace();
        }

    }
}
