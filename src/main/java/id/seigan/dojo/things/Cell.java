package id.seigan.dojo.things;

import java.util.ArrayList;
import java.util.List;

public class Cell extends Thing{
    private final int row;
    private final int col;
    private List<Thing> thing;

    public Cell(String name, String appearance, int row, int col) {
        super(name, appearance);
        this.row = row;
        this.col = col;
        thing = new ArrayList<>();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public List<Thing> getThings() {
        return thing;
    }

    public void addThing(Thing thing){

        this.thing.add(thing);
    }

    public void removeThing(){

        this.thing.remove(thing.getLast());
    }

    public void displayCell() {
        if (thing.size() > 0) {
            System.out.print(thing.getLast().getAppearance());
        } else {
            System.out.print("   ");
        }
    }
    // End of Class
}
