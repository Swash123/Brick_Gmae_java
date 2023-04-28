package com.System;

import javax.swing.*;

public class Window extends JFrame {

    Gameplay gameplay;
    Window(){
        gameplay=new Gameplay();
        setSize(700,610);
        setTitle("Brick Game");
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(gameplay);

    }

}
