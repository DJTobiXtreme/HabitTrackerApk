package com.example.habittracker;

import android.os.Parcel;
import android.os.Parcelable;

public class Habit implements Parcelable {
    private int id;
    private String name;
    private int green;
    private int blue;
    private int red;

    public Habit(int id, String name, int red, int blue, int green) {
        this.id = id;
        this.name = name;
        this.red = red;
        this.blue = blue;
        this.green = green;
    }

    protected Habit(Parcel in) {
        id = in.readInt();
        name = in.readString();
        green = in.readInt();
        blue = in.readInt();
        red = in.readInt();
    }

    public static final Creator<Habit> CREATOR = new Creator<Habit>() {
        @Override
        public Habit createFromParcel(Parcel in) {
            return new Habit(in);
        }

        @Override
        public Habit[] newArray(int size) {
            return new Habit[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Habit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", green=" + green +
                ", blue=" + blue +
                ", yellow=" + red +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(green);
        parcel.writeInt(blue);
        parcel.writeInt(red);
    }
}
