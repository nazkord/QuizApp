package com.example.nazar.myfirstapplication.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nazar.myfirstapplication.AppService;
import com.example.nazar.myfirstapplication.entities.Attempt;
import com.example.nazar.myfirstapplication.entities.EyeColor;
import com.example.nazar.myfirstapplication.entities.Person;
//import com.example.nazar.myfirstapplication.PersonStorage;
import com.example.nazar.myfirstapplication.R;

public class FourthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        addToolbar();

        createInstructionDialog();

        Spinner spinner = createSpinner();

        addListenerToSpinner(spinner);
    }

    private void addListenerToSpinner(final Spinner mySpinner) {
        final int activeid = AppService.getInstance().getActiveId();
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    String chosedColorEye = mySpinner.getSelectedItem().toString();
                    if (chosedColorEye.equals(AppService.getInstance().getDbHandler().getColorEyebyId(activeid))) {
                        Toast.makeText(FourthActivity.this,
                                "You're right! Go ahead", Toast.LENGTH_SHORT).show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                startActivity(new Intent(FourthActivity.this, FifthActivity.class));
                            }
                        }, 2000);
                    } else {
                        Toast.makeText(FourthActivity.this,
                                "Try one more time", Toast.LENGTH_SHORT).show();
                        Attempt.getInstance().mistake();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private Spinner createSpinner() {
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner2);

        String[] names = createStringArrayForSpinner();

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this, R.layout.custom_spinner_item, names);

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);
        return mySpinner;
    }

    @NonNull
    private String[] createStringArrayForSpinner() {
        String[] name = EyeColor.names();
        String[] names = new String[name.length + 1];
        names[0] = "Please Select Item";
        System.arraycopy(name, 0, names, 1, name.length);
        return names;
    }

    private void createInstructionDialog() {
        AlertDialog.Builder PreBuilder = new AlertDialog.Builder(FourthActivity.this);
        PreBuilder.setIcon(android.R.drawable.btn_star_big_on);
        PreBuilder.setTitle(R.string.dialogWelcomeTitle);
        PreBuilder.setMessage("Choose a correct eye-color of a chosen person");
        PreBuilder.setCancelable(false);
        PreBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = PreBuilder.create();
        alertDialog.show();
    }

    private void addToolbar() {
        Toolbar Toolbar4 = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(Toolbar4);
        int id = AppService.getInstance().getActiveId();
        String selectedName = AppService.getInstance().getDbHandler().getNamebyId(id);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(selectedName);
        }
    }
}
