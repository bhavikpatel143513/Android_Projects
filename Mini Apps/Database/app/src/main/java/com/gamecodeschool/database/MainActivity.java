package com.gamecodeschool.database;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnInsert;
    Button btnDelete;
    Button btnSelect;
    Button btnSearch;
    EditText editName;
    EditText editAge;
    EditText editDelete;
    EditText editSearch;
    DataManager dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dm = new DataManager(this);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnSelect = (Button) findViewById(R.id.btnSelect);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        editName = (EditText) findViewById(R.id.editName);
        editAge = (EditText) findViewById(R.id.editAge);
        editDelete = (EditText) findViewById(R.id.editDelete);
        editSearch = (EditText) findViewById(R.id.editSearch);
        // Register MainActivity as a listener
        btnSelect.setOnClickListener(this);
        btnInsert.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
    }

    public void showData(Cursor c){
        while (c.moveToNext()){
            Toast.makeText(this,"" + c.getString(1) + " , " + c.getString(2),Toast.LENGTH_LONG).show();
            Log.i(c.getString(1), c.getString(2));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnInsert:
                dm.insert(editName.getText().toString(),editAge.getText().toString());
                break;
            case R.id.btnSelect:
                showData(dm.selectAll());
                break;
            case R.id.btnSearch:
                showData(dm.searchName(editSearch.getText().toString()));
                break;
            case R.id.btnDelete:
                dm.delete(editDelete.getText().toString());
                break;
        }

    }
}
