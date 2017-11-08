package com.example.wendelltan059.bonanza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import java.util.*;
//import com.example.wendelltan059.bonanza.CCLoader;

/**
 * Created by wendell.tan059 on 10/11/17.
 */

public class Search extends AppCompatActivity {
    private String keyword;
    private ArrayList<Integer> resultArray;
    CCLoader fileLoad = new CCLoader("cc.txt");
    private ArrayList<String> textArray = fileLoad.GetLinetext();
    private ArrayList<String> timeStampsArray = fileLoad.GetTimestamps();

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
//    public static void main(String[] args) {
//
//        CCLoader myloader = new CCLoader("cc.txt");
//
//    }
    public Search()
    {

    }
    public Search(String keyword)
    {
        this.keyword = keyword;
    }
    public ArrayList<Integer> getResultArray()  {

        return resultArray;
    }
    private void searchForKeyword()
    {
        resultArray = new ArrayList<Integer>();

        if(!textArray.isEmpty())
        {
            for (int i = 0; i < textArray.size(); i ++)
            {
                if(textArray.get(i).contains(keyword))
                {
                    resultArray.add(i);
                }
            }
        }
        else
        {
            System.out.println("No input from text file.");
            System.exit(1);
        }
    }

    private void displayResultArray()
    {
        if(!resultArray.isEmpty())
        {
            for (int i = 0; i < resultArray.size(); i ++)
            {
                System.out.println(resultArray.get(i));
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("What keyword or phrase are you looking for?");
        Scanner scan = new Scanner(System.in);
        String keyword = scan.nextLine();
        Search setKeyword = new Search(keyword);
        setKeyword.searchForKeyword();
        setKeyword.displayResultArray();

    }

}
