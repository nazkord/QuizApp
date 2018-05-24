package com.example.nazar.myfirstapplication.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nazar.myfirstapplication.AppService;
import com.example.nazar.myfirstapplication.R;
import com.example.nazar.myfirstapplication.tabs.Tab2;
import com.example.nazar.myfirstapplication.tabs.TabbedActivity;

public class editableActivity extends AppCompatActivity {

    EditText input_name;
    EditText input_age;
    EditText input_coloreye;
    EditText input_temperament;
    Button addButton;
    Button deleteButton;
    Button returnButton;
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editable);

        addToolbar();

        input_name = (EditText) findViewById(R.id.input_name);
        input_age = (EditText) findViewById(R.id.input_age);
        input_coloreye = (EditText) findViewById(R.id.input_coloreye);
        input_temperament = (EditText) findViewById(R.id.input_temperament);

        addButton = (Button) findViewById(R.id.addButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        fab = (FloatingActionButton) findViewById(R.id.fab2);
        fabClicked();
    }

    private void fabClicked() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(editableActivity.this, TabbedActivity.class));
            }
        });
    }

    public void addButtonClicked(View view) {
        String name = input_name.getText().toString();
        String age = input_age.getText().toString();
        String coloreye = input_coloreye.getText().toString();
        String temperament = input_temperament.getText().toString();

        if(!name.matches("") && !age.isEmpty() && !coloreye.matches("") && !temperament.matches("")) {
            AppService.getInstance().getDbHandler().addPerson(name,age,coloreye,temperament);
            input_name.setText("");
            input_age.setText("");
            input_coloreye.setText("");
            input_temperament.setText("");
        } else {
            createAddErrorDialog();
        }
    }

    public void deleteButtonClicked(View view) {
        String name = input_name.getText().toString();
        if(!name.matches("")) {
            AppService.getInstance().getDbHandler().deletePerson(name);
            input_name.setText("");
        } else {
            createDeleteErrorDialog();
        }
    }

    private void createAddErrorDialog() {
        AlertDialog.Builder PreBuilder = new AlertDialog.Builder(editableActivity.this);
        PreBuilder.setIcon(android.R.drawable.alert_dark_frame);
        PreBuilder.setTitle("Error");
        PreBuilder.setMessage("You have at least one blank line");
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

    private void createDeleteErrorDialog() {
        AlertDialog.Builder PreBuilder = new AlertDialog.Builder(editableActivity.this);
        PreBuilder.setIcon(android.R.drawable.alert_dark_frame);
        PreBuilder.setTitle("Error");
        PreBuilder.setMessage("You didn't type the person name");
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
        Toolbar Toolbar8 = (Toolbar) findViewById(R.id.toolbar8);
        setSupportActionBar(Toolbar8);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.changeDatabaseText));
        }
    }

}
