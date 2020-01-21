package com.example.apiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText editTextSearch;
    private ImageView imageViewWeeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WeebService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeebService weebService = retrofit.create(WeebService.class);

        Call<Weeb> weebCall = weebService.getListByInput("haikyu");
        weebCall.enqueue(new Callback<Weeb>() {
            @Override
            public void onResponse(Call<Weeb> call, Response<Weeb> response) {
                // ANY CODE THAT DEPENDS ON THE RESULT OF THE SEARCH HAS TO GO HERE
                Weeb foundWeeb = response.body();
                // check if the body isn't null
                if (foundWeeb != null) {
                    Toast.makeText(MainActivity.this, foundWeeb.getMessage(), Toast.LENGTH_SHORT).show();
                    Picasso.get().load(foundWeeb.getMessage()).into(imageViewWeeb);
                }
            }

            @Override
            public void onFailure(Call<Weeb> call, Throwable t) {
                // TOAST THE FAILURE
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        editTextSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomWeeb(weebService);
            }
        });
    }

    private void randomWeeb(WeebService weebService) {
        Call<Weeb> dogCall = weebService.getListByInput("hound");
        dogCall.enqueue(new Callback<Weeb>() {
            @Override
            public void onResponse(Call<Weeb> call, Response<Weeb> response) {
                // ANY CODE THAT DEPENDS ON THE RESULT OF THE SEARCH HAS TO GO HERE
                Weeb foundWeeb = response.body();
                // check if the body isn't null
                if (foundWeeb != null) {
                    Toast.makeText(MainActivity.this, foundWeeb.getMessage(), Toast.LENGTH_SHORT).show();
                    Picasso.get().load(foundWeeb.getMessage()).into(imageViewWeeb);
                }
            }

            @Override
            public void onFailure(Call<Weeb> call, Throwable t) {
                // TOAST THE FAILURE
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void wireWidgets() {
        editTextSearch = findViewById(R.id.editText_main_search);
        imageViewWeeb = findViewById(R.id.imageView_main_weebImage);
    }
}
