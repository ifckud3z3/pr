import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
public class MenuFrame extends JFrame {
    private BufferedImage backgroundImg;
    private BufferedImage closeButtonImg;

    // Заглушка-область для старта игры (пока нет отдельной картинки кнопки старта)
    private final Rectangle startBounds = new Rectangle(300, 220, 200, 65);
    // Реальная кнопка выхода
    private final Rectangle exitBounds = new Rectangle(350, 310, 100, 100);

    public MenuFrame() {
        try {
            backgroundImg = ImageIO.read(new File("src/img/backgroung.png"));
            closeButtonImg = ImageIO.read(new File("src/img/close_button.png"));
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки файлов из src/img", e);
        }

        setTitle("Bus Out - Меню");
        setSize(589, 1050);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();
                if (startBounds.contains(p)) {
                    dispose();
                    SwingUtilities.invokeLater(GameFrame::new);
                } else if (exitBounds.contains(p)) {
                    System.exit(0);
                }
            }
        });

        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (backgroundImg != null) {
            g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
        }

        // Временная кнопка "Старт"
        g.setColor(new Color(40, 167, 69));
        g.fillRect(startBounds.x, startBounds.y, startBounds.width, startBounds.height);
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 20));
        g.drawString("СТАРТ", startBounds.x + 62, startBounds.y + 40);

        if (closeButtonImg != null) {
            g.drawImage(closeButtonImg, exitBounds.x, exitBounds.y, exitBounds.width, exitBounds.height, this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuFrame::new);
    }
}
