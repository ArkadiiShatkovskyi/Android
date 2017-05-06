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
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {

    final static String key="saved text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        final Intent intent=new Intent(this,MenuActivity.class);
        Button menu=(Button)findViewById(R.id.btn_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferencesSettings =getSharedPreferences("preferences", MODE_PRIVATE);
        String s= sharedPreferencesSettings.getString(key, "Hi on the first display!");
        int colorOfText=sharedPreferencesSettings.getInt("color of greeting",16777215);
        //int bgrImage=sharedPreferencesSettings.getInt("background", 0);
        int bgrImage=sharedPreferencesSettings.getInt("int image", 0);

        TextView txt_greeting=(TextView)findViewById(R.id.txt_greeting_text);
        txt_greeting.setText(s);
        txt_greeting.setTextColor(colorOfText);

        setImage(bgrImage);
        //RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.relativeLavout_first);
        //relativeLayout.setBackgroundResource(bgrImage);

        //setBackgroundImage(bgrImage);
    }

    private void setImage(int i){
        //RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.relativeLavout_first);
        int image=0;
        switch (i){
            case 0 : image=R.drawable.bgd0; break;
            case 1 : image=R.drawable.bgd1; break;
            case 2 : image=R.drawable.bgd2; break;
            case 3 : image=R.drawable.bgd3; break;
            case 4 : image=R.drawable.bgd4; break;
            case 5 : image=R.drawable.bgd5; break;
            case 6 : image=R.drawable.bgd6; break;
        }
        setBackgroundImage(image);
    }

    private void setBackgroundImage(int i){
        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.relativeLavout_first);
        int image=0;
        switch (i){
            case 0 : image=R.drawable.bgd0; break;
            case 1 : image=R.drawable.bgd1; break;
            case 2 : image=R.drawable.bgd2; break;
            case 3 : image=R.drawable.bgd3; break;
            case 4 : image=R.drawable.bgd4; break;
            case 5 : image=R.drawable.bgd5; break;
            case 6 : image=R.drawable.bgd6; break;
        }

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

    public void onClickExit(View view){
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SharedPreferences sharedPreferencesSettings=getSharedPreferences("preferences", MODE_PRIVATE);
        String s= sharedPreferencesSettings.getString(key, "");
        int color=sharedPreferencesSettings.getInt("background", 0);

        TextView txt_greeting=(TextView)findViewById(R.id.txt_greeting_text);
        txt_greeting.setText(s!=""?s:"Hi on the first display!");
        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.relativeLavout_first);
        relativeLayout.setBackgroundColor(color!=0?color:16777215);
    }
}
