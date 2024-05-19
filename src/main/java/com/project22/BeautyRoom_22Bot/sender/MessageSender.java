package com.project22.BeautyRoom_22Bot.sender;

import com.project22.BeautyRoom_22Bot.enums.Constants;
import com.project22.BeautyRoom_22Bot.model.*;
import com.project22.BeautyRoom_22Bot.repository.*;
import com.project22.BeautyRoom_22Bot.service.*;
import com.vdurmont.emoji.EmojiParser;
import lombok.*;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
@Component
@RequiredArgsConstructor
public class MessageSender {
    private final UserProcedureService userProcedureService;
    private final UserService userService;
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
        List<InlineKeyboardButton> fifthRowInline = new ArrayList<>();
        List<InlineKeyboardButton> sixthRowInline = new ArrayList<>();

        var paintBrowButton = new InlineKeyboardButton();
        paintBrowButton.setText("Фарбування брів");
        paintBrowButton.setCallbackData(Constants.PAINT_BROW);

        var lamBrowButton = new InlineKeyboardButton();
        lamBrowButton.setText("Ламінування брів"); // Corrected this line
        lamBrowButton.setCallbackData(Constants.LAM_BROW); // Corrected this line

        var paintLashButton = new InlineKeyboardButton();
        paintLashButton.setText("Нарощення вій");
        paintLashButton.setCallbackData(Constants.PAINT_LASH);

        var lamLashButton = new InlineKeyboardButton();
        lamLashButton.setText("Ламінування вій");
        lamLashButton.setCallbackData(Constants.LAM_LASH);

        var muButton = new InlineKeyboardButton();
        muButton.setText("Макіяж");
        muButton.setCallbackData(Constants.MU_BUTTON);

        var nailButton = new InlineKeyboardButton();
        nailButton.setText("Манікюр");
        nailButton.setCallbackData(Constants.NAIL_BUTTON);

        firstRowInLine.add(paintBrowButton);
        secondRowInLine.add(lamBrowButton);
        thirdRowInLine.add(paintLashButton);
        fourthRowInLine.add(lamLashButton);
        fifthRowInline.add(muButton);
        sixthRowInline.add(nailButton);

        rowsInLine.add(firstRowInLine);
        rowsInLine.add(secondRowInLine);
        rowsInLine.add(thirdRowInLine);
        rowsInLine.add(fourthRowInLine);
        rowsInLine.add(fifthRowInline);
        rowsInLine.add(sixthRowInline);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        try {
            BotSender.getInstance().execute(message);
        }
        catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public String sendProcedures(long chatId) {
        User user = userService.findUserWithProcedures(chatId);
        List<UserProcedure> userProcedure = userProcedureService.findAll(user);
        return userProcedure.toString();
    }


    public void sendPrice(long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Натисність щоб дізнатися ціни");
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> firstRowInLine = new ArrayList<>();

        var priceButton = new InlineKeyboardButton();
        priceButton.setText("Дізнатися");
        priceButton.setCallbackData(Constants.PRICE);

        firstRowInLine.add(priceButton);

        rowsInLine.add(firstRowInLine);

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
