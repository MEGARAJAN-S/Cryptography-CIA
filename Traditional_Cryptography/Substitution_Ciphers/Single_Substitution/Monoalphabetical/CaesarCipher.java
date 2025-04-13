import java.util.Scanner;

public class CaesarCipher{

    // Method to encrypt the input text using the key
    public static String encrypt(String text, int key) {
        StringBuilder result = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (Character.isUpperCase(character)) {
                char ch = (char) (((character - 'A' + key) % 26) + 'A');
                result.append(ch);
            } else if (Character.isLowerCase(character)) {
                char ch = (char) (((character - 'a' + key) % 26) + 'a');
                result.append(ch);
            } else {
                result.append(character); // Leave other characters unchanged
            }
        }

        return result.toString();
    }

    // Method to decrypt the input text using the key
    public static String decrypt(String text, int key) {
        return encrypt(text, 26 - (key % 26));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the text: ");
        String inputText = scanner.nextLine();

        System.out.print("Enter the shift key: ");
        int key = scanner.nextInt();

        String encryptedText = encrypt(inputText, key);
        String decryptedText = decrypt(encryptedText, key);

        System.out.println("\nEncrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }
}
