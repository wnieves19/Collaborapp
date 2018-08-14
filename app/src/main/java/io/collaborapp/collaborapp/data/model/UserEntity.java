package io.collaborapp.collaborapp.data.model;

/**
 * Created by wilfredonieves on 10/31/17.
 */

public class UserEntity {

    public UserEntity(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public String userId;
    public String email;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
