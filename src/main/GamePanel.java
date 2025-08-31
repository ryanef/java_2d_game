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


    Thread gameThread; 
    public GamePanel(){
            this.setPreferredSize(new Dimension(screenWidth, screenHeight));
            this.setBackground(Color.black);
            this.setDoubleBuffered(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
      // make the game loop
        while(gameThread != null){
            // as long as thread exists, repeat this
            System.out.println("This game loop has started");
        }
    }

    public void update(){

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(Color.white);
        g2D.fillRect(100, 100, tileSize, tileSize); // for now will be the player char
        g2D.dispose(); //
    }
}
