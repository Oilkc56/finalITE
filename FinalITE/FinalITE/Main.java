import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Player me = new Player("Kaolad", 100);
        Play play = new Play();
        Profile profile = new Profile(me);
        History history = new History(play);
        MatchmakingQueue queue = new MatchmakingQueue();

        while (true) {
            System.out.println("===========================================");
            System.out.println("      Chess Matchmaking System (Demo)      ");
            System.out.println("===========================================");
            System.out.println("[1] Ready to find room");
            System.out.println("[2] Profile");
            System.out.println("[3] Exit");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine().trim();
            System.out.println();

            if (option.equals("1")) {
                findRoom(scanner, me, queue, play, history);
            } else if (option.equals("2")) {
                profile.showProfile();
                System.out.println();
                history.showHistory();
                System.out.println();
            } else if (option.equals("3")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Wrong option");
                System.out.println();
            }
        }
        scanner.close();
    }

    // หน้าหลังกด "เตรียมเข้าหาห้อง": ชื่อเราอยู่ในคิวแล้ว
    // มี while เลยเป็น On(โอเอ็น)
    private static void findRoom(Scanner scanner, Player me, MatchmakingQueue queue,
                                 Play play, History history) {
        queue.join(me);
        System.out.println("You are in the queue. (" + me + ")");

        while (true) {
            System.out.println();
            System.out.println("[1] Start finding room");
            System.out.println("[2] Cancel queue");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine().trim();
            System.out.println();

            if (option.equals("1")) {
                System.out.println("Searching... (elo +/-50)");
                Player opponent = queue.findOpponent(me);

                if (opponent == null) {
                    System.out.println("No room found yet. Waiting for more players...");
                    queue.newPlayerJoins(me);
                } else {
                    System.out.println("Room found!");
                    System.out.println();
                    queue.removeMatched(me, opponent);
                    play.playMatch(me, opponent);
                    history.addHistory();
                    System.out.println();
                    return;
                }
            } else if (option.equals("2")) {
                queue.cancel(me);
                System.out.println("Queue cancelled.");
                System.out.println();
                return;
            } else {
                System.out.println("Wrong option");
            }
        }
    }
}
