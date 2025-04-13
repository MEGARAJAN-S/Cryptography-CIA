import java.util.Scanner;

public class VigenereCipher {

    // Function to generate repeating key of equal length as text
    public static String generateKey(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toLowerCase();
        int keyIndex = 0;

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                result.append(key.charAt(keyIndex % key.length()));
                keyIndex++;
            } else {
                result.append(ch); // Keep symbols as is
            }
        }

        return result.toString();
    }

    // Encryption
    public static String encrypt(String plaintext, String key) {
        StringBuilder cipher = new StringBuilder();
        key = generateKey(plaintext, key);

        for (int i = 0; i < plaintext.length(); i++) {
            char p = plaintext.charAt(i);
            char k = key.charAt(i);

            if (Character.isUpperCase(p)) {
                char c = (char) (((p - 'A' + (k - 'a')) % 26) + 'A');
                cipher.append(c);
            } else if (Character.isLowerCase(p)) {
                char c = (char) (((p - 'a' + (k - 'a')) % 26) + 'a');
                cipher.append(c);
            } else {
                cipher.append(p);
            }
        }

        return cipher.toString();
    }

    // Decryption
    public static String decrypt(String ciphertext, String key) {
        StringBuilder plain = new StringBuilder();
        key = generateKey(ciphertext, key);

        for (int i = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            char k = key.charAt(i);

            if (Character.isUpperCase(c)) {
                char p = (char) (((c - 'A' - (k - 'a') + 26) % 26) + 'A');
                plain.append(p);
            } else if (Character.isLowerCase(c)) {
                char p = (char) (((c - 'a' - (k - 'a') + 26) % 26) + 'a');
                plain.append(p);
            } else {
                plain.append(c);
            }
        }

        return plain.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter the key: ");
        String key = scanner.nextLine();

        String encrypted = encrypt(plaintext, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("\nEncrypted Text: " + encrypted);
        System.out.println("Decrypted Text: " + decrypted);

        scanner.close();
    }
}
