package com.example.arek.lab3_czesc2;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    private String[] animals = {"Cat", "Dog", "Horse", "Rabbit", "Raccoon", "Deer", "Panda"};
    private String[] countries = {"England", "Poland", "Russia", "USA", "Sweden", "Germany", "France", "Japan"};
    private String[] colors = {"White-black", "Gray", "Yellow", "White", "Black", "Brown"};
    private int memory; // -1 - Internal storage, 1 - external storage, 0 - both
    private final String folderName="myFolder";
    private int internalFiles=0;
    private int externalFiles=0;
    private String color="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        SharedPreferences sharedPreferencesSettings = getSharedPreferences("preferences", MODE_PRIVATE);
        int bgrImage=sharedPreferencesSettings.getInt("background", R.drawable.bgd0);

        setBackgroundImage(bgrImage);
        createSpinner(animals, R.id.spinner_animal);
        createSpinner(countries, R.id.spinner_country);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, colors);
        ListView lv = (ListView) findViewById(R.id.listView_color);
        lv.setOnItemClickListener(this);
        lv.setOnItemSelectedListener(this);
        lv.setAdapter(arrayAdapter);

        setNumbersOfNextElements();
        memory = setMemory();
    }

    private void setBackgroundImage(int image){
        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.relativeLavout_add);

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

    private void createSpinner(String[] s, int id) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, s);
        Spinner spinner = (Spinner) findViewById(id);
        //spinner.setOnItemSelectedListener(this);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setNumbersOfNextElements(){
        SharedPreferences sharedPreferencesSettings =getSharedPreferences("preferences", MODE_PRIVATE);
        internalFiles=sharedPreferencesSettings.getInt("internalFiles", 0);
        externalFiles=sharedPreferencesSettings.getInt("externalFiles", 0);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        SharedPreferences sharedPreferencesSettings = getSharedPreferences("preferences", MODE_PRIVATE);
        int color = sharedPreferencesSettings.getInt("background", 0);

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLavout_menu);
        relativeLayout.setBackgroundColor(color != 0 ? color : 16777215);
    }

    private int setMemory() {
        SharedPreferences sharedPreferencesSettings = getSharedPreferences("preferences", MODE_PRIVATE);
        String s = sharedPreferencesSettings.getString("memory", "");
        int x;
        if (s.equals("Internal memory")) {
            x = -1;
        } else if (s.equals("External memory")) {
            x = 1;
        } else {
            x = 0;
        }
        return x;
    }

    public void onClickAddAnimal(View view) {
        if(memory==-1){
            onIFileSaving(true);
        }
        else if(memory==1){
            onEFIleSaving(true);
        }
        else{
            onIFileSaving(false);
            onEFIleSaving(false);
            clearViews();

            Toast toast = Toast.makeText(this, "Animal was added!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    private void onIFileSaving(boolean flag){
        FileOutputStream os;

        TextView ename=(TextView)findViewById(R.id.edt_name);
        Spinner sanimal=(Spinner)findViewById(R.id.spinner_animal);
        Spinner scountry=(Spinner)findViewById(R.id.spinner_country);

        String name=ename.getText().toString();
        String animal=sanimal.getSelectedItem().toString();
        String country=scountry.getSelectedItem().toString();

        try {
            String fileName="file"+internalFiles+".txt";
            ++internalFiles;
            os=openFileOutput(fileName, Context.MODE_APPEND);
            byte[] buffer=(name+"\n"+animal+"\n"+country+"\n"+color).getBytes();
            os.write(buffer,0,buffer.length);
            os.close();

            if(flag) {
                Toast toast = Toast.makeText(this, "Animal was added!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(flag){
            clearViews();
        }
    }

    private void clearViews(){
        EditText editText=(EditText)findViewById(R.id.edt_name);
        editText.setText("");

        ListView listView=(ListView)findViewById(R.id.listView_color);
        listView.setSelection(0);
    }

    public boolean isExternalStorageWritable()
    {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    private void onEFIleSaving(boolean flag){

        if(!isExternalStorageWritable()){
            Toast toast=Toast.makeText(this,"SD card was not founded!",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
        else{
            TextView ename=(TextView)findViewById(R.id.edt_name);
            Spinner sanimal=(Spinner)findViewById(R.id.spinner_animal);
            Spinner scountry=(Spinner)findViewById(R.id.spinner_country);

            String name=ename.getText().toString();
            String animal=sanimal.getSelectedItem().toString();
            String country=scountry.getSelectedItem().toString();

            //String path=Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + folderName + "/"+"file"+externalFiles+".txt";
            String path="file"+externalFiles+".txt";
            ++externalFiles;

            SaveFile(path, name + "\n" + animal + "\n" + country + "\n" + color);

            if(flag) {
                Toast toast = Toast.makeText(this, "Animal was added!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }
        if(flag){
            clearViews();
        }
    }

    public void SaveFile (String filePath, String FileContent)
    {
        try
        {
                File file = new File(getExternalFilesDir(folderName), filePath);// filePath - name of file
                //Toast.makeText(this, "fhandel: "+(file==null)+", PATH: "+file, Toast.LENGTH_SHORT).show();

                FileOutputStream fOut = new FileOutputStream(file);
                OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                myOutWriter.write(FileContent);
                myOutWriter.close();
                fOut.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void onClickBack(View view) {
        SharedPreferences preferences=getSharedPreferences("preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putInt("internalFiles",internalFiles);
        editor.putInt("externalFiles",externalFiles);
        editor.commit();
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            color=colors[i];
    }
}
