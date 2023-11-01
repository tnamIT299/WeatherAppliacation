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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

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

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            //Lấy dữ liệu ngày từ JsonObject dt
                            String day = jsonObject.getString("dt");
                            //Lấy tên thành phố từ JsonObject name
                            String cityName = jsonObject.getString("name");
                            txt_city.setText(cityName);

                            //Format dữ liệu ngày tháng năm
                            long l = Long.valueOf(day);
                            Date date = new Date(l*1000L);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE yyyy-MM-dd HH:mm:ss");
                            String Day = simpleDateFormat.format(date);
                            txt_Updateday.setText(Day);

                            //Đọc dữ liệu từ jsonObject Weather
                            JSONArray jsonArrayWeather = jsonObject.getJSONArray("weather");
                            JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
                            String status = jsonObjectWeather.getString("main");
                            String icon = jsonObjectWeather.getString("icon");
                            Log.d("icon", icon);


                            //Đọc file ảnh
                            Picasso.with(MainActivity.this).load("http://openweathermap.org/img/wn/"+icon+".png").into(imgView_icon);

                            //Đọc trạng thái , gán text
                            txt_status.setText(status);

                            //Đọc dữ liệu từ jsonObject main
                            JSONObject jsonObject1Main = jsonObject.getJSONObject("main");
                            String nhietdo = jsonObject1Main.getString("temp");
                            String doam = jsonObject1Main.getString("humidity");
                            //format nhietdo về int
                            Double t = Double.valueOf(nhietdo);
                            String temperature = String.valueOf(t.intValue());

                            //gán Text
                            txt_Temperature.setText(temperature+"°C");
                            txt_humidity.setText(doam +"%");

                            //Đọc dữ liệu từ jsonObject Wind
                            JSONObject jsonObject1Wind = jsonObject.getJSONObject("wind");
                            String gio = jsonObject1Wind.getString("speed");
                            txt_wind.setText(gio+"m/s");



                            //Đpc dữ liệu từ jsonObject cloud
                            JSONObject jsonObjectCloud = jsonObject.getJSONObject("clouds");
                            String may = jsonObjectCloud.getString("all");
                            txt_cloud.setText(may+"%");


                            //Đpc dữ liệu từ jsonObject sys
                            JSONObject jsonObjectSys = jsonObject.getJSONObject("sys");
                            String country = jsonObjectSys.getString("country");
                            txt_country.setText(country);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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