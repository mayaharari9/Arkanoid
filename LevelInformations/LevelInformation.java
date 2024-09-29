// name: Maya Harari, ID: 216441469, File: LevelInformation
package LevelInformations;

import Objects.Velocity;
import SpiritsAndCollidables.Ball;
import SpiritsAndCollidables.Block;
import SpiritsAndCollidables.Sprite;
import java.util.List;

/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-06-14
 */
public interface LevelInformation {
    /**
     * Returns the number of balls in level.
     * @return Returns the number of balls in level.
     */
    int numberOfBalls();
    /**
     * Returns a list of the Blocks that make up this level.
     * @return Returns a list of the Blocks that make up this level.
     */
    List<Ball> balls();

    /**
     * Returns a list of the initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     * @return Returns a list of the initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the speed of the paddle in level.
     * @return Returns the speed of the paddle in level.
     */
    int paddleSpeed();

    /**
     * Returns the width of the paddle in level.
     * @return Returns the width of the paddle in level.
     */
    int paddleWidth();

    /**
     * Returns the name of the level.
     * (The level name will be displayed at the top of the screen.)
     * @return Returns the name of the level (String type).
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return Returns a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * Returns a list of the Blocks that make up this level (each block contains its size, color and location).
     * @return Returns a list of the Blocks that make up this level (each block contains its size, color and location).
     */
    List<Block> blocks();
    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();

    /**
     * Returns the number of blocks that should be removed before the level is considered to be "cleared"
     * (This number should be <= blocks.size()).
     * @return Returns the number of blocks that should be removed before the level is considered to be "cleared".
     */
    int numberOfBlocksToRemove();
}
