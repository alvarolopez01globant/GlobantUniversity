package com.globant.university.app;

import com.globant.university.model.FullTimeTeacher;
import com.globant.university.model.PartTimeTeacher;
import com.globant.university.model.SchoolClass;
import com.globant.university.model.Student;
import com.globant.university.model.Teacher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final String MENU_BORDER = "========================================";
    private static final String SECTION_BORDER = "----------------------------------------";

    public static void main(String[] args) {
        PartTimeTeacher andresTeacher = new PartTimeTeacher("Andres", 20, 30);
        PartTimeTeacher carlosTeacher = new PartTimeTeacher("Carlos", 15, 25);
        FullTimeTeacher juanTeacher = new FullTimeTeacher("Juan", 30, 40);
        FullTimeTeacher mariaTeacher = new FullTimeTeacher("Maria", 25, 35);
        List<Teacher> teachers = Arrays.asList(andresTeacher, carlosTeacher, juanTeacher, mariaTeacher);

        Student luisStudent = new Student("Luis", 20, 1);
        Student anaStudent = new Student("Ana", 22, 2);
        Student pedroStudent = new Student("Pedro", 21, 3);
        Student sofiaStudent = new Student("Sofia", 23, 4);
        Student carlaStudent = new Student("Carla", 20, 5);
        Student diegoStudent = new Student("Diego", 22, 6);
        List<Student> allStudents = Arrays.asList(luisStudent, anaStudent, pedroStudent, sofiaStudent, carlaStudent, diegoStudent);

        SchoolClass mathClass = new SchoolClass("Math", juanTeacher);
        mathClass.addStudent(luisStudent);
        mathClass.addStudent(anaStudent);

        SchoolClass scienceClass = new SchoolClass("Science", mariaTeacher);
        scienceClass.addStudent(pedroStudent);
        scienceClass.addStudent(sofiaStudent);

        SchoolClass historyClass = new SchoolClass("History", andresTeacher);
        historyClass.addStudent(carlaStudent);
        historyClass.addStudent(diegoStudent);

        SchoolClass englishClass = new SchoolClass("English", carlosTeacher);
        englishClass.addStudent(luisStudent);
        englishClass.addStudent(anaStudent);

        List<SchoolClass> schoolClasses = new ArrayList<>(Arrays.asList(mathClass, scienceClass, historyClass, englishClass));

        while (true) {
            printMenu();
            int menuOption = readInt("Choose an option: ");

            switch (menuOption) {
                case 1:
                    printTeachers(teachers);
                    break;
                case 2:
                    printClassesWithDetails(schoolClasses);
                    break;
                case 3:
                    createStudent(INPUT, schoolClasses);
                    break;
                case 4:
                    createSchoolClass(INPUT, teachers, allStudents, schoolClasses);
                    break;
                case 5:
                    printClassesForStudent(INPUT, schoolClasses);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println(MENU_BORDER);
        System.out.println("      Globant University - Main Menu");
        System.out.println(MENU_BORDER);
        System.out.println(" 1) View all teachers");
        System.out.println(" 2) View classes and details");
        System.out.println(" 3) Add a new student to a class");
        System.out.println(" 4) Create a new class");
        System.out.println(" 5) Search classes by student ID");
        System.out.println(" 6) Exit");
        System.out.println(MENU_BORDER);
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (INPUT.hasNextInt()) {
                return INPUT.nextInt();
            }
            System.out.println("  Invalid input. Please enter a number.");
            INPUT.next();
        }
    }

    private static void printTeachers(List<Teacher> teachers) {
        System.out.println();
        System.out.println(SECTION_BORDER);
        System.out.println("Teachers");
        System.out.println(SECTION_BORDER);
        for (Teacher teacher : teachers) {
            System.out.println("• Teacher: " + teacher.getName() + " | Base salary: " + teacher.getBaseSalary() + " | Final salary: " + teacher.getSalary());
        }
    }

    private static void printClassesWithDetails(List<SchoolClass> schoolClasses) {
        System.out.println();
        System.out.println(SECTION_BORDER);
        System.out.println("Classes");
        System.out.println(SECTION_BORDER);
        for (int index = 0; index < schoolClasses.size(); index++) {
            System.out.println(" " + (index + 1) + ") " + schoolClasses.get(index).getName());
        }

        int selectedClassIndex = readInt("Select a class number: ") - 1;
        if (selectedClassIndex >= 0 && selectedClassIndex < schoolClasses.size()) {
            SchoolClass selectedClass = schoolClasses.get(selectedClassIndex);
            System.out.println();
            System.out.println("You selected: " + selectedClass.getName());
            System.out.println("Assigned teacher: " + selectedClass.getTeacher().getName() + " | Salary: " + selectedClass.getTeacher().getSalary());
            System.out.println("Enrolled students:");
            for (Student student : selectedClass.getStudents()) {
                System.out.println("   • " + student.getName() + " | Age: " + student.getAge() + " | ID: " + student.getId());
            }
        } else {
            System.out.println("Invalid class selection.");
        }
    }

    public static void createStudent(Scanner input, List<SchoolClass> schoolClasses) {
        System.out.println();
        System.out.println(SECTION_BORDER);
        System.out.println("Create student");
        System.out.println(SECTION_BORDER);

        System.out.print("Enter student name: ");
        String name = input.next();
        int age = readInt("Enter student age: ");
        int id = readInt("Enter student id: ");

        Student newStudent = new Student(name, age, id);
        System.out.println("Student created: " + newStudent.getName() + " | Age: " + newStudent.getAge() + " | ID: " + newStudent.getId());

        System.out.println("Select a class to add the student to:");
        for (int index = 0; index < schoolClasses.size(); index++) {
            System.out.println(" " + (index + 1) + ") " + schoolClasses.get(index).getName());
        }

        int selectedClassIndex = readInt("Select a class to add the student to: ") - 1;
        if (selectedClassIndex >= 0 && selectedClassIndex < schoolClasses.size()) {
            SchoolClass selectedClass = schoolClasses.get(selectedClassIndex);
            selectedClass.addStudent(newStudent);
            System.out.println("Student \"" + newStudent.getName() + "\" added to class \"" + selectedClass.getName() + "\".");
        } else {
            System.out.println("Invalid class selection.");
        }
    }

    public static void createSchoolClass(Scanner input, List<Teacher> teachers, List<Student> students, List<SchoolClass> schoolClasses) {
        System.out.println();
        System.out.println(SECTION_BORDER);
        System.out.println("Create class");
        System.out.println(SECTION_BORDER);

        System.out.print("Enter class name: ");
        String className = input.next();

        System.out.println("Select a teacher for the class:");
        for (int index = 0; index < teachers.size(); index++) {
            System.out.println(" " + (index + 1) + ") " + teachers.get(index).getName());
        }

        int teacherIndex = readInt("Select a teacher for the class: ") - 1;
        if (teacherIndex < 0 || teacherIndex >= teachers.size()) {
            System.out.println("Invalid teacher selection.");
            return;
        }
        Teacher selectedTeacher = teachers.get(teacherIndex);
        System.out.println("Teacher selected: " + selectedTeacher.getName());

        System.out.println("Select students for the class (enter IDs separated by commas):");
        for (Student student : students) {
            System.out.println(" " + student.getId() + ") " + student.getName());
        }

        String studentInput = input.next();
        String[] studentIds = studentInput.split(",");
        List<Student> selectedStudents = new ArrayList<>();
        for (String studentIdText : studentIds) {
            if (!studentIdText.trim().matches("\\d+")) {
                System.out.println("Invalid student ID: " + studentIdText.trim());
                continue;
            }
            int studentId = Integer.parseInt(studentIdText.trim());
            for (Student student : students) {
                if (student.getId() == studentId) {
                    selectedStudents.add(student);
                    break;
                }
            }
        }

        SchoolClass newClass = new SchoolClass(className, selectedTeacher);
        for (Student student : selectedStudents) {
            newClass.addStudent(student);
        }
        schoolClasses.add(newClass);
        System.out.println("Class created: " + newClass.getName());
        System.out.println("Teacher: " + newClass.getTeacher().getName());
        System.out.print("Students: ");
        if (selectedStudents.isEmpty()) {
            System.out.println("none");
        } else {
            for (int index = 0; index < selectedStudents.size(); index++) {
                System.out.print(selectedStudents.get(index).getName());
                if (index < selectedStudents.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    public static void printClassesForStudent(Scanner input, List<SchoolClass> schoolClasses) {
        System.out.println();
        System.out.println(SECTION_BORDER);
        System.out.println("Search classes by student");
        System.out.println(SECTION_BORDER);

        int studentId = readInt("Enter student ID: ");
        boolean found = false;
        for (SchoolClass schoolClass : schoolClasses) {
            for (Student student : schoolClass.getStudents()) {
                if (student.getId() == studentId) {
                    if (!found) {
                        System.out.println("Student ID " + studentId + " is enrolled in:");
                        found = true;
                    }
                    System.out.println("• " + schoolClass.getName());
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("No classes found for student ID " + studentId + ".");
        }
    }
}
