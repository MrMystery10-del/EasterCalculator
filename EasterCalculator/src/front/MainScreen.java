package front;

import myst.swing.blueprint.ButtonBlueprint;

import java.util.HashMap;
import java.util.function.Consumer;

import base.ActionHandler;
import base.Functions;
import base.YearBox;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;

/**
 * The MainScreen class extends a JPanel for the main gui.
 * Everything of visible Gui happens in this class.
 * 
 * @version 1.0
 * @author Kirils Turkins / MrMystery
 */
public class MainScreen extends JPanel {

    private Functions pressFunction = new Functions();
    private ImageManager imageManager;
    private Animation animation = new Animation();

    /**
     * Constructs the main GUI. Also gives the button a
     * consumer-function using hashmap
     */
    public MainScreen(short width, short height, ImageManager imageManager) {
        System.out.println("I: Set up MainScreen...");
        this.imageManager = imageManager;
        HashMap<JButton, Consumer<ActionEvent>> buttons = new HashMap<>();

        ActionHandler actionHandler = new ActionHandler(buttons);
        YearBox yearBox = new YearBox(550, 330, 300, 70);
        ButtonBlueprint calculationButton = new ButtonBlueprint(400, 50, 400, 120, actionHandler);

        setUpPanel(width, height);

        buttons.put(calculationButton, event -> {
            int date = pressFunction.getEasternDate(yearBox.getSelectedIndex() + 2000);
            animation.setMainScreen(pressFunction.getCurrentScreen());

            calculationButton.setLocation(-1000, 0);
            yearBox.setLocation(-1000, 0);

            animation.startAnimation(date);
        });
        calculationButton.setIcon(new ImageIcon(imageManager.getCalculateButton()));

        add(calculationButton);
        add(yearBox);
    }

    // Sets the basic stats of the JPanel
    private void setUpPanel(short width, short height){
        setPreferredSize(new Dimension(width, height));
        setLayout(null);

        setBackground(Color.WHITE);
    }

    // Main paint method
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.ORANGE);
        g2d.setFont(new Font("Comic Sans MS", Font.BOLD, 120));

        if (animation.getRelativeAnimation() < 1) {
            drawBase(g2d);
        } else {
            drawAnimation(g2d);
        }
    }

    // Draws the base screen on start
    private void drawBase(Graphics2D g2d) {
        g2d.drawImage(imageManager.getBackground(), 0, 0, 1200, 800, null);
    }

    // Draws the animation and everything active happens
    private void drawAnimation(Graphics2D g2d) {
        if (animation.getStartedZoom()) {
            drawZoom(g2d);
        } else {
            drawCircle(g2d);
            if (animation.getApril() && animation.getRelativeDegree() == 2415) {
                drawPickedApril(g2d);
            } else if (animation.getApril() == false && animation.getRelativeDegree() == 2445) {
                drawPickedMarch(g2d);
            }
        }
    }

    // Draws the Zoom stage
    private void drawZoom(Graphics2D g2d) {
        g2d.setFont(new Font("Comic Sans MS", Font.BOLD, 80));

        // Draws the calender
        g2d.drawImage(imageManager.getCalender(), -animation.getRelativeZoom() + 1200,
                -animation.getRelativeYZoom() - 500, 3200, 3200, null);

        if (animation.getFinished()) {
            drawFinish(g2d);
        }
    }

    // Draws the rotating circle
    private void drawCircle(Graphics2D g2d) {
        // Draws the updated base screen
        g2d.drawImage(animation.getMainScreen(), -animation.getRelativeAnimation(), 0, 1200, 800, null);

        // Draws the rotating circle
        g2d.drawImage(pressFunction.getRotation(animation, getHeight()).filter(imageManager.getYearCircle(), null),
                -animation.getRelativeAnimation() + 1200, 0, 1200, 800, null);
    }

    // Draws end of rotation of the circle when April got selected
    private void drawPickedApril(Graphics2D g2d) {
        g2d.drawImage(imageManager.getPickedCircle(), -animation.getRelativeAnimation() + 1200, 0, 1200, 800, null);
        g2d.drawString("APRIL", 400, 600);
    }

    // Draws end of rotation of the circle when March got selected
    private void drawPickedMarch(Graphics2D g2d) {
        g2d.drawImage(imageManager.getPickedCircle(), -animation.getRelativeAnimation() + 1200, 0, 1200, 800, null);
        g2d.drawString("MÄRZ", 400, 600);
    }

    // Draws the mark on the selected date and draws a new String to the left with the full date
    private void drawFinish(Graphics2D g2d){
        g2d.drawImage(imageManager.getMark(), 350, 150, 500, 500, null);

        if (animation.getApril()) {
            g2d.drawString(animation.getCalculatedDate() + ".April", 10, 100);
        } else {
            g2d.drawString(animation.getCalculatedDate() + ".März", 10, 100);
        }
    }
}
