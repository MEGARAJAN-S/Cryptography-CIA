import java.util.Scanner;

public class HillCipher {

    static int[][] keyMatrix = new int[2][2];
    static int[][] inverseKey = new int[2][2];

    // Convert letter to number (A=0, B=1, ..., Z=25)
    public static int charToInt(char ch) {
        return ch - 'A';
    }

    // Convert number to letter
    public static char intToChar(int n) {
        return (char) (n + 'A');
    }

    // Get determinant
    public static int getDeterminant(int[][] matrix) {
        return (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]) % 26;
    }

    // Get modular inverse of a number mod 26
    public static int modInverse(int a) {
        a = a % 26;
        for (int x = 1; x < 26; x++) {
            if ((a * x) % 26 == 1) return x;
        }
        return -1;
    }

    // Generate inverse key matrix (only works for 2x2)
    public static boolean generateInverseKey() {
        int det = getDeterminant(keyMatrix);
        int invDet = modInverse(det);

        if (invDet == -1) {
            return false; // inverse doesn't exist
        }

        inverseKey[0][0] = ( keyMatrix[1][1] * invDet) % 26;
        inverseKey[0][1] = (-keyMatrix[0][1] * invDet) % 26;
        inverseKey[1][0] = (-keyMatrix[1][0] * invDet) % 26;
        inverseKey[1][1] = ( keyMatrix[0][0] * invDet) % 26;

        // Handle negative values
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                if (inverseKey[i][j] < 0)
                    inverseKey[i][j] += 26;

        return true;
    }

    // Encrypt pair of characters
    public static String encrypt(String plaintext) {
        StringBuilder cipher = new StringBuilder();

        // Pad if needed
        if (plaintext.length() % 2 != 0) {
            plaintext += "X";
        }

        for (int i = 0; i < plaintext.length(); i += 2) {
            int[] vector = {
                charToInt(plaintext.charAt(i)),
                charToInt(plaintext.charAt(i + 1))
            };

            int[] result = {
                (keyMatrix[0][0] * vector[0] + keyMatrix[0][1] * vector[1]) % 26,
                (keyMatrix[1][0] * vector[0] + keyMatrix[1][1] * vector[1]) % 26
            };

            cipher.append(intToChar(result[0]));
            cipher.append(intToChar(result[1]));
        }

        return cipher.toString();
    }

    // Decrypt pair of characters
    public static String decrypt(String ciphertext) {
        StringBuilder plain = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += 2) {
            int[] vector = {
                charToInt(ciphertext.charAt(i)),
                charToInt(ciphertext.charAt(i + 1))
            };

            int[] result = {
                (inverseKey[0][0] * vector[0] + inverseKey[0][1] * vector[1]) % 26,
                (inverseKey[1][0] * vector[0] + inverseKey[1][1] * vector[1]) % 26
            };

            plain.append(intToChar(result[0]));
            plain.append(intToChar(result[1]));
        }

        return plain.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter 4 integers for 2x2 key matrix (row-wise):");
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                keyMatrix[i][j] = scanner.nextInt();

        if (!generateInverseKey()) {
            System.out.println("Key matrix is not invertible. Please choose another key.");
            return;
        }

        scanner.nextLine(); // consume leftover newline

        System.out.print("Enter plaintext (A–Z only): ");
        String input = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "");

        String encrypted = encrypt(input);
        String decrypted = decrypt(encrypted);

        System.out.println("\nEncrypted Text: " + encrypted);
        System.out.println("Decrypted Text: " + decrypted);

        scanner.close();
    }
}
