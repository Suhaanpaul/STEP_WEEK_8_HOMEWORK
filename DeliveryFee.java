import java.util.*;

abstract class Delivery {
    double weight, distance;
    Delivery(double w, double d) { weight = w; distance = d; }
    abstract double fee();
}
class StandardDelivery extends Delivery {
    StandardDelivery(double w, double d) { super(w, d); }
    double fee() { return 5 + 0.50 * weight + 0.10 * distance; }
}
class ExpressDelivery extends Delivery {
    ExpressDelivery(double w, double d) { super(w, d); }
    double fee() { return 15 + weight + 0.20 * distance; }
}
class InternationalDelivery extends Delivery {
    double customs;
    InternationalDelivery(double w, double d, double c) {
        super(w, d); customs = c;
    }
    double fee() { return 25 + 2 * weight + 0.50 * distance + customs; }
}
public class DeliveryFee {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            String[] s = sc.nextLine().trim().split("\\s+");
            String type = s[0];
            double w = Double.parseDouble(s[1]);
            double d = Double.parseDouble(s[2]);
            Delivery delivery;
            if (type.equals("STANDARD")) delivery = new StandardDelivery(w, d);
            else if (type.equals("EXPRESS")) delivery = new ExpressDelivery(w, d);
            else delivery = new InternationalDelivery(w, d, Double.parseDouble(s[3]));
            double fee = delivery.fee();
            System.out.printf(Locale.US, "%s: %.2f%n", type, fee);
            total += fee;
        }
        System.out.printf(Locale.US, "Total: %.2f%n", total);
        sc.close();
    }
}
