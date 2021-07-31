package sample;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Action {
    Ara ara;
    int wait = 0;
    int idx = 0;

    public Action (Ara ara){
        this.ara = ara;
    }

    public void right(Pane pane, TextureLoader txl){
        if(ara.isGrounded())
            run(pane, ara.imagesRunRight);
        ara.x += ara.speed;
    }

    public void left(Pane pane, TextureLoader txl){
        if(ara.isGrounded())
            run(pane, ara.imagesRunLeft);
        ara.x-= ara.speed;
    }

    public void jump(Pane pane, TextureLoader txl, ImageView[] list){
        if(wait<2 || wait==4 || wait==6 || wait == 8 || wait == 10 || ara.grounded) {
            idx++;
        }
        if (idx >= list.length){
            idx = 0;
            if(ara.stateIsActive(State.JumpingRight)) {
                ara.changeState(State.JumpingRight, false);
                ara.changeState(State.StandingRight, true);
            }else {
                ara.changeState(State.JumpingLeft, false);
                ara.changeState(State.StandingLeft, true);
            }
            wait = 5;
            ara.grounded = true;
        }else if(wait<8) {
            ara.y -= (Ara.GRAVITY + ara.jump);
            ara.grounded = false;
        }
        wait++;
        ara.changeImage(pane, list, idx);
    }

    public void standing(Pane pane, ImageView[] list) {
        if(wait>20) {
            idx++;
            if (idx == list.length) {
                idx = 0;
                wait = 0;
            }
        }
        wait++;
        ara.changeImage(pane, list, idx);
    }

    private void run(Pane pane, ImageView [] list) {
        idx++;
        if (idx >= list.length)
            idx = 0;
        ara.changeImage(pane, list, idx);
    }
}