// name: Maya Harari, ID: 216441469, File: AnimationRunner
package Animations;

import biuoop.DrawSurface;
import biuoop.GUI;
/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-06-14
 */
public class AnimationRunner {
    private static final int FRAMES_PER_SECONDE_DEFAULT = 60;
    private GUI gui;
    private int framesPerSecond;


    /**
     * Constructor (when getting framesPerSecond).
     * @param gui The GUI object.
     * @param framesPerSecond The frame rate.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * Constructor.
     * @param gui The GUI object.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = FRAMES_PER_SECONDE_DEFAULT;
    }

    /**
     * Returns the gui of the runner.
     * @return Returns the gui of the runner.
     */
    public GUI getGUI() {
        return this.gui;
    }

    /**
     * Runs the animation.
     * @param animation The animation to run.
     */
    public void run(Animation animation) {

        biuoop.Sleeper sleeper = new biuoop.Sleeper();

        int millisecondsPerFrame =  1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
