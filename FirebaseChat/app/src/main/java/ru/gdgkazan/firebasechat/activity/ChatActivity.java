package ru.gdgkazan.firebasechat.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.gdgkazan.firebasechat.R;
import ru.gdgkazan.firebasechat.adapter.ChatAdapter;
import ru.gdgkazan.firebasechat.model.Message;
import ru.gdgkazan.firebasechat.presenter.ChatPresenter;
import ru.gdgkazan.firebasechat.view.ChatView;

public class ChatActivity extends AppCompatActivity implements ChatView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.messagesRecyclerView)
    RecyclerView mMessagesRecycler;

    @BindView(R.id.messageEditText)
    EditText mMessageEdit;

    private ChatAdapter mAdapter;

    private ChatPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mAdapter = new ChatAdapter();
        mPresenter = new ChatPresenter(this);
        mMessagesRecycler.setAdapter(mAdapter);
        mMessagesRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        mPresenter.start();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mPresenter.stop();
        super.onStop();
    }

    @Override
    public void showMessages(@NonNull List<Message> messages) {
        mAdapter.changeDataSet(messages);
        mMessagesRecycler.smoothScrollToPosition(mAdapter.getItemCount() - 1);
    }

    @Override
    public void addNewMessage(@NonNull Message message) {
        mAdapter.addMessage(message);
        mMessagesRecycler.smoothScrollToPosition(mAdapter.getItemCount() - 1);
    }

    @OnClick(R.id.sendMessageButton)
    public void onSendButtonClick() {
        mPresenter.sendMessage(mMessageEdit.getText().toString());
    }
}


