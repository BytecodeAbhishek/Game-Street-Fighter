// Ryu Player Class
package com.abhishek.gaming.sprites;
import static com.abhishek.gaming.utils.GameConstants.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Player extends CommonPlayer{
    // array to store the different moves  while walking/punching;
    private BufferedImage walkImages[] = new BufferedImage[6];
    private BufferedImage kickImages [] = new BufferedImage[6];
    private BufferedImage punchImages [] = new BufferedImage[6];
    private BufferedImage damageEffectImages [] = new BufferedImage[5];
    public Player() throws Exception{
        x = 100;
        h = w = 200;
        y = FLOOR - h;
        image = ImageIO.read(Player.class.getResource(PLAYER_IMAGE));
        speed = 0;
        loadWalkImages();
        loadKickImages();
        loadPunchImages();
        loadDamageEffect();
    }
    public void loadDamageEffect(){
        damageEffectImages[0] = image.getSubimage(335,2534,72,90);
        damageEffectImages[1] = image.getSubimage(248,2537,75,88);
        damageEffectImages[2] = image.getSubimage(127,2451,67,68);
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
    // method to implement walk images
    private void loadWalkImages(){
        walkImages[0] = image.getSubimage(64,237,68,95);
        walkImages[1] = image.getSubimage(147,238,65,97);
        walkImages[2] = image.getSubimage(229,236,57,97);
        walkImages[3] = image.getSubimage(306,236,55,96);
        walkImages[4] = image.getSubimage(379,233,58,99);
        walkImages[5] = image.getSubimage(456,241,59,91);
    }
    private void loadKickImages() {
        kickImages[0] = image.getSubimage(38, 1040,73,105);
        kickImages[1] = image.getSubimage(123, 1039,65,106);
        kickImages[2] = image.getSubimage(199, 1037,118,110);
        kickImages[3] = image.getSubimage(327, 1045,71,99);
        kickImages[4] = image.getSubimage(405,1044,70,99);
        kickImages[5] = image.getSubimage(480, 1047,97,103);
    }
    private void loadPunchImages() {
        punchImages[0] = image.getSubimage(24,819,70,106);
        punchImages[1] = image.getSubimage(105,816,72,104);
        punchImages[2] = image.getSubimage(187,817,115,103);
        punchImages[3] = image.getSubimage(310,819,79,107);
        punchImages[4] = image.getSubimage(401,817,108,105);
        punchImages[5] = image.getSubimage(518,816,76,105);
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
        if( imageIndex > 2){
            imageIndex = 0;
            currentMove = WALK;
        }
        BufferedImage img = damageEffectImages[imageIndex];
        imageIndex++; // changing image frames
        return img;
    }
//    @Override
//    public BufferedImage walk(){
//        return image.getSubimage(28,820,63,99);
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
