
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main extends JFrame {
    public BufferedImage pers;
    Main()
    {
        try{
            pers = ImageIO.read(new File("/H://IMG_9617 (1).png/"));

        }catch (Exception e){
            throw new RuntimeException(e);
        }
        setSize(800, 600);
        setVisible(true);
    }
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(pers, 0, 0, this);
    }
    public static void main(String[] args){
        new Main();
    }
}
