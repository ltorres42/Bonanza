package com.example.wendelltan059.bonanza;

import java.util.ArrayList;

/**
 * Created by haleywurst on 11/21/17.
 */

public class getTime {

    public getTime()
    {

    }
    public int time(int resultIndex, ArrayList<String> timeStamps)
    {
        String timeStamp = timeStamps.get(resultIndex);
        String min;
        String sec;
        //01234
        //03:16
        min = timeStamp.substring(0,2);
        sec = timeStamp.substring(3);

        int minutes = Integer.valueOf(min);
        int seconds = Integer.valueOf(sec);

        seconds = seconds + (minutes * 60);

        return seconds;

    }
}
