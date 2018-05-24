package com.example.nazar.myfirstapplication.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nazar.myfirstapplication.R;
import com.example.nazar.myfirstapplication.activities.FirstActivity;

/**
 * Created by nazar on 07.06.2017.
 */

public class Tab3 extends Fragment{

    Button goForwardButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.tab3, container, false);

        goForwardButton = (Button) fragmentView.findViewById(R.id.buttonForward);
        addListenerToButtonForward();

        return fragmentView;
    }

    private void addListenerToButtonForward() {
        goForwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), FirstActivity.class));
            }
        });
    }
}
