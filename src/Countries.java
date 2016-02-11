import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Countries {
    public static void main(String[] args) throws FileNotFoundException {
        HashMap<String, ArrayList<Country>> countryMap = new HashMap<>();


        ArrayList<Country> allCountries =  new ArrayList<>();

        File f = new File("countries.txt");
        Scanner fileScanner = new Scanner(f);  //try catch here?

        //ok so i'm just reading everything into a list called allCountries. this is prob an extra step
        while (fileScanner.hasNext()) {
            String[] lineIn = fileScanner.nextLine().split("\\|");
            Country tempCountry = new Country(lineIn[0], lineIn[1]);
            allCountries.add(tempCountry);
        }

        //i need to make the structure of my HashMap
        //lets make the keys and make a key only if there is a corresponding letter.
        //technically this is sort of not needed because I'm prety sure there is a country from A-Z so...i dont really need to iterate over all this shit
        for (Country c : allCountries) {
            String first = c.getCountryName().substring(0,1);
            countryMap.put(first, new ArrayList<Country>());
        }

        //if country starts with an A, I need to pop it into the "A" key. Etc.
        for (Country c : allCountries) {
            String first = c.getCountryName().substring(0,1);
            countryMap.get(first).add(c);
        }







    }






    //reads in a single line of a file! Just one line!
    static String readFile(String fileName) throws FileNotFoundException {
        File f = new File(fileName);
        Scanner fileScanner = new Scanner(f);

        String lineIn = fileScanner.nextLine();
        return lineIn;
    }

    static void writeFile(String fileName, String fileContent) throws IOException {
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        fw.write(fileContent);
        fw.close();
    }
}
