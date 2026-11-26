import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("Bus Out - Уровень");
        setSize(589, 1050);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new GamePanel(this));
        setVisible(true);
    }
}

class GamePanel extends JPanel {
    private final JFrame parentFrame;
    private BufferedImage backgroundImg;
    private BufferedImage busImg;
    private BufferedImage guyImg;
    private BufferedImage restartButtonImg;
    private BufferedImage closeButtonImg;

    // Позиция и размеры жёлтого автобуса
    private int busX = 180;
    private int busY = 360;
    private final int busW = 120;
    private final int busH = 120;

    // Невидимая целевая точка для расчёта движения
    private final int targetX = 500;
    private final int targetY = 120;
    private boolean movingToSlot = false;

    // Кнопки на экране уровня
    private final Rectangle restartBounds = new Rectangle(560, 20, 100, 100);
    private final Rectangle exitBounds = new Rectangle(665, 20, 100, 100);

    public GamePanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        try {
            backgroundImg = ImageIO.read(new File("src/img/backgroung.png"));
            busImg = ImageIO.read(new File("src/img/yellow_bus.png"));
            guyImg = ImageIO.read(new File("src/img/blue_guy.png"));
            restartButtonImg = ImageIO.read(new File("src/img/restart_button.png"));
            closeButtonImg = ImageIO.read(new File("src/img/close_button.png"));
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки спрайтов уровня из src/img", e);
        }

        Timer moveTimer = new Timer(16, e -> {
            if (!movingToSlot) return;

            int speed = 6;
            if (busX < targetX) busX = Math.min(busX + speed, targetX);
            if (busX > targetX) busX = Math.max(busX - speed, targetX);
            if (busY < targetY) busY = Math.min(busY + speed, targetY);
            if (busY > targetY) busY = Math.max(busY - speed, targetY);

            if (busX == targetX && busY == targetY) {
                movingToSlot = false;
            }
            repaint();
        });
        moveTimer.start();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();
                Rectangle busBounds = new Rectangle(busX, busY, busW, busH);

                if (busBounds.contains(p)) {
                    movingToSlot = true;
                } else if (restartBounds.contains(p)) {
                    // Кнопка рестарта пока просто отрисована; логику добавим позже
                } else if (exitBounds.contains(p)) {
                    parentFrame.dispose();
                    SwingUtilities.invokeLater(MenuFrame::new);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImg != null) {
            g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
        }

        // Человечек рядом с зоной посадки
        if (guyImg != null) {
            g.drawImage(guyImg, 445, 125, 30, 60, this);
        }

        // Жёлтый автобус
        if (busImg != null) {
            g.drawImage(busImg, busX, busY, busW, busH, this);
        }

        // Кнопка рестарта (просто нарисована)
        if (restartButtonImg != null) {
            g.drawImage(restartButtonImg, restartBounds.x, restartBounds.y, restartBounds.width, restartBounds.height, this);
        }

        // Рабочая кнопка выхода
        if (closeButtonImg != null) {
            g.drawImage(closeButtonImg, exitBounds.x, exitBounds.y, exitBounds.width, exitBounds.height, this);
        }
    }
}

