

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author UmairAkhtar
 */
public class ReverseString {

    // Assuming that the alphabet includes numbers, letters and special characters
    public static String[] reverseString(int numberOfStrings, int numberOfCharacters) {
        String[] strings = new String[numberOfStrings];
        int initialCharInASCIITable = 40;
        int finalCharInASCIITable = 126;

        for(int string = 0; string < numberOfStrings; string++) {
            StringBuilder currentString = new StringBuilder();

            for(int character = 0; character < numberOfCharacters; character++) {
                char characterValue = (char) StdRandom.uniform(initialCharInASCIITable, finalCharInASCIITable + 1);
                currentString.append(characterValue);
            }

            strings[string] = currentString.reverse().toString();
        }

        return strings;
    }

    public static void main(String[] args) {
        int numberOfStrings = 1;
        int numberOfCharacters = 500;

        String[] randomstrings = ReverseString.reverseString(numberOfStrings,
                numberOfCharacters);
        System.out.println("Random strings generated:");

        for(String randomstring : randomstrings) {
//            System.out.println(randomstring);
            System.out.println("Using String Builder\n");
//            for(int i = 0; i < 1 ; i++){            
                System.gc();
                StringBuilder strB = new StringBuilder(randomstring);
                System.out.println("string = " + strB);
                System.out.println("reverse = " + strB.reverse());
//                System.out.println(randomstring);
//            }
            System.out.println("\nUsing String Class");
//            for(int k = 0; k < 5 ; k++){            
                System.gc();
                String str = randomstring;
                System.out.println("\nstring = " + str);
                System.out.print("reverse = ");
                char[] try1 = str.toCharArray();             
                for (int j = try1.length-1; j>=0; j--)
                    System.out.print(try1[j]);
//            }
        }
    }
}