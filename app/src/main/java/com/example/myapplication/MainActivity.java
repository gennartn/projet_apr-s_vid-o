package com.example.myapplication;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
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
    Button btnviewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        editprix = (EditText) findViewById(R.id.editText);
        editmagasin = (EditText) findViewById(R.id.editText2);
        editcathegorie = (EditText) findViewById(R.id.editText3);
        btnAddData = (Button) findViewById(R.id.button);
        btnviewAll = (Button)findViewById(R.id.button2);

        addData();
        viewAll();
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
    public void viewAll(){
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res=myDb.getAllData();
                        if(res.getCount()==0){
                            showMessage("Error", "Nothing False");
                            return;
                        }
                        else{
                            StringBuffer buffer = new StringBuffer();
                            while(res.moveToNext()){
                                buffer.append("Nom :"+ res.getString(0)+ "\n");
                                buffer.append("Prix :"+ res.getString(1)+ "\n");
                                buffer.append("Magasin :"+ res.getString(2)+ "\n");
                                buffer.append("cathégorie :"+ res.getString(3)+ "\n\n");
                            }
                            showMessage("Data", buffer.toString());
                        }
                    }
                }
        );
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true); //cancel after it's use
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
