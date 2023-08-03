import java.util.Random;
import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

public class DancingBadgers {
    private static PImage backgroundImage; // PImage object that represents the
    // background image
    private static Badger[] badgers; // perfect size array storing the badgers
    // present in this application

    private static Random randGen; // Generator of random numbers

    public static void main(String[] args) {
        Utility.runApplication();
    }

    /**
     * Draws and updates the application display window.
     * This callback method called in an infinite loop.
     */
    public static void draw() {
        badgers = new Badger[5];
        Utility.background(Utility.color(255, 218, 185));
        // Draw the background image to the center of the screen
        Utility.image(backgroundImage, (float)Utility.width() / 2, (float)Utility.height() / 2);

        //badgers[0] = new Badger((float)Utility.width() / 2, (float)Utility.height() / 2);
        //badgers[0].draw(); // where i is the index of the created Badger in the badgers array.
        randGen.setSeed((long) Utility.height() * Utility.width());
        for (int i = 0; i < badgers.length - 1; i++) {
            float width = (float) (randGen.nextInt(Utility.width()));
            float height = (float) (randGen.nextInt(Utility.height()));
            badgers[i] = new Badger(width, height);
            badgers[i].draw();
        }
        badgers[0].setX((float)Utility.mouseX());
        badgers[0].setY((float)Utility.mouseY());
    }

    /**
     * Checks if the mouse is over a specific Badger whose reference is provided
     * as input parameter
     *
     * @param badger reference to a specific Badger object
     * @return true if the mouse is over the specific Badger object passed as input
     * (i.e. over the image of the Badger), false otherwise
     */
    public static boolean isMouseOver(Badger badger) {
        float height = badger.image().height; //8
        float width = badger.image().width; //4
        float leftBotX = badger.getX() - (width/2); //the left bot is based on upright from my perspective (4)
        float leftBotY = badger.getY() + (height/2); //(6)

        float leftTopX = badger.getX() - (width/2);
        float leftTopY = badger.getY() - (height/2);

        float rightTopX = badger.getX() + (width/2); //(6)
        float rightTopY = badger.getY() - (height/2); //(2)


        float rightBotX = badger.getX() + (width/2); //(6)
        float rightBotY = badger.getY() + (height/2); //(6)

        if (Utility.mouseX() < leftBotX || Utility.mouseY() > leftBotY || Utility.mouseX() > rightTopX || Utility.mouseY() < rightTopY) {
        return false;
        }
        return true;
    }

    public static void setup() {
        badgers = new Badger[5];

        randGen = new Random();
        //Utility.background(rn.nextInt());
        // load the image of the background
        backgroundImage = Utility.loadImage("images" + File.separator + "background.png");

    }

    /**
     * Callback method called each time the user presses the mouse
     */

     public static void mousePressed() {
        badgers = new Badger[5];
        for (int i = 0; i < badgers.length; i++) {
            if (DancingBadgers.isMouseOver(badgers[i]) == true) {
                for (int j = 1; j < badgers.length; j++) {
                if (!DancingBadgers.isMouseOver(badgers[j])) {
                    badgers[i].startDragging();
                }
                }
            }
        }

    }

    /**
     * Callback method called each time the mouse is released
     */
    public static void mouseReleased() {
        for (int i = 0; i < badgers.length; i++) {

        }
    }

    /**
     * Callback method called each time the user presses a key
     */
    //keyPressed method below
    /**
    public static void keyPressed() {
        randGen = new Random();
        randGen.setSeed(Utility.height() * Utility.width());
        float width = (float) (randGen.nextInt(Utility.width()));
        float height = (float) (randGen.nextInt(Utility.height()));
        if (Utility.key() == 'b' || Utility.key() == 'B') {
            for (int i = 0; i < badgers.length - 1; i++) {
                if (badgers[i] == null) {
                    badgers[i] = new Badger(width, height);
                }
            }
        }
        if (Utility.key() == 'b' || Utility.key() == 'B') {
            for (int i = 0; i < badgers.length - 1; i++) {
                if (DancingBadgers.isMouseOver(badgers[i]) == true) {
                    badgers[i] = null;
                }
            }
        }
    }
    */
}

