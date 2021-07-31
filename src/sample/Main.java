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
        ara.loadAraImages(pane);

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
                        System.out.println("========================");
                        for(State s : State.values()){
                            if(ara.stateIsActive(s)) System.out.println(s);
                        }
                        System.out.println("========================");
                    }
                });
            }
        };
        fall.schedule(task, 0, 360);

    }

    private void keyboardEvents(Scene scene, Ara ara, TextureLoader txl) {
        scene.setOnKeyPressed( new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                move(ara, event.getCode());
                if(event.getCode().equals(KeyCode.CONTROL))
                    ara.setSprint(true);

                if(event.getCode().equals(KeyCode.SPACE) && ara.isGrounded()) {
                    boolean lookRight = ara.stateIsActive(State.StandingRight) || ara.stateIsActive(State.RunningRight);
                    if(lookRight)
                        ara.changeState(State.JumpingRight, true);
                    else
                        ara.changeState(State.JumpingLeft, true);
                }
                ara.changeState(State.StandingRight, false);
                ara.changeState(State.StandingLeft, false);
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                stopMove(ara, event.getCode());
                if(event.getCode().equals(KeyCode.CONTROL))
                    ara.setSprint(false);
//                if(event.getCode().equals(KeyCode.SPACE) && ara.isGrounded()) {
//                    ara.actionMaker.wait = 0;
//                    if(ara.stateIsActive(State.StandingRight) || ara.stateIsActive(State.RunningRight))
//                        ara.changeState(State.JumpingLeft, false);
//                    else
//                        ara.changeState(State.JumpingRight, false);
//                }

//
//                if (event.getCode().equals(KeyCode.LEFT)) {
//                    ara.runningLeft = false;
//                    if (ara.isGrounded())
//                        ara.changeState(State.StandingLeft, true);
//                    else
//                        ara.changeState(State.JumpingLeft, true);
//                }
//                if (event.getCode().equals(KeyCode.RIGHT))
//                    if(ara.isGrounded())
//                        ara.changeState(State.StandingRight, true);
//                    else
//                        ara.changeState(State.JumpingRight, true);
//                if(event.getCode().equals(KeyCode.CONTROL))
//                    ara.setSprint(false);
            }
        });
    }

    private void move(Ara ara, KeyCode code) {
        if(code.equals(KeyCode.RIGHT) || code.equals(KeyCode.LEFT)) {
            State state = State.RunningRight;
            if (code.equals(KeyCode.LEFT))
                state = State.RunningLeft;
            ara.changeState(state, true);
        }
    }

    private void stopMove(Ara ara, KeyCode code) {
        if(code.equals(KeyCode.RIGHT) || code.equals(KeyCode.LEFT)) {
            State state = State.RunningRight;
            if (code.equals(KeyCode.LEFT))
                state = State.RunningLeft;
            ara.changeState(state, false);
            if (!ara.stateIsActive(State.JumpingRight) && !ara.stateIsActive(State.JumpingLeft) && !ara.stateIsActive(State.RunningRight) && !ara.stateIsActive(State.RunningLeft)) {
                if(state.equals(State.RunningRight))
                    ara.changeState(State.StandingRight, true);
                else
                    ara.changeState(State.StandingLeft, true);

            }
        }
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