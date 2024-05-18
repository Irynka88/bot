package com.project22.BeautyRoom_22Bot.sender;

import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;

public class BotSender extends DefaultAbsSender {
    private static BotSender instance;
    private BotSender() {
        super(new DefaultBotOptions());
    }

    public static BotSender getInstance() {
        if (instance == null){
            instance = new BotSender();
        }
        return instance;
    }

    @Override
    public String getBotToken(){
        return "7074709024:AAFHfiD4uHxTOUs1R05nPLdCKd67gnF-Em4";
    }
}
