package com.example.nazar.myfirstapplication.tabs;

import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nazar.myfirstapplication.R;

import static android.R.attr.tag;

/**
 * Created by nazar on 15.06.2017.
 */

public class DatabaseDialog extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_editable, null);
    }

}
