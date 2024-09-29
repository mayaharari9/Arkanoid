package LevelInformations;

import Backgrounds.DirectHitBackground;
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
public class DirectHitInformation implements LevelInformation {
    private static final int NUM_OF_BALLS = 1;
    private static final int INITIAL_VELOCITY_OF_BALL = -4;
    private static final int VELOCITY_OF_PADDLE = 7;
    private static final int WIDTH_OF_PADDLE = 80;
    private static final String LEVEL_NAME = "Direct Hit";
    private static final int NUM_OF_BLOCKS_TO_REMOVE = 1;
    private static final int WIDTH_OF_BLOCK = 20;
    private static final int HEIGHT_OF_BLOCK = 20;
    private static final int UPPER_LEFT_X = 390;
    private static final int UPPER_LEFT_Y = 140;
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
        b = new Ball(new Point(WIDTH_OF_SCREEN / 2, 500), SIZE_OF_BALL, Color.WHITE);
        l.add(b);
        return l;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> l = new ArrayList<Velocity>();
        l.add(new Velocity(0, INITIAL_VELOCITY_OF_BALL));
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
        return new DirectHitBackground();
    }

    @Override
    public List<Block> blocks() {
        Block b = new Block(new Rectangle(new Point(UPPER_LEFT_X, UPPER_LEFT_Y), WIDTH_OF_BLOCK, HEIGHT_OF_BLOCK),
                Color.RED);
        List<Block> l = new ArrayList<Block>();
        l.add(b);
        return l;


    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS_TO_REMOVE;
    }
}
