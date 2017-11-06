package com.example.wendelltan059.bonanza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by wendell.tan059 on 10/11/17.
 */

public class Catalogs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalogs);
    }


    public void setting (View view)
    {
        Intent intent = new Intent(Catalogs.this, Settings.class);
        startActivity(intent);

    }

    public void account_setting (View view)
    {
        Intent intent = new Intent(Catalogs.this, AccountSetting.class);
        startActivity(intent);

    }


}
