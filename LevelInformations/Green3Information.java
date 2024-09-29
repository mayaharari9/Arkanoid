package LevelInformations;
import Backgrounds.Green3Background;
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
public class Green3Information implements LevelInformation {
    private static final int NUM_OF_BLOCKS_ROWS = 6;
    private static final int NUM_OF_BLOCKS_IN_FIRST_ROW = 7;

    private static final int NUM_OF_BALLS = 2;
    private static final int INITIAL_VELOCITY_OF_BALL = -5;
    private static final int VELOCITY_OF_PADDLE = 8;
    private static final int WIDTH_OF_PADDLE = 70;
    private static final String LEVEL_NAME = "Green 3";
    private static final int NUM_OF_BLOCKS_TO_REMOVE = 57;
    private static final int WIDTH_OF_BLOCK = 50;
    private static final int HEIGHT_OF_BLOCK = 20;
    private static final int WIDTH_OF_FRAME = 800;
    private static final double HEIGHT_OF_FIRST_ROW = 240;
    private static final int WIDTH_OF_VERTICAL_BORDER = 25;
    private static final int WIDTH_OF_SCREEN = 800;
    private static final int SIZE_OF_BALL = 5;
    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Ball> balls() {
        Ball b;
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
        for (int i = 0; i < this.numberOfBalls(); i++) {
            l.add(new Velocity((1 - 2 * i) * (INITIAL_VELOCITY_OF_BALL - 1), INITIAL_VELOCITY_OF_BALL));
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
        return new Green3Background();
    }
    /**
     * Return the color of the blocks' row in the game according to the number of the row.
     * @param row Type: int. The roe number.
     * @return Type: java.awt.Color. Return the color of the blocks' row in the game according to the number of the row.
     */
    public java.awt.Color getColorForRowBlocks(int row) {
        if (row == 1) {
            return new Color(250, 52, 52);
        }
        if (row == 2) {
            return new Color(244, 55, 250);
        }
        if (row == 3) {
            return new Color(81, 120, 248);
        }
        if (row == 4) {
            return new Color(27, 238, 220);
        }
        if (row == 5) {
            return new Color(160, 243, 82);
        }
        return new Color(250, 159, 55);
    }
    @Override
    public List<Block> blocks() {
        List<Block> l = new ArrayList<Block>();
        Block block;
        for (int i = 0; i < NUM_OF_BLOCKS_ROWS; i++) {
            for (int j = 0; j < i + NUM_OF_BLOCKS_IN_FIRST_ROW; j++) {
                block = new Block(new Rectangle(new Point(WIDTH_OF_FRAME - WIDTH_OF_VERTICAL_BORDER
                        - (j + 1) * WIDTH_OF_BLOCK, HEIGHT_OF_FIRST_ROW - i * HEIGHT_OF_BLOCK),
                        WIDTH_OF_BLOCK, HEIGHT_OF_BLOCK), getColorForRowBlocks(i));
                l.add(block);
            }
        }

        return l;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS_TO_REMOVE;
    }
}
