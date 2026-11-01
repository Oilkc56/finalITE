public class Player {
    private final String name;
    private final boolean bot;
    private int elo;

    public Player(String name, int elo, boolean bot) {
        this.name = name;
        this.elo = elo;
        this.bot = bot;
    }

    public String getName() { return name; }
    public int getElo() { return elo; }
    public boolean isBot() { return bot; }

    // elo ไม่ติดลบ
    public void changeElo(int amount) {
        elo = Math.max(0, elo + amount);
    }

    // rank คิดจาก elo
    public String getRank() {
        if (elo < 250) {
            return "Beginner";
        } else if (elo < 500) {
            return "Intermediate";
        } else {
            return "Advanced";
        }
    }

    @Override
    public String toString() {
        return name + " (" + getRank() + ", elo " + elo + ")";
    }
}
