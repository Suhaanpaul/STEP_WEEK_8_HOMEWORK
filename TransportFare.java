import java.util.*;

abstract class Transport {
    double distance;
    Transport(double distance) { this.distance = distance; }
    abstract double fare();
}
class Bus extends Transport {
    Bus(double d) { super(d); }
    double fare() { return Math.min(10.0, 2.0 + 0.10 * distance); }
}
class Train extends Transport {
    Train(double d) { super(d); }
    double fare() { return 3.0 + 0.15 * distance; }
}
class Metro extends Transport {
    double factor;
    Metro(double d, double f) { super(d); factor = f; }
    double fare() { return (1.50 + 0.20 * distance) * factor; }
}
public class TransportFare {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            String[] s = sc.nextLine().trim().split("\\3s+");
            String type = s[0];
            double distance = Double.parseDouble(s[1]);
            Transport t;
            if (type.equals("BUS")) t = new Bus(distance);
            else if (type.equals("TRAIN")) t = new Train(distance);
            else t = new Metro(distance, Double.parseDouble(s[2]));
            double fare = t.fare();
            System.out.printf(Locale.US, "%s: %.2f%n", type, fare);
            total += fare;
        }
        System.out.printf(Locale.US, "Total: %.2f%n", total);
        sc.close();
    }
}
