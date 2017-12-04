package com.example.wendelltan059.bonanza;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import android.util.Log;
import java.util.Arrays;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by wendell.tan059 on 10/11/17.
 */

public class Search extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener  {

    private String keyword;
    private int numberResults = 0;
    private int resultIndex = 0;
    private ArrayList<Integer> resultArray;
    CCLoader fileLoad;
    private ArrayList<String> textArray;
    private ArrayList<String> timeStampsArray;
    String video;
    private searchFunctions setKeyword;
    private getTime newTimeObject;

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    String video_ID;

    private YouTubePlayer player;

    private int results_highlighted;
    private int highlight_starts[];
    private String textString;


//    @SuppressWarnings("deprecation")
//    public static Spanned fromHtml(String html){
//        Spanned result;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//            result = Html.fromHtml(html,Html.FROM_HTML_MODE_LEGACY);
//        } else {
//            result = Html.fromHtml(html);
//        }
//        return result;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(Config.YOUTUBE_API_KEY, this);

        Intent intent = getIntent();
        video_ID = intent.getStringExtra("key1");

        final ScrollView s = (ScrollView) findViewById(R.id.scrollView2);

        final TextView tView = (TextView) findViewById(R.id.mytextview);

//        InputStream inputStream = getResources().openRawResource(R.raw.cc);
//        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//
//        final String myText = "";
//        int in;
//        try {
//            in = inputStream.read();
//            while (in != -1)
//            {
//                byteArrayOutputStream.write(in);
//                in = inputStream.read();
//            }
//            inputStream.close();
//
//            byteArrayOutputStream.toString();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        tView.setText(byteArrayOutputStream.toString(););



        fileLoad = new CCLoader("cc.txt", this);

        textArray = fileLoad.GetLinetext();
        timeStampsArray = fileLoad.GetTimestamps();

        String textString2 = new String();

        for (int i = 0; i<textArray.size(); i++)
        {
            textString2 = textString2 + textArray.get(i);
            textString2 = textString2 + "\n\n";
        }

        tView.setText(textString2);

        //EditText textbox = (EditText) findViewById(R.id.editText9);
        //textbox.setText(textArray.get(0));

        Button enter = (Button) findViewById(R.id.enterbutton);
        enter.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        EditText edit_text = (EditText) findViewById(R.id.editText9);
                        keyword = edit_text.getText().toString();
                        setKeyword = new searchFunctions(keyword);
                        resultArray = setKeyword.searchForKeyword(textArray);
                        final TextView searchView = (TextView) findViewById(R.id.mytextview);

                        textString = new String();
                        results_highlighted = 0;
                        highlight_starts = new int[resultArray.size()];
                        int char_count = 0;

                        for (int i = 0; i<resultArray.size(); i++)
                        {
                            String thisLine = new String();
                            thisLine = textArray.get(resultArray.get(i));
                            if (thisLine.contains(keyword))
                            {
                                highlight_starts[results_highlighted] = char_count + thisLine.indexOf(keyword);
                                results_highlighted++;
                            }

                            textString = textString + textArray.get(resultArray.get(i));
                            textString = textString+ "\n\n";

                            char_count += thisLine.length() + 2;
                        }

                        Spannable wordToSpan = new SpannableString(textString);

