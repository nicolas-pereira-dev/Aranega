package sample;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{
    static final int SCREEN_RATIO = 3;
    int x = 0;
    int y = 0;

    public static void main (String args []) {
        Application.launch(args);
    }

    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        TextureLoader txl = new TextureLoader();
        Ara ara = new Ara(348, 459);

        txl.loadLevel("level1", pane);
        ara.loadAra(pane);

        Scene scene = new Scene(pane);

        keyboardEvents(scene, ara, txl);

        stage.setScene(scene);
        stage.show();
        stage.setTitle("Aragena's Adventure");
        stage.setOnCloseRequest(e->{
            System.exit(0);
        });


        //Timer
        Timer fall = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
//                      play(platformFile, pane, doodle);
                        ara.gravity(txl);
                        ara.animateAra(pane);
                    }
                });
            }
        };
        fall.schedule(task, 0, 60);

    }

    private void keyboardEvents(Scene scene, Ara ara, TextureLoader txl) {
        scene.setOnKeyPressed( new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.LEFT))
                    ara.setState(State.RuningLeft);
                if(event.getCode().equals(KeyCode.RIGHT))
                    ara.setState(State.RuningRight);
                if(event.getCode().equals(KeyCode.CONTROL))
                    ara.setSprint(true);
                if(event.getCode().equals(KeyCode.SPACE)) {
                    if(ara.getState().ordinal()%2 == 1)
                        ara.setState(State.JumpingLeft);
                    else
                        ara.setState(State.JumpingRight);
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.LEFT))
                    ara.setState(State.StandingLeft);
                if(event.getCode().equals(KeyCode.RIGHT))
                    ara.setState(State.StandingRight);
                if(event.getCode().equals(KeyCode.CONTROL))
                    ara.setSprint(false);
            }
        });
    }

//    private void mouse(Pane pane, Object doodle, Scene scene) {
//        scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
//            public void handle(MouseEvent event) {
//                //Left
//                if(event.getX()<WIDTH){ //doodle.getX()+Doodle.WIDTH/2
//                    ImageView tmp = doodle.getImageView();
//                    doodle.left();
//                    int idx = 0;
//                    while (!pane.getChildren().get(idx).equals(tmp))
//                        idx++;
//                    pane.getChildren().set(idx, doodle.getImageView());
//                }
//                //Right
//                else{
//                    ImageView tmp = doodle.getImageView();
//                    doodle.right();
//                    int idx = 0;
//                    while (!pane.getChildren().get(idx).equals(tmp))
//                        idx++;
//                    pane.getChildren().set(idx, doodle.getImageView());
//
//                }
//            }
//        });
//    }

//    private void play(File platformFile, Pane pane, Doodle doodle) {
//        idx = 0;
//        while(idx < platformsList.size()) {
//            DoodlePlatform platform = platformsList.get(idx);
//            if(platform.getY() > 900) {
//                pane.getChildren().remove(platform.getImageView());
//                platformsList.remove(idx);
//                Random rd = new Random();
//                createPlatform(platformFile, pane, rd.nextInt(412), -50);
//            }
//            if(!pose && !jump)
//                pose = platform.areYouOn(doodle.getX(), doodle.getY()+Doodle.HEIGHT);
//            idx++;
//        }
//        if(!pose) {
//            if(inertie > 0.0)
//                inertie=inertie-0.1;
//            else
//                jump = false;
//            doodle.applyForces(GRAVITY - inertie);
//        }else {
//            inertie = 10.0;
//            pose = false;
//            jump = true;
//        }
//
//        if(doodle.getY()<HEIGHT/4)
//            goDown();
//        if(doodle.getY()<HEIGHT/7)
//            goDown();
//
//    }

}