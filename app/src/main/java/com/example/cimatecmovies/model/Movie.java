package com.example.cimatecmovies.model;

public class Movie {
    public Movie(String name, int year) {
        Name = name;
        Year = year;
    }

    public Movie() {
    }

    private String Name;
    private int Year;

    @Override
    public String toString() {
        return "\t"+Name + " (" + Year + ")\n";
    }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public int getYear() {
        return Year;
    }
    public void setYear(int year) {
        Year = year;
    }
}
