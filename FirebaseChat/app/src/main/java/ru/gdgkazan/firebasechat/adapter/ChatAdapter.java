package ru.gdgkazan.firebasechat.adapter;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import ru.gdgkazan.firebasechat.adapter.holder.ChatMessageHolder;
import ru.gdgkazan.firebasechat.model.Message;

/**
 * @author Artur Vasilov
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatMessageHolder> {

    private final List<Message> mMessages;

    private final FirebaseUser mCurrentUser;

    public ChatAdapter() {
        mMessages = new ArrayList<>();
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    public void addMessage(@NonNull Message message) {
        mMessages.add(message);
        notifyItemInserted(mMessages.size() - 1);
    }

    @ItemType
    @Override
    public int getItemViewType(int position) {
        if (TextUtils.equals(mCurrentUser.getUid(), mMessages.get(position).getUid())) {
            return ItemType.CURRENT_USER;
        }
        return ItemType.OTHER_USER;
    }

    @Override
    public ChatMessageHolder onCreateViewHolder(ViewGroup parent, @ItemType int viewType) {
        boolean isCurrentUser = viewType == ItemType.CURRENT_USER;
        return ChatMessageHolder.create(parent, isCurrentUser);
    }

    @Override
    public void onBindViewHolder(ChatMessageHolder holder, int position) {
        holder.bind(mMessages.get(position));
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    @IntDef({ItemType.CURRENT_USER, ItemType.OTHER_USER})
    private @interface ItemType {
        int CURRENT_USER = 0;
        int OTHER_USER = 1;
    }
}
