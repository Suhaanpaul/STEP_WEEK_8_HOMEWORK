import java.util.*;

abstract class ExamQuestion {
    String type, correct, answer;
    double points;
    ExamQuestion(String type, String correct, String answer, double points) {
        this.type = type; this.correct = correct; this.answer = answer; this.points = points;
    }
    abstract double score();
}
class ObjectiveQuestion extends ExamQuestion {
    ObjectiveQuestion(String t, String c, String a, double p) { super(t, c, a, p); }
    double score() { return answer.equalsIgnoreCase(correct) ? points : 0; }
}
class EssayQuestion extends ExamQuestion {
    EssayQuestion(String t, String c, String a, double p) { super(t, c, a, p); }
    double score() {
        String[] keywords = correct.split(",");
        int found = 0;
        String response = answer.toLowerCase(Locale.ROOT);
        for (String keyword : keywords) {
            String k = keyword.trim().toLowerCase(Locale.ROOT);
            if (!k.isEmpty() && response.contains(k)) found++;
        }
        if (found >= 2) return points * 0.75;
        if (found == 1) return points * 0.50;
        return 0;
    }
}
public class QuestionGrader {
    static List<String> quotedFields(String line) {
        List<String> fields = new ArrayList<>();
       java.util.regex.Matcher m = java.util.regex.Pattern
    .compile("\"([^\"]*)\"|(\\S+)")
    .matcher(line);

while (m.find()) {
    fields.add(m.group(1) != null ? m.group(1) : m.group(2));
}
        return fields;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            List<String> f = quotedFields(sc.nextLine().trim());
            String type = f.get(0);
            // Fields: type, question text, correct answer, student answer, points
            String correct = f.get(2), answer = f.get(3);
            double points = Double.parseDouble(f.get(4));
            ExamQuestion q = type.equals("ESSAY")
                ? new EssayQuestion(type, correct, answer, points)
                : new ObjectiveQuestion(type, correct, answer, points);
            double score = q.score();
            System.out.printf(Locale.US, "%s: %.2f%n", type, score);
            total += score;
        }
        System.out.printf(Locale.US, "Total Score: %.2f%n", total);
        sc.close();
    }
}
