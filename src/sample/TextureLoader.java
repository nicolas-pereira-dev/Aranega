package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;

public class TextureLoader {
    private ArrayList<ImageView> floor;
    private int x;
    private int y;

    TextureLoader(){
        this.floor = new ArrayList<ImageView>();
    }

    public ArrayList<ImageView> getFloor() {
        return floor;
    }

    public void loadLevel(String name, Pane pane) throws Exception {
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
                        x += t.getX() * Main.SCREEN_RATIO;
                    }else{
                        x += 8 * Main.SCREEN_RATIO;
                    }
                }
            }
            x=0;
            y+=8 * Main.SCREEN_RATIO;
        }
    }

    private ImageView loadImage(Texture texture) throws Exception{
        Image image = new Image( new FileInputStream("./resources/assets/"+texture.getImage()), texture.getX()*Main.SCREEN_RATIO, texture.getY()*Main.SCREEN_RATIO,false,false);
        ImageView im = new ImageView(image);
        im.setX(x);
        im.setY(y);
        if(texture.equals(Texture.floor))
            floor.add(im);
        return im;
    }
}
