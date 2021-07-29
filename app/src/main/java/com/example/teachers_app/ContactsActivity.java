package com.example.teachers_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Phone> people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        recyclerView = findViewById(R.id.contacts_list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        people = new ArrayList<Phone>();
        people.add(new Phone("Abhilasha Mishra","CSE","1st Year","9867042610"));
        people.add(new Phone("Sukanya Senapati","CSE","2nd Year","9084561287"));
        people.add(new Phone("Biswaranjan Nayak","CSE","3rd Year","7689034512"));
        people.add(new Phone("Ritupurna Sethi","CSE","4th Year","7656483902"));
        people.add(new Phone("Sasmita Nayak","IT","1st Year","7656432190"));
        people.add(new Phone("Tanmaya Mishra","IT","2nd Year","7698034541"));
        people.add(new Phone("Dhananjaya Jena","IT","3rd Year","9807654320"));
        people.add(new Phone("Jenamani Barik","IT","4th Year","9875401763"));
        people.add(new Phone("Biswajit Panda","Mechanical","1st Year","7656432190"));
        people.add(new Phone("Ananya Parida","Mechanical","2nd Year","9876408262"));
        people.add(new Phone("Trisha Sabat","Mechanical","3rd Year","7657654972"));
        people.add(new Phone("Rahul Mishra","Mechanical","4th Year","8098576310"));
        people.add(new Phone("Manohar Mohanty","Electrical","1st Year","7205675091"));
        people.add(new Phone("Parineeti Chopra","Electrical","2nd Year","6780934210"));
        people.add(new Phone("Anamika Chauhan","Electrical","3rd Year","7890654301"));
        people.add(new Phone("Raj Kumar","Electrical","4th Year","6709834510"));
        people.add(new Phone("Tanya Bharti","IEE","1st Year","8085768910"));
        people.add(new Phone("Alok Mohanty","IEE","2nd Year","7689045612"));
        people.add(new Phone("Samarjit Banik","IEE","3rd Year","9807854310"));
        people.add(new Phone("Abhisek Nayak","IEE","4th Year","6780934510"));

        adapter = new ContactsAdapter(this,people);
        recyclerView.setAdapter(adapter);

    }
}