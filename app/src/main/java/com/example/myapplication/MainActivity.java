package com.example.myapplication;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editprix, editmagasin, editcathegorie;
    Button btnAddData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        editprix = (EditText) findViewById(R.id.editText);
        editmagasin = (EditText) findViewById(R.id.editText2);
        editcathegorie = (EditText) findViewById(R.id.editText3);
        btnAddData = (Button) findViewById(R.id.button);
        addData();
    }
    public void addData() {
        btnAddData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(editprix.getText().toString(),
                        editmagasin.getText().toString(),
                        editcathegorie.getText().toString());
                if(isInserted == true){
                    Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
