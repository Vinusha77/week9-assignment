
import java.util.Scanner;

abstract class Appliance {
    protected double hours;
    protected double power;

    Appliance(double power, double hours) {
        this.power = power;
        this.hours = hours;
    }

    double calculateUnits() {
        return power * hours / 1000.0;
    }

    double calculateCost(double units) {
        return units * 8.0;
    }

    abstract String getType();
}

interface SaverMode {
    double SAVING_RATE = 0.25;

    default double applySaver(double units) {
        return units * (1 - SAVING_RATE);
    }
}

class Fridge extends Appliance {
    Fridge(double hours) {
        super(150, hours);
    }

    String getType() {
        return "FRIDGE";
    }
}

class AC extends Appliance implements SaverMode {
    AC(double hours) {
        super(1500, hours);
    }

    String getType() {
        return "AC";
    }
}

class TV extends Appliance {
    TV(double hours) {
        super(100, hours);
    }

    String getType() {
        return "TV";
    }
}

class Washer extends Appliance implements SaverMode {
    Washer(double hours) {
        super(500, hours);
    }

    String getType() {
        return "WASHER";
    }
}

public class HomeApplianceEnergyReport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        double totalCost = 0;

        for (int i = 0; i < n; i++) {
            String[] input = sc.nextLine().trim().split("\\s+");

            String type = input[0].toUpperCase();
            double hours = Double.parseDouble(input[1]);
            boolean saver = input.length > 2
                    && input[2].equalsIgnoreCase("SAVER");

            Appliance appliance;

            switch (type) {
                case "FRIDGE":
                    appliance = new Fridge(hours);
                    break;
                case "AC":
                    appliance = new AC(hours);
                    break;
                case "TV":
                    appliance = new TV(hours);
                    break;
                case "WASHER":
                    appliance = new Washer(hours);
                    break;
                default:
                    System.out.println("Invalid appliance");
                    continue;
            }

            if (saver && !(appliance instanceof SaverMode)) {
                System.out.println(
                    type + ": saver mode not supported"
                );
                continue;
            }

            double units = appliance.calculateUnits();

            if (saver) {
                units = ((SaverMode) appliance).applySaver(units);
            }

            double cost = appliance.calculateCost(units);

            System.out.printf(
                "%s: Units=%.2f Cost=%.2f%n",
                type, units, cost
            );

            totalCost += cost;
        }

        System.out.printf("Total Cost: %.2f%n", totalCost);
        sc.close();
    }
}
