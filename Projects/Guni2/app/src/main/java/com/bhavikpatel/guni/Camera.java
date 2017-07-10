package com.bhavikpatel.guni;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by BHAVIK PATEL on 16-Jun-17.
 */

public class Camera{

    public static final String STUDY = "Study";
    public static final String ANDROID = "Android";
    public static String mCurrentPhotoPath;

    //  SAVE
    private static boolean hasSD(){
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }
    private static String getSDPath(){
        if(hasSD()){
            File sdPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            if(!sdPath.exists())sdPath.mkdirs();
            return sdPath.getAbsolutePath();
        }else {
            File path = Environment.getDataDirectory();
            if(!path.exists())path.mkdirs();
            return path.getAbsolutePath();
        }
    }
    public static File getSavePath(String type){
        File savePath;
        switch (type){
            case STUDY:
                savePath = new File(getSDPath(),STUDY);
                if(!savePath.exists())savePath.mkdirs();
                return savePath;
            case ANDROID:
                savePath = new File(getSDPath(),ANDROID);
                if(!savePath.exists())savePath.mkdirs();
                return savePath;
            default:return null;
        }
    }
    public static File createImageFile(String type)  {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getSavePath(type);
        File image = null;
        try {
            image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
    public static File getFile(String type){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentDateAndTime = sdf.format(new Date());
        File savePath = getSavePath(type);
        File file = new File(savePath,currentDateAndTime + ".jpg");
        return file;
    }
    public static void saveToFileName(File file,Bitmap bitmap){
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,0,out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String save(final Bitmap bitmap,final String saveType){

        final File fileName = getFile(saveType);
        saveToFileName(fileName,bitmap);
        //return null;
        /*AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {

            }
        };*/
        /*task.execute();*/
        return fileName.getAbsolutePath();
    }


    //  LOAD
    private static Bitmap decodeBitmap(String fileName, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(fileName,options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(fileName,options);
    }
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if(reqWidth == 0 || reqHeight == 0 )return 8;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
    public static Bitmap load(String fileName, int reqWidth, int reqHeight){
        File f = new File(fileName);
        if(!f.exists()) {
            //Log.i("BHAVIK","BITMAP = NULL  ,  fileName not exist : " + fileName);
            return null;
        }
        Bitmap bitmap = decodeBitmap(fileName,reqWidth,reqHeight);
        return bitmap;
    }
    public static Bitmap load(Resources resources,int id, int reqWidth, int reqHeight){
        if(resources == null) {
            Log.i("BHAVIK","My BITMAP = NULL :");
            return null;
        }
        return decodeBitmap(resources,id,reqWidth,reqHeight);
    }
    private static Bitmap decodeBitmap(Resources resources,int id, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources,id,options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources,id,options);
    }

}










































/*
public class Camera {



    public static String save(final Bitmap bitmap,final String name){


        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                File directory = new File(Environment.getExternalStorageDirectory() + File.separator + "guni");
                if(!directory.exists())directory.mkdirs();
                File file = new File(directory,name+".jpg");
                try {
                    file.createNewFile();
                    FileOutputStream out = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,90,out);
                    out.close();
                } catch (IOException e) {
                    Log.i("BHAVIK","in save : " + e );
                }
                return null;
            }
        };
        asyncTask.execute();
        File directory = new File(Environment.getExternalStorageDirectory() + File.separator + "guni");
        return directory.getAbsolutePath()+File.separator+name+".jpg";
    }

    private static final String GUNI = "guni";
    public static final String STUDY = "study";
    public static final String ANDROID = "android";

    void save1(Context context,InputStream is,String name) {

        File path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File file = new File(path, name+".jpg");

        try {

            path.mkdirs();

            //InputStream is = getResources().openRawResource(R.drawable.balloons);
            OutputStream os = new FileOutputStream(file);
            byte[] data = new byte[is.available()];
            is.read(data);
            os.write(data);
            is.close();
            os.close();

            MediaScannerConnection.scanFile(context,
                    new String[] { file.toString() }, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("ExternalStorage", "Scanned " + path + ":");
                            Log.i("ExternalStorage", "-> uri=" + uri);
                        }
                    });
        } catch (IOException e) {
            // Unable to create file, likely because external storage is
            // not currently mounted.
            Log.w("BHAVIK", "Error writing " + file, e);
        }
    }


    private static boolean hasSD(){
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }
    private static String getSDPath(){
        return Environment.getExternalStorageDirectory().toString();
    }
    private static String getSavePath(String type){
        File savePath;
        switch (type){
            case STUDY:
                if(hasSD()){
                    savePath = new File(getSDPath()+"/"+GUNI+"/"+STUDY+"/");
                    if(!savePath.exists())savePath.mkdirs();
                }else {
                    savePath = Environment.getDataDirectory();
                }
                return savePath.getAbsolutePath();
            case ANDROID:
                if(hasSD()){
                    savePath = new File(getSDPath()+"/"+GUNI+"/"+ANDROID+"/");
                    if(!savePath.exists())savePath.mkdirs();
                }else {
                    savePath = Environment.getDataDirectory();
                }
                return savePath.getAbsolutePath();
            default:return null;
        }
    }
    private static File getFile(String type,String name){
        String savePath = getSavePath(type);
        File file = new File(savePath,name + ".jpg");
        file.mkdirs();
        return file;
    }
    private static void saveToFileName(File file,Bitmap bitmap){
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            boolean saveSuccess = false;
            saveSuccess =  bitmap.compress(Bitmap.CompressFormat.JPEG,0,out);
            Log.i("BHAVIK","saving fileName = " + file.getAbsolutePath() + "     save success = " + saveSuccess );
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("BHAVIK","IN CATCH OF SAVE_TO_FILE exception : " + e);
        }
    }
    public static String save2(Bitmap bitmap,String type,String name){

        String root = Environment.getExternalStorageDirectory().toString();
        FileOutputStream out = null;
        File myDir = new File(root);
        if(!myDir.exists())myDir.mkdirs();
        File fname = new File(myDir,name+".jpg");
        try {
            out = new FileOutputStream(fname);
            bitmap.compress(Bitmap.CompressFormat.JPEG,90,out);
            Log.i("BHAVIK","SAVE SUCCESSFUL");
            out.flush();
            out.close();
        } catch (IOException e) {
            Log.i("BHAVIK","IN save , got excep : " + e);
        }
        return fname.getAbsolutePath();
    }

    private static Bitmap decodeBitmap(String fileName, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(fileName,options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(fileName,options);
    }
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
    public static Bitmap load(String fileName, int reqWidth, int reqHeight){
        File f = new File(fileName);
        if(!f.exists()) {
            Log.i("BHAVIK","BITMAP = NULL  ,  fileName not exist : " + fileName);
        }
        Log.i("BHAVIK","BITMAP = EXIST  ,  fileName not exist : " + fileName);
        Bitmap bitmap = BitmapFactory.decodeFile(fileName);
        //Bitmap bitmap = decodeBitmap(fileName,reqWidth,reqHeight);
        return bitmap;
    }

}
*/
