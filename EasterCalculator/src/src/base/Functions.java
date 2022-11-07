package base;

import java.awt.image.BufferedImage;

import front.Animation;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Robot;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class Functions {

    /** returns the eastern date as int, 0 - 31 March, 31+ April */
    public int getEasternDate(int year) {
        // calculates the eastern date for the given year
        int a = year % 4;
        int b = year % 7;
        int c = year % 19;
        int d = (19 * c + 24) % 30;
        int e = (2 * a + 4 * b + 6 * d + 5) % 7;
        int f = (c + 11 * d + 22 * e) / 451;

        return 22 + d + e - 7 * f;
    }

    /**
     * returns a new screen as BufferedImage by capturing the base screen. Used for
     * animation
     */
    public BufferedImage getCurrentScreen() {
        // gets the necassary size for the screenshot
        Rectangle rect = new Rectangle((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - 592),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - 389), 1200, 800);

        Robot robot;
        try {
            robot = new Robot();
            return robot.createScreenCapture(rect);
        } catch (AWTException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /** returns a transformation based on given parameters */
    public AffineTransformOp getRotation(Animation animation, int height) {

        AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(animation.getRelativeDegree()),
                -animation.getRelativeAnimation() + 1800, height / 2);

        return new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
    }
}
