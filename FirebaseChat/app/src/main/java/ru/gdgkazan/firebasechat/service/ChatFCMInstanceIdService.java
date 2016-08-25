package ru.gdgkazan.firebasechat.service;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * @author Artur Vasilov
 */
public class ChatFCMInstanceIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        // TODO : send token to your backend
    }

}


