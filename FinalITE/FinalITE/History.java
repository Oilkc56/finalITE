import java.util.ArrayDeque;

public class History {
    private final ArrayDeque<String> history = new ArrayDeque<>();
    private final Play play;
    private int matchCount = 1;

    public History(Play play) {
        this.play = play;
    }

    public void addHistory() {
        history.addFirst("Match : " + matchCount + " | Opponent : " + play.getOpponent()
                + ", Result : " + play.getResult());
        matchCount++;

        if (history.size() > 5) {   // เก็บแค่ 5 แมตช์ล่าสุด
            history.removeLast();
        }
    }

    public void showHistory() {
        System.out.println("--- Play History (last 5) ---");
        if (history.isEmpty()) {
            System.out.println("No history found");
        } else {
            for (String record : history) {
                System.out.println(record);
            }
        }
    }
}
