package front;

import javax.swing.JFrame;

/**
 * The GuiRoot class extends a JFrame which is used for the base structure
 * 
 * @version 1.0
 * @author Kirils Turkins / MrMystery
 */
public class GuiRoot extends JFrame {

    private MainScreen screen1;
    private ImageManager imageManager;

    /** Constructs the JFrame */
    public GuiRoot(short width, short height, MainScreen screen1, ImageManager imageManager) {
        System.out.println("I: Set up Frame...");
        this.screen1 = screen1;
        this.imageManager = imageManager;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Oster-Sonntag Rechner!");
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /** Sets the Frame up */
    public void setUp() {
        setIconImage(imageManager.geticon());
        setContentPane(screen1);
        pack();
        setVisible(true);

        startRepaintLoop();
    }

    // Loop to repaint the screen every 0,01 seconds
    private void startRepaintLoop() {
        new Thread(new Runnable() {
            public void run() {
                for (;;) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException exeception) {
                        exeception.printStackTrace();
                    }
                    screen1.repaint();
                }
            }
        }).start();
    }
}