package com.example.nazar.myfirstapplication.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nazar.myfirstapplication.AppService;
import com.example.nazar.myfirstapplication.entities.Attempt;
import com.example.nazar.myfirstapplication.entities.Person;
//import com.example.nazar.myfirstapplication.PersonStorage;
import com.example.nazar.myfirstapplication.R;
import com.example.nazar.myfirstapplication.db.DbHandler;


public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Attempt.getInstance().setResult(3);

        addToolbar();

        createInstructionDialog();

        mainCheckButtonOfEditText();

    }

    private void mainCheckButtonOfEditText() {

        final EditText myAgeText = (EditText) findViewById(R.id.editTextAge);
        Button myCheckButton = (Button) findViewById(R.id.buttonCheck);


        myCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myAgeText.getText().toString().isEmpty()) {
                    Toast.makeText(ThirdActivity.this,
                            "Write an age", Toast.LENGTH_SHORT).show();
            } else {
                    int typedAge = Integer.parseInt(myAgeText.getText().toString());

                    int activeId = AppService.getInstance().getActiveId();

                    if (typedAge == AppService.getInstance().getDbHandler().getAgebyId(activeId)) {
                        createGoForwardMessage();
                    } else {
                        createMistakeDialog();
                        Attempt.getInstance().mistake();
                    }
                }
            }
        });
    }

    private void createGoForwardMessage() {
        Toast.makeText(ThirdActivity.this, "You're right! Go ahead", Toast.LENGTH_SHORT).show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(ThirdActivity.this, FourthActivity.class));
            }
        }, 2000);
    }

    private void createMistakeDialog() {
        AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
        myBuilder.setIcon(android.R.drawable.btn_dialog);
        myBuilder.setTitle(R.string.dialogMistakeTitle);
        myBuilder.setMessage(R.string.dialogMistakeMessage);
        myBuilder.setCancelable(false);
        myBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = myBuilder.create();
        alertDialog.show();
    }

    private void createInstructionDialog() {
        AlertDialog.Builder PreBuilder = new AlertDialog.Builder(ThirdActivity.this);
        PreBuilder.setIcon(android.R.drawable.btn_star_big_on);
        PreBuilder.setTitle(R.string.dialogWelcomeTitle);
        PreBuilder.setMessage("Write a correct age of a chosen person");
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
        Toolbar Toolbar3 = (Toolbar) findViewById(R.id.toolbar3);
        int id = AppService.getInstance().getActiveId();
        String selectedName = AppService.getInstance().getDbHandler().getNamebyId(id);
        setSupportActionBar(Toolbar3);
        if ( getSupportActionBar() != null) {
            getSupportActionBar().setTitle(selectedName);
        }
    }

}
