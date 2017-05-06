package com.example.arek.lab4_part2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WildElementFragment extends Fragment {

    private View view;

    public WildElementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_element_wild, container, false);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_element_wild, container, false);
    }

    public void setWildElement(String animal,String type,String living,String agresive){
        TextView tv_animal=(TextView)getActivity().findViewById(R.id.tv_animal_value);
        TextView tv_type=(TextView)getActivity().findViewById(R.id.tv_type_value);
        TextView tv_living=(TextView)getActivity().findViewById(R.id.tv_living_place_value);
        TextView tv_agresive=(TextView)getActivity().findViewById(R.id.tv_agresive_value);

        tv_animal.setText(animal);
        tv_type.setText(type);
        tv_living.setText(living);
        tv_agresive.setText(agresive);
    }
}
