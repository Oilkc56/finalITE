public class Player {
    private final String name;
    private int elo;

    public Player(String name, int elo) {
        this.name = name;
        this.elo = elo;
    }

    public String getName() { return name; }
    public int getElo() { return elo; }

    // elo ไม่ติดลบ
    // O1 (โอหนึ่ง)
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
