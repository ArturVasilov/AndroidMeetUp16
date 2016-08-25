package ru.gdgkazan.firebasechat.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.gdgkazan.firebasechat.R;
import ru.gdgkazan.firebasechat.adapter.ChatAdapter;
import ru.gdgkazan.firebasechat.dialog.LoadingDialog;
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
        mPresenter = new ChatPresenter(this, LoadingDialog.view(getSupportFragmentManager()));
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_exit) {
            mPresenter.exit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addNewMessage(@NonNull Message message) {
        mAdapter.addMessage(message);
        mMessagesRecycler.smoothScrollToPosition(mAdapter.getItemCount() - 1);
    }

    @Override
    public void clearMessageText() {
        mMessageEdit.setText("");
    }

    @OnClick(R.id.sendMessageButton)
    public void onSendButtonClick() {
        mPresenter.sendMessage(mMessageEdit.getText().toString());
    }
}


