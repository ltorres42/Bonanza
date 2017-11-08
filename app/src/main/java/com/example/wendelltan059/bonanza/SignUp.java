package com.example.wendelltan059.bonanza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by wendell.tan059 on 10/11/17.
 */

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }

    public void signup_continue (View view)
    {
        Intent intent = new Intent(SignUp.this, Home.class);
        startActivity(intent);

    }
}
