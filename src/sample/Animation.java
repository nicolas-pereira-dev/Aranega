package sample;

public class Animation {
    int wait;
    int idx;
    public Animation(int wait, int idx){
        this.wait = wait;
        this.idx = idx;
    }

    public void reset(){
        wait = 0;
        idx = 0;
    }
}
