package ru.gdgkazan.firebasechat.adapter.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.gdgkazan.firebasechat.R;
import ru.gdgkazan.firebasechat.model.Message;

/**
 * @author Artur Vasilov
 */
public class ChatMessageHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.nameTextView)
    TextView mNameTextView;

    @BindView(R.id.messageTextView)
    TextView mMessageTextView;

    @BindView(R.id.userAvatar)
    ImageView mUserAvatar;

    public ChatMessageHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @NonNull
    public static ChatMessageHolder create(@NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_message, parent, false);
        return new ChatMessageHolder(itemView);
    }

    public void bind(@NonNull Message message) {
        mNameTextView.setText(message.getName());
        mMessageTextView.setText(message.getMessage());

        if (!TextUtils.isEmpty(message.getImageUrl())) {
            Picasso.with(mUserAvatar.getContext())
                    .load(message.getImageUrl())
                    .into(mUserAvatar);
        }
    }
}
