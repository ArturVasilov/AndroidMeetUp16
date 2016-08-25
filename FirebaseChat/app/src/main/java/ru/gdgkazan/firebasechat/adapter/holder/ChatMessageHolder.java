package ru.gdgkazan.firebasechat.adapter.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
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
    public static ChatMessageHolder create(@NonNull ViewGroup parent, boolean isCurrentUser) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_message, parent, false);
        itemView.setLayoutDirection(isCurrentUser ? View.LAYOUT_DIRECTION_RTL : View.LAYOUT_DIRECTION_LTR);
        ButterKnife.<TextView>findById(itemView, R.id.nameTextView).setGravity(isCurrentUser ? Gravity.END : Gravity.START);
        ButterKnife.<TextView>findById(itemView, R.id.messageTextView).setGravity(isCurrentUser ? Gravity.END : Gravity.START);
        return new ChatMessageHolder(itemView);
    }

    public void bind(@NonNull Message message) {
        mNameTextView.setText(message.getName());
        mMessageTextView.setText(message.getMessage());

        if (!TextUtils.isEmpty(message.getIconUrl())) {
            Picasso.with(mUserAvatar.getContext())
                    .load(message.getIconUrl())
                    .into(mUserAvatar);
        }
    }
}
