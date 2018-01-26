package xui;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by weiss on 17/1/16.
 */
public class RxSchedulers {
    public static    <T> ObservableTransformer<T, T> io_main() {

        ObservableTransformer transformer=   new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
               return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());

            }


        };
        return transformer;
     }
}

