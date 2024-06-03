package id.seigan.dojo.things;

import id.seigan.dojo.models.Point;

public interface AnimalBehavior {

    Point checkForward();
    void stepForward(Board board);

    Point checkLeft();
    void moveLeft(Board board);

    Point checkRight();
    void moveRight(Board board);
    
}
