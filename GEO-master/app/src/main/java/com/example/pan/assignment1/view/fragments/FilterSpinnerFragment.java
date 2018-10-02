package com.example.pan.assignment1.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pan.assignment1.R;

public class FilterSpinnerFragment extends Fragment{
    OnSpinnerSelectedListener mCallback;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.filter_spinner,container,false);
       return v;
    }

    public interface OnSpinnerSelectedListener{
        public void onItemSelected(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            mCallback = (OnSpinnerSelectedListener)getActivity();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
