package com.example.wendelltan059.bonanza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by wendell.tan059 on 10/11/17.
 */

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }

    public void setting (View view)
    {
        Intent intent = new Intent(Home.this, Settings.class);
        startActivity(intent);

    }

    public void account_setting (View view)
    {
        Intent intent = new Intent(Home.this, AccountSetting.class);
        startActivity(intent);

    }
    public void catalog (View view)
    {
        Intent intent = new Intent(Home.this, Catalogs.class);
        startActivity(intent);

    }
    public void video (View view)
    {
        Intent intent = new Intent(Home.this, Videos.class);
        startActivity(intent);

    }
    public void search (View view)
    {
        Intent intent = new Intent(Home.this, Search.class);
        startActivity(intent);

    }
}
