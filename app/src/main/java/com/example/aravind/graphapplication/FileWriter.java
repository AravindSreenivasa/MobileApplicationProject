package com.example.aravind.graphapplication;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Gautam on 3/9/2016.
 */
public class FileWriter {


    public static void Test(Context fileContext) {
        String filename = "mygfile.txt";
        String string = "Hello world!";
        FileOutputStream outputStream;
        try {
            outputStream = fileContext.openFileOutput(filename,fileContext.MODE_WORLD_READABLE);
            outputStream.write(string.getBytes());
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
