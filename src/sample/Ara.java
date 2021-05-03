package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.textures.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Ara {
    static final int X_SIZE = 24;
    static final int Y_SIZE = 32;
    static final double REDUCTION = 0.2;
    public static final int GRAVITY = 14;
    public static double speed = 14;
    public static double jump = 14;

    private ImageView imagesStandingRight[] = new ImageView[11];
    private ImageView imagesStandingLeft[] = new ImageView[11];
    private ImageView imagesJumpRight[] = new ImageView[9];
    private ImageView imagesJumpLeft[] = new ImageView[9];
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

    public void setSprint(boolean sprint){
        if(sprint)
            speed = speed * 1.5;
        else
            speed = speed / 1.5;

    }

    public void setState(State state) {
        if(!state.equals(this.state)) {
            System.out.println("OK");
            this.idx = 0;
            this.wait = 0;
            this.grounded = false;
            this.state = state;
        }
    }

    public void gravity(TextureLoader txl){
        ArrayList<ImageView> floor = txl.getFloor();
        for (int i = 0; i < floor.size() && !grounded; i++) {
            grounded = (x+(X_SIZE* (1 - REDUCTION) * Main.SCREEN_RATIO) >= floor.get(i).getX() && x <= (floor.get(i).getX() + Texture.floor.getX() * Main.SCREEN_RATIO) && y + Y_SIZE * (1 - REDUCTION) * Main.SCREEN_RATIO > (floor.get(i).getY() - GRAVITY ));
            if(grounded) {
                y = floor.get(i).getY() - (Y_SIZE * (1 - REDUCTION) * Main.SCREEN_RATIO);
                System.out.println("HIT THE GOURND");
            }
        }
        if(!grounded)
            y+= GRAVITY;
    }

    public void right(Pane pane, TextureLoader txl){
        run(pane, imagesRunRight);
        x+= speed;
    }

    public void left(Pane pane, TextureLoader txl){
        run(pane, imagesRunLeft);
        x-= speed;
    }

    public void jump(Pane pane, TextureLoader txl, ImageView [] list){
        if(wait<2 || wait==4 || wait==6 || wait == 8 || wait == 10 | wait == 12 || grounded) {
            idx++;
            if (idx == list.length){
                idx = 0;
                if(state.ordinal()%2==0)
                    state = State.StandingRight;
                else
                    state = State.StandingLeft;
            }
        }
        if(wait<8) {
            y -= (GRAVITY + jump);
            grounded = false;
        }

        wait++;
        changeImage(pane, list);
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

    public void animateAra(Pane pane) {
        switch (state){
            case StandingRight:
                standing(pane, imagesStandingRight);
                break;
            case StandingLeft:
                standing(pane, imagesStandingLeft);
                break;
            case RuningRight:
                right(pane, null);
                break;
            case RuningLeft:
                left(pane, null);
                break;
            case JumpingRight:
                jump(pane, null, imagesJumpRight);
                break;
            case JumpingLeft:
                jump(pane, null, imagesJumpLeft);
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
            if (idx == list.length) {
                idx = 0;
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
