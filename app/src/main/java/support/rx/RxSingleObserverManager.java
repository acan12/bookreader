package support.rx;

import android.os.SystemClock;

import io.reactivex.Flowable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;

/**
 * Created by arysuryawan on 9/18/17.
 */

public class RxSingleObserverManager {

    private Flowable<Integer> observable = Flowable.just(1, 2, 3, 4);

    public RxSingleObserverManager(RxCallback callback) {
        observable.reduce(50, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer t1, Integer t2) {
                return t1 + t2;
            }
        }).subscribe(getObserver(callback));
    }

    private SingleObserver<Integer> getObserver(final RxCallback callback) {

        return new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                callback.onSubscribe(d);
            }

            @Override
            public void onSuccess(Integer integer) {
                callback.onSuccess(integer);
            }

            @Override
            public void onError(Throwable e) {
                callback.onError(e);
            }
            //            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d(TAG, " onSubscribe : " + d.isDisposed());
//            }
//
//            @Override
//            public void onSuccess(Integer value) {
//                textView.append(" onSuccess : value : " + value);
//                textView.append(AppConstant.LINE_SEPARATOR);
//                Log.d(TAG, " onSuccess : value : " + value);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                textView.append(" onError : " + e.getMessage());
//                textView.append(AppConstant.LINE_SEPARATOR);
//                Log.d(TAG, " onError : " + e.getMessage());
//            }
        };
    }

    public static class RxCallback {
        public void onSubscribe(Disposable d){

        }

        public void onSuccess(Integer integer) {

        }

        public void onError(Throwable e) {

        }
    }
}