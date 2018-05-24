package com.example.nazar.myfirstapplication.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nazar.myfirstapplication.AppService;
import com.example.nazar.myfirstapplication.R;
import com.example.nazar.myfirstapplication.activities.editableActivity;

/**
 * Created by nazar on 07.06.2017.
 */

public class Tab2 extends Fragment {

    TextView textView;
    FloatingActionButton fab;

     @Override
    public void onStart() {
        printDatabase();
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View myFragmentView = inflater.inflate(R.layout.tab2, container, false);

            textView = (TextView) myFragmentView.findViewById(R.id.databaseTextView);

            //printDatabase();

            fab = (FloatingActionButton) myFragmentView.findViewById(R.id.fab);

            addListenerAddButton();

            return myFragmentView;
    }



    private void printDatabase() {
        String dbString = AppService.getInstance().getDbHandler().databaseToString();
        textView.setText(dbString);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }

    private void addListenerAddButton() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), editableActivity.class));
            }
        });
    }

}
