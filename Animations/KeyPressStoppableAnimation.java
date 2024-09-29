package Animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-06-14
 */
public class KeyPressStoppableAnimation implements Animation {
    private boolean isAlreadyPressed;
    private Animation animation;
    private String key;
    private KeyboardSensor sensor;
    private boolean running;

    /**
     * Constructor.
     * @param sensor The keyboard sensor.
     * @param key The key which typing it makes the entered animation pause.
     * @param animation The animation which stops when the key "key" is pressed.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
        this.running = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //If key is not actually pressed.
        if (!this.sensor.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
        this.animation.doOneFrame(d);
        if (animation.shouldStop()) {
            this.running = false;
        }
        //If the space key is pressed.
        if (!this.isAlreadyPressed && this.sensor.isPressed(this.key)) {
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

}
