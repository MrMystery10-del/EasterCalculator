package front;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/** Manage all needed Images for the program */
public class ImageManager {

    private BufferedImage background, calculate_button, calender, picked_circle, year_circle, mark, icon;

    /** Preload all necassary images as BufferedImage */
    public ImageManager() {
        System.out.println("I: Loading all necassary images...");
        try {
            background = ImageIO.read(new File("gameDesign/images/background.png"));
            calculate_button = ImageIO.read(new File("gameDesign/images/calculate_button.png"));
            calender = ImageIO.read(new File("gameDesign/images/calender.png"));
            picked_circle = ImageIO.read(new File("gameDesign/images/picked_circle.png"));
            year_circle = ImageIO.read(new File("gameDesign/images/year_circle.png"));
            mark = ImageIO.read(new File("gameDesign/images/mark.png"));
            icon = ImageIO.read(new File("gameDesign/images/icon.png"));
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("E: Failed to load images!");
        }
    }

    /** return the Background Image as BufferedImage */
    protected BufferedImage getBackground() {
        return background;
    }

    /** return the Calculate-Button Image as BufferedImage */
    protected BufferedImage getCalculateButton() {
        return calculate_button;
    }

    /** return the Calender Image as BufferedImage */
    protected BufferedImage getCalender() {
        return calender;
    }

    /** return the Picked-Circle Image as BufferedImage */
    protected BufferedImage getPickedCircle() {
        return picked_circle;
    }

    /** return the Year-Circle Image as BufferedImage */
    protected BufferedImage getYearCircle() {
        return year_circle;
    }

    /** return the Mark Image as BufferedImage */
    protected BufferedImage getMark() {
        return mark;
    }

    /** return the Icon Image as BufferedImage */
    protected BufferedImage geticon() {
        return icon;
    }
}
