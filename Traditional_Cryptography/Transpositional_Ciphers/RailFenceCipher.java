import java.util.Scanner;

public class RailFenceCipher {

    // Encrypt plaintext using Rail Fence Cipher
    public static String encrypt(String text, int key) {
        if (key == 1) return text;

        StringBuilder[] rail = new StringBuilder[key];
        for (int i = 0; i < key; i++) rail[i] = new StringBuilder();

        int direction = 1;  // 1 = down, -1 = up
        int row = 0;

        for (char c : text.toCharArray()) {
            rail[row].append(c);

            row += direction;

            if (row == key - 1 || row == 0) {
                direction *= -1;
            }
        }

        // Combine all rails
        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : rail) result.append(sb);

        return result.toString();
    }

    // Decrypt ciphertext using Rail Fence Cipher
    public static String decrypt(String cipher, int key) {
        if (key == 1) return cipher;

        boolean[][] marker = new boolean[key][cipher.length()];
        int row = 0, direction = 1;

        // Step 1: mark positions
        for (int i = 0; i < cipher.length(); i++) {
            marker[row][i] = true;
            row += direction;

            if (row == 0 || row == key - 1) direction *= -1;
        }

        // Step 2: fill marked positions with cipher text
        char[][] rails = new char[key][cipher.length()];
        int index = 0;
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < cipher.length(); j++) {
                if (marker[i][j]) {
                    rails[i][j] = cipher.charAt(index++);
                }
            }
        }

        // Step 3: read zigzag
        StringBuilder result = new StringBuilder();
        row = 0;
        direction = 1;
        for (int i = 0; i < cipher.length(); i++) {
            result.append(rails[row][i]);

            row += direction;

            if (row == 0 || row == key - 1) direction *= -1;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the plaintext: ");
        String plaintext = sc.nextLine();

        System.out.print("Enter the number of rails: ");
        int rails = sc.nextInt();

        sc.nextLine(); // consume newline

        String encrypted = encrypt(plaintext, rails);
        String decrypted = decrypt(encrypted, rails);

        System.out.println("\nEncrypted Text: " + encrypted);
        System.out.println("Decrypted Text: " + decrypted);

        sc.close();
    }
}