                        for (int i =0; i<results_highlighted; i++)
                        {
                            wordToSpan.setSpan(new BackgroundColorSpan(Color.YELLOW), highlight_starts[i], (highlight_starts[i] + keyword.length()), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }

//                        String textString = new String();
//
//                        for (int i = 0; i<textArray.size(); i++)
//                        {
//                            textString = textString + textArray.get(i);
//                            textString = textString + "\n";
//                        }
//
//                        searchView.setText(textString);

                        if(resultArray.isEmpty()){
                            searchView.setText("Keyword not found...");
                            Button downButton = (Button) findViewById(R.id.downbutton);
                            downButton.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View view) {
                                }
                            });

                            Button upButton = (Button) findViewById(R.id.upbutton);
                            upButton.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                }
                            });
                        }
                        else {
                            //searchView.setText(keyword);
                            //tView.setText(byteArrayOutputStream.toString());
                            resultIndex = 0;
                            wordToSpan.setSpan(new BackgroundColorSpan(Color.GREEN), highlight_starts[0], (highlight_starts[0] + keyword.length()), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                            searchView.setText(wordToSpan);
//                            for (int i=0; i<resultArray.size(); i++)
//                            {
//                                tView.setText(textArray.get(resultArray.get(resultIndex)));
//                            }
//                            String newText = myText.replaceAll(textArray.get(resultArray.get(resultIndex)),"<font color='red'>"+textArray.get(resultArray.get(resultIndex))+"</font>");
//
//                            searchView.setText(Html.fromHtml(newText));

//                            final ScrollView s = (ScrollView) findViewById(R.id.scrollView2);
//
//                            s.post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    int y = searchView.getLayout().getLineTop(resultArray.get(resultIndex));
//                                    s.scrollTo(0, y);
//                                }
//                            });
                            //searchView.setText(textArray.get(resultArray.get(resultIndex)));

                            Button downButton = (Button) findViewById(R.id.downbutton);
                            downButton.setOnClickListener(new View.OnClickListener(){

                            @Override
                            public void onClick(View view) {


                                if (resultIndex < resultArray.size() - 1)
                                {
                                    resultIndex ++;
                                }

                                final ScrollView s = (ScrollView) findViewById(R.id.scrollView2);

                                s.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        int y = tView.getLayout().getLineTop(resultIndex * 2); // e.g. I want to scroll to line 40
                                        s.scrollTo(0, y);


                                        Spannable WordtoSpan = new SpannableString(textString);


                                        for (int i = 0; i < results_highlighted; i ++)
                                        {
                                            if (i != resultIndex)
                                            {
                                                WordtoSpan.setSpan(new BackgroundColorSpan(Color.YELLOW), highlight_starts[i], (highlight_starts[i] + keyword.length()), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                            }
                                            else
                                            {
                                                WordtoSpan.setSpan(new BackgroundColorSpan(Color.GREEN), highlight_starts[i], (highlight_starts[i] + keyword.length()), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                            }
                                        }
                                        tView.setText(WordtoSpan);



                                    }
                                });

                            }

                            });

                            Button upButton = (Button) findViewById(R.id.upbutton);
                            upButton.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    if(resultIndex == 0)
                                    {
                                        resultIndex = resultArray.size()-1;
                                    }
                                    else
                                    {
                                        resultIndex = (resultIndex - 1);

                                        final ScrollView s = (ScrollView) findViewById(R.id.scrollView2);

                                        s.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                int y = tView.getLayout().getLineTop(resultIndex * 2); // e.g. I want to scroll to line 40
                                                s.scrollTo(0, y);


                                                Spannable WordtoSpan = new SpannableString(textString);


                                                for (int i = 0; i < results_highlighted; i ++)
                                                {
                                                    if (i != resultIndex)
                                                    {
                                                        WordtoSpan.setSpan(new BackgroundColorSpan(Color.YELLOW), highlight_starts[i], (highlight_starts[i] + keyword.length()), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                                    }
                                                    else
                                                    {
                                                        WordtoSpan.setSpan(new BackgroundColorSpan(Color.GREEN), highlight_starts[i], (highlight_starts[i] + keyword.length()), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                                                    }
                                                }
                                                tView.setText(WordtoSpan);


                                            }
                                        });

                                    }
                                }
                            });
                            Button jumpTo = (Button) findViewById(R.id.jump);
                            jumpTo.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    newTimeObject = new getTime();
                                    int time = newTimeObject.time(resultArray.get(resultIndex), timeStampsArray);
                                    player.seekToMillis(time * 1000);
                                }
                            });
                        }

                    }
                });


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

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            this.player = player;
            player.cueVideo(String.valueOf(video_ID)); // Plays https://www.youtube.com/watch?v=NYGCSSP-XSo
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
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

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return youTubeView;
    }

}
