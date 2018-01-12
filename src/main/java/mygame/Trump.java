package mygame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


/**
 * Created by SiXFOiL on 21.06.2017.
 */
public class Trump extends Pane {

    private Images image = new Images();
    private Image imgWithBomb = new Image(image.getTrumpWithBombImage());
    private Image imgNoBomb = new Image(image.getTrumpNoBombImage());
    private ImageView imageTrump;

    public Trump() {
        imageTrump = new ImageView(imgWithBomb);
        setTranslateX(0);
        setTranslateY(0);

        getChildren().addAll(imageTrump);
    }

    public void move(int eventPositionY) {
        setTranslateY(eventPositionY - 50);
    }

    public void changeImageTrump(boolean withBomb) {
        imageTrump.setImage(withBomb ? imgWithBomb: imgNoBomb);
    }
}
