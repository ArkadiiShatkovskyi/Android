package com.example.arek.lab3_czesc2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ShowActivity extends AppCompatActivity {

    private String fileName;
    private String filePath;
    private int memory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        SharedPreferences sharedPreferencesSettings =getSharedPreferences("preferences", MODE_PRIVATE);

        int bgrImage=sharedPreferencesSettings.getInt("background", R.drawable.bgd0);
        setBackgroundImage(bgrImage);

        fillTextView();
    }

    private void setBackgroundImage(int image){
        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.relativeLavout_show);

        Bitmap bitmap =decodeSampledBitmapFromResource(getResources(), image, relativeLayout.getWidth(), relativeLayout.getHeight());
        Drawable d = new BitmapDrawable(getResources(), bitmap);
        relativeLayout.setBackground(d);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public void onClickBackS(View view){
        finish();
    }

    private void fillTextView(){
        Intent intent=getIntent();
        TextView textView=(TextView)findViewById(R.id.txt_show);

        String name=intent.getStringExtra("name");
        String animal=intent.getStringExtra("animal");
        String country=intent.getStringExtra("country");
        String color=intent.getStringExtra("color")==null?"no information":intent.getStringExtra("color");

        fileName=intent.getStringExtra("fileName");
        filePath=intent.getStringExtra("path");
        memory=intent.getIntExtra("memory", 0);
        String smemory="";
        if(memory==-1){
            smemory="Internal storage";
        }
        else if(memory==1){
            smemory="External storage";
        }
        else{
            smemory="Internal and External storagies";
        }

        textView.setText("Name: "+name+"\nAnimal: "+animal+"\nCountry: "+country+"\nColor: "+color+"\nUsing memory: "+smemory);
    }
}
