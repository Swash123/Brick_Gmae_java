package com.System;

import java.awt.*;

public class Bricks {
    public int brick[][];
    public int brickWidth;
    public int brickHeight;
    Bricks(int rows,int cols){
        brick =new int[rows][cols];
        for (int i = 0; i< brick.length; i++){
            for (int j = 0; j< brick[i].length; j++){
                brick[i][j]=1;
            }
        }

        brickWidth=540/cols;
        brickHeight=200/rows;

    }

    public void draw(Graphics2D g){
        for (int i = 0; i< brick.length; i++) {
            for (int j = 0; j < brick[i].length; j++) {

                if(brick[i][j]>0){
                    g.setColor(Color.GRAY);
                    g.fillRect(j*brickWidth+80,i*brickHeight+50,brickWidth,brickHeight);


                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.BLACK);
                    g.drawRect(j*brickWidth+80,i*brickHeight+50,brickWidth,brickHeight);
                }
            }

        }
    }

    public void changeBrick(int value, int rows,int cols){
        brick[rows][cols]=value;
    }
}
