package ru.gdgkazan.firebasechat.rx;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.concurrent.Callable;

import rx.AsyncEmitter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author Artur Vasilov
 */
public final class RxTask {

    private RxTask() {
    }

    @NonNull
    public static <T> Observable<T> observeTask(@NonNull final Task<T> task) {
        return Observable.fromCallable(task::getResult)
                .compose(RxDecor.async());
    }

}


