package com.zhangpeng.administrator.gson;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String mes ;
    Result result ;
    String rs;
    private Handler handler2 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mes = msg.obj.toString();
            Gson gson = new Gson();
            result = gson.fromJson(mes, Result.class);
            rs = result.getTv().createTime+result.getTv().firstCategory+result.getList();
            Log.d("TAG",rs);
            TextView tv = (TextView) findViewById(R.id.tv);
            tv.setText(rs);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn =(Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestStockJson();
                //dealData();
                //Log.d("TAG","2016-01-28"+"1"+"4");
            }
        });

    }

    public void requestStockJson() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    StringBuilder content;
                    URL url = new URL("http://123.56.189.59/englishStudy/queryMoviesbyName?info={\"movieName\":\"的\"}");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    if (conn.getResponseCode() == 200) {
                        InputStream in = conn.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        String line = "";
                        content = new StringBuilder();
                        while ((line = reader.readLine()) != null) {
                            content = content.append(line);
                        }
                        Message mes = new Message();
                        mes.obj = content;
                        handler2.sendMessage(mes);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();

    }
    public  void dealData(){
        Gson gson = new Gson();
        Result result = gson.fromJson(mes, Result.class);
        Log.d("TAG",result.getTv().createTime+result.getTv().firstCategory+result.getList());
    }
}
