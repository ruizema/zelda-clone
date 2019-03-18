import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/**
 * @author Rui Ze Ma and Valerie Triassi
 *
 */

public class LegendOfZoe {
    /**
     * Main method of the game Legend of Zoe is the entry point of the program. It generates the game by using all the
     * classes in the package
     * @param args
     */
    public static void main(String[] args) {

        // MAIN
        // TODO : JavaDoc documentation
        // TODO : Bonus pathfinder AI

        // BUGS
        // TODO : Can attack from two spaces away

        // IMPROVEMENTS
        // TODO : Replace objects (from Level) with specific arrays (ex. Entity[])

        // Game initialization
        Messages.afficherIntro();
        int zoeHealth = 5;
        boolean bonus = args.length > 0 && args[0].equals("--bonus");

        for (int i = 1; i <= 6; i++) {

            // Level initialization
            Level level = new Level(i, zoeHealth);
            display(level);

            // Turn management
            Queue<String> commands = new LinkedList<>();
            do {

                // Reading commands
                if (commands.size() == 0) {

                    Scanner input = new Scanner(System.in);
                    String stringCommands = input.nextLine();

                    if (!stringCommands.equals("")) {
                        for (int j = 0; j < stringCommands.length(); j++) {
                            commands.add(stringCommands.substring(j, j + 1));
                        }
                    }

                }

                // Executing commands
                Zoe zoe = level.getZoe();

                if (commands.size() != 0) {
                    String command = commands.remove();
                    switch (command) {
                        case "w":
                            zoe.move(0, -1);
                            break;
                        case "a":
                            zoe.move(-1, 0);
                            break;
                        case "s":
                            zoe.move(0, 1);
                            break;
                        case "d":
                            zoe.move(1, 0);
                            break;
                        case "c":
                            zoe.dig();
                            break;
                        case "x":
                            zoe.attack();
                            break;
                        case "o":
                            zoe.open();
                            break;
                        case "q":
                            System.exit(0);
                            break;
                    }

                }

                // Monsters' turn
                for (int j = 0; j < level.getNbObjects(); j++) {
                    Object object = level.getObject(j);
                    if (object instanceof Monster && !((Monster) object).getIsDead()) {
                        ((Monster) object).movementAI(bonus);
                    }
                }

                // Death
                if (level.getZoe().getIsDead()) {
                    Messages.afficherDefaite();
                    System.exit(0);
                }

                // Display
                display(level);

            } while (!level.getCanExit());

            zoeHealth = level.getZoe().getNbLives();

        }

        // Victory
        Messages.afficherVictoire();

    }

    /**
     * Method used to repeat a string a certain amount of time
     * @param str string that we want to repeat
     * @param count amount of times the string is going to be repeated
     * @return string of the initial string repeated count number of times
     */
    public static String repeat(String str, int count) {
        String output = "";
        for (int i = 0; i < count; i++) {
            output += str;
        }
        return output;
    }

    /**
     * Method used to display the game for each level
     * @param level the actual level being played
     */
    public static void display(Level level) {
        String topBar = "";
        topBar += "Niveau " + level.getLevelNumber();
        topBar += "   " + repeat("▲", level.getZoe().getNbHexaforce());
        topBar += repeat(" ", 29 - topBar.length());
        topBar += "Zoe : " + repeat("❤", level.getZoe().getNbLives()) + "\n";
        System.out.println(topBar);
        System.out.println(level);
    }

}
