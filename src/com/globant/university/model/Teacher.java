package com.globant.university.model;

public abstract class Teacher {
    private final String name;
    private final int baseSalary;
    private double salary;

    public Teacher(String name, int baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
        this.salary = 0.0;
    }

    public String getName() {
        return name;
    }

    public int getBaseSalary() {
        return baseSalary;
    }

    public double getSalary() {
        return salary;
    }

    protected void setSalary(double salary) {
        this.salary = salary;
    }

    public abstract double calculateSalary();
}
