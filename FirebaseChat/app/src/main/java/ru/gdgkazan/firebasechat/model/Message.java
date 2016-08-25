package ru.gdgkazan.firebasechat.model;

import android.support.annotation.NonNull;

/**
 * @author Artur Vasilov
 */
public class Message {

    private String mName;
    private String mMessage;
    private String mImageUrl;

    public Message() {
    }

    public Message(@NonNull String name, @NonNull String message, @NonNull String imageUrl) {
        mName = name;
        mMessage = message;
        mImageUrl = imageUrl;
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
    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(@NonNull String imageUrl) {
        mImageUrl = imageUrl;
    }
}
