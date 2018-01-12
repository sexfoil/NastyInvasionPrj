package mygame;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


/**
 * Created by SiXFOiL on 06.07.2017.
 */
public class InfoDesc extends Label {

    private Target target;

    public InfoDesc(Target target) {
        this.target = target;
        setTranslateX(1065);
        setTranslateY(10);
        //setFont(new Font("PlayBill", 42));
        setFont(new Font("impact", 24));
        setTextFill(Color.RED);
        setInfo(false, 0);
        setPrefSize(300, 150);
    }

    public void setInfo(boolean gameOver, int score) {

        setText((gameOver ? "GAME OVER!\n\n" : "") + "Stopped: " + target.getKilledMexicans() + "\nMissed: " + target.getPassedMexicans() +
        "\nLevel: " + target.getStep() + (gameOver ? ("\n\nYOUR SCORE: " + score) : ""));
    }
}
