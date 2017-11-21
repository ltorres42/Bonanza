package com.example.wendelltan059.bonanza;

import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;


/**
 * Created by wendell.tan059 on 10/11/17.
 */

public class Videos extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    EditText input;
    String link;
    String part2;

    public void setting (View view)
    {
        Intent intent = new Intent(Videos.this, Settings.class);
        startActivity(intent);

    }

    public void account_setting (View view)
    {
        Intent intent = new Intent(Videos.this, AccountSetting.class);
        startActivity(intent);

    }

    public Videos()
    {

    }

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videos);
    }

    public void load(View v)
    {
        this.input = (EditText) findViewById(R.id.editText8);
        this.link = input.getText().toString();
        String [] parts = link.split("=");
        part2 = parts[1];
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(Config.YOUTUBE_API_KEY, this);
        input.setText(part2);


    }

    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo(String.valueOf(part2)); // Plays https://www.youtube.com/watch?v=NYGCSSP-XSo
        }
    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), errorReason.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.YOUTUBE_API_KEY, this);
        }
    }

    protected Provider getYouTubePlayerProvider() {
        return youTubeView;
    }

    public String getID()
    {
        return this.part2;
    }


    public void searching (View view)
    {

        Intent intent = new Intent(Videos.this, Search.class);
        intent.putExtra("key1", part2);
        startActivity(intent);

    }

}
