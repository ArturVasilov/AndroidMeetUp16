package ru.gdgkazan.firebasechat.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseUser;

import ru.gdgkazan.firebasechat.BuildConfig;

/**
 * @author Artur Vasilov
 */
public final class Analytics {

    public static final String SIGN_IN_TYPE = "sign_in_type";
    public static final String EMAIL_PASSWORD = "email_password";
    public static final String SIGN_IN = "sign_in";

    private static final String APP_VERSION_KEY = "app_version";
    private static final String USER_ID_KEY = "user_id";

    private static FirebaseAnalytics sAnalytics;

    private Analytics() {
    }

    public static void init(@NonNull Context context) {
        sAnalytics = FirebaseAnalytics.getInstance(context);
        sAnalytics.setUserProperty(APP_VERSION_KEY, BuildConfig.VERSION_NAME);
    }

    public static void setCurrentUser(@NonNull FirebaseUser currentUser) {
        sAnalytics.setUserProperty(USER_ID_KEY, currentUser.getUid());
    }

    @NonNull
    public static EventBuilder buildEvent() {
        return new EventBuilder();
    }

    public static class EventBuilder {

        private final Bundle mBundle;

        private EventBuilder() {
            mBundle = new Bundle();
        }

        @NonNull
        public EventBuilder putString(@NonNull String key, @NonNull String value) {
            mBundle.putString(key, value);
            return this;
        }

        public void log(@NonNull String eventTag) {
            sAnalytics.logEvent(eventTag, mBundle);
        }
    }

}


