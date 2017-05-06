package com.example.arek.lab4_part1;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Fragment1.OnWyborOpcjiListener {

    Fragment11 f11;
    Fragment12 f12;
    Fragment13 f13;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        f11=new Fragment11();
        f12=new Fragment12();
        f13=new Fragment13();
        transaction=getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.kontener,f11);
        transaction.detach(f11);
        transaction.add(R.id.kontener,f12);
        transaction.detach(f12);
        transaction.add(R.id.kontener,f13);
        transaction.detach(f13);
        transaction.commit();
    }

    @Override
    public void onWyborOpcji(int opcja) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.detach(f11);
        transaction.detach(f12);
        transaction.detach(f13);

        switch (opcja){
            case 1: transaction.attach(f11); break;
            case 2: transaction.attach(f12); break;
            case 3: transaction.attach(f13); break;
        }

        transaction.commit();
    }
}