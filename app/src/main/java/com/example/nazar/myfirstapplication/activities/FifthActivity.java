package com.example.nazar.myfirstapplication.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nazar.myfirstapplication.AppService;
import com.example.nazar.myfirstapplication.entities.Attempt;
import com.example.nazar.myfirstapplication.R;
import com.example.nazar.myfirstapplication.entities.Temperament;
import com.example.nazar.myfirstapplication.tabs.Tab3;
import com.example.nazar.myfirstapplication.tabs.TabbedActivity;

public class FifthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

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
                if(i!=0) {
                    String chosedTemperament  = mySpinner.getSelectedItem().toString();
                    if(chosedTemperament.equals(AppService.getInstance().getDbHandler().getTemperamentbyId(activeid))) {
                        Handler handler = new Handler();
                        if(Attempt.getInstance().getResult()==3) {
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    startActivity(new Intent(FifthActivity.this, SixthActivity.class));
                                }
                            }, 500);
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    createResultDialog();
                                }
                            }, 6000);
                        } else {
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    createResultDialog();
                                }
                            }, 500);
                        }
                    }
                    else {
                        Toast.makeText(FifthActivity.this,
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

    private void createInstructionDialog() {
        AlertDialog.Builder PreBuilder = new AlertDialog.Builder(FifthActivity.this);
        PreBuilder.setIcon(android.R.drawable.btn_star_big_on);
        PreBuilder.setTitle(R.string.dialogWelcomeTitle);
        PreBuilder.setMessage("Choose a correct temperament of a chosen person");
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

    private void createResultDialog() {
        AlertDialog.Builder myBuilder = new AlertDialog.Builder(FifthActivity.this);
        myBuilder.setIcon(android.R.drawable.btn_default);
        myBuilder.setTitle(R.string.resultDialogMessage);
        myBuilder.setCancelable(false);
        if (Attempt.getInstance().getResult() == 3) {
            myBuilder.setMessage("Congratulations! Your result is " + 300/Attempt.getInstance().getResult() + "%");
            myBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(FifthActivity.this, TabbedActivity.class));
                }
            });
            AlertDialog alertdialog = myBuilder.create();
            alertdialog.show();
        } else {
            myBuilder.setMessage("Your result is " + 300 / Attempt.getInstance().getResult() + "%");
            myBuilder.setNegativeButton("Try Again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(FifthActivity.this, TabbedActivity.class));
                }
            });
            AlertDialog alertdialog = myBuilder.create();
            alertdialog.show();
        }
    }

    private Spinner createSpinner() {
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner3);

        String[] names = createStringArrayForSpinner();

        ArrayAdapter<String> adapterForSpinner = new ArrayAdapter<>(this, R.layout.custom_spinner_item, names);

        mySpinner.setAdapter(adapterForSpinner);

        return mySpinner;
    }

    private String[] createStringArrayForSpinner() {
        String[] name = Temperament.names();
        String[] names = new String[name.length+1];
        names[0]="Please Select Item";
        System.arraycopy(name, 0, names, 1, name.length);
        return names;
    }

    private void addToolbar() {
        Toolbar Toolbar5 = (Toolbar) findViewById(R.id.toolbar5);
        setSupportActionBar(Toolbar5);
        int id = AppService.getInstance().getActiveId();
        String selectedName = AppService.getInstance().getDbHandler().getNamebyId(id);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(selectedName);
        }
    }
}
