package com.example.snakeladders;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    static int tileSize = SnakeLadder.tileSize;
    static int height = SnakeLadder.height;
    static int width = SnakeLadder.width;

    static ArrayList<Pair<Integer,Integer>>positionCoordinates;
    static ArrayList<Integer> snakeLadderPosition;

    public Board()
    {
        populatePositionCoordinates();
        populateSnakeLadderPosition();
    }
    private static void populatePositionCoordinates(){
        positionCoordinates = new ArrayList<>();
        positionCoordinates.add(new Pair<Integer,Integer>(0,0));
        // 20,380
        int xTilePos, yTilePos;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(i%2 == 0) {
                    xTilePos = tileSize*j + (tileSize/2);
                }
                else {
                    xTilePos = tileSize*(width-1-j) + (tileSize/2);
                }

                yTilePos = tileSize/2 + (height-1-i)*tileSize;
                positionCoordinates.add(new Pair<Integer,Integer>(xTilePos,yTilePos));
            }
        }
        for (int i=0; i<positionCoordinates.size(); i++)
        {
            System.out.println(i + "x: " + positionCoordinates.get(i).getKey() + "y:" + positionCoordinates.get(i).getValue());
        }
    }

    private void populateSnakeLadderPosition() {
        snakeLadderPosition = new ArrayList<>();
        for(int i=0; i<101; i++) {
            snakeLadderPosition.add(i);
        }

        snakeLadderPosition.set(4, 25);
        snakeLadderPosition.set(21, 39);
        snakeLadderPosition.set(63, 80);
        snakeLadderPosition.set(43, 76);
        snakeLadderPosition.set(29, 74);
        snakeLadderPosition.set(71, 89);
        snakeLadderPosition.set(30, 7);
        snakeLadderPosition.set(47, 15);
        snakeLadderPosition.set(56, 19);
        snakeLadderPosition.set(73, 51);
        snakeLadderPosition.set(92, 75);
        snakeLadderPosition.set(98, 55);
        snakeLadderPosition.set(82, 42);
    }

    public int getNewPosition(int currentPosition) {
        if(currentPosition>0 && currentPosition<=100) {
            return snakeLadderPosition.get(currentPosition);
        }
        return -1;
    }
    int getXCoordinate(int position) {
        if(position >= 1 && position <= 100)
            return positionCoordinates.get(position).getKey();
        return -1;
    }

    int getYCoordinate(int position) {
        if(position >= 1 && position <= 100)
            return positionCoordinates.get(position).getValue();
        return -1;
    }

    public static void main(String[] args) {
        Board board = new Board();
    }
}