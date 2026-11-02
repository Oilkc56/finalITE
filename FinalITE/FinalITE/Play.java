import java.util.Random;

public class Play {
    private final Random random = new Random();
    private String opponent;
    private String result;

    public String getOpponent() { return opponent; }
    public String getResult() { return result; }

    public void playMatch(Player me, Player enemy) {
        opponent = enemy.getName();

        // elo ห่างกันไม่เกิน 50 ถือว่าสูสี จึงสุ่ม ชนะ/แพ้/เสมอ เท่า ๆ กัน
        int r = random.nextInt(3);
        if (r == 0) {
            result = "Win";
            me.changeElo(25);
            enemy.changeElo(-25);
        } else if (r == 1) {
            result = "Lose";
            me.changeElo(-25);
            enemy.changeElo(25);
        } else {
            result = "Draw";
        }

        System.out.println("==========================");
        System.out.println("   Welcome to the game   ");
        System.out.println("==========================");
        System.out.println("Opponent: " + enemy);
        System.out.println();
        System.out.println("=== Game result: " + result + " ===");
        if (result.equals("Win")) {
            System.out.println("elo +25");
        } else if (result.equals("Lose")) {
            System.out.println("elo -25");
        }
        System.out.println();
        System.out.println("Current rank: " + me.getRank());
        System.out.println("Current elo: " + me.getElo());
    }
}
