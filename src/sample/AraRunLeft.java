package sample;

public enum AraRunLeft {
    araRunLeft1("araRunLeft1.png"),
    araRunLeft2("araRunLeft2.png"),
    araRunLeft3("araRunLeft3.png"),
    araRunLeft4("araRunLeft4.png"),
    araRunLeft5("araRunLeft5.png"),
    araRunLeft6("araRunLeft6.png"),
    araRunLeft7("araRunLeft7.png"),
    araRunLeft8("araRunLeft8.png");

    private String image;

    AraRunLeft(String image){
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
