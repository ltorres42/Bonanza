package com.example.wendelltan059.bonanza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by wendell.tan059 on 10/11/17.
 */

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
    }
    public void setting (View view)
    {
        Intent intent = new Intent(Search.this, Settings.class);
        startActivity(intent);

    }
    public void account_setting (View view)
    {
        Intent intent = new Intent(Search.this, AccountSetting.class);
        startActivity(intent);

    }
}
