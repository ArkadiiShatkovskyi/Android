package com.example.arek.lab4_part2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PetElementsFragment extends Fragment {


    public PetElementsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_element_pet, container, false);
    }

    public void setPetElement(String name,String living,String drink){
        TextView tv_name=(TextView)findViewById(R.id.tv_name_value);
        TextView tv_living=(TextView)findViewById(R.id.tv_living_value);
        TextView tv_drink=(TextView)findViewById(R.id.tv_drink_value);

        tv_name.setText(name);
        tv_living.setText(living);
        tv_drink.setText(drink);
    }
}
