import java.util.*;
import java.time.*;

abstract class LibraryItem {
    String title;

    LibraryItem(String title) {
        this.title = title;
    }

    abstract int loanDays();

    LocalDate dueDate() {
        return LocalDate.parse("2023-10-26").plusDays(loanDays());
    }
}

class BookItem extends LibraryItem {
    BookItem(String t) {
        super(t);
    }

    int loanDays() {
        return 14;
    }
}

class DVDItem extends LibraryItem {
    DVDItem(String t) {
        super(t);
    }

    int loanDays() {
        return 7;
    }
}

class MagazineItem extends LibraryItem {
    MagazineItem(String t) {
        super(t);
    }

    int loanDays() {
        return 3;
    }
}

public class LibraryDueDate {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            int firstSpace = line.indexOf(' ');

            String type = line.substring(0, firstSpace);
            String title = line.substring(firstSpace + 1).trim();

            if (title.startsWith("\"") && title.endsWith("\"")) {
                title = title.substring(1, title.length() - 1);
            }

            LibraryItem item;

            switch (type) {
                case "BOOK":
                    item = new BookItem(title);
                    break;
                case "DVD":
                    item = new DVDItem(title);
                    break;
                default:
                    item = new MagazineItem(title);
            }

            System.out.println(item.title + ": " + item.dueDate());
        }

        sc.close();
    }
}
