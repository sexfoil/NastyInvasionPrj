package mygame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


/**
 * Created by SiXFOiL on 22.06.2017.
 */
public class Target extends Pane {

    private Images image = new Images();
    private ImageView imageTarget;
    private Bombshell bomb;

    private Image boomImage = new Image(image.getBoomImage());

    private static int passedMexicans = 0;
    private static int killedMexicans = 0;
    private int deadTimes = 10;
    private int deadTime = 0;
    private static int step = 1;
    private boolean alive = true;


    public Target(Bombshell bomb, int x, int y) {
        this.bomb = bomb;
        //this.player = player;
        setTranslateX(x);
        setTranslateY(y);
        setPrefSize(178, 111);
        Image img = new Image(image.getRandomMexican());
        imageTarget = new ImageView(img);
        getChildren().addAll(imageTarget);
        setVisible(true);
    }

    public void targetMoving() {
        if (alive) {
            int currentX = (int) getTranslateX();
            currentX -= step;
            setTranslateX(currentX);
            if (bomb.getTranslateX() - 20 > getTranslateX()) {
                if (bomb.getIsFlying() &&
                        !((bomb.getTranslateY() > getTranslateY() + 111) || (bomb.getTranslateY() + 40 < getTranslateY()))) {
                    alive = false;
                    killedMexicans++;
                    new Player("boom.wav", 4,false);
                    bomb.refreshBomb();
                }
            }
            if (getTranslateX() < -20) {

                passedMexicans++;
                new Player("whistle.wav", 4,false);
                setTranslateX(1200);
            }
        } else if (deadTime == 0) {
                imageTarget.setImage(boomImage);
                deadTime++;

        } else if (deadTime < deadTimes) {
            deadTime++;
        } else {
            imageTarget.setImage(new Image(image.getRandomMexican()));
            deadTime = 0;
            setTranslateX(1200);
            alive = true;
        }
    }

    public static int getPassedMexicans() {
        return passedMexicans;
    }

    public static int getKilledMexicans() {
        return killedMexicans;
    }

    public static int getStep() {
        return step;
    }

    public static void increaseStep(int totalKilled) {
        step = 1 + 1 * (totalKilled / 10);
    }

}
