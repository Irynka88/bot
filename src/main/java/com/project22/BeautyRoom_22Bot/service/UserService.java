package com.project22.BeautyRoom_22Bot.service;

import com.project22.BeautyRoom_22Bot.enums.Constants;
import com.project22.BeautyRoom_22Bot.model.User;
import com.project22.BeautyRoom_22Bot.repository.UserRepository;
import com.project22.BeautyRoom_22Bot.sender.BotSender;
import com.vdurmont.emoji.EmojiParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public void register(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(EmojiParser.parseToUnicode("Бажаєте зареєструватися? :innocent: "));

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        var yesButton = new InlineKeyboardButton();

        yesButton.setText("Так");
        yesButton.setCallbackData(Constants.YES_BUTTON);

        var noButton = new InlineKeyboardButton();

        noButton.setText("Ні");
        noButton.setCallbackData(Constants.NO_BUTTON);

        rowInLine.add(yesButton);
        rowInLine.add(noButton);

        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        try {
            BotSender.getInstance().execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }

    public void registerUser(Message msg) {

        if (userRepository.findById(msg.getChatId()).isEmpty()){

            var chatId = msg.getChatId();
            var chat = msg.getChat();

            User user = new User();
            user.setChatId(chatId);
            user.setFirstName(chat.getFirstName());
            user.setLastName(chat.getLastName());
            user.setUserName(chat.getUserName());
            user.setRegisteredAt(LocalDate.now());

            userRepository.save(user);
        }

    }
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public User findUserWithProcedures(Long chatId) {
        User user = userRepository.findById(chatId).orElseThrow();
        user.getProcedures().size();
        return user;
    }
}
