package com.dorado.labexer2_dorado;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class viewPage extends AppCompatActivity {

    Button btnLoadShared, btnLoadInternal, btnClear, btnBack;
    TextView tvCredentials;
    SharedPreferences preferences;
    FileInputStream fis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);

        btnLoadShared = (Button) findViewById(R.id.btn_loadShared);
        btnLoadInternal = (Button) findViewById(R.id.btn_loadInternal);
        btnClear = (Button) findViewById(R.id.btn_clear);
        btnBack = (Button) findViewById(R.id.btn_back);
        tvCredentials = (TextView) findViewById(R.id.tv_credentials);
        preferences = this.getSharedPreferences("preferences", MODE_PRIVATE);



    }

    public void loadPref (View view){

        String user = preferences.getString("user", "");
        String pwd = preferences.getString("pwd", "");
        tvCredentials.setText("Username is " + user + " and the password is " + pwd);
    }

    public void loadInternal(View view){


        StringBuffer buffer = new StringBuffer();
        int read = 0;

        try{
            fis = openFileInput("output.txt");
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvCredentials.setText(buffer.toString());


    }

    public void backPage(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void clear(View view){
        tvCredentials.setText(null);
    }
}
