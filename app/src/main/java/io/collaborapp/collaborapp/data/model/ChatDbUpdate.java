package io.collaborapp.collaborapp.data.model;

public class ChatDbUpdate {

    public static final int NEW_MESSAGE = 0;
    public static final int CHAT_MUTED = 1;
    public static final int MEMBER_ADDED = 2;
    public static final int MEMBER_REMOVED = 3;
    public static final int TITLE_CHANGED = 4;

    private ChatEntity chat;

    private int response;

    public ChatDbUpdate(ChatEntity chat, int response) {
        this.chat = chat;
        this.response = response;
    }

    public ChatEntity getChat() {
        return chat;
    }

    public int getResponse() {
        return response;
    }
}
