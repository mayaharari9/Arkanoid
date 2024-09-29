package LevelInformations;
import Backgrounds.WildEasyBackground;
import Objects.Point;
import Objects.Rectangle;
import Objects.Velocity;
import SpiritsAndCollidables.Ball;
import SpiritsAndCollidables.Block;
import SpiritsAndCollidables.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-06-14
 */
public class WideEasyInformation implements LevelInformation {
    private static final int NUM_OF_BALLS = 10;
    private static final int INITIAL_VELOCITY_OF_BALL = -6;
    private static final int VELOCITY_OF_PADDLE = 6;
    private static final int WIDTH_OF_PADDLE = 600;
    private static final String LEVEL_NAME = "Wide Easy";
    private static final int NUM_OF_BLOCKS_TO_REMOVE = 15;
    private static final int WIDTH_OF_BLOCK = 50;
    private static final int HEIGHT_OF_BLOCK = 20;
    private static final int UPPER_LEFT_Y = 250;
    private static final int WIDTH_OF_FRAME = 25;
    private static final int WIDTH_OF_SCREEN = 800;
    private static final int SIZE_OF_BALL = 5;
    private static final int ONE = 1;



    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Ball> balls() {
        //-1 - left of screen, 1 - right of screen.
        int side = 1;
        Ball b;
        double x;
        List<Ball> l = new ArrayList<Ball>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            b = new Ball(new Point(WIDTH_OF_SCREEN / 2, 500), SIZE_OF_BALL, Color.BLACK);
            l.add(b);
        }
        return l;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> l = new ArrayList<Velocity>();
        Velocity v = new Velocity(0, 0);
        for (int i = 0; i < this.numberOfBalls(); i++) {
            v = Velocity.fromAngleAndSpeed(-90 - 22.5 - 15 * i,
                    INITIAL_VELOCITY_OF_BALL);
            l.add(v);
        }
        return l;
    }

    @Override
    public int paddleSpeed() {
        return VELOCITY_OF_PADDLE;
    }

    @Override
    public int paddleWidth() {
        return WIDTH_OF_PADDLE;
    }

    @Override
    public String levelName() {
        return LEVEL_NAME;
    }

    @Override
    public Sprite getBackground() {
        return new WildEasyBackground();
    }

    /**
     * Return the color of the blocks' row in the game according to the number of the row.
     * @param i Type: int. The block number.
     * @return Type: java.awt.Color. Return the color of the blocks' row in the game according to the number of the row.
     */
    public java.awt.Color getColorForBlocks(int i) {
        if (0 <= i && i <= 1) {
            return Color.green;
        }
        if (2 <= i && i <= 3) {
            return Color.pink;
        }
        if (4 <= i && i <= 5) {
            return Color.blue;
        }
        if (6 <= i && i <= 8) {
            return Color.green;
        }
        if (9 <= i && i <= 10) {
            return Color.yellow;
        }
        if (11 <= i && i <= 12) {
            return Color.orange;
        }
        if (13 <= i && i <= 14) {
            return Color.red;
        }
        return Color.gray;
    }

    @Override
    public List<Block> blocks() {
        List<Block> l = new ArrayList<Block>();
        Block b;
        for (int i = 0; i < this.numberOfBlocksToRemove(); i++) {
            b = new Block(new Rectangle(new Point(WIDTH_OF_FRAME + i * WIDTH_OF_BLOCK, UPPER_LEFT_Y),
                    WIDTH_OF_BLOCK, HEIGHT_OF_BLOCK), this.getColorForBlocks(i));
            l.add(b);
        }
        return l;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS_TO_REMOVE;
    }
}
