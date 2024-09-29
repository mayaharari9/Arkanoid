package Objects;
/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-06-07
 */
public class Counter {


    private int counter;

    /**
     * Constructor.
     */
    Counter() {
        this.counter = 0;
    }

    /**
     * Constructor- When getting a value for the counter.
     * @param counter The value counter's value.
     */
    public Counter(int counter) {
        this.counter = counter;
    }

    /**
     * Add number to current count.
     * @param number
     */
    public void increase(int number) {
        this.counter += number;
    }
    /**
     * Subtract number from current count.
     * @param number
     */
    public void decrease(int number) {
        this.counter -= number;
    }
    /**
     * Get current count.
     * @return The counter value.
     */
    public int getValue() {
        return this.counter;
    }

}