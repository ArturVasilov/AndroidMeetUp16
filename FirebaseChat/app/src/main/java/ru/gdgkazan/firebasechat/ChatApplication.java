package ru.gdgkazan.firebasechat;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ru.gdgkazan.firebasechat.activity.AuthActivity;
import ru.gdgkazan.firebasechat.app.Analytics;

/**
 * @author Artur Vasilov
 */
public class ChatApplication extends Application {

    private final FirebaseAuth.AuthStateListener mAuthListener =
            firebaseAuth -> {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivity(AuthActivity.makeReAuthIntent(ChatApplication.this));
                }
            };

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
        Analytics.init(this);
    }
}


