package neu.tripolien.neutripolien;

public class Flags {

    private String countryName;
    private String imagePath;
    private String difficulty;

    public Flags (String countryName, String imagePath, String difficulty) {

        this.countryName = countryName;
        this.imagePath = imagePath;
        this.difficulty = difficulty;


    }

    public String getCountryName() {

        return countryName;
    }

    public String getImagePath() {

        return imagePath;
    }

    public String getDifficulty() {

        return difficulty;
    }


}
