package sample;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main (String args []) {
        Application.launch(args);
    }

    public void start(Stage stage) throws Exception {
        //Load files
        File level1 = new File("./resources/level1");

        Pane pane = new Pane();
        Scene scene = new Scene(pane);

//        keyboard(pane, doodle, scene);

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
                        System.out.println("Running");
//                        play(platformFile, pane, doodle);
                    }
                });
            }
        };
        fall.schedule(task, 0, 4);

    }

//    private void keyboard(Pane pane, Object doodle, Scene scene) {
//        scene.setOnKeyPressed( new EventHandler<KeyEvent>() {
//            public void handle(KeyEvent event) {
//                //Left
//                if(event.getCode().equals(KeyCode.LEFT)){
//                    ImageView tmp = doodle.getImageView();
//                    doodle.left();
//                    int idx = 0;
//                    while (!pane.getChildren().get(idx).equals(tmp))
//                        idx++;
//                    pane.getChildren().set(idx, doodle.getImageView());
//                }
//                //Right
//                if(event.getCode().equals(KeyCode.RIGHT)){
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