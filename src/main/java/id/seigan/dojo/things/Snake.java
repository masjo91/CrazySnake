package id.seigan.dojo.things;

import id.seigan.dojo.enums.Direction;
import id.seigan.dojo.models.Point;

import java.util.ArrayList;
import java.util.List;

public class Snake extends Thing implements AnimalBehavior{
    // Besar ular termasuk kepalanya
    private int size;
    private Point head;
    private List<Point> body;
    private Direction direction;

    public Snake(Builder builder) {
        super(builder.getName(), builder.getAppearance());
        // Menentukan koordinat posisi ular
        this.head = builder.getPosition();
        this.size = builder.getSize();

        body = new ArrayList<>();
    }

    public List<Point> getBody() {
        return body;
    }

    public void generateBody(){
        // Buat Implementasi body
        // Edit di sini
        int headPosX = getHead().getX();
        int headPosY = getHead().getY();

        for (int i = 0; i < size - 1; i++) {
            body.add(new Point(headPosX,  --headPosY));
        }

    }

    public Direction getDirection() {
        return direction;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Point getHead() {
        return head;
    }

    public void setHead(Point head) {
        this.head = head;
    }

    @Override
    public Point checkForward() {
        int xPos, yPos;

        if (head.getX() - 1 == body.getFirst().getX() ){
            // body ada di atasnya
            xPos = head.getX() + 1;
            yPos = head.getY();
            direction = Direction.DOWN;
        }else if (head.getY() - 1  == body.getFirst().getY() ){
            // body ada di kiri
            xPos = head.getX();
            yPos = head.getY() + 1;
            direction = Direction.RIGHT;
        }else if (head.getY() + 1  == body.getFirst().getY() ){
            // body ada di kanan
            xPos = head.getX();
            yPos = head.getY() - 1;
            direction = Direction.LEFT;
        }else if (head.getX() + 1  == body.getFirst().getX() ){
            // body ada di bawah
            xPos = head.getX() - 1;
            yPos = head.getY();
            direction = Direction.UP;
        }else{
            xPos = head.getX();
            yPos = head.getY();
            direction = Direction.STAY;
        }

        return new Point(xPos, yPos);
    }

    @Override
    public void stepForward(Board board) {
        board.putObject(head, null);
        for (Point body: this.getBody()){
            board.putObject(body, null);
        }
        int headXbefore = head.getX();
        int headYbefore = head.getY();

        Point newDirection = checkForward();
        head.setX(newDirection.getX());
        head.setY(newDirection.getY());

        List<Point> newBody = new ArrayList<>();
        for (int i = 0; i < body.size(); i++) {
            if (i == 0){
                newBody.add(new Point(headXbefore, headYbefore));
            }else{
                newBody.add(new Point(body.get(i-1).getX(), body.get(i-1).getY()));
            }
        }
        body.removeAll(body);
        body.addAll(newBody);
        board.putObject(this.getHead(), this);
        for (Point b: body){
            board.putObject(b, this);
        }
    }



    @Override
    public Point checkLeft() {
        int xPos, yPos;

        if (head.getX() - 1 == body.getFirst().getX() ){
            // body ada di atasnya
            xPos = head.getX();
            yPos = head.getY() - 1;

        }else if (head.getY() - 1  == body.getFirst().getY() ){
            // body ada di kiri
            xPos = head.getX() - 1;
            yPos = head.getY();
        }else if (head.getY() + 1  == body.getFirst().getY() ){
            // body ada di kanan
            xPos = head.getX() + 1;
            yPos = head.getY();

        }else if (head.getX() + 1  == body.getFirst().getX() ){
            // body ada di bawah
            xPos = head.getX();
            yPos = head.getY() - 1;
            // Move Right

        }else{
            xPos = head.getX();
            yPos = head.getY();
        }

        return new Point(xPos, yPos);
    }

    @Override
    public void moveLeft(Board board) {
        board.putObject(head, null);
        for (Point body: this.getBody()){
            board.putObject(body, null);
        }

        // Edit di sini
        int headXbefore = head.getX();
        int headYbefore = head.getY();


        Point newDirection = checkLeft();

        head.setX(newDirection.getX());
        head.setY(newDirection.getY());


        List<Point> newBody = new ArrayList<>();
        for (int i = 0; i < body.size(); i++) {
            if (i == 0){
                newBody.add(new Point(headXbefore, headYbefore));
            }else{
                newBody.add(new Point(body.get(i-1).getX(), body.get(i-1).getY()));
            }

        }

        body.removeAll(body);
        body.addAll(newBody);

        board.putObject(this.getHead(), this);
        for (Point b: body){
            board.putObject(b, this);
        }
    }

    @Override
    public Point checkRight() {
        int xPos, yPos;

        if (head.getX() - 1 == body.getFirst().getX() ){
            // body ada di atasnya
            xPos = head.getX();
            yPos = head.getY() + 1;
        }else if (head.getY() - 1  == body.getFirst().getY() ){
            // body ada di kiri
            xPos = head.getX() + 1;
            yPos = head.getY();
        }else if (head.getY() + 1  == body.getFirst().getY() ){
            // body ada di kanan
            xPos = head.getX() - 1;
            yPos = head.getY();

        }else if (head.getX() + 1  == body.getFirst().getX() ){
            // body ada di bawah
            xPos = head.getX();
            yPos = head.getY() +1;
            // Move Right

        }else{
            xPos = head.getX();
            yPos = head.getY();
        }

        return new Point(xPos, yPos);
    }

    @Override
    public void moveRight(Board board) {
        board.putObject(head, null);
        for (Point body: this.getBody()){
            board.putObject(body, null);
        }

        // Edit di sini
        int headXbefore = head.getX();
        int headYbefore = head.getY();


        Point newDirection = checkRight();

        head.setX(newDirection.getX());
        head.setY(newDirection.getY());


        List<Point> newBody = new ArrayList<>();
        for (int i = 0; i < body.size(); i++) {
            if (i == 0){
                newBody.add(new Point(headXbefore, headYbefore));
            }else{
                newBody.add(new Point(body.get(i-1).getX(), body.get(i-1).getY()));
            }

        }

        body.removeAll(body);
        body.addAll(newBody);

        board.putObject(this.getHead(), this);
        for (Point b: body){
            board.putObject(b, this);
        }
    }


    public static Builder getBuilder(){
        return new Builder();
    }

    public static class Builder{
        private int size, posX, posY;
        private String name, appearance;

        public int getSize() {return size;}
        public Builder setSize(int s){
            size = s;
            return this;
        }
        public String getName() {return name;}
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        public String getAppearance() {return appearance;}
        public Builder setAppearance(String appearance) {
            this.appearance = appearance;
            return this;
        }
        public Point getPosition(){
            return new Point(posX, posY);
        }
        public Builder setPosition(int x, int y){
            posX = x;
            posY = y;

            return this;
        }
        public Snake build(){
            return new Snake(this);
        }


    }
    // End of Class
}
