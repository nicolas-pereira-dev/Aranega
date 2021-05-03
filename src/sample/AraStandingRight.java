package sample;

public enum AraStandingRight {
    araRight1("araRight1.png"),
    araRight2("araRight2.png"),
    araRight3("araRight3.png"),
    araRight4("araRight4.png"),
    araRight5("araRight5.png"),
    araRight6("araRight6.png"),
    araRight7("araRight7.png"),
    araRight8("araRight8.png"),
    araRight9("araRight9.png"),
    araRight10("araRight10.png"),
    araRight11("araRight11.png");

   private String image;

   AraStandingRight(String image){
       this.image = image;
   }

   public String getImage() {
        return image;
    }
}
