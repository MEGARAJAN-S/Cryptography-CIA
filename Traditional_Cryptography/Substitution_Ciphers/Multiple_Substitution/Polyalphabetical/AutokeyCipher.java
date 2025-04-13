import java.util.Scanner;

public class AutokeyCipher {

    // Generate Auto Key Stream: key + plaintext
    public static String generateKey(String plaintext, String key) {
        StringBuilder fullKey = new StringBuilder(key.toLowerCase());
        for (int i = 0; fullKey.length() < plaintext.length(); i++) {
            char ch = plaintext.charAt(i);
            if (Character.isLetter(ch)) {
                fullKey.append(Character.toLowerCase(ch));
            }
        }
        return fullKey.toString();
    }

    // Encryption
    public static String encrypt(String plaintext, String key) {
        StringBuilder cipher = new StringBuilder();
        String keyStream = generateKey(plaintext, key);
        int j = 0;

        for (int i = 0; i < plaintext.length(); i++) {
            char p = plaintext.charAt(i);
            if (Character.isLetter(p)) {
                char base = Character.isUpperCase(p) ? 'A' : 'a';
                int shift = keyStream.charAt(j) - 'a';
                char c = (char) ((p - base + shift) % 26 + base);
                cipher.append(c);
                j++;
            } else {
                cipher.append(p);
            }
        }

        return cipher.toString();
    }

    // Decryption
    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        StringBuilder keyStream = new StringBuilder(key.toLowerCase());
        int j = 0;

        for (int i = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                int shift = keyStream.charAt(j) - 'a';
                char p = (char) ((c - base - shift + 26) % 26 + base);
                plaintext.append(p);
                keyStream.append(Character.toLowerCase(p)); // Add decrypted char to keyStream
                j++;
            } else {
                plaintext.append(c);
            }
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter the key (single word): ");
        String key = scanner.nextLine();

        String encrypted = encrypt(plaintext, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("\nEncrypted Text: " + encrypted);
        System.out.println("Decrypted Text: " + decrypted);

        scanner.close();
    }
}
