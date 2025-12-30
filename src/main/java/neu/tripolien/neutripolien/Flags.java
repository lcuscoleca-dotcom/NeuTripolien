package neu.tripolien.neutripolien;

public class Flags {

    private String countryName;
    private String imagePath;

    public Flags (String countryName, String imagePath) {

        this.countryName = countryName;
        this.imagePath = imagePath;

    }

    public String getCountryName() {

        return countryName;
    }

    public String getImagePath() {

        return imagePath;
    }


}
