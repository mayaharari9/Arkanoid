// name: Maya Harari, ID: 216441469, File: Ass6Game

import Animations.AnimationRunner;

import LevelInformations.DirectHitInformation;
import LevelInformations.Green3Information;
import LevelInformations.LevelInformation;
import LevelInformations.WideEasyInformation;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-05-05
 */
public class Ass6Game {
    private static final String NAME = "Arkanoid";
    private static final int WIDTH_OF_FRAME = 800;
    private static final int HEIGHT_OF_FRAME = 600;
    private static final int ONE = 1;
    private static final int ZERO = 0;
    private static final int MIN_NUM_OF_LEVEL = 1;
    private static final int MAX_NUM_OF_LEVEL = 3;


    /**
     * Runs the game with input levels.
     * @param levelsNumbers The levels (numbers) for the game.
     */
    public static void runGame(List<Integer> levelsNumbers) {
        GUI gui = new GUI(NAME, WIDTH_OF_FRAME, HEIGHT_OF_FRAME);
        KeyboardSensor ks = gui.getKeyboardSensor();
        GameFlow gf = new GameFlow(new AnimationRunner(gui), ks);
        //Getting a list of the levels' information to run in the game.
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        LevelInformation levelInformation;
        for (int i = 0; i < levelsNumbers.size(); i++) {
            levelInformation = getLevel(levelsNumbers.get(i));
            levels.add(levelInformation);
        }
        //Running the levels of the game.
        gf.runLevels(levels);
        gui.close();
    }

    /**
     * Returns the level information of the input level number.
     * @param levelNum The number of the level to return.
     * @return Returns the level information of the input level number
     * (if there is no level of the input number, returns the level "DirectHitInformation").
     */
    private static LevelInformation getLevel(int levelNum) {
        if (levelNum == MIN_NUM_OF_LEVEL) {
            return new DirectHitInformation();
        }
        if (levelNum == MIN_NUM_OF_LEVEL + 1) {
            return new WideEasyInformation();
        }
        if (levelNum == MAX_NUM_OF_LEVEL) {
            return new Green3Information();
        }
        return new DirectHitInformation();
    }

    /**
     * Checks if the input string is actually an integer between 1 to 3.
     * @param str Type: String. The string to check.
     * @return Type: int. Returns the level number if the input string is a level of the game.
     * Otherwise, returns "MIN_NUM_OF_LEVEL - ONE" ("MIN_NUM_OF_LEVEL - ONE" is not a level in the game).
     */
    public static int levelNum(String str) {
        try {
            int num = Integer.parseInt(str);
            if (num >= MIN_NUM_OF_LEVEL && num <= MAX_NUM_OF_LEVEL) {
                return num;
            }
        } catch (Exception e) {
            return MIN_NUM_OF_LEVEL - ONE;
        }
        return MIN_NUM_OF_LEVEL - ONE;
    }

    /**
     * Main function.
     * @param args non
     */
    public static void main(String[] args) {
        GUI gui = new GUI(NAME, WIDTH_OF_FRAME, HEIGHT_OF_FRAME);
        KeyboardSensor ks = gui.getKeyboardSensor();

        List<Integer> levelsNumbers = new LinkedList<>();
        //adding the input levels for the game in the input.
        for (int i = 0; i < args.length; i++) {
            if (levelNum(args[i]) >= MIN_NUM_OF_LEVEL && levelNum(args[i]) <= MAX_NUM_OF_LEVEL) {
                levelsNumbers.add(levelNum(args[i]));
            }
        }
        //If there are no "level" arguments in the input.
        if (levelsNumbers.size() == ZERO) {
            for (int i = MIN_NUM_OF_LEVEL; i <= MAX_NUM_OF_LEVEL; i++) {
                levelsNumbers.add(i);
            }
        }
        //Running the game.
        runGame(levelsNumbers);
    }
}
