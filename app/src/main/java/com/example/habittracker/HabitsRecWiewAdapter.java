package com.example.habittracker;

import android.content.Context;

import android.graphics.Color;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;


public class HabitsRecWiewAdapter extends RecyclerView.Adapter<HabitsRecWiewAdapter.ViewHolder> {

    private ArrayList<Habit> habits = new ArrayList<>();
    private Context context;
    private DataBaseHelper dataBaseHelper;
    private RecyclerViewClickInterface recyclerViewClickInterface;

    public HabitsRecWiewAdapter (RecyclerViewClickInterface recyclerViewClickInterface){
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_habit, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.txtHabitName.setText(habits.get(position).getName());
        //context = MainActivity.this;
        dataBaseHelper = new DataBaseHelper(context);

        holder.btnDelete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dataBaseHelper.deleteOne(habits.get(position).getId());
                Toast.makeText(context , "Deleted: " +  habits.get(position).getName(), Toast.LENGTH_SHORT).show();
                recyclerViewClickInterface.onLongItemClick(position);
                return true;
            }
        });


        holder.btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBaseHelper.updateCounter(habits.get(position).getId(), 0, habits.get(position).getRed()+1);
                recyclerViewClickInterface.onItemClick(position);
            }
        });

        holder.btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBaseHelper.updateCounter(habits.get(position).getId(), 1, habits.get(position).getBlue()+1);
                recyclerViewClickInterface.onItemClick(position);
            }
        });

        holder.btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBaseHelper.updateCounter(habits.get(position).getId(), 2, habits.get(position).getGreen()+1);
                recyclerViewClickInterface.onItemClick(position);
            }
        });

        //Chart
        ArrayList<PieEntry> habitsPercentage = new ArrayList<>();

        habitsPercentage.add(new PieEntry(habits.get(position).getRed()));
        habitsPercentage.add(new PieEntry(habits.get(position).getBlue()));
        habitsPercentage.add(new PieEntry(habits.get(position).getGreen()));

        PieDataSet pieDataSet = new PieDataSet(habitsPercentage, "bar");
        int red = Color.rgb(255, 51, 0);
        int blue = Color.rgb(0, 157, 255);
        int green = Color.rgb(0, 255, 38);
        pieDataSet.setColors(red, blue, green);

        PieData pieData = new PieData(pieDataSet);
        holder.pieChart.setData(pieData);


        holder.pieChart.setCenterTextColor(blue);
        holder.pieChart.setEntryLabelColor(blue);
        holder.pieChart.setHoleRadius(0);
        holder.pieChart.setTransparentCircleRadius(10);
        holder.pieChart.setTransparentCircleRadius(Color.WHITE);

        holder.pieChart.getDescription().setEnabled(false);
        Legend l = holder.pieChart.getLegend();
        l.setEnabled(false);
        //holder.pieChart.setData(pieData);
    }

    @Override
    public int getItemCount() {
        return habits.size();
    }

    public void setHabits(ArrayList<Habit> habits) {
        this.habits = habits;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private TextView txtHabitName;
        private Button btnRed, btnBlue, btnGreen, btnDelete;
        //Chart
        private PieChart pieChart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            txtHabitName = itemView.findViewById(R.id.txtHabitName);
            btnRed = itemView.findViewById(R.id.btnRed);
            btnBlue = itemView.findViewById(R.id.btnBlue);
            btnGreen = itemView.findViewById(R.id.btnGreen);
            parent = itemView.findViewById(R.id.parent);
            //TODO context bug sucks, when using databasehelper update method, when getwritabledatabase is enabled
            context = btnGreen.getContext();
            //Chart
            pieChart = itemView.findViewById(R.id.pieChart);
        }
    }
}