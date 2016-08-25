package ru.gdgkazan.firebasechat;

import android.app.Application;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import ru.gdgkazan.firebasechat.activity.AuthActivity;
import ru.gdgkazan.firebasechat.app.Analytics;

/**
 * @author Artur Vasilov
 */
public class ChatApplication extends Application {

    private static ChatApplication sInstance;

    private final FirebaseAuth.AuthStateListener mAuthListener =
            firebaseAuth -> {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    restartApp();
                }
            };

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
        Analytics.init(this);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    @NonNull
    public static ChatApplication getInstance() {
        return sInstance;
    }

    public void restartApp() {
        startActivity(AuthActivity.makeReAuthIntent(this));
    }
}


