package sample;

public enum AraStandingLeft {
    ara1Left("araLeft1.png"),
    ara2Left("araLeft2.png"),
    ara3Left("araLeft3.png"),
    ara4Left("araLeft4.png"),
    ara5Left("araLeft5.png"),
    ara6Left("araLeft6.png"),
    ara7Left("araLeft7.png"),
    ara8Left("araLeft8.png"),
    ara9Left("araLeft9.png"),
    ara10Left("araLeft10.png"),
    ara11Left("araLeft11.png");

    private String image;

    AraStandingLeft(String image){
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
