// name: Maya Harari, ID: 216441469, File: BallRemover

package Hitting;

import Animations.GameLevel;
import Objects.Counter;
import SpiritsAndCollidables.Ball;
import SpiritsAndCollidables.Block;

/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-06-07
 * A BallRemover is in charge of removing balls from the game, as well as keeping count
 * of the number of balls that remain.
 */

public class BallRemover implements HitListener {
    private static final int ONE = 1;
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Constructor.
     * @param gameLevel The game.
     * @param remainingBlocks A counter of the remaining balls in the game.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBlocks;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.remainingBalls.decrease(ONE);
    }
}
