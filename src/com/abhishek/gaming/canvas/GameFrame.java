package com.abhishek.gaming.canvas;
import javax.swing.*;
import static com.abhishek.gaming.utils.GameConstants.*;

public class GameFrame extends JFrame {
    public GameFrame() throws Exception{
        setResizable(false);
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(GWIDTH,GHEIGHT);
        setLocationRelativeTo(null);
        Board board = new Board();
        add(board);
        setVisible(true);
    }
    public static void main(String[] args) {
        try {
            GameFrame obj = new GameFrame();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
