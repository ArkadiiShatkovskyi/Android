package com.example.arek.lab3_czesc2;

import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public class WatchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private LinkedList<File> files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch);

        SharedPreferences sharedPreferencesSettings = getSharedPreferences("preferences", MODE_PRIVATE);
        int bgrImage=sharedPreferencesSettings.getInt("background", R.drawable.bgd0);

        setBackgroundImage(bgrImage);

        createListView();
    }

    private void setBackgroundImage(int image){
        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.relativeLavout_watch);

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

    private String[] getStringOfElements(){
        String[] ielements=createStringOfIElements();
        String[] eelements=createStringOfEElements();
        LinkedList<String> result=new LinkedList<String>();
        for(int i=0;i<ielements.length;i++){
            if(ielements[i]!=null && ielements[i]!="")
                result.add(ielements[i]);
        }
        for(int j=0;j<eelements.length;j++){
            if(eelements[j]!=null && eelements[j]!="")
                result.add(eelements[j]);
        }
        String[] s=new String[result.size()];
        for(int i=0;i<s.length;i++){
            s[i]=result.get(i);
        }
        if(s.length==0){
            s=new String[1];
            s[0]="No elements";
            files.clear();
        }
        return s;
    }

    private String[] createStringOfIElements(){
        LinkedList<String> result=new LinkedList<String>();
        if(files==null){
            files=new LinkedList<File>();
        }

        SharedPreferences sharedPreferencesSettings = getSharedPreferences("preferences", MODE_PRIVATE);
        int lastIFile=sharedPreferencesSettings.getInt("internalFiles",0);

        for(int i=0;i<lastIFile;i++){
            String gif=getIFile("file"+i+".txt");
            if(gif!=""){
                result.add(gif);
            }
        }

        String[] sresult=null;
        if(result.size()==0){
            sresult=new String[1];
            sresult[0]="";
        }
        else{
            sresult=new String[result.size()];
            for(int j=0;j<result.size();j++){
                sresult[j]=result.get(j);
            }
        }
        return sresult;
    }

    private String[] createStringOfEElements(){
        LinkedList<String> result=new LinkedList<String>();
        if(files==null){
            files=new LinkedList<File>();
        }

        SharedPreferences sharedPreferencesSettings = getSharedPreferences("preferences", MODE_PRIVATE);
        int lastIFile=sharedPreferencesSettings.getInt("externalFiles", 0);

        for(int i=0;i<lastIFile;i++){
            String gif=getEFile("file" + i + ".txt");
            if(gif!=""){
                result.add(gif);
            }
        }

        String[] sresult=null;
        if(result.size()==0){
            sresult=new String[1];
            sresult[0]="";
        }
        else{
            sresult=new String[result.size()];
            for(int j=0;j<result.size();j++){
                sresult[j]=result.get(j);
            }
        }
        return sresult;
    }

    private String getIFile(String fileName){
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

            if(!(file.getName()==""))
                files.add(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getName();
    }


    private String getEFile(String fileName){
        File file=new File();
        final String folderName="myFolder";
        try {
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

            if(!(file.getName()==""))
                files.add(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getName();
    }

    private void createListView(){

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getStringOfElements());
        ListView lv = (ListView) findViewById(R.id.listView_elements);
        lv.setOnItemClickListener(this);
        lv.setOnItemLongClickListener(this);
        lv.setAdapter(arrayAdapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        SharedPreferences sharedPreferencesSettings = getSharedPreferences("preferences", MODE_PRIVATE);
        int color = sharedPreferencesSettings.getInt("background", 0);

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLavout_watch);
        relativeLayout.setBackgroundColor(color != 0 ? color : 16777215);

        createListView();
    }

    public void onClickBackW(View view){
        finish();
        files=null;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        if(files.size()>0){
            Intent intent=new Intent(this,ShowActivity.class);
            File file=files.get(i);

            intent.putExtra("name",file.getName());
            intent.putExtra("animal",file.getAnimal());
            intent.putExtra("fileName",file.getFilename());
            intent.putExtra("path",file.getPath());
            intent.putExtra("country", file.getCountry());
            intent.putExtra("memory", file.getMemory());
            intent.putExtra("color",file.getColor());

            startActivity(intent);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(!files.isEmpty()){
            File file=files.get(i);
            int memory=file.getMemory();
            if(memory==-1){
                onIFileLongClick(i);
            }
            else if(memory==1){
                onEFileLongClick(i);
            }
            createListView();
        }
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
