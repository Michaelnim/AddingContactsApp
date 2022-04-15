package com.example.assignment7;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContact extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText editTextName, editTextNumber, editTextEmail;
    Button addData, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        mydb = new DatabaseHelper(this);
        editTextName = (EditText)findViewById(R.id.editTextName);
        editTextNumber = (EditText)findViewById(R.id.editTextNumber);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        addData = (Button)findViewById(R.id.addData);
        cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(v -> {
            Intent myIntent = new Intent(AddContact.this, MainActivity.class);
            AddContact.this.startActivity(myIntent);
        });
        AddData();
    }

    private void AddData() {
        addData.setOnClickListener(
                v -> {
                    boolean isInserted = mydb.insertData(editTextName.getText().toString(),
                            editTextNumber.getText().toString(),
                            editTextEmail.getText().toString());
                    if (isInserted)
                        Toast.makeText(AddContact.this, "Data Inserted Successfully", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(AddContact.this, "Failed to Insert the Data", Toast.LENGTH_LONG).show();
                }
        );
    }
}