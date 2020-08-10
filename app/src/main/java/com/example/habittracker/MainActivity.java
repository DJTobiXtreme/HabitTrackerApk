package com.example.habittracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.AdapterListUpdateCallback;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickInterface{

    private RecyclerView habitsRecView;
    private HabitsRecWiewAdapter adapter;
    private Button btnAddHabit, btnDeleteHabit;

    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new HabitsRecWiewAdapter(this);
        habitsRecView = findViewById(R.id.habitsRecView);

        habitsRecView.setAdapter(adapter);
        habitsRecView.setLayoutManager(new LinearLayoutManager(this));

        dataBaseHelper = new DataBaseHelper(MainActivity.this);
        refreshList();

        /// Shows the user add habit acivity only, doesnt send data
        btnAddHabit = findViewById(R.id.btnAddHabit);
        btnAddHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddHabitActivity.class);
                startActivity(intent);
            }
        });


// TODO implementar two day rule
// TODO implementar barra de vida
// TODO fix graph show only integers numbers


    }

    public void refreshList() {
        List<Habit> toConvert = dataBaseHelper.getEveryone();
        ArrayList<Habit> conversionList = new ArrayList<>(toConvert);
        adapter.setHabits(conversionList);
    }

    @Override
    public void onItemClick(int position) {
        refreshList();
    }

    @Override
    public void onLongItemClick(int position) {
        refreshList();
    }
}