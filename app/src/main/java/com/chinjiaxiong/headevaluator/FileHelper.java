package com.chinjiaxiong.headevaluator;

import android.content.Context;
        import android.os.Environment;
        import android.util.Log;

        import java.io.BufferedReader;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileHelper {
    final static String fileName = "data.txt";
    final static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/"  + fileName;
    final static String TAG = FileHelper.class.getName();

    public static ArrayList<String> readFile(){
        String line = null;
        ArrayList<String> ls = new ArrayList<>();

        try {
            Log.d("testresult", path);
            FileInputStream fileInputStream = new FileInputStream (new File(path));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while ( (line = bufferedReader.readLine()) != null && !line.isEmpty())
            {
                ls.add(line);
            }
            fileInputStream.close();
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }
        catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return ls;
    }

    public static boolean saveToFile(ArrayList<String> data){
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            for(String d: data){
                fileOutputStream.write((d + "\n").getBytes());
            }
            return true;
        }  catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }  catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return  false;
    }

}