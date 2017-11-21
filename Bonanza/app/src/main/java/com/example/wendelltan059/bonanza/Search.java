package com.example.wendelltan059.bonanza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;
import android.util.Log;

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

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    String video_ID;

    private YouTubePlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
//
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(Config.YOUTUBE_API_KEY, this);

        Intent intent = getIntent();
        video_ID = intent.getStringExtra("key1");


        // CODE TO SKIP THE PART OF THE VIDEO BASED ON THE GET-TIME RESULT;

        // Button buttonToSeek = (Button) findViewById(R.id.button_to_seek); // basically a NEXT Button
        // buttonToSeek.setOnClickListener(new View.OnClickListener() {
        // @Override
        // public void onClick(View view) {
        // int skipToSeconds = (the gettime result);
        // player.seekToMillis(skipToSeconds * 1000);
        // }
        // });

        fileLoad = new CCLoader("cc.txt", this);

        textArray = fileLoad.GetLinetext();
        timeStampsArray = fileLoad.GetTimestamps();

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
                        TextView tView = (TextView) findViewById(R.id.textView17);

                        if(resultArray.isEmpty()){
                            tView.setText("Keyword not found...");
                            Button downButton = (Button) findViewById(R.id.downbutton);
                            downButton.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View view) {
                                }
                            });

                            //skip back up through keyword occurances
                            Button upButton = (Button) findViewById(R.id.upbutton);
                            upButton.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                }
                            });
                        }
                        else {
                            //tView.setText(keyword);
                            resultIndex = 0;
                            tView.setText(textArray.get(resultArray.get(resultIndex)));
                            //skip down through keyword occurances

                            Button downButton = (Button) findViewById(R.id.downbutton);
                            downButton.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View view) {
                                    TextView tView = (TextView) findViewById(R.id.textView17);
                                    //tView.setText("down button pressed");
                                    resultIndex = (resultIndex + 1) % resultArray.size();
                                    tView.setText(textArray.get(resultArray.get(resultIndex)));
                                }

                            });

                            //skip back up through keyword occurances
                            Button upButton = (Button) findViewById(R.id.upbutton);
                            upButton.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    TextView tView = (TextView) findViewById(R.id.textView17);
                                    resultIndex = Math.abs(resultIndex - 1) % resultArray.size();
                                    tView.setText(textArray.get(resultArray.get(resultIndex)));
                                    //tView.setText("up button pressed");
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

//    public Search()
//    {
//
//    }
//    public Search(String keyword)
//    {
//        this.keyword = keyword;
//    }
//    public ArrayList<Integer> getResultArray()  {
//
//        return resultArray;
//    }
//    private void searchForKeyword()
//    {
//        resultArray = new ArrayList<Integer>();
//
//        if(!textArray.isEmpty())
//        {
//            for (int i = 0; i < textArray.size(); i ++)
//            {
//                if(textArray.get(i).contains(keyword))
//                {
//                    resultArray.add(i);
//                }
//            }
//        }
//        else
//        {
//            System.out.println("No input from text file.");
//            System.exit(1);
//        }
//    }
//
//    private void displayResultArray()
//    {
//        if(!resultArray.isEmpty())
//        {
//            for (int i = 0; i < resultArray.size(); i ++)
//            {
//                System.out.println(resultArray.get(i));
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//
//        System.out.println("What keyword or phrase are you looking for?");
//        Scanner scan = new Scanner(System.in);
//        String keyword = scan.nextLine();
//        Search setKeyword = new Search(keyword);
//        setKeyword.searchForKeyword();
//        setKeyword.displayResultArray();
//
//    }
}
