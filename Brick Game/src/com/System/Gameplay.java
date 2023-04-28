package com.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private Bricks map;

    private boolean play=false;
    private int score=0;
    private int totalBrick=21;
    private Timer timer;
    private int delay =8;



    private int paddleX=310;

    private int ballPosX=120;
    private int ballPosY=350;
    private int ballDirX=3;
    private int ballDirY=1;





    Gameplay(){
        map=new Bricks(5,7);


        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
        timer.start();
    }


    public void paint(Graphics g){
        //bg
        g.setColor(Color.BLACK);
        g.fillRect(0,0,700,610);
        



        //bricks
        map.draw((Graphics2D) g);

        //borders

        g.setColor(Color.GREEN);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,683,3);
        g.fillRect(683,0,3,592);


        //the paddle

        g.setColor(Color.BLUE);
        g.fillRect(paddleX,550,100,8);

        //the ball
        g.setColor(Color.YELLOW);
        g.fillOval(ballPosX,ballPosY,20,20);

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if(play){

            if(new Rectangle(ballPosX,ballPosY,20,20).intersects(new Rectangle(paddleX,550,100,8))){
                ballDirY=-ballDirY;
            }

            here:
            for (int i = 0; i<map.brick.length; i++){
                for (int j=0;j<map.brick[i].length;j++){
                    if(map.brick[i][j]>0){
                        int brickX=j*map.brickWidth+80;
                        int brickY=i*map.brickHeight+50;
                        int brickWidth=map.brickWidth;
                        int brickHeight=map.brickHeight;

                        Rectangle brickRect=new Rectangle(brickX,brickY,brickWidth,brickHeight);
                        Rectangle ballRect=new Rectangle(ballPosX,ballPosY,20,20);

                        if(ballRect.intersects(brickRect)){
                            map.changeBrick(0,i,j);
                            totalBrick--;
                            score+=5;

                            if(ballPosX+19<=brickRect.x||ballPosX+1>=brickRect.x+brickRect.width){
                                ballDirX=-ballDirX;
                            }else{
                                ballDirY=-ballDirY;
                            }
                            break here;
                        }

                    }
                }
            }

            ballPosX+=ballDirX;
            ballPosY+=ballDirY;
            if(ballPosX<0){
                ballDirX=-ballDirX;
            }
            if(ballPosY<0){
                ballDirY=-ballDirY;
            }
            if(ballPosX>663){
                ballDirX=-ballDirX;
            }
        }

        if(ballPosY > 580){
            play=false;
            JOptionPane.showMessageDialog(this,"hahaha noop can't even use bug to win");
            ballPosX=120;
            ballPosY=350;
            ballDirX=3;
            ballDirY=1;
            totalBrick=21;
            for (int i = 0; i<map.brick.length; i++) {
                for (int j = 0; j < map.brick[i].length; j++) {
                    map.changeBrick(1,i,j);
                }
            }

        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode()==KeyEvent.VK_RIGHT){

            if(paddleX>=600){
                paddleX=600;
            }else{
                moveRight();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){

            if(paddleX<=0){
                paddleX=0;
            }else{
                moveLeft();
            }
        }


    }

    private void moveRight() {
        play=true;
        paddleX+=20;
    }

    private void moveLeft() {
        play=true;
        paddleX-=20;
    }

}
