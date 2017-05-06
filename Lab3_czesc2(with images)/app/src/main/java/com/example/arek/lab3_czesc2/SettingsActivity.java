package com.example.arek.lab3_czesc2;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String[] texts={"Hi on the first display!","Greeting!", "Why you are still here?"};
    private String[] textMemory={"Internal memory","External memory","Both memories"};
    private int seekR,seekG,seekB;
    private SeekBar redSeekBar,greenSeekBar,blueSeekBar;
    private RelativeLayout mScreen;
    private Spinner choose_greeting;
    private Spinner choose_memory;
    private int backgroundImage=R.drawable.bgd0;
    private int numberOfBackgroundImage=0;
    private Drawable drawableM;

    private SeekBar.OnSeekBarChangeListener seekBarChangeBackgroundListener=
            new SeekBar.OnSeekBarChangeListener(){
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    int img = 0;
                    switch (i){
                        case 0 : img=R.drawable.bgd0; break;
                        case 1 : img=R.drawable.bgd1; break;
                        case 2 : img=R.drawable.bgd2; break;
                        case 3 : img=R.drawable.bgd3; break;
                        case 4 : img=R.drawable.bgd4; break;
                        case 5 : img=R.drawable.bgd5; break;
                        case 6 : img=R.drawable.bgd6; break;
                    }
                    backgroundImage=img;
                    numberOfBackgroundImage=i;
                    setBackgroundImage(img);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {}

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {}
            };

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener=
            new SeekBar.OnSeekBarChangeListener(){
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    updateColorOfGreeting();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {}

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {}
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        choose_greeting=createSpinner(texts,R.id.spinner_gretting);
        choose_memory=createSpinner(textMemory,R.id.spinner_choose_memory);

        createSeekBars();
        setViewsValues();
        setBackgroundImage(backgroundImage);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        SharedPreferences sharedPreferencesSettings =getSharedPreferences("preferences", MODE_PRIVATE);
        int color=sharedPreferencesSettings.getInt("background", 0);

        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.relativeLavout_menu);
        relativeLayout.setBackgroundColor(color != 0 ? color : 16777215);

        setViewsValues();
    }

    private Spinner createSpinner(String[] s,int id){
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,s);
        Spinner spinner=(Spinner)findViewById(id);
        spinner.setOnItemSelectedListener(this);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return spinner;
    }

    private void createSeekBars(){

        redSeekBar=(SeekBar)findViewById(R.id.seekBar_R);
        greenSeekBar=(SeekBar)findViewById(R.id.seekBar_G);
        blueSeekBar=(SeekBar)findViewById(R.id.seekBar_B);
        updateColorOfGreeting();

        redSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        greenSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        blueSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        SeekBar seekBackgroundImage=(SeekBar)findViewById(R.id.seekBar_backgournd_image);
        seekBackgroundImage.setOnSeekBarChangeListener(seekBarChangeBackgroundListener);
    }

    private void setBackgroundImage(int image){
        mScreen=(RelativeLayout)findViewById(R.id.screenSetting);
        Bitmap bitmap =decodeSampledBitmapFromResource(getResources(), image, mScreen.getWidth(), mScreen.getHeight());
        Drawable d = new BitmapDrawable(getResources(), bitmap);
        drawableM=d;
        mScreen.setBackground(d);
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

    private int[] updateColorOfGreeting(){
        seekR=redSeekBar.getProgress();
        seekG=greenSeekBar.getProgress();
        seekB=blueSeekBar.getProgress();
        int[] result={0xff000000 + seekR * 0x10000 + seekG * 0x100 + seekB,seekR,seekG,seekB};

        TextView textView=(TextView)findViewById(R.id.txt_color_of_greeting);
        textView.setTextColor(result[0]);
        return result;
    }

    public void onClickSave(View view){
        SharedPreferences preferences=getSharedPreferences("preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(FirstActivity.key, choose_greeting.getSelectedItem().toString());
        editor.putString("memory",choose_memory.getSelectedItem().toString());
        editor.putInt("background", backgroundImage);
        editor.putInt("R", updateColorOfGreeting()[1]);
        editor.putInt("G", updateColorOfGreeting()[2]);
        editor.putInt("B", updateColorOfGreeting()[3]);
        editor.putInt("color of greeting",updateColorOfGreeting()[0]);
        editor.putInt("int image",numberOfBackgroundImage);
        //editor.putInt("s",drawableM);
        editor.commit();

        Toast toast=Toast.makeText(this, "Settings saved!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    public void setViewsValues(){
        SharedPreferences preferences=getSharedPreferences("preferences", MODE_PRIVATE);
        String s=preferences.getString(FirstActivity.key,"");
        String s2=preferences.getString("memory","");
        numberOfBackgroundImage=preferences.getInt("int image",0);
        backgroundImage=preferences.getInt("background",R.drawable.bgd0);
        int textColor=preferences.getInt("color of greeting",16777215);

        for(int i=0;i<texts.length;i++){
            if(s.equals(texts[i])){   //s greeting
                choose_greeting.setSelection(i);
            }
        }

        for(int i=0;i<textMemory.length;i++){
            if(s2.equals(textMemory[i])){   //memory
                choose_memory.setSelection(i);
            }
        }

        redSeekBar.setProgress(preferences.getInt("R", 255));
        greenSeekBar.setProgress(preferences.getInt("G", 255));
        blueSeekBar.setProgress(preferences.getInt("B", 255));

        SeekBar backgroundImage=(SeekBar)findViewById(R.id.seekBar_backgournd_image);
        backgroundImage.setProgress(numberOfBackgroundImage);

        TextView textGreeting=(TextView)findViewById(R.id.txt_color_of_greeting);
        textGreeting.setTextColor(textColor);
        }

    public void onClickBack(View view){
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
