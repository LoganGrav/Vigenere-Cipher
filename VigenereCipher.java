import java.util.Scanner;

public class VigenereCipher {
    public static void main(String[] args) {

        if (args.length != 2) {         //Check Args
            System.out.println("Error: Incorrect Argument Format"); //Throw error
            return;                     //end if error
        }

        String key = args[0];           //initialize key and plaintext as 1st and 2nd args
        String plaintext = args[1];


        if (!key.matches("[a-zA-Z]+")) {    //check key format
            System.out.println("Error: Key must contain only English letters"); //throw error
            return;
        }

        key = key.toLowerCase();    //set key lowercase

        String ciphertext = encrypt(plaintext, key);        //call encrypt method
        System.out.println("Ciphertext: " + ciphertext);    //print with formatting

        // Decrypt the ciphertext
        String decryptedText = decrypt(ciphertext, key);    //call decrypt method
        System.out.println("Plaintext: " + decryptedText);  //print plaintext with formatting
    }

    private static String encrypt(String plaintext, String key) {   //encrypting method
        StringBuilder ciphertext = new StringBuilder();             // use stringbuilder
        int index = 0;      //initialize key ID

        for (char c : plaintext.toCharArray()) {    //enhanced for loop, go through each char
            if (Character.isLetter(c)) {            //check if alphabetic symbol
                boolean isUpperCase = Character.isUpperCase(c); //check case
                char base = isUpperCase ? 'A' : 'a';            //if statement plus char initialization


                char encryptedChar = (char) ((c - base + (key.charAt(index) - 'a')) % 26 + base);    //use vigenere encryption
                ciphertext.append(encryptedChar);   //append each encrypted char to ciphertext array

                index = (index + 1) % key.length();   //iterate index
            } else {
                ciphertext.append(c);           //if not alphabetic symbol, ignore
            }
        }

        return ciphertext.toString();           //set char array to string, return
    }

    private static String decrypt(String ciphertext, String key) {  //decrypting method, almost exact same as encrypt
        StringBuilder plaintext = new StringBuilder();
        int index = 0;

        for (char c : ciphertext.toCharArray()) {
            if (Character.isLetter(c)) {
                boolean isUpperCase = Character.isUpperCase(c);
                char base = isUpperCase ? 'A' : 'a';

                //only difference between methods is formula, which reverses Vigenere to decrypt
                char decryptedChar = (char) ((c - base - (key.charAt(index) - 'a') + 26) % 26 + base);
                plaintext.append(decryptedChar);

                index = (index + 1) % key.length();
            } else {
                plaintext.append(c);    //again, ignore non-alphabetic symbol
            }
        }

        return plaintext.toString();    
    }
}
