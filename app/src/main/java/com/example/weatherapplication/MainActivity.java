package com.example.weatherapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    EditText editText_search;
    Button btn_search , btn_Nextday;
    TextView txt_city , txt_country , txt_Temperature,txt_status,txt_humidity,txt_cloud,txt_wind,txt_Updateday;
    ImageView imgView_icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = editText_search.getText().toString();
                    getCurrentData(city);
                }
        });
    }

    public void getCurrentData(String data){
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+data+"&units=metric&appid=34afcbdd01d08294961e3cb7006d269e";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Ketqua", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(stringRequest);

    }

    private void Anhxa(){
        editText_search = (EditText) findViewById(R.id.editText_search);
        btn_search = (Button) findViewById(R.id.btn_search);
        btn_Nextday=(Button) findViewById(R.id.btn_Nextday);
        txt_city = (TextView) findViewById(R.id.txt_city);
        txt_country = (TextView) findViewById(R.id.txt_country);
        txt_Temperature = (TextView) findViewById(R.id.txt_Temperature);
        txt_status = (TextView) findViewById(R.id.txt_status);
        txt_humidity = (TextView) findViewById(R.id.txt_humidity);
        txt_cloud = (TextView) findViewById(R.id.txt_cloud);
        txt_wind = (TextView) findViewById(R.id.txt_wind);
        txt_Updateday = (TextView) findViewById(R.id.txt_Updateday);
        imgView_icon=(ImageView) findViewById(R.id.imgView_icon);

    }
}