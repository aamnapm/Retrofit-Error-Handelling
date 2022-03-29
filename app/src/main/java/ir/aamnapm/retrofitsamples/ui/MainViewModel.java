package ir.aamnapm.retrofitsamples.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ir.aamnapm.retrofitsamples.CallBackApi;
import ir.aamnapm.retrofitsamples.model.GetDataResponse;
import ir.aamnapm.retrofitsamples.repository.MainRepository;

public class MainViewModel extends ViewModel {

    private MutableLiveData<GetDataResponse> apiSuccessLiveDataResponse;

    private MutableLiveData<Object> apiServerErrorLiveData;
    private MutableLiveData<Object> apiNetworkErrorLiveData;
    private MutableLiveData<String> apiNotFoundErrorLiveData;
    private MutableLiveData<String> apiForbiddenErrorLiveData;
    private MutableLiveData<Object> apiUnexpectedErrorLiveData;
    private MutableLiveData<String> apiValidationErrorLiveData;
    private MutableLiveData<String> apiBadRequestErrorLiveData;
    private MutableLiveData<String> apiAuthFailureErrorLiveData;

    {
        apiServerErrorLiveData = new MutableLiveData<>();
        apiNetworkErrorLiveData = new MutableLiveData<>();
        apiNotFoundErrorLiveData = new MutableLiveData<>();
        apiForbiddenErrorLiveData = new MutableLiveData<>();
        apiValidationErrorLiveData = new MutableLiveData<>();
        apiSuccessLiveDataResponse = new MutableLiveData<>();
        apiBadRequestErrorLiveData = new MutableLiveData<>();
        apiUnexpectedErrorLiveData = new MutableLiveData<>();
        apiAuthFailureErrorLiveData = new MutableLiveData<>();
    }

    public MainRepository getMainRepository() {
        return mainRepository;
    }

    public void setMainRepository(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public MutableLiveData<GetDataResponse> getApiSuccessLiveDataResponse() {
        return apiSuccessLiveDataResponse;
    }

    public void setApiSuccessLiveDataResponse(MutableLiveData<GetDataResponse> apiSuccessLiveDataResponse) {
        this.apiSuccessLiveDataResponse = apiSuccessLiveDataResponse;
    }

    public MutableLiveData<Object> getApiServerErrorLiveData() {
        return apiServerErrorLiveData;
    }

    public void setApiServerErrorLiveData(MutableLiveData<Object> apiServerErrorLiveData) {
        this.apiServerErrorLiveData = apiServerErrorLiveData;
    }

    public MutableLiveData<Object> getApiNetworkErrorLiveData() {
        return apiNetworkErrorLiveData;
    }

    public void setApiNetworkErrorLiveData(MutableLiveData<Object> apiNetworkErrorLiveData) {
        this.apiNetworkErrorLiveData = apiNetworkErrorLiveData;
    }

    public MutableLiveData<String> getApiNotFoundErrorLiveData() {
        return apiNotFoundErrorLiveData;
    }

    public void setApiNotFoundErrorLiveData(MutableLiveData<String> apiNotFoundErrorLiveData) {
        this.apiNotFoundErrorLiveData = apiNotFoundErrorLiveData;
    }

    public MutableLiveData<String> getApiForbiddenErrorLiveData() {
        return apiForbiddenErrorLiveData;
    }

    public void setApiForbiddenErrorLiveData(MutableLiveData<String> apiForbiddenErrorLiveData) {
        this.apiForbiddenErrorLiveData = apiForbiddenErrorLiveData;
    }

    public MutableLiveData<Object> getApiUnexpectedErrorLiveData() {
        return apiUnexpectedErrorLiveData;
    }

    public void setApiUnexpectedErrorLiveData(MutableLiveData<Object> apiUnexpectedErrorLiveData) {
        this.apiUnexpectedErrorLiveData = apiUnexpectedErrorLiveData;
    }

    public MutableLiveData<String> getApiValidationErrorLiveData() {
        return apiValidationErrorLiveData;
    }

    public void setApiValidationErrorLiveData(MutableLiveData<String> apiValidationErrorLiveData) {
        this.apiValidationErrorLiveData = apiValidationErrorLiveData;
    }

    public MutableLiveData<String> getApiBadRequestErrorLiveData() {
        return apiBadRequestErrorLiveData;
    }

    public void setApiBadRequestErrorLiveData(MutableLiveData<String> apiBadRequestErrorLiveData) {
        this.apiBadRequestErrorLiveData = apiBadRequestErrorLiveData;
    }

    public MutableLiveData<String> getApiAuthFailureErrorLiveData() {
        return apiAuthFailureErrorLiveData;
    }

    public void setApiAuthFailureErrorLiveData(MutableLiveData<String> apiAuthFailureErrorLiveData) {
        this.apiAuthFailureErrorLiveData = apiAuthFailureErrorLiveData;
    }


    private MainRepository mainRepository;

    public void callRemoteData() {
        mainRepository = new MainRepository();
        Log.e("MainViewModel", "callRemoteApi ");
        mainRepository.callRemoteApi(new CallBackApi<>() {
            @Override
            public void onSuccess(int code, GetDataResponse obj) {
                Log.e("MainViewModel", "(GetDataResponse) obj " + ((GetDataResponse) obj).getUsername());

                GetDataResponse data = (GetDataResponse) obj;
                apiSuccessLiveDataResponse.postValue(data);
            }

            @Override
            public void unAuthenticated(int code, Object obj) {
                apiAuthFailureErrorLiveData.setValue("AuthFailureError");
            }

            @Override
            public void clientError(int statusCode, Object obj) {
                switch (statusCode) {
                    case 403:
                        apiForbiddenErrorLiveData.setValue("");
                        break;
                    case 404:
                        apiNotFoundErrorLiveData.setValue("");
                        break;
                    case 400:
                        apiBadRequestErrorLiveData.setValue("");
                        break;
                }
            }

            @Override
            public void serverError(int code, Object obj) {
                apiServerErrorLiveData.setValue(obj);
            }

            @Override
            public void networkError(Object obj) {
                apiNetworkErrorLiveData.setValue(obj);
            }

            @Override
            public void unexpectedError(Object obj) {
                apiUnexpectedErrorLiveData.setValue(obj);
            }
        });
    }
}
