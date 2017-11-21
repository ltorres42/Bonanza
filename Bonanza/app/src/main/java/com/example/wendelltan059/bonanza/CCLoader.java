package com.example.wendelltan059.bonanza;

/**
 * Created by Luis on 11/6/17.
 */
import java.util.*;
import java.io.*;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;


public class CCLoader {
    private ArrayList<String> timestamps;
    private ArrayList<String> linetext;

    private String sourcefile;

    private Context mContext;


    public ArrayList<String> GetTimestamps()  {

        return timestamps;
    }


    public ArrayList<String> GetLinetext()  {

        return linetext;
    }


    public void TestPrintTimestamps()  {

        if (timestamps.size() > 0)  {

            for (int i = 0; i < timestamps.size(); i ++)
            {
                System.out.println("timestamp:\t" + timestamps.get(i));
            }
        }

    }


    public void TestPrintLinetext()  {

        if (linetext.size() > 0)  {

            for (int i = 0; i < linetext.size(); i ++)
            {
                System.out.println("linetext:\t" + linetext.get(i));
            }
        }
    }



    private void LoadCC()  {

        String line = null;

        timestamps = new ArrayList<String>();
        linetext = new ArrayList<String>();

        Scanner s = new Scanner(mContext.getResources().openRawResource(R.raw.cc));

        try {

            while(s.hasNext())  {

                line = s.nextLine();
                timestamps.add(line);

                line = s.nextLine();

                linetext.add(line);


            }


        } finally{
            s.close();
        }



    }






    private void LoadCCold()  {
        String line = null;

        timestamps = new ArrayList<String>();
        linetext = new ArrayList<String>();

        try {

            FileReader fileReader = new FileReader(sourcefile);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null)
            {

                timestamps.add(line.trim());
                line = bufferedReader.readLine();
                if (line != null)
                {
                    linetext.add(line.trim());
                }
            }
        }

        catch(FileNotFoundException ex) {
            System.out.println("CC file not found");
        }

        catch(IOException ex)   {
            System.out.println("Error reading file");
        }

        TestPrintTimestamps();
        TestPrintLinetext();

    }


    public CCLoader(String filename, Context context)  {
        sourcefile = filename;
        mContext = context;
        LoadCC();

    }

//    public static void main(String[] args) {
//
//        CCLoader myloader = new CCLoader("cc.txt");
//
//    }
}
