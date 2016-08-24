package ru.gdgkazan.firebasechat.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.gdgkazan.firebasechat.R;
import ru.gdgkazan.firebasechat.dialog.LoadingDialog;
import ru.gdgkazan.firebasechat.presenter.AuthPresenter;
import ru.gdgkazan.firebasechat.view.AuthView;

/**
 * @author Artur Vasilov
 */
public class AuthActivity extends AppCompatActivity implements AuthView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.emailInputLayout)
    TextInputLayout mInputLayout;

    @BindView(R.id.emailEdit)
    EditText mEmailEdit;

    @BindView(R.id.passwordEdit)
    EditText mPasswordEdit;

    private AuthPresenter mPresenter;

    @NonNull
    public static Intent makeReAuthIntent(@NonNull Context context) {
        return new Intent(context, AuthActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mPresenter = new AuthPresenter(this, LoadingDialog.view(getSupportFragmentManager()), this, getLoaderManager());
        mPresenter.init();
    }

    @OnClick(R.id.signInButton)
    public void onSignInClick() {
        String email = mEmailEdit.getText().toString();
        String password = mPasswordEdit.getText().toString();
        mPresenter.signIn(email, password);
    }

    @Override
    public void openMainScreen() {
        startActivity(new Intent(this, ChatActivity.class));
        finish();
    }

    @Override
    public void showError() {
        mInputLayout.setError(getString(R.string.auth_error));
        mInputLayout.setErrorEnabled(true);
    }

    @Override
    public void hideError() {
        mInputLayout.setError("");
        mInputLayout.setErrorEnabled(false);
    }
}
