package mygame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class NastyInvasionMain extends Application {

    private int qty = 5;
    private int maxPassedMexicans = 50;

    private Trump trump;
    private Bombshell bomb;
    private Target[] targets = new Target[qty];
    private InfoDesc info;
    private int score = 0;
    private Player backgroundMusic;

    private boolean statWrite = true;
    private boolean startGame = false;
    private boolean gameOver = false;

    private Stage primaryStage;
    private Scene scene;
    private Pane root;
    private Label startLabel;


    @Override
    synchronized public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        root = new Pane();
        //startLabel = new Label("Donald J. Trump\n*************\nNa$ty666\t\t\t     <START>");
        startLabel = new Label("Donald J. Trump\n*************\nNa$ty666\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t [START]");
        startLabel.relocate(410, 564);
        startLabel.setFont(new Font("impact", 24));
        //startLabel.setFont(new Font("Nasalization", 28));
        startLabel.setTextFill(Color.BLACK);
        startLabel.setOnMouseClicked(event -> startGame());
        root.getChildren().addAll(startLabel);
        scene = new Scene(root, 1200, 674);

        scene.getStylesheets().add(getClass().getResource("/start.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Nasty Invasion by Slava Poliakov v.1.0714");

        //System.out.println(javafx.scene.text.Font.getFamilies());

        primaryStage.show();
        primaryStage.setResizable(false);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (startGame && !primaryStage.isIconified()) {
                    update();
                }
                if (gameOver) {
                    endGame();
                }
            }
        };
        timer.start();
    }


    public void startGame() {

        scene.getStylesheets().add(getClass().getResource("/game.css").toExternalForm());

        startGame=true;
        scene.setOnMouseMoved(event -> trump.move((int) event.getY()));
        scene.setOnMouseClicked(event -> fire());
        root.getChildren().removeAll(startLabel);

        trump = new Trump();
        bomb = new Bombshell(trump);
        for (int i = 0; i < qty; i++) {
            int distance = (int) (500 * Math.random());
            targets[i] = new Target(bomb, 1100 + distance, 150 + 100 * i);
        }
        info = new InfoDesc(targets[0]);
        backgroundMusic = new Player("labamba.wav", 2, true);
        for (Target t : targets) {
            root.getChildren().add(t);
        }
        root.getChildren().add(trump);
        root.getChildren().add(bomb);

        root.getChildren().add(info);
        primaryStage.setOnCloseRequest(event -> backgroundMusic.getT().stop());
    }

    public void update() {
        bomb.bombMoving();
        for (Target t : targets) {
            t.targetMoving();
            if ((t.getKilledMexicans() != 0 && t.getKilledMexicans() % 10 == 0)) {
                if (statWrite) {
                    new Player("arriba.wav", 5, false);
                    t.increaseStep(t.getKilledMexicans());
                    score = t.getKilledMexicans()*t.getStep() - t.getPassedMexicans()*3;
                    statWrite = false;
                }
            } else {
                if (!statWrite) {
                    statWrite = true;
                }
            }
            if (t.getPassedMexicans() >= maxPassedMexicans) {
                gameOver = true;
            }
        }
        info.setInfo(gameOver, score);
        if (gameOver) {
            startGame = false;
        }
    }

    public void endGame() {
        for (Target t : targets) {
            root.getChildren().removeAll(t);
        }
        root.getChildren().removeAll(trump);
        root.getChildren().removeAll(bomb);
        backgroundMusic.setPlaying(false);
        scene.getStylesheets().add(getClass().getResource("/end.css").toExternalForm());
        info.setTranslateX(300);
        info.setTranslateY(180);
        info.setFont(new Font("impact", 44));
        info.setTextFill(Color.WHITE);
        info.setPrefSize(600, 400);
        info.setInfo(true, score);
    }

    public void fire() {
        if (!bomb.getIsFlying()) {
            new Player("shoot.wav", 4, false);
            trump.changeImageTrump(false);
            bomb.setTranslateX(0);
            bomb.setTranslateY(trump.getTranslateY() + 50);
            bomb.setIsFlying(true);
        }
    }



    synchronized public static void main(String[] args) {
        launch(args);
    }
}
