package sample;

public enum AraRunRight {
    araRunRight1("araRunRight1.png"),
    araRunRight2("araRunRight2.png"),
    araRunRight3("araRunRight3.png"),
    araRunRight4("araRunRight4.png"),
    araRunRight5("araRunRight5.png"),
    araRunRight6("araRunRight6.png"),
    araRunRight7("araRunRight7.png"),
    araRunRight8("araRunRight8.png");

    private String image;

    AraRunRight(String image){
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
