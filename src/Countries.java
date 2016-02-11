import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Countries {
    static HashMap<Character, ArrayList<Country>> countryMap = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws IOException {

        readFileToHashMap(); //read the source file into a HashMap
        Character userChoice; //need to access this outside my loopy loop

        //Give the user an option
        while (true) {
            System.out.println("Please choose the starting letter of the states you wish to display:");
            System.out.println("IE, \"A\" or \"B\"");
            userChoice = scanner.nextLine().toUpperCase().charAt(0);
            if (userChoice.charValue() >= 65 && userChoice.charValue() <=90) //this is testing for A-Z
                break;
        }


        ArrayList<Country> tempOutput = countryMap.get(userChoice);
        String output = "";

        for (Country c : tempOutput) {
            output += c.getCountryShort() + " | " + c.getCountryName() + "\n";
        }

        String fileName = userChoice + "_Countries.txt";
        String fileNameJson = userChoice + "_Countries.json";
        writeFile(fileName, output);
        writeToJson(fileNameJson, countryMap.get(userChoice));


    }



    public static void readFileToHashMap() throws FileNotFoundException {
        File f = new File("countries.txt");
        Scanner fileScanner = new Scanner(f);  //try catch here?
        while (fileScanner.hasNext()) {  //going through the file line by line
            String[] lineIn = fileScanner.nextLine().split("\\|");         //turning a line into string array on the |
            Country tempCountry = new Country(lineIn[0], lineIn[1]);           //creating a temp object and loading it from the string
            Character firstLetter = lineIn[1].toUpperCase().charAt(0);         //making my key

            if (!countryMap.keySet().contains(firstLetter)) { //if the key has not yet been created
                countryMap.put(firstLetter, new ArrayList<>());  //create key and empty array. //CAN I COMBINE THESE LINES?
                countryMap.get(firstLetter).add(tempCountry); //go ahead and add in the country. I mean..why not?! Why not bro. Why not
            } else {
                countryMap.get(firstLetter).add(tempCountry); //otherwise just pull up the key and add the object value to the array
            }

        }
    }



    static void writeFile(String fileName, String fileContent) throws IOException {
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        fw.write(fileContent);
        fw.close();
    }


    static void writeToJson(String filename, ArrayList<Country> fileContent) throws IOException {
        File f = new File(filename);
        FileWriter fw = new FileWriter(f);
        JsonSerializer serializer = new JsonSerializer();

        String output = serializer.include("*").serialize(fileContent);
        fw.write(output);
        fw.close();

    }
}
