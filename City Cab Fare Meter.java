
import java.util.Scanner;

abstract class Cab {
    static final double MIN_FARE = 100.0;
    protected double km;

    Cab(double km) {
        this.km = km;
    }

    abstract double calculateFare();

    abstract String getType();
}

interface NightService {
    double NIGHT_SURCHARGE = 0.20;
}

class Mini extends Cab {
    Mini(double km) {
        super(km);
    }

    double calculateFare() {
        return Math.max(km * 10, MIN_FARE);
    }

    String getType() {
        return "MINI";
    }
}

class Sedan extends Cab implements NightService {
    Sedan(double km) {
        super(km);
    }

    double calculateFare() {
        return Math.max(km * 14, MIN_FARE);
    }

    double calculateNightFare() {
        return calculateFare() * (1 + NIGHT_SURCHARGE);
    }

    String getType() {
        return "SEDAN";
    }
}

class SUV extends Cab implements NightService {
    SUV(double km) {
        super(km);
    }

    double calculateFare() {
        return Math.max(km * 18, MIN_FARE);
    }

    double calculateNightFare() {
        return calculateFare() * (1 + NIGHT_SURCHARGE);
    }

    String getType() {
        return "SUV";
    }
}

public class CityCabFareMeter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase();
            double km = sc.nextDouble();
            String time = sc.next().toUpperCase();

            Cab cab;

            switch (type) {
                case "MINI":
                    cab = new Mini(km);
                    break;
                case "SEDAN":
                    cab = new Sedan(km);
                    break;
                case "SUV":
                    cab = new SUV(km);
                    break;
                default:
                    System.out.println("Invalid cab type");
                    continue;
            }

            if (time.equals("NIGHT") && !(cab instanceof NightService)) {
                System.out.println(
                    type + ": night service not available"
                );
                continue;
            }

            double fare = cab.calculateFare();

            if (time.equals("NIGHT")) {
                fare *= 1.20;
            }

            System.out.printf("%s: %.2f%n", type, fare);
            total += fare;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
