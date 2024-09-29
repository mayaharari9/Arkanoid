package LevelInformations;

import Objects.Line;
import SpiritsAndCollidables.Collidable;
import SpiritsAndCollidables.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maya Harari < maya.harari@live.biu.ac.il >
 * @version 1.0
 * @since 2023-05-05
 */
public class GameEnvironment {
    /**
     * A list of the collidable objects of the game.
     */
    private List<Collidable> collidableObjects = new ArrayList<>();
    /**
     * Returns the collidable objects list of the game.
     * @return Type: List< Collidable >. Returns the collidable objects list of the game.
     */
    public List<Collidable> getCollidableObjects() {
        return this.collidableObjects;
    }


    /**
     * Add the given collidable to the environment.
     * @param c Type: Collidable. The collidable to add to the environment.
     */
    public void addCollidable(Collidable c) {
        collidableObjects.add(c);
    }

    /**
     * Remove the given collidable to the environment.
     * @param c Type: Collidable. The collidable to remove from the environment.
     */
    public void removeCollidable(Collidable c) {
        this.collidableObjects.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory The trajectory of the ball ("how the ball will move
     * without any obstacles").
     * @return If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int indexCollisionWith = 0;
        List<Collidable> collidables = new ArrayList<>(this.collidableObjects);
        CollisionInfo collisionInfo1;
        CollisionInfo collisionInfo2;
        for (int i = 1; i < collidables.size(); i++) {
            collisionInfo1 = new CollisionInfo(collidables.get(indexCollisionWith), trajectory);
            collisionInfo2 = new CollisionInfo(collidables.get(i), trajectory);
            if (collisionInfo1.collisionPoint() == null) {
                indexCollisionWith = i;
                continue;
            }
            if (collisionInfo2.collisionPoint() == null || collisionInfo2.collisionPoint().equals(trajectory.start())) {
                continue;
            }
            if (collisionInfo1.collisionPoint().distance(trajectory.start())
                    > collisionInfo2.collisionPoint().distance(trajectory.start())
                    || collisionInfo1.collisionPoint().equals(trajectory.start())) {
                indexCollisionWith = i;
            }
        }

        CollisionInfo theCollisionInfoToReturn = new CollisionInfo(collidables.get(indexCollisionWith),
                trajectory);
        if (theCollisionInfoToReturn.collisionPoint() == null) {
            return null;
        }
        return theCollisionInfoToReturn;
    }
}
