package com.abhishek.gaming.sprites;

import java.awt.*;
import java.awt.image.BufferedImage;

// implementing the health bar of both the players
public class Power extends CommonPlayer{
    String playerName;
    public Power(int x , String playerName){
        this.x = x;
        this.playerName = playerName;
        y =100;
        h = 40;
        w = 500;
    }
    @Override
    public BufferedImage defaultImage() {
        return null;
    }
    public void printRectangle(Graphics pen){
        pen.setColor(Color.RED);
        pen.fillRect(x,y,w,h);
        pen.setColor(Color.GREEN);
        pen.fillRect(x,y,health,h);
        pen.setColor(Color.WHITE);
        pen.setFont(new Font("times",Font.BOLD,30));
        pen.drawString(playerName,x,y+h+40);
    }
}
