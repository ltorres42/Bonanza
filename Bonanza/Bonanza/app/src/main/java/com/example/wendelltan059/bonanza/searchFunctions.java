package com.example.wendelltan059.bonanza;

import android.content.Intent;
import android.view.View;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by haley.wurst523 on 11/20/17.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import java.util.*;
import android.util.Log;
import android.widget.Toast;

public class searchFunctions {
    private String keyword;
    private int numberResults = 0;
    private int resultIndex = 0;
    private ArrayList<Integer> resultArray;
    CCLoader fileLoad;
    private ArrayList<String> textArr;
    private ArrayList<String> timeStampsArray;

    public void NewSearch()
    {

    }

    public searchFunctions()
    {

    }
    public searchFunctions(String newSearchKeyword)
    {
        keyword = newSearchKeyword;
    }
    //public ArrayList<Integer> getResultArray()  {

    //    return resultArray;
    //}
    public ArrayList<Integer> searchForKeyword(ArrayList<String> arr)
    {
        //textArr = arr;
        resultArray = new ArrayList<Integer>();

        if(!arr.isEmpty())
        {
            for (int i = 0; i < arr.size(); i ++)
            {
                if(arr.get(i).contains(keyword))
                {
                    resultArray.add(i);
                    numberResults ++;
                }
            }
        }
        else {
            System.out.println("No input from text file.");
            System.exit(1);
        }
        //resultArray.add(0);
        //resultArray.add(1);
        return resultArray;
    }


}


