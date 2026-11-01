public class Profile {
    private final Player player;

    public Profile(Player player) {
        this.player = player;
    }

    public void showProfile() {
        System.out.println("==========================");
        System.out.println("       USER PROFILE       ");
        System.out.println("==========================");
        System.out.println("Username: " + player.getName());
        System.out.println("Rank: " + player.getRank());
        System.out.println("Elo: " + player.getElo());
    }
}
