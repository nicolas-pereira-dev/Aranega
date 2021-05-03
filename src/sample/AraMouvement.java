package sample;

public enum AraMouvement {
   ara1("ara1.png"), ara2("ara2.png"), ara3("ara3.png"), ara4("ara4.png"), ara5("ara5.png"), ara6("ara6.png"), ara7("ara7.png"), ara8("ara8.png"), ara9("ara9.png"), ara10("ara10.png"), ara11("ara11.png");

   private String image;

   AraMouvement (String image){
       this.image = image;
   }

   public String getImage() {
        return image;
    }
}
