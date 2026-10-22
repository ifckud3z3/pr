import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class menu extends JFrame {
    public BufferedImage fon, button_start, button_ext, button_settings;
    MouseListener ml = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getPoint().x > 100 && e.getPoint().x < 200 && e.getPoint().y > 100 && e.getPoint().y < 200){
                new Main();
            }
            if(e.getPoint().x > 250 && e.getPoint().x < 350 && e.getPoint().y > 100 && e.getPoint().y < 200){
                System.out.println("hello");
                System.exit(0);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };
    menu()
    {
        try{
            fon = ImageIO.read(new File("/H://IMG_9665.png/"));
            button_start = ImageIO.read(new File("/H://IMG_9667.png/"));
            button_ext = ImageIO.read(new File("/H://IMG_9666.png/"));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        setSize(800, 600);
        setVisible(true);
        addMouseListener(ml);
    }
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(fon, 0, 0, this);
        g.drawImage(button_start, 100, 100, this);
        g.drawImage(button_ext, 250, 100 , this);
    }
    public static void main(String[] args){
        new menu();
    }
}
