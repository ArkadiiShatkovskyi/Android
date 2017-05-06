package com.example.arek.lab4_part2;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;


public class Fragment_Tab3 extends android.support.v4.app.ListFragment {

    private View view;
    private LinkedList<File> files;
    private int wFiles;
    private int pFiles;
    private AppCompatActivity A1;
    private OnChooseElementListener listener;

    public interface OnChooseElementListener {
        void onChooseElement(int option,File file);
    }

    public Fragment_Tab3() {
        // Required empty public constructor
        wFiles=0;
        pFiles=0;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_wild_animal, container, false);

        createListView();

        return inflater.inflate(R.layout.fragment_tab3, container, false);
    }

    public void createListView(){

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,getStringOfElements());
        setListAdapter(arrayAdapter);
    }

    public String[] getStringOfElements(){
        String[] ielements=createStringOfWElements();
        String[] eelements=createStringOfPElements();
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

    private String[] createStringOfWElements(){
        LinkedList<String> result=new LinkedList<String>();
        if(files==null){
            files=new LinkedList<File>();
        }

        int lastIFile=wFiles;
        // int lastIFile=10;

        for(int i=0;i<lastIFile;i++){
            String gif=getWFile("wfile"+i+".txt");
            if(gif!=""){
                result.add(gif);
            }
        }

        String[] sresult=null;
        if(result.size()==0){
            sresult=new String[1];
            sresult[0]="No elements!";
        }
        else{
            sresult=new String[result.size()];
            for(int j=0;j<result.size();j++){
                if(result.get(j)!=null)
                    sresult[j]=result.get(j);
            }
        }
        return sresult;
    }

    private String getWFile(String fileName){
        File file=new File();
        try {
            FileInputStream fis = getActivity().openFileInput(fileName);
            DataInputStream dis=new DataInputStream(fis);
            String animal=dis.readLine();
            String type=dis.readLine();
            String living=dis.readLine();
            String agresive=dis.readLine();
            dis.close();

            System.out.println("WANIMAL: "+animal+"    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

            //byte[] buffer=(animal+"\n"+animalType+"\n"+animalLiving+"\n"+agresive).getBytes();

            file.setWild(true);
            file.setAnimal(animal);
            file.setType(type);
            file.setLivingPlace(living);
            file.setAgresive(agresive);

            files.add(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getAnimal();
    }

    private String[] createStringOfPElements(){
        LinkedList<String> result=new LinkedList<String>();
        if(files==null){
            files=new LinkedList<File>();
        }

        int lastIFile=pFiles;
        // int lastIFile=10;

        for(int i=0;i<lastIFile;i++){
            String gif=getPFile("pfile"+i+".txt");
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

    private String getPFile(String fileName){
        File file=new File();
        try {
            FileInputStream fis = getActivity().openFileInput(fileName);
            DataInputStream dis=new DataInputStream(fis);
            String name=dis.readLine();
            String drink=dis.readLine();
            String living=dis.readLine();
            dis.close();

            //byte[] buffer=(name+"\n"+drink+"\n"+living_place).getBytes();
            file.setWild(false);
            file.setName(name);
            file.setDrink(drink);
            file.setLivingPlace(living);

            files.add(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getName();
    }

    public void setNumberOfFiles(int wFiles,int pFiles){
        this.pFiles=pFiles;
        this.wFiles=wFiles;
    }

    public void setwFiles(int wFiles){
        this.wFiles=wFiles;
    }

    public void setpFiles(int pFiles){
        this.pFiles=pFiles;
    }

    public LinkedList<File> getFiles(){
        return files;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            A1=(AppCompatActivity)context;
            listener=(OnChooseElementListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(A1.toString() + " must implement OnChooseElementListener!");
        }
    }


/**
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getActivity(),"TUUUUUUUUUUUUUUUUUUUU!!!!!!!!!",Toast.LENGTH_SHORT).show();
        listener.onChooseElement(i,files.get(i));
    }
**/
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        listener.onChooseElement(position,files.get(position));
    }
}
