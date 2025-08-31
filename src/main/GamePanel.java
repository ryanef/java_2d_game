package main;
import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //    screen settings
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int maxScreenColumns = 16; //horizontal
    final int maxScreenRows = 12; //vertical 4:3 ratio
    final int screenWidth = tileSize * maxScreenColumns; // 768 pixels
    final int screenHeight = tileSize * maxScreenRows; // 48 * 12 = 576px

    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;  // speed will be thought of as PIXELS.

    public GamePanel(){
            this.setPreferredSize(new Dimension(screenWidth, screenHeight));
            this.setBackground(Color.black);
            this.setDoubleBuffered(true);
            this.addKeyListener(keyH);
            this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }



    @Override
    public void run() {
        double drawScreenDelay = (double) 1000000000 / FPS; // every 0.0166 seconds the screen will be redrawn at 60 FPS

        double nextDrawTime = System.nanoTime()+drawScreenDelay; // current system time in nanoseconds + the drawScreenDelay(0.0166s)

        while(gameThread != null){
            // as long as thread exists, repeat

            System.out.println("This game loop has started");

            update();
            repaint();
            try {
                double timeRemaining = nextDrawTime - System.nanoTime();
                timeRemaining = timeRemaining/1000000;

                if (timeRemaining < 0) {
                    timeRemaining = 0;
                }
                Thread.sleep( (long) timeRemaining);

                nextDrawTime += drawScreenDelay;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
   // upper left corner is x 0 y 0. Y values increase as they go down. X values increase to the right
    public void update(){
        if ( keyH.upPressed ){
            playerY -= playerSpeed;
        }
        else if (keyH.downPressed) {
            playerY += playerSpeed;
        }
        else if (keyH.leftPressed) {
            playerX -= playerSpeed;
        }
        else if (keyH.rightPressed) {
            playerX += playerSpeed;
        }
    }
    // after a key press, this paintComponent will redraw the rectangle with the new x y
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(Color.white);
        g2D.fillRect(playerX, playerY, tileSize, tileSize); // for now will be the player char
        g2D.dispose(); //
    }
}
