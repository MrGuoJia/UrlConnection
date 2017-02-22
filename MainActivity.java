package com.example.jia.httpurlconnection_kuaidi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Button btn_check;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        btn_check= (Button) findViewById(R.id.btn);
        textView= (TextView) findViewById(R.id.tv);

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url=new URL("http://v.juhe.cn/exp/index?com=tt&no=667300323472&dtype=&key=b2725a94f8d23467e1b8804be164b719");
                            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                            InputStream is=conn.getInputStream();
                            BufferedReader br=new BufferedReader(new InputStreamReader(is,"utf-8"));
                            StringBuffer buffer=new StringBuffer();
                            String line=br.readLine();
                            while(line!=null){
                                buffer.append(line);
                                line=br.readLine();
                            }
                            final String webContent=buffer.toString();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(webContent);
                                }
                            });

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();


            }
        });
    }
}
