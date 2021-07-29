package com.example.teachers_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    CardView pdfCard, docStorageCard, reminderCard, calcCard, contactsCard, tdlCard, scheduleCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pdfCard = findViewById(R.id.pdfCard);
        docStorageCard = findViewById(R.id.storageCard);
        reminderCard = findViewById(R.id.reminderCard);
        calcCard = findViewById(R.id.calcCard);
        contactsCard = findViewById(R.id.pocCard);
        tdlCard = findViewById(R.id.tdlCard);
        scheduleCard = findViewById(R.id.scheduleCard);

        pdfCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pdfCard.setCardBackgroundColor(getResources().getColor(R.color.teal));
                Intent intent = new Intent(HomeActivity.this,pdfActivity.class);
                startActivity(intent);
            }
        });

        docStorageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                docStorageCard.setCardBackgroundColor(getResources().getColor(R.color.teal));
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/"));
                startActivity(intent);
            }
        });

        reminderCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reminderCard.setCardBackgroundColor(getResources().getColor(R.color.teal));
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://calendar.google.com/"));
                startActivity(intent);
            }
        });

        calcCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcCard.setCardBackgroundColor(getResources().getColor(R.color.teal));
                Intent intent = new Intent(HomeActivity.this,CalcActivity.class);
                startActivity(intent);
            }
        });

        contactsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactsCard.setCardBackgroundColor(getResources().getColor(R.color.teal));
                Intent intent = new Intent(HomeActivity.this,ContactsActivity.class);
                startActivity(intent);
            }
        });

        tdlCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tdlCard.setCardBackgroundColor(getResources().getColor(R.color.teal));
                Intent intent = new Intent(HomeActivity.this,ToDoListActivity.class);
                startActivity(intent);
            }
        });

        scheduleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheduleCard.setCardBackgroundColor(getResources().getColor(R.color.teal));
                Intent intent = new Intent(HomeActivity.this,TimeTableActivity.class);
                startActivity(intent);
            }
        });
    }
}