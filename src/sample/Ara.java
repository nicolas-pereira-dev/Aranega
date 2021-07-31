package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.textures.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Ara {
    static final int X_SIZE = 24;
    static final int Y_SIZE = 32;
    static final double REDUCTION = 0.2;
    public static final int GRAVITY = 14;
    public static double speed = 14;
    public static double jump = 14;

    // Arrays for the different animation image set
    ImageView imagesStandingRight[] = new ImageView[11];
    ImageView imagesStandingLeft[] = new ImageView[11];
    ImageView imagesJumpRight[] = new ImageView[9];
    ImageView imagesJumpLeft[] = new ImageView[9];
    ImageView imagesRunRight[] = new ImageView[8];
    ImageView imagesRunLeft[] = new ImageView[8];

    ImageView last;
    Action actionMaker;
    private HashMap<State, Boolean> states = new HashMap<State, Boolean>();
    boolean grounded = false;
    double x;
    double y;

    Ara(int x, int y){
        for(State state : State.values())
            states.put(state, false);
        states.put(State.StandingRight, true);
        this.x = x;
        this.y = y;
        actionMaker = new Action(this);
    }


    public void setSprint(boolean sprint){
        if(sprint)
            speed = speed * 1.5;
        else
            speed = speed / 1.5;
    }

    public boolean stateIsActive(State state){
        return states.get(state);
    }

    public void changeState(State state, boolean activate) {
        if(states.get(state) != activate){
            if(!states.get(State.JumpingRight) && !states.get(State.JumpingLeft)) {
                actionMaker.wait = 0;
                actionMaker.idx = 0;
            }
            grounded = false;
            states.put(state, activate);
        }
    }

    public boolean isGrounded(){
        return grounded;
    }

    public void gravity(TextureLoader txl){
        ArrayList<ImageView> floor = txl.getFloor();
        for (int i = 0; i < floor.size() && !grounded; i++) {
            grounded = (x+(X_SIZE* (1 - REDUCTION) * Main.SCREEN_RATIO) >= floor.get(i).getX() && x <= (floor.get(i).getX() + Texture.floor.getX() * Main.SCREEN_RATIO) && y + Y_SIZE * (1 - REDUCTION) * Main.SCREEN_RATIO > (floor.get(i).getY() - GRAVITY ));
            if(grounded)
                y = floor.get(i).getY() - (Y_SIZE * (1 - REDUCTION) * Main.SCREEN_RATIO);
        }
        if(!grounded)
            y+= GRAVITY;
    }


    public void animateAra(Pane pane) {
        for(State state : State.values())
            if(states.get(state))
                applyAction(state, pane);
    }

    private void applyAction(State state, Pane pane){
        switch (state){
            case StandingRight:
                actionMaker.standing(pane, imagesStandingRight);
                break;
            case StandingLeft:
                actionMaker.standing(pane, imagesStandingLeft);
                break;
            case RunningRight:
                actionMaker.right(pane, null);
                break;
            case RunningLeft:
                actionMaker.left(pane, null);
                break;
            case JumpingRight:
                actionMaker.jump(pane, null, imagesJumpRight);
                break;
            case JumpingLeft:
                actionMaker.jump(pane, null, imagesJumpLeft);
                break;
            default:
        }
    }

    public void changeImage(Pane pane, ImageView [] list, int idx) {
        pane.getChildren().remove(last);
        last = list[idx];
        last.setX(x);
        last.setY(y);
        pane.getChildren().add(last);
    }

    public void loadAraImages(Pane pane) throws FileNotFoundException {
        int idx = 0;

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

        for(AraJumpRight ara : AraJumpRight.values()){
            imagesJumpRight[idx] = new ImageView(new Image( new FileInputStream("./resources/assets/ara/"+ara.getImage()), X_SIZE*Main.SCREEN_RATIO*(1-REDUCTION), Y_SIZE*Main.SCREEN_RATIO*(1-REDUCTION),false,false));
            imagesJumpRight[idx].setX(x);
            imagesJumpRight[idx++].setY(y);
        }
        idx = 0;

        for(AraJumpLeft ara : AraJumpLeft.values()){
            imagesJumpLeft[idx] = new ImageView(new Image( new FileInputStream("./resources/assets/ara/"+ara.getImage()), X_SIZE*Main.SCREEN_RATIO*(1-REDUCTION), Y_SIZE*Main.SCREEN_RATIO*(1-REDUCTION),false,false));
            imagesJumpLeft[idx].setX(x);
            imagesJumpLeft[idx++].setY(y);
        }
        idx = 0;

        last = imagesStandingRight[0];
        pane.getChildren().add(last);
    }

}
