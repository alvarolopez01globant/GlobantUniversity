import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List <Teacher> teachers = Arrays.asList();




        //Rules:
        //- The project must be uploaded to a new public github repository, which should be public. The repository
        //should include appropriate use of .gitIgnore, more than one branch, and multiple commits.
        //- The project repository link must be sent to the Java mentors (Silvana, Juan) before the due date. The
        //link must be specified on the following document: Final Project The project is due on Monday August
        //31st, 2026 at 11:59 pm.
        //- The project should include at least:
        //● Design Diagram (Any format you want, UML recommended, may be any standard format, even
        //a cellphone photo)
        //● Access modifiers
        //● Encapsulation
        //● Inheritance
        //● Polymorphism
        //● Constructors
        //● Static attributes/methods
        //● Main class
        //● Packages and layers with proper naming
        //● Reading and printing (it’s not necessary to do it from console, should not be on the data mode*//


        //*nitialize minimum 2 different teachers of each type (full time, part time).
        PartTimeTeacher andres = new PartTimeTeacher("Andres", 20);
        PartTimeTeacher carlos = new PartTimeTeacher("Carlos", 15);
        FullTimeTeacher juan = new FullTimeTeacher("Juan", 30, 40);
        FullTimeTeacher maria = new FullTimeTeacher("Maria", 25, 35);

        teachers.add(andres);
        teachers.add(carlos);
        teachers.add(juan);
        teachers.add(maria);


        //2. Initialize minimum 6 different students
        Student luis = new Student("Luis", 20,1);
        Student ana = new Student("Ana", 22,2);
        Student pedro = new Student("Pedro", 21,3);
        Student sofia = new Student("Sofia", 23,4);
        Student carla = new Student("Carla", 20,5);
        Student diego = new Student("Diego", 22,6);

        List <Student> allStudents = Arrays.asList(luis, ana, pedro, sofia, carla, diego);


        //3. Initialize minimum 4 different classes including its teacher , students and other relevant data
        Class class1 = new Class("Math", juan, Arrays.asList(luis, ana));
        Class class2 = new Class("Science", maria, Arrays.asList(pedro, sofia));
        Class class3 = new Class("History", andres, Arrays.asList(carla, diego));
        Class class4 = new Class("English", carlos, Arrays.asList(luis, pedro));

        List<Class> classes = Arrays.asList(class1, class2, class3, class4);

        //4. Print a menú including the following options:

        Scanner scanner = new Scanner(System.in);

        int option = scanner.nextInt();
        while (true) {

            switch (option) {
                //a. Print all the professors with its data
                case 1:
                    for (Teacher teacher : teachers) {
                        System.out.println("Teacher Name: " + teacher.getName() + ", Base Salary: " + teacher.getBaseSalary());
                        // metodo para mostrar las clases en las que el profe esta
                    }
                    break;
                //b. Print all the classes and a submenu to select a class in order to print the class data including its
                //teacher and students
                case 2:
                    int i=0;
                    // Print all classes and submenu to select a class
                    for (Class unitClass : classes){
                        System.out.println((i)+"Class Name: " + unitClass.getName());
                        i++;


                    }
                    System.out.println("Select a class to view details using number");
                    int opt=scanner.nextInt()-1;
                    if (opt >= 0 && opt < classes.size()) {
                        Class selectedClass = classes.get(opt);
                        System.out.println("Class Name: " + selectedClass.getName());
                        System.out.println("Teacher: " + selectedClass.getTeacher().getName());
                        System.out.println("Students:");
                        for (Student student : selectedClass.getStudents()) {
                            System.out.println("- " + student.getName() + ", Age: " + student.getAge() + ", ID: " + student.getId());
                        }
                    } else {
                        System.out.println("Invalid class selection.");
                    }

                    break;

                //c. Create a new student and add it to an existing class
                    case 3:
                    createStudent(scanner, classes);
                    break;

                //d. Create a new class and add an existing teacher, existing students and its relevant data
                case 4:
                    // Create a new class and add an existing teacher and students
                    createClass(scanner, teachers, allStudents, classes);
                    break;

                //e. List all the classes in which a given student is included (hint: search by id)
                case 5:
                    // List all classes in which a given student is included
                    break;

                //f. Exit
                    case 6:
                    System.out.println("Exiting...");
                    return;


                default:
                    System.out.println("Invalid option. Please try again.");


            }

        }



    }

    public static void createStudent(Scanner scanner, List<Class> classes) {
        System.out.println("Enter student name:");
        String name = scanner.next();
        System.out.println("Enter student age:");
        int age = scanner.nextInt();
        System.out.println("Enter student id:");
        int id = scanner.nextInt();

        Student newStudent = new Student(name, age, id);

        System.out.println("Select a class to add the student to:");
        for (int i = 0; i < classes.size(); i++) {
            System.out.println((i + 1) + ". " + classes.get(i).getName());
        }

        int classOption = scanner.nextInt() - 1;
        if (classOption >= 0 && classOption < classes.size()) {
            classes.get(classOption).getStudents().add(newStudent);
            System.out.println("Student added to class " + classes.get(classOption).getName());
        } else {
            System.out.println("Invalid class selection.");
        }
    }

    public static void createClass(Scanner scanner, List<Teacher> teachers, List<Student> students, List<Class> classes) {
        System.out.println("Enter class name:");
        String className = scanner.next();

        System.out.println("Select a teacher for the class:");
        for (int i = 0; i < teachers.size(); i++) {
            System.out.println((i + 1) + ". " + teachers.get(i).getName());
        }

        int teacherOption = scanner.nextInt() - 1;
        Teacher selectedTeacher = null;
        if (teacherOption >= 0 && teacherOption < teachers.size()) {
            selectedTeacher = teachers.get(teacherOption);
        } else {
            System.out.println("Invalid teacher selection.");
            return;
        }

        System.out.println("Select students for the class (enter ids separated by commas):");
        for (Student student : students) {
            System.out.println(student.getId() + ". " + student.getName());
        }

        String[] studentIds = scanner.next().split(",");
        List<Student> selectedStudents = new ArrayList<>();
        for (String idStr : studentIds) {
            int id = Integer.parseInt(idStr.trim());
            for (Student student : students) {
                if (student.getId() == id) {
                    selectedStudents.add(student);
                    break;
                }
            }
        }

        Class newClass = new Class(className, selectedTeacher, selectedStudents);
        classes.add(newClass);
        // Add the new class to your list of classes
    }
}