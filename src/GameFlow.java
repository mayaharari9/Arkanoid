import Animations.AnimationRunner;
import Animations.EndAnimation;
import Animations.GameLevel;
import Animations.KeyPressStoppableAnimation;
import LevelInformations.DirectHitInformation;
import LevelInformations.Green3Information;
import LevelInformations.LevelInformation;
import LevelInformations.WideEasyInformation;
import Objects.Counter;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-06-14
 */
public class GameFlow {
    private static final int ZERO = 0;
    private static final int MAX_POINTS_IN_LEVEL_ONE = 105;
    private static final int MAX_POINTS_IN_LEVEL_TWO = 175;
    private static final int MAX_POINTS_IN_LEVEL_TREE = 385;
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private int maxPointsInGame;

    /**
     * Constructor.
     * @param ar The animation runner or the game.
     * @param ks The keyboard sensor of the gui which is used for the game running.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
    }

    /**
     * Runs the game with the provided levels.
     * @param levels The levels of teh game to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        this.maxPointsInGame = ZERO;
        Counter score = new Counter(ZERO);
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, score);
            level.initialize();
            this.animationRunner.run(level);
            this.maxPointsInGame += getMaxPointsInLevel(levelInfo);
        }
        EndAnimation endAnimation = new EndAnimation(this.keyboardSensor, score, this.maxPointsInGame);
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY, endAnimation));
        this.animationRunner.getGUI().close();
    }

    /**
     * Returns the maximum number of points the player can win in the input level.
     * @param levelInfo The level to find the maximum number of points the player can win in.
     * @return Returns the maximum number of points the player can win in the input level.
     */
    private static int getMaxPointsInLevel(LevelInformation levelInfo) {
        if (levelInfo == null) {
            return ZERO;
        }
        if (levelInfo.levelName().equals(new DirectHitInformation().levelName())) {
            return MAX_POINTS_IN_LEVEL_ONE;
        }
        if (levelInfo.levelName().equals(new WideEasyInformation().levelName())) {
            return MAX_POINTS_IN_LEVEL_TWO;
        }
        if (levelInfo.levelName().equals(new Green3Information().levelName())) {
            return MAX_POINTS_IN_LEVEL_TREE;
        }
        return ZERO;
    }
}
