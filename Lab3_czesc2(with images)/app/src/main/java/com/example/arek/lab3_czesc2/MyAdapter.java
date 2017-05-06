package com.example.arek.lab3_czesc2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

public class MyAdapter extends BaseAdapter {

    private LinkedList<File> objects=new LinkedList<>();
    private LayoutInflater lInflater;

    public MyAdapter(Context context,LinkedList<File> objects){
        this.objects=objects;
        this.lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //System.out.println("HERE FILE: !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return objects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View mview=view;
        if(mview==null){
            mview=lInflater.inflate(R.layout.file, null);
        }

        File file=objects.get(i);

        int memory;
        int imemory=file.getMemory();
        if(imemory==1){
            memory=R.drawable.sdcard;
        }
        else{
            memory=R.drawable.imemory;
        }

        TextView name=(TextView)mview.findViewById(R.id.txt_aname);
        name.setText(file.getName());
        TextView country=(TextView)mview.findViewById(R.id.txt_acountry);
        country.setText("Country:" + file.getCountry());
        TextView color=(TextView)mview.findViewById(R.id.txt_acolor);
        color.setText("Color:" + file.getColor());
        TextView animal=(TextView)mview.findViewById(R.id.txt_animal);
        animal.setText("Animal:" + file.getAnimal());
        ImageView imageView=(ImageView) mview.findViewById(R.id.imageView_aimage);
        imageView.setImageResource(file.getImage());
        ImageView imgMemory=(ImageView)mview.findViewById(R.id.imageView_scMemory);
        imgMemory.setImageResource(memory);

        return mview;
    }
}
