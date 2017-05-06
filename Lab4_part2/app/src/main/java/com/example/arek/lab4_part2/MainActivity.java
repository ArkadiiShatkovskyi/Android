package com.example.arek.lab4_part2;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements ChooseElementFragment.OnChooseOptionListener, ActionBar.TabListener,Fragment_Tab3.OnChooseElementListener{

    WildAnimalFragment wildAnimalFragment;
    PetFragment petFragment;
    ChooseElementFragment chooseElementFragment;
    Fragment_Tab1 fragment_tab1;
    Fragment_Tab3 fragment_tab3;
    WildElementFragment wildElementFragment;
    PetElementsFragment petElementsFragment;
    private LinkedList<String> files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        wildAnimalFragment=new WildAnimalFragment();
        petFragment=new PetFragment();
        chooseElementFragment=new ChooseElementFragment();
        fragment_tab1=new Fragment_Tab1();
        fragment_tab3=new Fragment_Tab3();
        wildElementFragment=new WildElementFragment();
        petElementsFragment=new PetElementsFragment();

        setNumberOfFiles();

        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.mframelayout_static,chooseElementFragment);
        transaction.detach(chooseElementFragment);
        transaction.add(R.id.mframelayout_dynamic,wildAnimalFragment);
        transaction.detach(wildAnimalFragment);
        transaction.add(R.id.mframelayout_dynamic,petFragment);
        transaction.detach(petFragment);
        transaction.add(R.id.mframelayout_dynamic,fragment_tab1);
        transaction.detach(fragment_tab1);
        transaction.add(R.id.mframelayout_static,fragment_tab3);
        transaction.detach(fragment_tab3);
        transaction.add(R.id.mframelayout_dynamic,petElementsFragment);
        transaction.detach(petElementsFragment);
        transaction.add(R.id.mframelayout_dynamic,wildElementFragment);
        transaction.detach(wildElementFragment);
        transaction.commit();
        createActionBar();
    }

    private void createActionBar(){

        ActionBar ab = getSupportActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ab.setDisplayShowTitleEnabled(true);
        ab.addTab(ab.newTab().setText("Tab 1").setTabListener(this));
        ab.addTab(ab.newTab().setText("Tab 2").setTabListener(this));
        ab.addTab(ab.newTab().setText("Tab 3").setTabListener(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onChooseOption(int option) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.detach(wildAnimalFragment);
        transaction.detach(petFragment);

        switch (option){
            case 1: transaction.attach(wildAnimalFragment); break;
            case 2: transaction.attach(petFragment); break;
        }

        transaction.commit();
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

        int nTab = tab.getPosition();
        FragmentTransaction transaktion = getSupportFragmentManager().beginTransaction();

        switch (nTab){
            case 0:
                transaktion.detach(chooseElementFragment);
                transaktion.detach(petFragment);
                transaktion.detach(wildAnimalFragment);
                transaktion.detach(fragment_tab3);
                transaktion.detach(wildElementFragment);
                transaktion.detach(petElementsFragment);

                transaktion.attach(fragment_tab1);
                transaktion.commit();
                break;
            case 1:
                transaktion.detach(fragment_tab1);
                transaktion.detach(fragment_tab3);
                transaktion.detach(petFragment);
                transaktion.detach(wildAnimalFragment);
                transaktion.detach(wildElementFragment);
                transaktion.detach(petElementsFragment);

                transaktion.attach(chooseElementFragment);
                transaktion.commit();
                break;
            case 2:
                transaktion.detach(chooseElementFragment);
                transaktion.detach(petFragment);
                transaktion.detach(wildAnimalFragment);
                transaktion.detach(fragment_tab1);

                transaktion.attach(fragment_tab3);
                //fragment_tab3.createListView();
                transaktion.commit();
                break;
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    public void onClickBtnSave(View view){
        int n=wildAnimalFragment.onIFileSaving();
        saveNumbersOfFiles(true,n);
        fragment_tab3.setwFiles(n);
    }

    public void onClickBtnSave2(View view){
        int n=petFragment.onIFileSaving();
        saveNumbersOfFiles(false,n);
        fragment_tab3.setpFiles(n);
    }

    private void saveNumbersOfFiles(boolean flag,int n){
        SharedPreferences preferences=getSharedPreferences("preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        if(flag){
            editor.putInt("WFiles",n);
        }
        else{
            editor.putInt("PFiles",n);
        }
        editor.commit();
    }

    private void setNumberOfFiles(){
        SharedPreferences sharedPreferencesSettings = getSharedPreferences("preferences", MODE_PRIVATE);
        int wFiles = sharedPreferencesSettings.getInt("WFiles", 0);
        int pFiles = sharedPreferencesSettings.getInt("PFiles", 0);


        wildAnimalFragment.setNumberOfFiles(wFiles);
        petFragment.setNumberOfFiles(pFiles);
        setFragment3N(wFiles,pFiles);
    }

    private void setFragment3N(int wFiles,int pFiles){
        fragment_tab3.setNumberOfFiles(wFiles,pFiles);
    }

    @Override
    public void onChooseElement(int option, File file) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.detach(wildElementFragment);
        transaction.detach(petElementsFragment);

        if (file.isWild() == true) {
            transaction.attach(wildElementFragment);
            wildElementFragment.setWildElement(file.getAnimal(),file.getType(),file.getLivingPlace(),file.getAgresive());
        }
        else {
            transaction.attach(petElementsFragment);
            petElementsFragment.setPetElement(file.getName(),file.getLivingPlace(),file.getDrink());
        }

        transaction.commit();
    }

}