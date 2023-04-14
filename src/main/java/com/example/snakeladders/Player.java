package com.example.snakeladders;


import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


public class Player {
    private Circle coin;
    private int currentPosition;
    private String name;

    static Board gameBoard = new Board();


    public Player(int tileSize, Color coinColor, String playerName){
        coin = new Circle(tileSize/2);
        coin.setFill(coinColor);
        currentPosition = 0;
        movePlayer(1);
        name = playerName;
    }
    public void movePlayer(int diceValue){
        if (currentPosition + diceValue <= 100){
            currentPosition += diceValue;
            TranslateTransition move1 = translateAnimation(), move2 = null;

            int newPosition = gameBoard.getNewPosition(currentPosition);
            if(newPosition != currentPosition && newPosition != -1) {
                currentPosition = newPosition;
                move2 = translateAnimation();
            }

            if(move2 == null) {
                move1.play();
            }
            else {
                SequentialTransition seqTransition = new SequentialTransition(
                        move1,
                        new PauseTransition(Duration.millis(500)),
                        move2
                );
                seqTransition.play();
            }
        }
        //int x = gameBoard.getXCoordinate(currentPosition);
        //int y = gameBoard.getYCoordinate(currentPosition);

        //coin.setTranslateX(x);
        //coin.setTranslateY(y);


    }

    private TranslateTransition translateAnimation() {
        TranslateTransition animate = new TranslateTransition(Duration.millis(1000), coin);
        animate.setToX(gameBoard.getXCoordinate(currentPosition));
        animate.setToY(gameBoard.getYCoordinate(currentPosition));
        animate.setAutoReverse(false);
        return animate;
    }

    public void startingPosition() {
        currentPosition = 1;
        movePlayer(0);
    }

    boolean isWinner() {
        if(currentPosition == 100) return true;
        return false;
    }
    public Circle getCoin() {
        return coin;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public String getName() {
        return name;
    }
}
