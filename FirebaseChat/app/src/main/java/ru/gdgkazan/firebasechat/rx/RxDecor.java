package ru.gdgkazan.firebasechat.rx;

import android.support.annotation.NonNull;

import ru.gdgkazan.firebasechat.view.LoadingView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Artur Vasilov
 */
public final class RxDecor {

    private RxDecor() {
    }

    @NonNull
    public static <T> Observable.Transformer<T, T> loading(@NonNull LoadingView view) {
        return observable -> observable
                .doOnSubscribe(view::showLoadingIndicator)
                .doOnTerminate(view::hideLoadingIndicator);
    }

    @NonNull
    public static <T> Observable.Transformer<T, T> async() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
