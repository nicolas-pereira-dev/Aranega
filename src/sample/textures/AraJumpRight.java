package sample.textures;

public enum AraJumpRight {
    araJumpRight1("araJumpRight1.png"),
    araJumpRight2("araJumpRight2.png"),
    araJumpRight3("araJumpRight3.png"),
    araJumpRight4("araJumpRight4.png"),
    araJumpRight5("araJumpRight5.png"),
    araJumpRight6("araJumpRight6.png"),
    araJumpRight7("araJumpRight7.png"),
    araJumpRight8("araJumpRight8.png"),
    araJumpRight9("araJumpRight9.png");

    private String image;

    AraJumpRight(String image){
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
