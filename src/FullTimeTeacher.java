public class FullTimeTeacher extends Teacher{

    private int yearsOfExperience;

    public FullTimeTeacher(String name, int baseSalary, int yearsOfExperience) {
        super(name, baseSalary);
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public void calculateSalary(int yearsOfExperience) {
        double salary = getBaseSalary() + (yearsOfExperience * 1.10);
        System.out.println("Full-time teacher " + getName() + " has a salary of: " + salary);

    }
}
