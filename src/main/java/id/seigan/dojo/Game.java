package id.seigan.dojo;

import id.seigan.dojo.models.Point;
import id.seigan.dojo.things.Board;
import id.seigan.dojo.things.Cell;
import id.seigan.dojo.things.Snake;
import id.seigan.dojo.things.Wall;

import java.io.IOException;
import java.util.List;
import java.util.Random;

// Class untuk mengontrol jalannya game, mengatur board, snake , dll
public class Game {
    private final Board board;
    private List<Wall> walls;

    private Snake snake;
    private int speed;

    public Game(Builder builder){
        this.board = builder.board;
        this.walls = builder.walls;
        this.snake =  builder.snake;
        this.speed = builder.speed;
    }

    public void render() throws InterruptedException, IOException {
        while (true){
            board.displayBoard();

//            autoMovement();
            snake.stepForward(board);
            System.out.println(snake.getDirection());
            Thread.sleep(100);

            new ProcessBuilder("clear").inheritIO().start().waitFor();
        }

    }

    public Board getBoard() {
        return board;
    }

    public void autoMovement(){
        Random random = new Random();

//        snake.stepForward(board);
        Point move = snake.checkForward();
        Cell cell = board.getBoard().get(move.getX()).get(move.getY());

        if(cell.getThings().size() == 0){
        // Maju ke depan
            snake.stepForward(board);
        }else{
            Point mLeft = snake.checkLeft();
            if (board.getBoard().get(mLeft.getX()).get(mLeft.getY()).getThings().size() == 0){
//                System.out.println("kiri");
                snake.moveLeft(board);
            }else{
//                System.out.println("kanan");
                snake.moveRight(board);
            }

        }




    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public static class Builder{
        Board board;
        List<Wall> walls;

        Snake snake;
        int speed;

        public Builder createBoard(int row, int col){
            board = new Board("Board", "", row, col);
            return this;
        }

        public Builder createWalls(){
            // Method untuk membuat dinding area game
            int row = board.getRow();
            int col = board.getCol();

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                        board.putObject(new Point(i, j), new Wall("Wall", " * "));
                    }
                }
            }
            return this;
        }

        public Builder createSnake(Snake snake){
            this.snake = snake;

            return this;
        }

        // dipakai untuk membuat object ular dan fruit
        public Builder generatePopulation(){
            board.putObject(snake.getHead(), snake);

            for (Point body: snake.getBody()){
                board.putObject(body, snake);
            }
            return this;
        }


        public Game build(){
            return new Game(this);
        }

        // End of inner Class
    }
    // End of Class
}
