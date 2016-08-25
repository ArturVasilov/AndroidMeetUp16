package ru.gdgkazan.firebasechat.presenter;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import ru.gdgkazan.firebasechat.ChatApplication;
import ru.gdgkazan.firebasechat.model.Message;
import ru.gdgkazan.firebasechat.view.ChatView;

/**
 * @author Artur Vasilov
 */
public class ChatPresenter {

    private static final String MESSAGES = "messages";
    private static final String NAME = "name";
    private static final String ICON = "icon";
    private static final String MESSAGE = "message";

    private final ChatView mChatView;

    private final DatabaseReference mMessagesReference;

    private final ChildEventListener mListener;

    public ChatPresenter(@NonNull ChatView chatView) {
        mChatView = chatView;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mMessagesReference = database.getReference(MESSAGES);
        mListener = new MessagesListener();
    }

    public void start() {
        mMessagesReference.addChildEventListener(mListener);
    }

    public void stop() {
        mMessagesReference.removeEventListener(mListener);
    }

    public void sendMessage(@NonNull String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            ChatApplication.getInstance().restartApp();
            return;
        }

        Map<String, String> params = new HashMap<>();
        params.put(NAME, user.getDisplayName());
        if (user.getPhotoUrl() != null) {
            params.put(ICON, user.getPhotoUrl().toString());
        }
        params.put(MESSAGE, message);

        mMessagesReference.push().setValue(params);
    }

    private class MessagesListener implements ChildEventListener {

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            Message message = dataSnapshot.getValue(Message.class);
            mChatView.addNewMessage(message);
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            // Do nothing
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            // Do nothing
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            // Do nothing
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // Do nothing
        }

    }
}

