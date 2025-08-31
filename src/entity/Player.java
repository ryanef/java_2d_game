package entity;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandler keyH;
    Graphics2D g2d;


    public Player(GamePanel gamePanel, KeyHandler keyH) {
        this.gamePanel = gamePanel;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void getPlayerImage(){
        try{
            up1 =  ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/boy_up_1.png")));
            up2 =  ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/boy_right_2.png")));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void update(){
        if(keyH.upPressed==true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed==true){

            if ( keyH.upPressed ){
                direction = "up";
                y -= speed;
            }
            else if (keyH.downPressed) {
                direction = "down";
                y += speed;
            }
            else if (keyH.leftPressed) {
                direction = "left";
                x -= speed;
            }
            else if (keyH.rightPressed) {
                direction = "right";
                x += speed;
            }

            spriteCounter++;

            if(spriteCounter>15){
                if (spriteNumber==1){
                    spriteNumber=2;
                }
                else if(spriteNumber==2){
                    spriteNumber=1;
                }
                spriteCounter=0;
            }
        }

    }

    public void draw(Graphics2D g2D){
       // g2D.setColor(Color.white);
        // g2D.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize); // for now will be the player char

        BufferedImage image = null;

        switch(direction){
            case "up":
                if(spriteNumber == 1){
                    image = up1;
                }
                if(spriteNumber == 2){
                    image = up2;
                }
                break;

            case "down":
                if(spriteNumber == 1){
                    image = down1;
                }
                if(spriteNumber == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNumber == 1){
                    image = left1;
                }
                if(spriteNumber == 2){
                    image = left2;
                }

                break;
            case "right":
                if(spriteNumber == 1){
                    image = right1;
                }
                if(spriteNumber == 2){
                    image = right2;
                }
                break;
        }

        g2D.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }

}
