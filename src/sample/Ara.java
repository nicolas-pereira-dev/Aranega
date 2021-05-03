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
    public static final int SPEED = 0;

    private ImageView imagesStandingRight[] = new ImageView[11];
    private ImageView imagesStandingLeft[] = new ImageView[11];
    private ImageView imagesRunRight[] = new ImageView[8];
    private ImageView imagesRunLeft[] = new ImageView[8];
    private ImageView last;
    private State state;
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
        this.state = State.StandingRight;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        if(!state.equals(this.state)) {
            this.idx = 0;
            this.wait = 0;
            this.grounded = false;
            this.state = state;
        }
    }

    public void gravity(TextureLoader txl){
        ArrayList<ImageView> floor = txl.getFloor();
        for (int i = 0; i < floor.size() && !grounded; i++) {
            grounded = (x >= floor.get(i).getX() && x <= (floor.get(i).getX() + Texture.floor.getX() * Main.SCREEN_RATIO) && y + Y_SIZE * (1 - REDUCTION) * Main.SCREEN_RATIO > (floor.get(i).getY() - GRAVITY ));
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
        for(AraStandingRight ara : AraStandingRight.values()){
            imagesStandingRight[idx] = new ImageView(new Image( new FileInputStream("./resources/assets/ara/"+ara.getImage()), X_SIZE*Main.SCREEN_RATIO*(1-REDUCTION), Y_SIZE*Main.SCREEN_RATIO*(1-REDUCTION),false,false));
            imagesStandingRight[idx].setX(x);
            imagesStandingRight[idx++].setY(y);
        }
        idx = 0;

        for(AraStandingLeft ara : AraStandingLeft.values()){
            imagesStandingLeft[idx] = new ImageView(new Image( new FileInputStream("./resources/assets/ara/"+ara.getImage()), X_SIZE*Main.SCREEN_RATIO*(1-REDUCTION), Y_SIZE*Main.SCREEN_RATIO*(1-REDUCTION),false,false));
            imagesStandingLeft[idx].setX(x);
            imagesStandingLeft[idx++].setY(y);
        }
        idx = 0;

        for(AraRunRight ara : AraRunRight.values()){
            imagesRunRight[idx] = new ImageView(new Image( new FileInputStream("./resources/assets/ara/"+ara.getImage()), X_SIZE*Main.SCREEN_RATIO*(1-REDUCTION), Y_SIZE*Main.SCREEN_RATIO*(1-REDUCTION),false,false));
            imagesRunRight[idx].setX(x);
            imagesRunRight[idx++].setY(y);
        }
        idx = 0;

        for(AraRunLeft ara : AraRunLeft.values()){
            imagesRunLeft[idx] = new ImageView(new Image( new FileInputStream("./resources/assets/ara/"+ara.getImage()), X_SIZE*Main.SCREEN_RATIO*(1-REDUCTION), Y_SIZE*Main.SCREEN_RATIO*(1-REDUCTION),false,false));
            imagesRunLeft[idx].setX(x);
            imagesRunLeft[idx++].setY(y);
        }
        idx = 0;

        last = imagesStandingRight[0];
        pane.getChildren().add(last);
    }

    public void animateAra(Pane pane) {
        switch (state){
            case StandingRight:
                standing(pane, imagesStandingRight);
                break;
            case StandingLeft:
                standing(pane, imagesStandingLeft);
                break;
            case RuningRight:
                run(pane, imagesRunRight);
                break;
            case RuningLeft:
                run(pane, imagesRunLeft);
                break;
            default:
        }
    }

    private void changeImage(Pane pane, ImageView [] list) {
        pane.getChildren().remove(last);
        last = list[idx];
        last.setX(x);
        last.setY(y);
        pane.getChildren().add(last);
    }

    private void standing(Pane pane, ImageView [] list) {
        if(wait>4) {
            idx++;
            if (idx == list.length)
                idx = 0;
            if (idx == 0) {
                wait = 0;
            }
        }
        wait++;
        changeImage(pane, list);
    }

    private void run(Pane pane, ImageView [] list) {
        idx++;
        if (idx == list.length)
            idx = 0;
        changeImage(pane, list);
    }

}
