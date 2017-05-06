package com.example.arek.lab4_part2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLOutput;

public class WildAnimalFragment extends Fragment {

    private View view;
    private int internalFiles=0;

    public WildAnimalFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view=inflater.inflate(R.layout.fragment_wild_animal, container, false);

        return inflater.inflate(R.layout.fragment_wild_animal, container, false);
    }

    public int onIFileSaving(){
        FileOutputStream os;

        EditText ename=(EditText) getActivity().findViewById(R.id.et_animal);
        RadioGroup rb_animal_type=(RadioGroup)getActivity().findViewById(R.id.rg_animal_type);
        RadioGroup rb_living=(RadioGroup)getActivity().findViewById(R.id.rg_living_place);
        RadioButton rb_an=(RadioButton)getActivity().findViewById(rb_animal_type.getCheckedRadioButtonId());
        RadioButton rb_lv=(RadioButton)getActivity().findViewById(rb_living.getCheckedRadioButtonId());
        SeekBar agresivly=(SeekBar)getActivity().findViewById(R.id.sb_agresive);

        String animal=ename.getText().toString();
        String animalType=rb_an.getText().toString();
        String animalLiving=rb_lv.getText().toString();
        int agresive=agresivly.getProgress();
        String agr=String.valueOf(agresive);

        //System.out.println("ANIMAL HERERERERERERERER: "+animal+"  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        Toast toast = Toast.makeText(getActivity(), "ANIMAL HERERERERERERERER: "+animal+"  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        try {
            String fileName="wfile"+internalFiles+".txt";
            ++internalFiles;
            os=getActivity().openFileOutput(fileName, Context.MODE_APPEND);
            byte[] buffer=(animal+"\n"+animalType+"\n"+animalLiving+"\n"+agr).getBytes();
            os.write(buffer,0,buffer.length);
            os.close();

            //Toast toast = Toast.makeText(getActivity(), "Animal was added!", Toast.LENGTH_SHORT);
            //toast.setGravity(Gravity.CENTER, 0, 0);
            //toast.show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("EXIST: ");
            clearViews();
        return internalFiles;
    }

    private void clearViews(){
        EditText ename=(EditText) getActivity().findViewById(R.id.et_animal);
        ename.setText("");
        SeekBar agresivly=(SeekBar)getActivity().findViewById(R.id.sb_agresive);
        agresivly.setProgress(0);
    }

    public void setNumberOfFiles(int files){
        this.internalFiles=files;
    }

}
