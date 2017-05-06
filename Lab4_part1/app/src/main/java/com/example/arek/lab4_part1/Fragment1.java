package com.example.arek.lab4_part1;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Fragment1 extends Fragment implements RadioGroup.OnCheckedChangeListener{

    AppCompatActivity A1;
    OnWyborOpcjiListener sluchaczF1;

    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment1, container, false);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.radioButton: sluchaczF1.onWyborOpcji(1); break;
            case R.id.radioButton2: sluchaczF1.onWyborOpcji(2); break;
            case R.id.radioButton3: sluchaczF1.onWyborOpcji(3); break;
        }
    }

    public interface OnWyborOpcjiListener{
        public void onWyborOpcji(int opcja);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((RadioGroup)getActivity().findViewById(R.id.radioGroup)).setOnCheckedChangeListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            A1=(AppCompatActivity)context;
            sluchaczF1=(OnWyborOpcjiListener)context;
        }catch (ClassCastException e){
            throw new ClassCastException(A1.toString() + " musi implementowac OnWyborOpcjiListener");
        }
    }
}
