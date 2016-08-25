package ru.gdgkazan.firebasechat.model;

import android.support.annotation.NonNull;

/**
 * @author Artur Vasilov
 */
public class Message {

    private String mUid;
    private String mName;
    private String mMessage;
    private String mIconUrl;

    public Message() {
    }

    public Message(@NonNull String uid, @NonNull String name, @NonNull String message, @NonNull String iconUrl) {
        mUid = uid;
        mName = name;
        mMessage = message;
        mIconUrl = iconUrl;
    }

    @NonNull
    public String getUid() {
        return mUid;
    }

    public void setUid(@NonNull String uid) {
        mUid = uid;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    public void setName(@NonNull String name) {
        mName = name;
    }

    @NonNull
    public String getMessage() {
        return mMessage;
    }

    public void setMessage(@NonNull String message) {
        mMessage = message;
    }

    @NonNull
    public String getIconUrl() {
        return mIconUrl;
    }

    public void setIconUrl(@NonNull String iconUrl) {
        mIconUrl = iconUrl;
    }
}
