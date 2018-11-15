package com.example.lenovo.helloworld.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.helloworld.R;

/**
 * Created by lenovo on 2018/10/9.
 */
public class ThreeFragment extends Fragment {
    public ThreeFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.three_layout,container,false);
    }
}
