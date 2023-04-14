package com.example.snakeladders;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {
    public static final int tileSize=40, width=10, height=10;

    public static final int buttonLine = height*tileSize + 100, labelLine = buttonLine - 70;

    private static Dice dice = new Dice();

    private Player playerOne, playerTwo;

    private boolean gameStarted = false, playerOneTurn = false, playerTwoTurn = false;

    private Pane createContent() {
        Pane root = new Pane();
        root.setPrefSize(width*tileSize, height*tileSize + 150);

        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++) {
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().addAll(tile);
            }
        }

        Image img = new Image("E:\\AccioProjects\\SnakeLadders\\src\\main\\resources\\SnakeLadderBoard.jpg");
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);

        //Buttons
        Button playerOneButton = new Button("Player 1");
        Button playerTwoButton = new Button("Player 2");
        Button startButton = new Button("Start");

        playerOneButton.setTranslateX(40);
        playerOneButton.setTranslateY(buttonLine);
        playerOneButton.setDisable(true);

        playerTwoButton.setTranslateX(300);
        playerTwoButton.setTranslateY(buttonLine);
        playerTwoButton.setDisable(true);

        startButton.setTranslateX(170);
        startButton.setTranslateY(buttonLine);

        //Info Display
        Label playerOneLabel = new Label("");
        Label playerTwoLabel = new Label("");
        Label diceLabel = new Label("Start the Game");

        playerOneLabel.setTranslateX(40);
        playerOneLabel.setTranslateY(labelLine);
        playerTwoLabel.setTranslateX(300);
        playerTwoLabel.setTranslateY(labelLine);
        diceLabel.setTranslateX(170);
        diceLabel.setTranslateY(labelLine);

        playerOne = new Player(tileSize, Color.BLACK, "Nitin");
        playerTwo = new Player(tileSize-6, Color.WHITE, "Jatin");

        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted) {
                    if(playerOneTurn) {
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : " + diceValue);
                        playerOne.movePlayer(diceValue);
                        //Winning Condition
                        if(playerOne.isWinner()) {
                            diceLabel.setText("Winner is " + playerOne.getName());

                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                            startButton.setDisable(false);
                            gameStarted = false;
                            startButton.setText("Restart");
                        }
                        else {
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn = true;
                            playerTwoButton.setDisable(false);
                            playerTwoLabel.setText("Your turn " + playerTwo.getName());
                        }

                    }
                }
            }
        });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted) {
                    if(playerTwoTurn) {
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : " + diceValue);
                        playerTwo.movePlayer(diceValue);
                        //Winning Condition
                        if(playerTwo.isWinner()) {
                            diceLabel.setText("Winner is " + playerTwo.getName());

                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                            startButton.setDisable(false);
                            gameStarted = false;
                            startButton.setText("Restart");
                        }
                        else {
                            playerOneTurn = true;
                            playerOneButton.setDisable(false);
                            playerOneLabel.setText("Your turn " + playerOne.getName());

                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");
                        }
                    }
                }
            }
        });

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted = true;
                diceLabel.setText("Game Started!");
                startButton.setDisable(true);

                playerOneTurn = true;
                playerTwoTurn = false;

                playerOneLabel.setText("Your turn " + playerOne.getName());
                playerTwoLabel.setText("");

                playerOneButton.setDisable(false);
                playerTwoButton.setDisable(true);

                playerOne.startingPosition();
                playerTwo.startingPosition();
            }
        });

        root.getChildren().addAll(board,
                playerOneButton, playerTwoButton, startButton,
                playerOneLabel, playerTwoLabel, diceLabel,
                playerOne.getCoin(), playerTwo.getCoin()
        );


        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}