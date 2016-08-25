package ru.gdgkazan.firebasechat.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.gdgkazan.firebasechat.adapter.holder.ChatMessageHolder;
import ru.gdgkazan.firebasechat.model.Message;

/**
 * @author Artur Vasilov
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatMessageHolder> {

    private final List<Message> mMessages;

    public ChatAdapter() {
        mMessages = new ArrayList<>();
    }

    public void changeDataSet(@NonNull List<Message> messages) {
        mMessages.clear();
        mMessages.addAll(messages);
        notifyDataSetChanged();
    }

    public void addMessage(@NonNull Message message) {
        mMessages.add(message);
        notifyItemInserted(mMessages.size() - 1);
    }

    @Override
    public ChatMessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ChatMessageHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ChatMessageHolder holder, int position) {
        holder.bind(mMessages.get(position));
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }
}
