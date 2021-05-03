package sample.textures;

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

public enum Texture {
  backWallBlackHalf ("backWallBlackHalf.png", 16, 8), box ("box.png", 16, 16), coin ("coin.png",16,16), doorOpen ("doorOpen.png",32,32),
  horiozontalBeam ("horiozontalBeam.png", 48, 16), rope ("rope.png",16,48), wall ("wall.png",16,16), backWallBlack ("backWallBlack.png",16,8),
  cabinet ("cabinet.png",16,32), dawer ("dawer.png", 16,16), door ("door.png",16,24), key ("key.png", 16, 16), shelf ("shelf.png", 46, 16),
  backWallHalf ("backWallHalf.png", 16, 8), chair1 ("chair1.png", 16,16), dirt ("dirt.png", 16,16), flag ("flag.png", 16, 48), ladderBot ("ladderBot.png", 16, 16),
  stool ("stool.png", 16,16), backWall ("backWall.png",16,8), chair2 ("chair2.png", 16,16), door2Open ("door2Open.png", 32, 32), floor ("floor.png", 16, 16),
  ladderTop ("ladderTop.png", 16,16), table ("table.png",32, 16), barrel ("barrel.png",16,16), chest ("chest.png", 32, 16), door2 ("door2.png",32,32),
  flowers ("flowers.png", 16, 16), platform ("platform.png",16,8), verticalBeam ("verticalBeam.png", 16, 48), rightCorner("rightCorner.png", 16,16), leftCorner("leftCorner.png", 16,16);

  private String image;
  private int x;
  private int y;

  Texture (String image, int x, int y){
      this.image = image;
      this.x = x;
      this.y = y;
  }

  public String getImage() {
    return image;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

}
