package sample;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Action {
    Ara ara;
    Animation jumpAnimation;
    Animation standingAnimation;
    Animation runAnimation;

    public Action (Ara ara){
        this.ara = ara;
        this.jumpAnimation = new Animation(0, 0);
        this.standingAnimation = new Animation(0, 0);
        this.runAnimation = new Animation(0, 0);
    }

    public void right(Pane pane, TextureLoader txl){
        if(ara.grounded)
            run(pane, ara.imagesRunRight);
        ara.x += ara.speed;
    }

    public void left(Pane pane, TextureLoader txl){
        if(ara.grounded)
            run(pane, ara.imagesRunLeft);
        ara.x-= ara.speed;
    }

    public void jump(Pane pane, TextureLoader txl, ImageView[] list){
        boolean running = ara.stateIsActive(State.RunningRight) || ara.stateIsActive(State.RunningLeft);
        if(jumpAnimation.wait<2 || jumpAnimation.wait==4 || jumpAnimation.wait==6 || jumpAnimation.wait == 8 || jumpAnimation.wait == 10 || ara.grounded) {
            jumpAnimation.idx++;
        }
        if (jumpAnimation.idx >= list.length || (jumpAnimation.idx > 1 && ara.grounded && running )){
            jumpAnimation.idx = 0;
            if(ara.stateIsActive(State.JumpingRight)) {
                ara.changeState(State.JumpingRight, false);
                if(!running)
                    ara.changeState(State.StandingRight, true);
            }else {
                ara.changeState(State.JumpingLeft, false);
                if(!running)
                    ara.changeState(State.StandingLeft, true);
            }
            jumpAnimation.wait = 5;
            ara.grounded = true;
        }else if(jumpAnimation.wait<8) {
            ara.y -= (Ara.GRAVITY + ara.jump);
            ara.grounded = false;
        }
        jumpAnimation.wait++;
        ara.changeImage(pane, list, jumpAnimation.idx);
    }

    public void standing(Pane pane, ImageView[] list) {
        if(standingAnimation.wait>20) {
            standingAnimation.idx++;
            if (standingAnimation.idx == list.length) {
                standingAnimation.idx = 0;
                standingAnimation.wait = 0;
            }
        }
        standingAnimation.wait++;
        ara.changeImage(pane, list, standingAnimation.idx);
    }

    private void run(Pane pane, ImageView [] list) {
        runAnimation.idx++;
        if (runAnimation.idx >= list.length)
            runAnimation.idx = 0;
        ara.changeImage(pane, list, runAnimation.idx);
    }
}