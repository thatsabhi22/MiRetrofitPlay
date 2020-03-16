package com.udacity.miretrofitplay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        getTodos();
    }

    public void getTodos(){

        Call<List<Todo>> call = apiInterface.getTodoUsingQuery(2);

        call.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                Log.e(TAG, "onResponse: " + response.body());

                List<Todo> listtodo = response.body();


                Log.e(TAG, "onResponse: " + listtodo.get(0).getTitle() );
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
