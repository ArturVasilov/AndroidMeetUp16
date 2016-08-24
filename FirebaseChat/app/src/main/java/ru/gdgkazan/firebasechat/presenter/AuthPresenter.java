package ru.gdgkazan.firebasechat.presenter;

import android.app.LoaderManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

import ru.gdgkazan.firebasechat.R;
import ru.gdgkazan.firebasechat.rx.RxDecor;
import ru.gdgkazan.firebasechat.rx.RxTask;
import ru.gdgkazan.firebasechat.rx.rxloader.RxLoader;
import ru.gdgkazan.firebasechat.view.AuthView;
import ru.gdgkazan.firebasechat.view.LoadingView;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public class AuthPresenter {

    private final AuthView mAuthView;
    private final LoadingView mLoadingView;

    private final Context mContext;
    private final LoaderManager mLm;

    public AuthPresenter(@NonNull AuthView authView, @NonNull LoadingView loadingView,
                         @NonNull Context context, @NonNull LoaderManager lm) {
        mAuthView = authView;
        mLoadingView = loadingView;
        mContext = context;
        mLm = lm;
    }

    public void init() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            mAuthView.openMainScreen();
        }
    }

    public void signIn(@NonNull String email, @NonNull String password) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            mAuthView.showError();
        } else {
            mAuthView.hideError();
            Task<AuthResult> authTask = FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password);
            Observable<FirebaseUser> authObservable = RxTask.observeTask(authTask)
                    .flatMap(authResult -> {
                        FirebaseUser user = authResult.getUser();
                        if (user == null) {
                            return Observable.error(new IOException());
                        }
                        return Observable.just(user);
                    })
                    .compose(RxDecor.async())
                    .compose(RxDecor.loading(mLoadingView));

            RxLoader.create(mContext, mLm, R.id.auth_loader, authObservable)
                    .restart(firebaseUser -> mAuthView.openMainScreen(), throwable -> mAuthView.showError());
        }
    }
}
