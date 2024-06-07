package id.seigan.dojo;

import id.seigan.dojo.things.Snake;

import java.io.IOException;

public class Run {
    static Game game;
    static Snake snake;

    static {
        System.loadLibrary("native");

        Thread runControl = new Thread(new Runnable() {
            @Override
            public void run() {
                controls();
            }
        });

        runControl.start();
    }

    public static native void controls();

    public static void controlUp(){
        switch (snake.getDirection()){
            case LEFT -> {
                snake.moveRight(game.getBoard());
            }
            case RIGHT -> {
                snake.moveLeft(game.getBoard());
            }
        }

    }

    public static void controlDown(){
        switch (snake.getDirection()){
            case LEFT -> {
                snake.moveLeft(game.getBoard());
            }
            case RIGHT -> {
                snake.moveRight(game.getBoard());
            }
        }
    }

    public static void controlLeft(){
        snake.moveLeft(game.getBoard());
    }

    public static void controlRight(){
        snake.moveRight(game.getBoard());
    }

    public static void main(String[] args) {

        int row = 30;
        int col = 30;

        int posX = 5;
        int posY = 10;

        int snakeSize = 5;

        snake = Snake.getBuilder()
                .setName("Ularku")
                .setAppearance(" O ")
                .setPosition(posX, posY)
                .setSize(snakeSize)
                .build();

        snake.generateBody();

        game = Game.getBuilder()
                .createBoard(row, col)
                .createWalls()
                .createSnake(snake)
                .generatePopulation()
                .build();


        try {
            game.render();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // End of Class
}