package ru.gdgkazan.firebasechat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ru.gdgkazan.firebasechat.R;
import ru.gdgkazan.firebasechat.rx.RxTask;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        /*FirebaseAuth.getInstance().signInWithEmailAndPassword("alice@gmail.com", "123456")
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(ChatActivity.this, R.string.success_auth, Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ChatActivity.this, R.string.failed_auth, Toast.LENGTH_LONG).show();
                    }
                });*/

        /*FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword("bob@gmail.com", "1234567")
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser user = authResult.getUser();
                        Toast.makeText(ChatActivity.this, user.getEmail(), Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // show error
                    }
                });*/

        /*Task<AuthResult> authTask = FirebaseAuth.getInstance()
                .signInWithEmailAndPassword("alice@gmail.com", "123456");
        RxTask.observeTask(authTask)
                .subscribe(authResult -> {
                    FirebaseUser user = authResult.getUser();
                    Toast.makeText(ChatActivity.this, user.getEmail(), Toast.LENGTH_LONG).show();
                }, throwable -> {

                });*/
    }
}


