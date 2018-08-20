package io.collaborapp.collaborapp.data.model;

public class ChatDbUpdate {

    public static final int NEW_MESSAGE = 0;
    public static final int CHAT_MUTED = 1;
    public static final int MEMBER_ADDED = 2;
    public static final int MEMBER_REMOVED = 3;
    public static final int TITLE_CHANGED = 4;

    private String chatId;

    private int response;

    public ChatDbUpdate(String chatId, int response) {
        this.chatId = chatId;
        this.response = response;
    }

    public String getChatId() {
        return chatId;
    }

    public int getResponse() {
        return response;
    }
}
