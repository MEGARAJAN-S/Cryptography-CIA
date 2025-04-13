import java.util.Scanner;

public class RouteCipher {

    // Encrypt plaintext using Route Cipher (top-to-bottom column read)
    public static String encrypt(String plaintext, int cols) {
        plaintext = plaintext.replaceAll("\\s+", "").toUpperCase();

        int len = plaintext.length();
        int rows = (int) Math.ceil((double) len / cols);

        char[][] matrix = new char[rows][cols];
        int index = 0;

        // Fill matrix row-wise
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (index < len)
                    matrix[i][j] = plaintext.charAt(index++);
                else
                    matrix[i][j] = 'X'; // padding
            }
        }

        // Read matrix column-wise (top to bottom)
        StringBuilder cipher = new StringBuilder();
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                cipher.append(matrix[i][j]);
            }
        }

        return cipher.toString();
    }

    // Decrypt ciphertext using Route Cipher
    public static String decrypt(String ciphertext, int cols) {
        ciphertext = ciphertext.replaceAll("\\s+", "").toUpperCase();

        int len = ciphertext.length();
        int rows = (int) Math.ceil((double) len / cols);

        char[][] matrix = new char[rows][cols];
        int index = 0;

        // Fill matrix column-wise
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                if (index < len)
                    matrix[i][j] = ciphertext.charAt(index++);
            }
        }

        // Read matrix row-wise
        StringBuilder plain = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                plain.append(matrix[i][j]);
            }
        }

        return plain.toString(); // can strip trailing 'X' if needed
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine();

        System.out.print("Enter number of columns (key): ");
        int cols = sc.nextInt();

        String encrypted = encrypt(plaintext, cols);
        String decrypted = decrypt(encrypted, cols);

        System.out.println("\nEncrypted Text: " + encrypted);
        System.out.println("Decrypted Text: " + decrypted);

        sc.close();
    }
}
