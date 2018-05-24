package com.example.nazar.myfirstapplication.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nazar.myfirstapplication.R;

/**
 * Created by nazar on 07.06.2017.
 */

public class Tab1 extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.tab1, container, false);
    }

}
