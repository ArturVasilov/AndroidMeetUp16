package ru.gdgkazan.firebasechat.rx;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

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
        return Observable.fromAsync(new Action1<AsyncEmitter<T>>() {
            @Override
            public void call(final AsyncEmitter<T> asyncEmitter) {
                wrapTask(task, asyncEmitter);
            }
        }, AsyncEmitter.BackpressureMode.LATEST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private static <T> void wrapTask(@NonNull Task<T> task, @NonNull final AsyncEmitter<T> emitter) {
        task.addOnSuccessListener(emitter::onNext)
                .addOnFailureListener(emitter::onError)
                .addOnCompleteListener(task1 -> emitter.onCompleted());
    }

}


