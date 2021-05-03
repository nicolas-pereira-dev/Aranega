package sample.textures;

public enum AraJumpLeft {
    araJumpLeft1("araJumpLeft1.png"),
    araJumpLeft2("araJumpLeft2.png"),
    araJumpLeft3("araJumpLeft3.png"),
    araJumpLeft4("araJumpLeft4.png"),
    araJumpLeft5("araJumpLeft5.png"),
    araJumpLeft6("araJumpLeft6.png"),
    araJumpLeft7("araJumpLeft7.png"),
    araJumpLeft8("araJumpLeft8.png"),
    araJumpLeft9("araJumpLeft9.png");

    private String image;

    AraJumpLeft(String image){
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
