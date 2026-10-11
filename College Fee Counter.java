
import java.util.Scanner;

abstract class Student {
    protected String name;
    static final double TUITION = 40000.0;
    static final double BUS_FEE = 12000.0;

    Student(String name) {
        this.name = name;
    }

    abstract double calculateFee();

    String getName() {
        return name;
    }
}

interface BusUser {
    double getBusFee();
}

class DayScholar extends Student implements BusUser {
    DayScholar(String name) {
        super(name);
    }

    public double getBusFee() {
        return BUS_FEE;
    }

    double calculateFee() {
        return TUITION + getBusFee();
    }
}

class Hosteller extends Student {
    static final double HOSTEL_FEE = 60000.0;

    Hosteller(String name) {
        super(name);
    }

    double calculateFee() {
        return TUITION + HOSTEL_FEE;
    }
}

class ScholarshipStudent extends Student implements BusUser {
    ScholarshipStudent(String name) {
        super(name);
    }

    public double getBusFee() {
        return BUS_FEE;
    }

    double calculateFee() {
        return (TUITION / 2) + getBusFee();
    }
}

public class CollegeFeeCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double totalCollected = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase();
            String name = sc.next();

            Student student;

            switch (type) {
                case "DAY_SCHOLAR":
                    student = new DayScholar(name);
                    break;
                case "HOSTELLER":
                    student = new Hosteller(name);
                    break;
                case "SCHOLAR":
                    student = new ScholarshipStudent(name);
                    break;
                default:
                    System.out.println("Invalid student type");
                    continue;
            }

            double fee = student.calculateFee();
            System.out.printf("%s: %.2f%n",
                              student.getName(), fee);
            totalCollected += fee;
        }

        System.out.printf("Total Collected: %.2f%n",
                          totalCollected);
        sc.close();
    }
}
