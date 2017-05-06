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

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button back=(Button)findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        SharedPreferences sharedPreferencesSettings =getSharedPreferences("preferences", MODE_PRIVATE);

        int bgrImage=sharedPreferencesSettings.getInt("background", R.drawable.bgd0);
        setBackgroundImage(bgrImage);
    }

    private void setBackgroundImage(int image){
        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.relativeLavout_menu);

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

    @Override
    protected void onRestart() {
        super.onRestart();

        SharedPreferences sharedPreferencesSettings =getSharedPreferences("preferences", MODE_PRIVATE);
        int color=sharedPreferencesSettings.getInt("background", 0);

        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.relativeLavout_menu);
        relativeLayout.setBackgroundColor(color != 0 ? color : 16777215);
    }

    public void onClickSettings(View view){
        Intent settings=new Intent(this,SettingsActivity.class);
        startActivity(settings);
    }

    public void onClickAdd(View view){
        Intent intent=new Intent(this,AddActivity.class);
        startActivity(intent);
    }

    public void onClickWatch(View view){
        Intent intent=new Intent(this,WatchActivity.class);
        startActivity(intent);
    }

    public void onClickMlActivity(View view){
        Intent intent=new Intent(this,WithMyAdapterActivity.class);
        startActivity(intent);
    }
}
