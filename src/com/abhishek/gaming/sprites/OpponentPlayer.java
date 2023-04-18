// Ken player class
package com.abhishek.gaming.sprites;

import com.abhishek.gaming.utils.GameConstants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class OpponentPlayer extends CommonPlayer implements GameConstants {
    // array to store the different moves  while walking/punching;
    private BufferedImage walkImages[] = new BufferedImage[6];
    private BufferedImage kickImages [] = new BufferedImage[6];
    private BufferedImage punchImages [] = new BufferedImage[6];
    private BufferedImage damageEffectImages [] = new BufferedImage[5];
    public OpponentPlayer() throws Exception{
        x = GWIDTH -300;
        h = w = 200;
        y = FLOOR - h;
        image = ImageIO.read(OpponentPlayer.class.getResource(OPP_PLAYER_IMAGE));
        speed = SPEED;
        loadWalkImages();
        loadKickImages();
        loadPunchImages();
        loadDamageEffect();
    }
    public void loadDamageEffect(){
        damageEffectImages[0] = image.getSubimage(1365,3279,62,85);
        damageEffectImages[1] = image.getSubimage(1438,3276,83,89);
        damageEffectImages[2] = image.getSubimage(1537,3276,75,90);
        damageEffectImages[3] = image.getSubimage(1630,3278,67,88);
        damageEffectImages[4] = image.getSubimage(1712,3277,61,88);
    }
    public void jump(){
        if(!isJUMP) {
            isJUMP = true;
            force = -15;
            y = y + force;
        }
    }
    public void fall(){
        if(y>=(FLOOR-h)){
            isJUMP = false;
            return;
        }
        y = y + force;
        force = force + GRAVITY;
    }
    private void loadWalkImages(){
        walkImages[0] = image.getSubimage(1225,865,58,91);
        walkImages[1] = image.getSubimage(1339,866,55,90);
        walkImages[2] = image.getSubimage(1410,865,55,88);
        walkImages[3] = image.getSubimage(1483,867,52,87);
        walkImages[4] = image.getSubimage(1549,868,58,89);
        walkImages[5] = image.getSubimage(1623,868,55,88);
    }
    private void loadKickImages() {
        kickImages[0] = image.getSubimage(923, 1795,56,98);
        kickImages[1] = image.getSubimage(996, 1797,46,95);
        kickImages[2] = image.getSubimage(1063, 1779,86,116);
        kickImages[3] = image.getSubimage(1188, 1767,50,121);
        kickImages[4] = image.getSubimage(1252,1792,51,100);
        kickImages[5] = image.getSubimage(1323, 1796,60,95);
    }
    private void loadPunchImages() {
        punchImages[0] = image.getSubimage(1104,1144,59,100);
        punchImages[1] = image.getSubimage(1182,1150,65,91);
        punchImages[2] = image.getSubimage(1255,1149,105,96);
        punchImages[3] = image.getSubimage(1378,1147,67,97);
        punchImages[4] = image.getSubimage(1454,1151,58,94);
        punchImages[5] = image.getSubimage(1521,1150,58,92);
    }
    private BufferedImage printWalk(){
        isAttacking = false;
        if( imageIndex > 5){
            imageIndex = 0;
        }
        BufferedImage img = walkImages[imageIndex];
        imageIndex++; // changing image frames
        return img;
    }
    private BufferedImage printKick(){
        if( imageIndex > 5){
            imageIndex = 0;
            currentMove = WALK;
            isAttacking = false;
        }
        isAttacking = true;
        BufferedImage img = kickImages[imageIndex];
        imageIndex++; // changing image frames
        return img;
    }
    private BufferedImage printPunch(){
        if( imageIndex > 5){
            imageIndex = 0;
            currentMove = WALK;
            isAttacking = false;
        }
        isAttacking = true;
        BufferedImage img = punchImages[imageIndex];
        imageIndex++; // changing image frames
        return img;
    }
    private BufferedImage printDamageImage(){
        if( imageIndex > 4){
            imageIndex = 0;
            currentMove = WALK;
        }
        BufferedImage img = damageEffectImages[imageIndex];
        imageIndex++; // changing image frames
        return img;
    }
//    @Override
//    public BufferedImage walk(){
//        return image.getSubimage(934,1261,56,100);
//    }
    @Override
    public BufferedImage defaultImage(){
        if(currentMove == WALK){
            return printWalk();
        }
        else if(currentMove == KICK){
            return printKick();
        }
        else if(currentMove == DAMAGE){
            return printDamageImage();
        }
        else{
            return printPunch();
        }
    }
}
