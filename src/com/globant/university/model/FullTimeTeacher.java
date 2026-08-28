package com.globant.university.model;

public class FullTimeTeacher extends Teacher {
    private final int yearsOfExperience;

    public FullTimeTeacher(String name, int baseSalary, int yearsOfExperience) {
        super(name, baseSalary);
        this.yearsOfExperience = yearsOfExperience;
        calculateSalary();
    }

    @Override
    public double calculateSalary() {
        double salary = getBaseSalary() + (yearsOfExperience * 1.10);
        setSalary(salary);
        return salary;
    }
}
