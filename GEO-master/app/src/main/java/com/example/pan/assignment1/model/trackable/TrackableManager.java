package com.example.pan.assignment1.model.trackable;

import android.util.Log;

import com.example.pan.assignment1.MainActivity;
import com.example.pan.assignment1.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TrackableManager {

    private static List<Trackable> trackables = getAllList();
    private TrackableManager(){}

    public static List<Trackable> getTrackables(){return trackables; }
    private static List<Trackable> getAllList(){
        List<Trackable> data = new ArrayList<>();
        InputStream is = MainActivity.getContext().getResources().openRawResource(R.raw.food_truck_data);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;
        try {
            while((line = br.readLine())!=null){
                Log.i("FOOD TRUCK",line);
                String[] result = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                String name,description,url,category;
                name= result[1].substring(1,result[1].length()-1);
                description = result[2].substring(1,result[2].length()-1);
                url = result[3].substring(1,result[3].length()-1);
                category = result[4].substring(1,result[4].length()-1);

                FoodTruck t = new FoodTruck(name,description,url,category);
                data.add(t);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  data;
    }

    public static List<String> getAllCategories(){

        List<String> categories = new ArrayList<>();
        for(Trackable t : trackables){
            if(!categories.contains(t.getCategory()))
                categories.add(t.getCategory());
        }
        return categories;
    }

    public static List<Trackable> findTrackableByCategory(String category){
        if(!getAllCategories().contains(category)){
            return trackables;
        }
        List<Trackable> filtered =new ArrayList<>();
        for(Trackable t : trackables){
            if(t.getCategory().equals(category)){
                filtered.add(t);
            }
        }
        return filtered;

    }

    public static String getName(int ID){
        String name = "";
        for(Trackable t : trackables){
            if(t.getId() == ID){
                name = t.getName();
                break;
            }
        }
        return name;
    }

//    public static Trackable getTrackableById(String id){
//
//    }

}
