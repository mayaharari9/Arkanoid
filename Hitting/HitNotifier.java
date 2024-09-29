// name: Maya Harari, ID: 216441469, File: HitNotifier
package Hitting;

/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-06-07
 */
public interface HitNotifier {
    // Add hl as a listener to hit events.

    /**
     * Add hl as a listener to hit events.
     * @param hl The hl to be added.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl The hl to be removed.
     */
    void removeHitListener(HitListener hl);
}