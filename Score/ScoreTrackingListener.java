// name: Maya Harari, ID: 216441469, File: ScoreTrackingListener
package Score;

import Hitting.HitListener;
import Objects.Counter;
import SpiritsAndCollidables.Ball;
import SpiritsAndCollidables.Block;

/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-06-10
 * The class ScoreTrackingListener is in charge of updating the player's score during the game.
 */
public class ScoreTrackingListener implements HitListener {
    private static final int NUM_OF_POINTS_FOR_HITTING_A_BLOCK = 5;
    private Counter currentScore;

    /**
     * Constructor.
     * @param scoreCounter A counter of the score in the game.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(NUM_OF_POINTS_FOR_HITTING_A_BLOCK);
    }
}
