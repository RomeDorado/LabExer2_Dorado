package com.dorado.labexer2_dorado;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class MainActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnShared, btnInternal, btnNext;
    SharedPreferences preferences;
    FileOutputStream fos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnShared = (Button) findViewById(R.id.btn_shared);
        btnInternal = (Button) findViewById(R.id.btn_internal);
        btnNext = (Button) findViewById(R.id.btn_next);
        preferences = this.getSharedPreferences("preferences", MODE_PRIVATE);


    }

    public void sharedPage (View view){

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user", etUsername.getText().toString());
        editor.putString("pwd", etPassword.getText().toString());
        editor.commit();

        Toast.makeText(this, "Saved in Preferences!", Toast.LENGTH_SHORT).show();
    }

    public void internalStorage(View view){

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        String message = "Username is " + username + " and the password is " + password;
        try{

            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write(message.getBytes());


        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Toast.makeText(this, "Saved in Internal Storage!", Toast.LENGTH_SHORT).show();

    }

    public void nextPage(View view){

        Intent intent = new Intent(this, viewPage.class);
        //intent.putExtra("name", et_Name.getText().toString());
        startActivity(intent);

    }
}
