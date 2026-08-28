package com.globant.university.model;

public class PartTimeTeacher extends Teacher {
    private final int hoursWorked;

    public PartTimeTeacher(String name, int baseSalary, int hoursWorked) {
        super(name, baseSalary);
        this.hoursWorked = hoursWorked;
        calculateSalary();
    }

    @Override
    public double calculateSalary() {
        double salary = getBaseSalary() * hoursWorked;
        setSalary(salary);
        return salary;
    }
}
