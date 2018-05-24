package com.example.nazar.myfirstapplication.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nazar.myfirstapplication.AppService;
import com.example.nazar.myfirstapplication.R;
import com.example.nazar.myfirstapplication.db.DbHandler;

import static com.example.nazar.myfirstapplication.R.id.myListView;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        addToolbar();

        createInstructionDialog();

        ListView mListView = createListView();

        addListenerToListView(mListView);

    }

    private void createInstructionDialog() {
        AlertDialog.Builder PreBuilder = new AlertDialog.Builder(FirstActivity.this);
        PreBuilder.setIcon(android.R.drawable.btn_star_big_on);
        PreBuilder.setTitle(R.string.dialogWelcomeTitle);
        PreBuilder.setMessage("Choose a person");
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
        Toolbar Toolbar1 = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(Toolbar1);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void addListenerToListView(final ListView mListView) {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(FirstActivity.this, ThirdActivity.class);
                String selectedName = (mListView.getItemAtPosition(i).toString());
                findPersonByName(selectedName);
                startActivity(intent);
            }
        });
    }

    private void findPersonByName(String name) {
        int id = AppService.getInstance().getDbHandler().findIdByName(name);
        AppService.getInstance().setActiveId(id);
    }

    @NonNull
    private ListView createListView() {
        ListView mListView = (ListView) findViewById(myListView);
        String[] names = AppService.getInstance().getDbHandler().columnToString(DbHandler.COL_PERSON_NAME);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, R.layout.listview_row, R.id.listview_row_item, names);
        mListView.setAdapter(myAdapter);
        return mListView;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}
