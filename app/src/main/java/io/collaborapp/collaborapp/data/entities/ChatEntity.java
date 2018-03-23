package io.collaborapp.collaborapp.data.entities;

import java.util.List;

/**
 * Created by wilfredonieves on 11/9/17.
 */

public class ChatEntity {

    private String chatId;
    private String title;
    private List<MessageEntity> messageList;
    private List<UserEntity> members;
    private long createdAt;

}
