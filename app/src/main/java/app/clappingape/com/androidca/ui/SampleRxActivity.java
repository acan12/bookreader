//package app.clappingape.com.androidca.ui;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import app.clappingape.com.androidca.R;
//import app.clappingape.com.androidca.ui.base.BaseActivity;
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import io.reactivex.Observable;
//import io.reactivex.Single;
//import io.reactivex.SingleObserver;
//import io.reactivex.disposables.Disposable;
//
///**
// * Created by arysuryawan on 9/4/17.
// */
//
//public class SampleRxActivity extends BaseActivity {
//    @BindView(R.id.recycler_view)
//    RecyclerView recyclerView;
//    @BindView(R.id.sample_label)
//    TextView sampleLabel;
//    @BindView(R.id.change_label)
//    Button changeLabelButton;
//
//    private Observable<Integer> serverDownloadObservable;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sample_rx);
//        ButterKnife.bind(this);
//
//        changeLabelButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                doSomeWork();
//            }
//        });
//
//
////        Observable<String> observable = Observable.just("Hello World from RX");
//
////        serverDownloadObservable =  Observable.create((x) -> {
////            SystemClock.sleep(1000); // simulate delay
////            x.onNext(5);
////            x.onComplete();
////        } );
////
////        serverDownloadObservable.observeOn(AndroidSchedulers.mainThread())
////                .subscribeOn(Schedulers.io())
////                .subscribe(integer ->{
////                    Toast.makeText(getApplicationContext(), "Load from RX, IO Thread", Toast.LENGTH_LONG).show();
////                });
//
//    }
//
//    private void doSomeWork() {
//        Single.just("Amit").subscribe(getSingleObserver());
//    }
//
//
//    private SingleObserver<String> getSingleObserver() {
//        return new SingleObserver<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d(getDebugTag(), " onSubscribe : " + d.isDisposed());
//            }
//
//            @Override
//            public void onSuccess(String value) {
//                sampleLabel.append(" onNext : value : " + value);
//                sampleLabel.append("|");
//                Log.d(getDebugTag(), " onNext value : " + value);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                sampleLabel.append(" onError : " + e.getMessage());
//                sampleLabel.append("|");
//                Log.d(getDebugTag(), " onError : " + e.getMessage());
//            }
//        };
//    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
////        serverDownloadObservable.unsubscribeOn(Schedulers.io());
//    }
//}
