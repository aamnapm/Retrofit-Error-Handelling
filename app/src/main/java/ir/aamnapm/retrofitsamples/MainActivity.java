package ir.aamnapm.retrofitsamples;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import ir.aamnapm.retrofitsamples.api.Api;
import ir.aamnapm.retrofitsamples.model.GetDataResponse;
import ir.aamnapm.retrofitsamples.rerofitUtils.ErrorHandlingCallAdapterFactory;
import ir.aamnapm.retrofitsamples.rerofitUtils.MyCall;
import ir.aamnapm.retrofitsamples.rerofitUtils.MyCallback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ResponseCallBack {

    private String result;
    private Button btnStart;
    private TextView txtResult;
    private ResponseCallBack responseCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseCallBack = this;

        btnStart = findViewById(R.id.btnStart);
        txtResult = findViewById(R.id.txtResult);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });

    }

    private void start() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addCallAdapterFactory(new ErrorHandlingCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api service = retrofit.create(Api.class);

        final MyCall<GetDataResponse> ip = service.getIp(5);
        ip.enqueue(new MyCallback<GetDataResponse>() {
            @Override
            public void success(Response<GetDataResponse> response) {
                responseCallBack.success(response.body());
                MainActivity.this.txtResult.setText("Success " + response.body().getUsername());
//                result = response.body().getEmail();
                Log.e("MainActivity", "SUCCESS! " + response.body().getEmail());
//                Log.e("MainActivity", "SUCCESS! " + result);
//                Toast.makeText(MainActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void unauthenticated(Response<?> response) {
                Log.e("Main", "UNAUTHENTICATED");
                txtResult.setText("UNAUTHENTICATED " + response.errorBody());
                Toast.makeText(MainActivity.this, "UNAUTHENTICATED", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void clientError(Response<?> response) {
                txtResult.setText("CLIENT ERROR  " + response.errorBody());
                Log.e("MainActivity", "CLIENT ERROR " + response.code() + " " + response.message());
                Toast.makeText(MainActivity.this, "CLIENT ERROR ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void serverError(Response<?> response) {
                txtResult.setText("SERVER ERROR " + response.errorBody());
                Log.e("MainActivity", "SERVER ERROR " + response.code() + " " + response.message());
                Toast.makeText(MainActivity.this, "SERVER ERROR ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void networkError(IOException e) {
                txtResult.setText("NETWORK ERROR  " + e.getMessage());
                Log.e("MainActivity", "NETWORK ERROR " + e.getMessage());
                Toast.makeText(MainActivity.this, "NETWORK ERROR ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void unexpectedError(Throwable t) {
                Log.e("MainActivity", "FATAL ERROR " + t.getMessage());
                Toast.makeText(MainActivity.this, "FATAL ERROR ", Toast.LENGTH_SHORT).show();
            }
        });
//        ip.cancel();
    }

    @Override
    public void success(GetDataResponse result) {
        Log.e("MainActivity", "SUCCESS! " + result.getUsername());
       txtResult.setText("Success " + result.getUsername() + " " + result.getEmail());
    }
}
