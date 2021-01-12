package com.example.habittracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddHabitActivity extends AppCompatActivity {

    private TextView edtTxtHabitName;
    private Button btnAddHabit;
    private int idCounter;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        dataBaseHelper = new DataBaseHelper(this);

        //TODO fix id counter
        idCounter = 0;
        btnAddHabit = findViewById(R.id.btnAddHabit);
        edtTxtHabitName = findViewById(R.id.edtTxtHabitName);
        btnAddHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idCounter++;
                Intent intent = new Intent(AddHabitActivity.this, MainActivity.class);
                Habit habit = new Habit(0, edtTxtHabitName.getText().toString(), 0, 0, 0, 0);
                dataBaseHelper.addOne(habit);
                startActivity(intent);
            }
        });
    }
}