public abstract class Teacher {
    private String name;
    private int baseSalary;


    public Teacher(String name, int baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public String getName() {
        return name;
    }

    public int getBaseSalary() {
        return baseSalary;
    }



    public abstract void calculateSalary(int target);






}

