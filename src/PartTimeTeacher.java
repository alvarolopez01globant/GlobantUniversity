public class PartTimeTeacher extends Teacher{


    public PartTimeTeacher(String name, int baseSalary) {
        super(name, baseSalary);
    }

    @Override
    public void calculateSalary(int hoursWorked) {
        double salary = getBaseSalary() * hoursWorked;
        System.out.println("Part-time teacher " + getName() + " has a salary of: " + salary);
    }
}
