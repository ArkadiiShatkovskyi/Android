package com.example.arek.lab4_part2;


import android.content.Context;
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
import android.widget.Switch;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PetFragment extends Fragment {

    private View view;
    private int internalFiles=0;

    public PetFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_wild_animal, container, false);

        return inflater.inflate(R.layout.fragment_pet, container, false);
    }

    public int onIFileSaving(){
        FileOutputStream os;

        EditText ename=(EditText) getActivity().findViewById(R.id.ed_name_pet);
        RadioGroup rg=(RadioGroup)getActivity().findViewById(R.id.rg_drink);
        RadioButton rb_drink=(RadioButton)getActivity().findViewById(rg.getCheckedRadioButtonId());
        Switch sw_live=(Switch)getActivity().findViewById(R.id.sw_place);

        String name=ename.getText().toString();
        String drink=rb_drink.getText().toString();
        String living_place=sw_live.isChecked()?"Street":"House";

        try {
            String fileName="pfile"+internalFiles+".txt";
            ++internalFiles;
            os=getActivity().openFileOutput(fileName, Context.MODE_APPEND);
            byte[] buffer=(name+"\n"+drink+"\n"+living_place).getBytes();
            os.write(buffer,0,buffer.length);
            os.close();

            Toast toast = Toast.makeText(getActivity(), "Animal was added!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clearViews();
        return internalFiles;
    }

    private void clearViews(){
        EditText ename=(EditText) getActivity().findViewById(R.id.ed_name_pet);
        ename.setText("");
    }

    public void setNumberOfFiles(int files){
        this.internalFiles=files;
    }
}
