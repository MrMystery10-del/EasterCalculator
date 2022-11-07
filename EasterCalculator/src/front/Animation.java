package front;

import java.awt.image.BufferedImage;

/**
 * The Aniamtion class is the head of all running animations by increasing the
 * animation ticks via methods
 */
public class Animation {

    private BufferedImage mainScreen;

    private int relativeAnimation = 0;
    private int relativeDegree = 0;
    private int relativeZoom = 0;
    private int relativeYZoom = 0;

    private int xMove = 0;
    private int yMove = 0;

    private int date;

    private boolean april = false;
    private boolean finished = false;

    /**
     * Sets the new MainScreen as BufferedImage after screenshoted the Base screen
     * after update
     */
    protected void setMainScreen(BufferedImage image) {

        mainScreen = image;
    }

    /**
     * returns the new MainScreen as BufferedImage after screenshoted the Base
     * screen after update
     */
    protected BufferedImage getMainScreen() {

        return mainScreen;
    }

    /** returns the animation state as int */
    public int getRelativeAnimation() {

        return relativeAnimation;
    }

    /** returns the degree for the circle as int */
    public int getRelativeDegree() {

        return relativeDegree;
    }

    /** returns the zoom ticks for x as int */
    protected int getRelativeZoom() {

        return relativeZoom;
    }

    /** returns the zoom ticks for y as int */
    protected int getRelativeYZoom() {

        return relativeYZoom;
    }

    /** returns if the date is in april */
    protected boolean getApril() {

        return april;
    }

    /** returns if the animation is finished */
    protected boolean getFinished() {
        return finished;
    }

    /** returns if zoom state got started */
    protected boolean getStartedZoom() {
        return relativeZoom > 0;
    }

    /** start the animation ticks */
    protected void startAnimation(int d) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    date = d;
                    System.out.println("I: Enter Animation.");
                    checkForApril();
                    moveOutMainScreen();
                    fastRotation();
                    slowRotation();
                    if (april) {
                        aprilRotation();
                    } else {
                        marchRotation();
                    }
                    zoomToDate();
                } catch (InterruptedException exeception) {
                    exeception.printStackTrace();
                    System.out.println("E: Animation failed!");
                }
            }
        }).start();
    }

    // checks if the date is in april
    private void checkForApril() {
        if (date > 31) {
            date -= 31;
            april = true;
        }
    }

    // moves from MainScreen to circle
    private void moveOutMainScreen() throws InterruptedException {
        System.out.println("I: Moving MainScreen out.");
        while (relativeAnimation < 1200) {
            Thread.sleep(1);
            relativeAnimation++;
        }
    }

    // enter the fast rotation
    private void fastRotation() throws InterruptedException {
        System.out.println("I: Enter fast Rotation.");
        while (relativeDegree < 1440) {
            Thread.sleep(2);
            relativeDegree++;
        }
    }

    // enter the slow rotation
    private void slowRotation() throws InterruptedException {
        System.out.println("I: Enter slow Rotation.");
        while (relativeDegree < 2160) {
            Thread.sleep(4);
            relativeDegree++;
        }
    }

    // enter the april rotation
    private void aprilRotation() throws InterruptedException {
        System.out.println("I: Enter April Rotation.");
        while (relativeDegree < 2415) {
            Thread.sleep(10);
            relativeDegree++;
        }
    }

    // enter the march rotation
    private void marchRotation() throws InterruptedException {
        System.out.println("I: Enter March Rotation.");
        while (relativeDegree < 2445) {
            Thread.sleep(10);
            relativeDegree++;
        }
    }

    // Zooms to the right Date
    private void zoomToDate() throws InterruptedException {
        System.out.println("I: Waiting...");
        Thread.sleep(1200);

        moveToCalender();
        calculateSpotOnCalender();
        System.out.println("I: Enter Zoom to date mode.");

        doXMoves();
        doYMoves();

        System.out.println("I: Zoom completed.");
        finished = true;
    }

    // Moves to the calender from circle screen
    private void moveToCalender() throws InterruptedException {
        System.out.println("I: Moving to calender...");
        while (relativeZoom < 1000) {
            Thread.sleep(1);
            relativeZoom++;
        }
    }

    // calculate needed x and y moves for the animation
    private void calculateSpotOnCalender() throws InterruptedException {
        System.out.println("I: Calculating...");
        if (date > 31) {
            date -= 31;
        }
        for (xMove = date + 2; xMove > 7; yMove++) {
            xMove -= 7;
        }
        System.out.println("I: Needed xMoves: " + xMove + " | Needed yMoves: " + yMove + " | Date: " + date);
        xMove *= 305;
        yMove *= 220;
    }

    /** returns the new calculated date */
    protected int getCalculatedDate() {
        return date;
    }

    // Zomes to right until done calculated xMoves
    private void doXMoves() throws InterruptedException {
        System.out.println("I: Doing X moves...");
        int tempZoomX = relativeZoom;
        while (relativeZoom < tempZoomX + xMove) {
            Thread.sleep(3);
            relativeZoom++;
        }
    }

    // Zomes to left until done calculated yMoves
    private void doYMoves() throws InterruptedException {
        System.out.println("I: Doing Y moves...");
        int tempZoomY = relativeYZoom;
        while (relativeYZoom < 530) {
            Thread.sleep(2);
            relativeYZoom++;
        }
        while (relativeYZoom < tempZoomY + yMove + 530) {
            Thread.sleep(5);
            relativeYZoom++;
        }
    }
}
