// name: Maya Harari, ID: 216441469, File: Game
package Animations;
/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-05-05
 */

import LevelInformations.DirectHitInformation;
import LevelInformations.GameEnvironment;
import LevelInformations.LevelInformation;
import SpiritsAndCollidables.SpriteCollection;
import Hitting.BallRemover;
import Hitting.BlockRemover;
import Hitting.HitListener;
import Objects.Counter;
import Objects.Point;
import Objects.Rectangle;
import Objects.Velocity;
import Score.ScoreTrackingListener;
import SpiritsAndCollidables.Ball;
import SpiritsAndCollidables.Block;
import SpiritsAndCollidables.Paddle;
import SpiritsAndCollidables.Collidable;
import SpiritsAndCollidables.Sprite;
import SpiritsAndCollidables.LevelName;

import Score.ScoreIndicator;


import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;

/**
 * A Game class that will hold the sprites and the collidables, and will be in charge of the animation.
 */
public class GameLevel implements Animation {
    private static final int HEIGHT_OF_PADDLE = 15;
    private int pointForClearing = 100;
    private static final int HEIGHT_OF_HORIZONTAL_BORDER = 25;
    private final LevelInformation levelInfo;
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private List<Ball> balls;
    private Counter reminingBlocks;
    private Counter reminingBalls;
    private Counter score;
    private static final int WIDTH_OF_VERTICAL_BORDER = 25;
    private static final int HEIGHT_OF_SCORE_DISPLAY = 25;
    private static final int WIDTH_OF_FRAME = 800;
    private static final int HEIGHT_OF_FRAME = 600;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;


    /**
     * Constructor.
     * @param levelInfo The level information of current level.
     * @param ks The KeyboardSensor for the game.
     * @param animationRunner The runner of th game level.
     * @param score The counter of the score of the player.
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor ks, AnimationRunner animationRunner, Counter score) {
        if (levelInfo == null) {
            this.levelInfo = new DirectHitInformation();
        } else {
            this.levelInfo = levelInfo;
            }
        this.keyboard = ks;
        this.score = score;
        this.runner = animationRunner;
        this.pointForClearing = 100;
        this.running = true;
    }
    /**
     * Add the given collidable to the environment.
     * @param c Type: Collidable. The collidable to add to the environment.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Removes the given collidable to the environment.
     * @param c Type: Collidable. The collidable to remove from the environment.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Adds a sprite to the list of the sprites of the game.
     * @param s Type: Sprite. The sprite to add to the list.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Removes a sprite to the list of the sprites of the game.
     * @param s Type: Sprite. The sprite to remove from the list.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * In charge of setting up the game, initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {

        //dead region block
        Block b = new Block(new Rectangle(new Point(0, HEIGHT_OF_FRAME), WIDTH_OF_FRAME, HEIGHT_OF_HORIZONTAL_BORDER),
                Color.WHITE, Color.WHITE);
        this.reminingBalls = new Counter(this.levelInfo.numberOfBalls());
        HitListener removeBall = new BallRemover(this, this.reminingBalls);
        b.addHitListener(removeBall);
        b.addToGame(this);

        //the background
        this.addSprite(this.levelInfo.getBackground());

        //Borders (low border is replaced by the dead region block below).
        b = new Block(new Rectangle(new Point(0, HEIGHT_OF_FRAME), WIDTH_OF_VERTICAL_BORDER,
                HEIGHT_OF_FRAME), java.awt.Color.gray);
        b.addToGame(this);
        b.addToGame(this);
        b = new Block(new Rectangle(new Point(WIDTH_OF_FRAME - WIDTH_OF_VERTICAL_BORDER, HEIGHT_OF_FRAME),
                WIDTH_OF_VERTICAL_BORDER, HEIGHT_OF_FRAME), java.awt.Color.gray);
        b.addToGame(this);
        b = new Block(new Rectangle(new Point(0, HEIGHT_OF_HORIZONTAL_BORDER + HEIGHT_OF_SCORE_DISPLAY),
                WIDTH_OF_FRAME, HEIGHT_OF_HORIZONTAL_BORDER + HEIGHT_OF_SCORE_DISPLAY), Color.gray);
        b.addToGame(this);

        //Score
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);
        //Level name
        LevelName name = new LevelName(this.levelInfo.levelName());
        name.addToGame(this);

        //Blocks
        this.reminingBlocks = new Counter(ZERO);
        HitListener removeBlock = new BlockRemover(this, this.reminingBlocks);
        HitListener scoreTracking = new ScoreTrackingListener(this.score);
        List<Block> blocks = this.levelInfo.blocks();
        Block block;
        for (int i = 0; i < this.levelInfo.numberOfBlocksToRemove(); i++) {
            block = blocks.get(i);
            block.addHitListener(removeBlock);
            block.addHitListener(scoreTracking);
            block.addToGame(this);
            this.reminingBlocks.increase(ONE);
        }
        //Paddle
        Paddle paddle = new Paddle(new Rectangle(new Point((int) (WIDTH_OF_FRAME / 2
                - this.levelInfo.paddleWidth() / 2), HEIGHT_OF_FRAME - WIDTH_OF_VERTICAL_BORDER),
                this.levelInfo.paddleWidth(), HEIGHT_OF_PADDLE),
                Color.blue, this.levelInfo.paddleSpeed(), this.keyboard);
        paddle.addToGame(this);
        //Balls
        Ball ball;
        List<Ball> balls = this.levelInfo.balls();
        List<Velocity> velocities = this.levelInfo.initialBallVelocities();
        for (int i = 0; i < this.levelInfo.numberOfBalls(); i++) {
            ball = balls.get(i);
            ball.setVelocity(velocities.get(i));
            ball.addToGame(this);
            ball.setGameEnvironment(this.environment);
        }
    }


    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        if (this.runner == null) {
            return;
        }
        this.createBallsOnTopOfPaddle(); // or a similar method
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }
    private void createBallsOnTopOfPaddle() {
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        //If all blocks were destroyed.
        if (this.reminingBlocks.getValue() == ZERO) {
            this.score.increase(this.pointForClearing);
        }
        //If the game should end.
        if (this.reminingBlocks.getValue() <= ZERO || this.reminingBalls.getValue() <= ZERO) {
            this.running = false;
        }
        //If the game should pause.
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
