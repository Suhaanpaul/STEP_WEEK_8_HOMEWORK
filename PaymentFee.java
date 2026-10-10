import java.util.*;

abstract class Payment {
    double amount;
    Payment(double amount) { this.amount = amount; }
    abstract double adjustedAmount();
}
class CardPayment extends Payment {
    CardPayment(double a) { super(a); }
    double adjustedAmount() { return amount * 1.02; }
}
class WalletPayment extends Payment {
    WalletPayment(double a) { super(a); }
    double adjustedAmount() { return amount * 1.01; }
}
class BankTransferPayment extends Payment {
    BankTransferPayment(double a) { super(a); }
    double adjustedAmount() { return amount; }
}
public class PaymentFee {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            String[] s = sc.nextLine().trim().split("\\s+");
            String type = s[0];
            double amount = Double.parseDouble(s[1]);
            Payment p;
            switch (type) {
                case "CARD": p = new CardPayment(amount); break;
                case "WALLET": p = new WalletPayment(amount); break;
                default: p = new BankTransferPayment(amount);
            }
            double adjusted = p.adjustedAmount();
            System.out.printf(Locale.US, "%s: %.2f%n", type, adjusted);
            total += adjusted;
        }
        System.out.printf(Locale.US, "Total: %.2f%n", total);
        sc.close();
    }
}
