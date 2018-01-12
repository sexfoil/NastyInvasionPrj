package mygame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Created by SiXFOiL on 21.06.2017.
 */
public class Bombshell extends Pane {

    private boolean isFlying = false;

    private Images image = new Images();
    private ImageView imageBombshell;

    private Trump trump;

    public Bombshell(Trump trump) {
        this.trump = trump;
        imageBombshell = new ImageView(new Image(image.getBombImage()));

        setTranslateX(trump.getTranslateX());
        setTranslateY(trump.getTranslateY());

        getChildren().addAll(imageBombshell);
        setVisible(false);
    }

    public void bombMoving() {
        if (isFlying) {
            if (!isVisible()) {
                setVisible(true);
            }
            int currentX = (int) getTranslateX();
            setTranslateX(currentX + 20);
            if (getTranslateX() > 1200) {
                refreshBomb();
            }
        }
    }

    public void refreshBomb() {
        setVisible(false);
        isFlying = false;
        trump.changeImageTrump(true);
    }

    public void setIsFlying(boolean flying) {
        isFlying = flying;
    }

    public boolean getIsFlying() {
        return isFlying;
    }
}
