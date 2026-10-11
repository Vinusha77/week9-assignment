
import java.util.Scanner;

abstract class MovieTicket {
    static final double CONVENIENCE_FEE = 20.0;
    protected double price;

    MovieTicket(double price) {
        this.price = price;
    }

    double calculateAmount(int count) {
        return (price + CONVENIENCE_FEE) * count;
    }
}

class Regular extends MovieTicket {
    Regular() {
        super(150);
    }
}

class Premium extends MovieTicket {
    Premium() {
        super(250);
    }
}

class Recliner extends MovieTicket {
    Recliner() {
        super(400);
    }
}

public class MovieTicketCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String seat = sc.next().toUpperCase();
            int count = sc.nextInt();
            MovieTicket ticket = null;

            switch (seat) {
                case "REGULAR":
                    ticket = new Regular();
                    break;
                case "PREMIUM":
                    ticket = new Premium();
                    break;
                case "RECLINER":
                    ticket = new Recliner();
                    break;
                default:
                    System.out.println("Invalid seat type");
                    continue;
            }

            double amount = ticket.calculateAmount(count);
            System.out.printf("%s: %.2f%n", seat, amount);
            total += amount;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
