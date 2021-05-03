package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Ara {
    static final int X_SIZE = 24;
    static final int Y_SIZE = 32;
    static final double REDUCTION = 0.2;
    public static final int GRAVITY = 14;
    public static final int SPEED = 10;

    private ImageView imagesStaticRight[] = new ImageView[11];
    private ImageView imagesRunRight[] = new ImageView[8];
    private ImageView last;
    private boolean grounded;
    private double x;
    private double y;
    private int wait;
    private int idx;

    Ara(int x, int y){
        this.x = x;
        this.y = y;
        this.wait = 0;
        this.idx = 0;
        this.grounded = false;
    }

    public void gravity(TextureLoader txl){
        ArrayList<ImageView> floor = txl.getFloor();
        for (int i = 0; i < floor.size() && !grounded; i++) {
            grounded = (x > floor.get(i).getX() && x < (floor.get(i).getX() + Texture.floor.getX() * Main.SCREEN_RATIO) && y + Y_SIZE * (1 - REDUCTION) * Main.SCREEN_RATIO > (floor.get(i).getY() - GRAVITY ));
            if(grounded)
                y = floor.get(i).getY() - (Y_SIZE * (1 - REDUCTION) * Main.SCREEN_RATIO);
        }
        if(!grounded)
            y+= GRAVITY;
    }

    public void right(TextureLoader txl){
        x+= SPEED;
    }

    public void left(TextureLoader txl){
        x-= SPEED;
    }

    public void loadAra(Pane pane) throws FileNotFoundException {
        for(AraStaticRight ara : AraStaticRight.values()){
            imagesStaticRight[idx] = new ImageView(new Image( new FileInputStream("./resources/assets/ara/"+ara.getImage()), X_SIZE*Main.SCREEN_RATIO*(1-REDUCTION), Y_SIZE*Main.SCREEN_RATIO*(1-REDUCTION),false,false));
            imagesStaticRight[idx].setX(x);
            imagesStaticRight[idx++].setY(y);
        }
        idx = 0;

        for(AraRunRight ara : AraRunRight.values()){
            imagesRunRight[idx] = new ImageView(new Image( new FileInputStream("./resources/assets/ara/"+ara.getImage()), X_SIZE*Main.SCREEN_RATIO*(1-REDUCTION), Y_SIZE*Main.SCREEN_RATIO*(1-REDUCTION),false,false));
            imagesRunRight[idx].setX(x);
            imagesRunRight[idx++].setY(y);
        }
        idx = 0;
        last = imagesRunRight[0];
//        last = imagesStaticRight[0];
        pane.getChildren().add(last);
    }

    public void animateAra(Pane pane) {
        runRight(pane);
//        staticRight(pane);
    }

    private void changeImage(Pane pane, ImageView [] list) {
        pane.getChildren().remove(last);
        last = list[idx];
        last.setX(x);
        last.setY(y);
        pane.getChildren().add(last);
    }

    private void staticRight(Pane pane) {
        if(wait>45) {
            idx++;
            if (idx == imagesStaticRight.length)
                idx = 0;
            if (idx == 0) {
                wait = 0;
            }
        }
        wait++;
        changeImage(pane, imagesStaticRight);
    }

    private void runRight(Pane pane) {
        idx++;
        if (idx == imagesRunRight.length)
            idx = 0;
        changeImage(pane, imagesRunRight);
    }

}
