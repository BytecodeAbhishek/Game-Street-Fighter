package com.abhishek.gaming.canvas;

import com.abhishek.gaming.sprites.OpponentPlayer;
import com.abhishek.gaming.sprites.Player;
import com.abhishek.gaming.sprites.Power;
import com.abhishek.gaming.utils.GameConstants;
//import static com.abhishek.gaming.utils.GameConstants.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Board extends JPanel implements GameConstants {
    BufferedImage imageGameBackground;
    private Player player;
    private OpponentPlayer opp;
    // implementing multithreading
    private Timer timer;
    private Power ryuFullPower;
    private Power kenFullPower;
    private boolean gameOver;
    // to implement the power bar
    private void loadPower(){
        ryuFullPower = new Power(30,"RYU");
        kenFullPower = new Power(GWIDTH-570,"KEN");
    }
    private void printFullPower(Graphics g){
        ryuFullPower.printRectangle(g);
        kenFullPower.printRectangle(g);
    }
    private void gameLoop(){
       timer = new Timer(GAME_LOOP, new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               repaint();
               if(gameOver){
                   timer.stop();
               }
               player.fall();
               opp.fall();
               collision();
               isGameOver();
           }
       });
       timer.start();
    }
    // method for collision
    private boolean isCollide(){
        int xDistance = Math.abs(player.getX() - opp.getX());
        int yDistance = Math.abs(player.getY() - opp.getY());
        int maxH = Math.max(player.getH(),opp.getH());
        int maxW = Math.max(player.getW(),opp.getW());
        return xDistance <= maxW && yDistance <= maxH;
    }
    private void collision(){
        if(isCollide()){
            if(player.isAttacking() && opp.isAttacking()){
                opp.setCurrentMove(DAMAGE);
                kenFullPower.setHealth();
                player.setCurrentMove(DAMAGE);
                ryuFullPower.setHealth();
            }
            if(player.isAttacking()){
               opp.setCurrentMove(DAMAGE);
               kenFullPower.setHealth();
            }else if(opp.isAttacking()){
                player.setCurrentMove(DAMAGE);
                ryuFullPower.setHealth();
            }
            player.setCollide(true);
            opp.setCollide(true);
            // player will not move
            //System.out.println("Collision........");
            player.setSpeed(0);
            opp.setSpeed(0);
        }
        else{
            player.setCollide(false);
            opp.setCollide(false);
            player.setSpeed(SPEED);
            opp.setSpeed(SPEED);
        }
    }
    private void isGameOver(){
       if(ryuFullPower.getHealth() <= 0 || kenFullPower.getHealth() <= 0){
           gameOver = true;
       }
    }
    private void printGameOver(Graphics pen ){
        if(gameOver) {
            pen.setFont(new Font("times", Font.BOLD, 40));
            pen.drawString("Game Over", GWIDTH / 2 - 100, GHEIGHT / 2 - 100);
        }
    }
    public Board() throws Exception{
        player = new Player();
        opp = new OpponentPlayer();
        loadBackgroundImage();
        setFocusable(true);
        bindEvents();
        gameLoop();
        loadPower();
    }
    // moving thew player left or right
    private void bindEvents(){
        KeyListener listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("Typed" +e.getKeyCode()+ " "+e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // Ryu Player
                if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    player.setSpeed(-SPEED);
                    // because we need to move left to make the value of collision false
                    player.setCollide(false);
                    player.move();
                    // no longer needed because of timer
                    //repaint();
                }
                else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    player.setSpeed(SPEED);
                    player.move();
                    //repaint();
                }
                // ryu Kick
                else if(e.getKeyCode() == KeyEvent.VK_K){
                    player.setCurrentMove(KICK);
                    // kick will attack
                    //player.setAttacking(true);
                }
                // ryu punch
                else if(e.getKeyCode() == KeyEvent.VK_P){
                    player.setCurrentMove(PUNCH);
                }
                // ryu jump
                else if(e.getKeyCode() == KeyEvent.VK_SPACE){
                    player.jump();
                }
                // Ken player
                else if(e.getKeyCode() == KeyEvent.VK_A){
                    opp.setSpeed(-SPEED);
                    opp.move();
                    //repaint();
                }
                else if(e.getKeyCode() == KeyEvent.VK_D){
                    opp.setSpeed(SPEED);
                    opp.setCollide(false);
                    opp.move();
                    //repaint();
                }
                // ken kick
                else if (e.getKeyCode() == KeyEvent.VK_E) {
                    opp.setCurrentMove(KICK);
                }
                // ken punch
                else if (e.getKeyCode() == KeyEvent.VK_Q) {
                    opp.setCurrentMove(PUNCH);
                }
                // ken jump
                else if (e.getKeyCode() == KeyEvent.VK_W) {
                    opp.jump();
                }
                System.out.println("Pressed" +e.getKeyCode()+ " "+e.getKeyChar());
            }
            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("Released" +e.getKeyCode()+ " "+e.getKeyChar());
            }
        };
        // attaching with the board
        this.addKeyListener(listener);
    }
    @Override
    public void paintComponent(Graphics pen){
        // Rendering , Painting
        printBackgroundImage(pen);
        // already drawn in player class
        //player.drawPlayer(pen);
        player.printPlayer(pen);
        //opp.drawPlayer(pen);
        opp.printPlayer(pen);
        printFullPower(pen);
        printGameOver(pen);
    }
    // Used to print the images on the screen
    private void printBackgroundImage(Graphics pen){
        pen.drawImage(imageGameBackground,0,0,GWIDTH,GHEIGHT,null);
    }
    private void loadBackgroundImage(){
        try {
            imageGameBackground = ImageIO.read(Board.class.getResource(BG_IMG));
        }
        catch (Exception ex){
            System.out.println("Image Not Found");
            System.exit(0);
        }
    }
}
