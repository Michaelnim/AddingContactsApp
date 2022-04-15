package com.example.assignment7;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewContact extends AppCompatActivity {

    ListView listView;
    Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        DatabaseHelper db = new DatabaseHelper(ViewContact.this);

        ArrayList<String> contacts = new ArrayList<>();
        listView = findViewById(R.id.listView);
        delete = (Button)findViewById(R.id.delete);
        delete.setOnClickListener(v -> {
            db.deleteData();
            Toast.makeText(ViewContact.this, "Data Deleted Successfully", Toast.LENGTH_LONG).show();
            Intent myIntent = new Intent(ViewContact.this, MainActivity.class);
            ViewContact.this.startActivity(myIntent);
        });

        //Get All Contacts
        List<Contact> allContacts = db.getAllContacts();
        for (Contact contact: allContacts){
            contacts.add("\nName: " + contact.getName() + "\n\nPhone: " + contact.getNumber() + "\n\ne-mail: " + contact.getEmail() + "\n");
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);
        listView.setAdapter(arrayAdapter);
    }
}