package sample;

import java.io.*;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application{
    final int SCREEN_RATIO = 3;
    int x = 0;
    int y = 0;

    public static void main (String args []) {
        Application.launch(args);
    }

    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        loadLevel("level1", pane);

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
//                        play(platformFile, pane, doodle);
                    }
                });
            }
        };
        fall.schedule(task, 0, 4);

    }

    private void loadLevel(String name, Pane pane) throws Exception {
        pane.setBackground(new Background(new BackgroundFill(Color.web("322B28"), null, null)));

        File level1 = new File("./resources/"+name);
        BufferedReader br = new BufferedReader( new FileReader(level1));
        String line;
        while ((line = br.readLine()) != null){
            String [] textures = line.split(";");
            for(String texture : textures){
                int id = Integer.parseInt(texture.split("x")[0]);
                int nbr = Integer.parseInt(texture.split("x")[1]);
                for(int i=0; i<nbr; i++){
                    if(id!=-1) {
                        Texture t = Texture.values()[id];
                        pane.getChildren().add(loadImage(t));
                        x += t.getX() * SCREEN_RATIO;
                    }else{
                        x += 8 * SCREEN_RATIO;
                    }
                }
            }
            x=0;
            y+=8*SCREEN_RATIO;
        }
    }

    private ImageView loadImage(Texture texture) throws Exception{
        Image image = new Image( new FileInputStream("./resources/assets/"+texture.getImage()), texture.getX()*SCREEN_RATIO, texture.getY()*SCREEN_RATIO,false,false);
        ImageView im = new ImageView(image);
        im.setX(x);
        im.setY(y);
        return im;
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