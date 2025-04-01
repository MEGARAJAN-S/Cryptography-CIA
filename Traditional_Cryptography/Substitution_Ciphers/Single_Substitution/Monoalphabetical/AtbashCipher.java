import java.util.Scanner;

public class AtbashCipher {

    public static String encryptDecrypt(String input) {
        StringBuilder result = new StringBuilder();

        for (char ch : input.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                ch = (char) ('Z' - (ch - 'A')); 
            } else if (Character.isLowerCase(ch)) {
                ch = (char) ('z' - (ch - 'a')); 
            }

            result.append(ch);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the Plain Text: ");
        String text = input.nextLine();

        String encryptedText = encryptDecrypt(text);
        System.out.println("Encrypted Text: " + encryptedText);

        String decryptedText = encryptDecrypt(encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);

        input.close();
    }
}
