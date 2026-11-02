import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class MatchmakingQueue {
    private static final int RANGE = 50; // หาคู่ที่ elo ห่างกันไม่เกิน +-50

    private final Queue<Player> waiting = new LinkedList<>();
    private final Random random = new Random();
    private int botCount = 1;

    // สร้างบอทรอในคิว 5 คน
    public MatchmakingQueue() {
        for (int i = 0; i < 5; i++) {
            addBot(random.nextInt(61) * 10); // elo 0 - 600
        }
    }

    // O1 (โอหนุึ่ง)
    private void addBot(int elo) {
        waiting.add(new Player("Bot" + botCount, elo));
        botCount++;
    }

    // 1. เอาชื่อเข้าคิว
    // On (โอเอ็น)
    public void join(Player me) {
        if (!waiting.contains(me)) {
            waiting.add(me);
        }
    }

    // 2. ยกเลิกคิว
    // On (โอเอ็น)
    public void cancel(Player me) {
        waiting.remove(me);
    }

    // หาคู่: คนที่รอก่อนและ elo ห่างไม่เกิน +-50 จะได้คู่ก่อน
    // On (โอเอ็น)
    public Player findOpponent(Player me) {
        for (Player other : waiting) {
            if (other != me && Math.abs(other.getElo() - me.getElo()) <= RANGE) {
                return other;
            }
        }
        return null;
    }

    // ถ้าหาไม่เจอ จำลองว่ามีผู้เล่นใหม่เข้าคิวเพิ่ม (elo ใกล้เคียงเรา)
    public void newPlayerJoins(Player me) {
        int elo = Math.max(0, me.getElo() + (random.nextInt(11) - 5) * 10); //0-100
        addBot(elo);
    }

    // จับคู่สำเร็จ เอาออกจากคิวทั้งคู่ แล้วเติมบอทใหม่ 1 คน
    // On (โอเอ็น)
    public void removeMatched(Player me, Player opponent) {
        waiting.remove(me);
        waiting.remove(opponent);
        addBot(random.nextInt(61) * 10);
    }
}
