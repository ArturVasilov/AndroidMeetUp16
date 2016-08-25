package ru.gdgkazan.firebasechat.view;

import android.support.annotation.NonNull;

import java.util.List;

import ru.gdgkazan.firebasechat.model.Message;

/**
 * @author Artur Vasilov
 */
public interface ChatView {

    void showMessages(@NonNull List<Message> messages);

    void addNewMessage(@NonNull Message message);

}
