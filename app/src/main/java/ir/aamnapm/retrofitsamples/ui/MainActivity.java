package ir.aamnapm.retrofitsamples.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;
import ir.aamnapm.retrofitsamples.R;
import ir.aamnapm.retrofitsamples.di.config.ViewModelProviderFactory;
import ir.aamnapm.retrofitsamples.model.GetDataResponse;

public class MainActivity extends DaggerAppCompatActivity {

    private MainViewModel mainViewModel;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;


    @BindView(R.id.btnStart)
    Button btnStart;
    @BindView(R.id.txtResult)
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(MainViewModel.class);

        attachObserverLogin();
    }

    @OnClick(R.id.btnStart)
    public void btnCallApi() {
        Log.e("MainActivity", "btnStart ");
        mainViewModel.callRemoteData();

        mainViewModel.getLiveDataRemote().observe(this,
                new Observer<GetDataResponse>() {
                    @Override
                    public void onChanged(GetDataResponse getDataResponse) {
                        Log.e("Activity", "" + getDataResponse.getUsername());
                    }
                });
    }


    private void attachObserverLogin() {
//        mainViewModel.getApiNetworkErrorLiveData().observe(this, this::network);
//        mainViewModel.getApiServerErrorLiveData().observe(this, this::serverError);
//        mainViewModel.getApiNotFoundErrorLiveData().observe(this, this::notFound);
//        mainViewModel.getApiForbiddenErrorLiveData().observe(this, this::forbidden);
//        mainViewModel.getApiValidationErrorLiveData().observe(this, this::validation);
//        mainViewModel.getApiBadRequestErrorLiveData().observe(this, this::badRequest);
//        mainViewModel.getApiAuthFailureErrorLiveData().observe(this, this::authFailure);
//        mainViewModel.getApiSuccessLiveDataResponse().observe(this, this::responseLoginApi);
    }

    private void authFailure(String s) {
        txtResult.setText("authFailure");
    }

    private void network(Object o) {
        txtResult.setText("networkError");
    }

    private void badRequest(String s) {
        txtResult.setText("badRequest (400)");
    }

    private void validation(String s) {
        txtResult.setText("validation (422)");
    }

    private void forbidden(String s) {
        txtResult.setText("Forbiden (403)");
    }

    private void notFound(String s) {
        txtResult.setText("NotFound (404)");
    }

    private void serverError(Object o) {
        txtResult.setText("serverError");
    }

    private void responseLoginApi(GetDataResponse getDataResponse) {
        Log.e("Activity", "getDataResponse " + getDataResponse.getUsername());
        txtResult.setText("username : " + getDataResponse.getUsername()
                + " email : " + getDataResponse.getEmail()
                + " webSite : " + getDataResponse.getWebsite());
    }
}
