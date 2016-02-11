/**
 * Created by branden on 2/11/16 at 13:13.
 */
public class Country {

    private String countryShort;
    private String countryName;


    public Country(String countryShort, String countryName) {
        setCountryShort(countryShort);
        setCountryName(countryName);
    }

    ///getters and setters
    public String getCountryShort() {
        return countryShort;
    }

    public void setCountryShort(String countryShort) {
        this.countryShort = countryShort;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }


    @Override
    public String toString() {
        return "Country{" +
                "countryShort='" + countryShort + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}