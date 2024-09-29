package Hitting;

import Animations.GameLevel;
import Objects.Counter;
import SpiritsAndCollidables.Ball;
import SpiritsAndCollidables.Block;

/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-06-07
 * A BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    private static final int ONE = 1;

    /**
     * Constructor.
     * @param gameLevel The game.
     * @param remainingBlocks A counter of the remaining blocks in the game.
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        this.remainingBlocks.decrease(ONE);
    }
}
