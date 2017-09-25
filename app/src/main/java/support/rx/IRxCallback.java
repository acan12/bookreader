package support.rx;

/**
 * Created by arysuryawan on 9/24/17.
 */


interface IRxCallback {
    public void onNext();
    public void onError();
    public void onComplete();
}