package com.abhishek.gaming.sprites;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.abhishek.gaming.utils.GameConstants.MAX_HEALTH;

public abstract class CommonPlayer {
    protected int x;
    protected int y;
    protected int w;
    protected int h;
    protected BufferedImage image;
    protected int speed;
    protected int imageIndex;
    protected int currentMove;
    protected boolean isCollide;
    protected boolean isAttacking;
    protected int health;

    public int getHealth() {
        return health;
    }
    public void setHealth() {
        this.health = (int)(this.health - MAX_HEALTH * 0.10);
    }
    public CommonPlayer(){
        health = MAX_HEALTH;
    }
    public boolean isAttacking() {
        return isAttacking;
    }

    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }

    public boolean isCollide() {
        return isCollide;
    }

    public void setCollide(boolean collide) {
        isCollide = collide;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    protected int force;
    protected boolean isJUMP;

    public int getCurrentMove() {
        return currentMove;
    }

    public void setCurrentMove(int currentMove) {
        this.currentMove = currentMove;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
//    public abstract BufferedImage walk();
    public abstract BufferedImage defaultImage();
    public void printPlayer(Graphics pen){
        pen.drawImage(defaultImage(),x,y,w,h,null);
    }
    // we use graphics to draw
//    public void drawPlayer(Graphics pen){
//        pen.drawImage(walk(),x,y,w,h,null);
//    }
    public void move (){
        if(!isCollide) {
            x = x + speed;
        }
    }
}
