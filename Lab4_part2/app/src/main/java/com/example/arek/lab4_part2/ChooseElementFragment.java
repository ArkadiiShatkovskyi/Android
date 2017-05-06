package com.example.arek.lab4_part2;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

public class ChooseElementFragment extends Fragment implements RadioGroup.OnCheckedChangeListener{

    AppCompatActivity A1;
    OnChooseOptionListener listenerF1;
    View root;

    public interface OnChooseOptionListener {
        public void onChooseOption(int option);
    }

    public ChooseElementFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_element, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((RadioGroup)getActivity().findViewById(R.id.radioGroup_choose)).setOnCheckedChangeListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            A1=(AppCompatActivity)context;
            listenerF1=(OnChooseOptionListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(A1.toString() + " must implement OnChooseOptionListener!");
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.radioButton: listenerF1.onChooseOption(1); break;
            case R.id.radioButton2: listenerF1.onChooseOption(2); break;
        }
    }
}
