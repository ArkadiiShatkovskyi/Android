package com.example.arek.lab3_czesc2;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public class WithMyAdapterActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{

    private LinkedList<File> files=new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_my_adapter);

        createListView();
    }

    private void setBackgroundImage(int image){
        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.relativeLavout_list_with_my_adapter);

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

    public void onClickBackToMenu(View view){
        finish();
    }

    private void createListView(){
        SharedPreferences sharedPreferencesSettings = getSharedPreferences("preferences", MODE_PRIVATE);
        int bgrImage=sharedPreferencesSettings.getInt("background", R.drawable.bgd0);
        setBackgroundImage(bgrImage);

        files.clear();
        addIElements();
        addEElements();

        ListView listView=(ListView)findViewById(R.id.listView_madapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        listView.setAdapter(new MyAdapter(this, files));
    }

    private void addIElements(){
        if(files==null){
            files=new LinkedList<>();
        }

        SharedPreferences sharedPreferencesSettings = getSharedPreferences("preferences", MODE_PRIVATE);
        int lastIFile=sharedPreferencesSettings.getInt("internalFiles",0);

        for(int i=0;i<lastIFile;i++){
            getIFile("file" + i + ".txt");
        }
    }

    private void addEElements(){
        if(files==null){
            files=new LinkedList<>();
        }

        SharedPreferences sharedPreferencesSettings = getSharedPreferences("preferences", MODE_PRIVATE);
        int lastIFile=sharedPreferencesSettings.getInt("externalFiles", 0);

        for(int i=0;i<lastIFile;i++){
            getEFile("file" + i + ".txt");
        }
    }

    private void getIFile(String fileName){
        File file=new File();
        try {
            FileInputStream fis = openFileInput(fileName);
            DataInputStream dis=new DataInputStream(fis);
            String name=dis.readLine();
            String animal=dis.readLine();
            String country=dis.readLine();
            String color=dis.readLine();
            dis.close();

            file.setName(name);
            file.setFilename(fileName);
            file.setAnimal(animal);
            file.setCountry(country);
            file.setColor(color);
            file.setMemory(-1);
            file.setImage(getImage(file.getAnimal()));

            if(!(file.getName()==""))
                files.add(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void getEFile(String fileName){
        File file=new File();
        final String folderName="myFolder";
        try {
            //String spath= Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + folderName;
            //String s=Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + folderName + "/"+fileName;
            String spath= String.valueOf(getExternalFilesDir(folderName));
            String s=getExternalFilesDir(folderName) + "/"+fileName;

            FileInputStream fis = new FileInputStream(s);
            DataInputStream dis=new DataInputStream(fis);

            String name=dis.readLine();
            String animal=dis.readLine();
            String country=dis.readLine();
            String color=dis.readLine();
            dis.close();

            file.setName(name);
            file.setPath(spath);
            file.setFilename(fileName);
            file.setAnimal(animal);
            file.setCountry(country);
            file.setColor(color);
            file.setMemory(1);
            file.setImage(getImage(file.getAnimal()));

            if(!(file.getName()==""))
                files.add(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getImage(String animal){
        String[] animals = {"Cat", "Dog", "Horse", "Rabbit", "Raccoon", "Deer", "Panda"};
        int result=0;
            switch (animal){
                case "Cat": result=R.drawable.cat; break;
                case "Dog": result=R.drawable.dog; break;
                case "Horse": result=R.drawable.horse; break;
                case "Rabbit": result=R.drawable.rabbit; break;
                case "Raccoon": result=R.drawable.raccoon; break;
                case "Deer": result=R.drawable.deer; break;
                case "Panda": result=R.drawable.panda; break;
                case "": result=R.drawable.noimage; break;
            }
        return result;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this,"Hi there!",Toast.LENGTH_SHORT);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        File file=files.get(i);
        int memory=file.getMemory();
        if(memory==-1) {
            onIFileLongClick(i);
        }
        else if(memory== 1) {
            onEFileLongClick(i);
        }
        createListView();
        return false;
    }

    private void onIFileLongClick(int i){
        File file=files.get(i);
        java.io.File dir=getFilesDir();
        java.io.File f=new java.io.File(dir,file.getFilename());
        f.delete();

        files.remove(i);

        Toast toast= Toast.makeText(this,"File was removed!",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    private void onEFileLongClick(int i){
        File file=files.get(i);
        java.io.File f=new java.io.File(file.getPath(),file.getFilename());
        boolean b=f.delete();

        files.remove(i);
        Toast toast= Toast.makeText(this,"File was removed!",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}
