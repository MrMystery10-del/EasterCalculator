import front.GuiRoot;
import front.ImageManager;
import front.MainScreen;

public class Start {

    // Program start
    public static void main(String[] args) {
        System.out.println("I: Starting...");

        // Base parameters
        short width = 1200;
        short height = 800;

        ImageManager imageManager = new ImageManager();
        MainScreen screen1 = new MainScreen(width, height, imageManager);
        GuiRoot guiRoot = new GuiRoot(width, height, screen1, imageManager);

        guiRoot.setUp();
    }
}
