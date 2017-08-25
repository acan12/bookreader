package app.clappingape.com.androidca.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback;
import com.midtrans.sdk.corekit.core.MidtransSDK;
import com.midtrans.sdk.corekit.core.TransactionRequest;
import com.midtrans.sdk.corekit.models.BillInfoModel;
import com.midtrans.sdk.corekit.models.CustomerDetails;
import com.midtrans.sdk.corekit.models.ItemDetails;
import com.midtrans.sdk.corekit.models.snap.CreditCard;
import com.midtrans.sdk.corekit.models.snap.TransactionResult;

import java.util.ArrayList;

import app.clappingape.com.androidca.IConfig;
import app.clappingape.com.androidca.R;
import app.clappingape.com.androidca.model.dao.BaseDao;
import app.clappingape.com.androidca.model.dao.SourceDao;
import app.clappingape.com.androidca.ui.base.BaseActivity;
import butterknife.ButterKnife;

/**
 * Created by arysuryawan on 8/25/17.
 */

public class PaymentActivity extends BaseActivity implements TransactionFinishedCallback {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);

        showProgressDialogOnDAOCalled(new SourceDao(this) {
            @Override
            public void call() {
                this.getSourcesDAO(PaymentActivity.this, BaseDao.getInstance(PaymentActivity.this, IConfig.KEY_CALLER_API_SOURCE).callback);
            }
        });

//        String client_key = "VT-client-U-3OJXNU7Gtt2_85";
//        String base_url = "https://sample-merhcant-url.com";
//        SdkUIFlowBuilder.init(this, client_key, base_url, this)
//                .enableLog(true)
//                .setColorTheme(new CustomColorTheme("#FFE51255", "#B61548", "#FFE51255"))
//                .useBuiltInTokenStorage(false)
//                .buildSDK();
//
//        doTransaction(this);



    }

    private void doTransaction(PaymentActivity paymentActivity) {
        // Create new Transaction Request
        TransactionRequest transactionRequestNew = new
                TransactionRequest(System.currentTimeMillis() + "", 6000);

        //define customer detail (mandatory for coreflow)
        CustomerDetails mCustomerDetails = new CustomerDetails();
        mCustomerDetails.setPhone("624234234234");

        mCustomerDetails.setFirstName("sample full name");

        mCustomerDetails.setEmail("mail@mail.com");
        transactionRequestNew.setCustomerDetails(mCustomerDetails);


        // Define item details
        ItemDetails itemDetails = new ItemDetails("1", 1000, 1, "Trekking Shoes");
        ItemDetails itemDetails1 = new ItemDetails("2", 1000, 2, "Casual Shoes");
        ItemDetails itemDetails2 = new ItemDetails("3", 1000, 3, "Formal Shoes");

        // Add item details into item detail list.
        ArrayList<ItemDetails> itemDetailsArrayList = new ArrayList<>();
        itemDetailsArrayList.add(itemDetails);
        itemDetailsArrayList.add(itemDetails1);
        itemDetailsArrayList.add(itemDetails2);
        transactionRequestNew.setItemDetails(itemDetailsArrayList);
        // Set Bill info
        BillInfoModel billInfoModel = new BillInfoModel("demo_label", "demo_value");
        transactionRequestNew.setBillInfoModel(billInfoModel);

        // Create creditcard options for payment
        // noted : channel migs is needed if bank type is BCA, BRI or MyBank
        CreditCard creditCard = new CreditCard();

        String cardClickType = getString(R.string.card_click_type_two_click);
        creditCard.setSaveCard(true);
        creditCard.setSecure(false);
        transactionRequestNew.setCreditCard(creditCard);


        transactionRequestNew.setCardPaymentInfo(cardClickType, true);

        MidtransSDK.getInstance().setTransactionRequest(transactionRequestNew);
        MidtransSDK.getInstance().startPaymentUiFlow(this);
    }

    @Override
    public void onTransactionFinished(TransactionResult transactionResult) {

    }
}
