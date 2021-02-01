/**
 * @author Nasredine ABDELLI
 */

package algorithms;

public final class Cesar {

    private final int k;

    public Cesar(int k) {
        this.k = k;
    }

    /**
     * A method that process a single char
     *
     * @param input    the input character
     * @param encOrDec boolean value indicating operation type ENCRYPTION(true) or DECRYPTION(false)
     * @return the processed char (output)
     */
    private Character process(Character input, boolean encOrDec) {
        if (Character.isAlphabetic(input)) {
            int offset = Character.getType(input) == Character.UPPERCASE_LETTER ? 64 : 96;
            int output = input - offset;
            output = encOrDec ? (output + k) : (output - k);
            output %= 26;
            output += offset;
            return (char) output;
        } else {
            return input;
        }
    }

    /**
     * The encryption method
     *
     * @param clearText clear text to be encrypted
     * @return the encrypted text
     */
    public String encrypt(String clearText) {
        StringBuilder sb = new StringBuilder();
        for (Character character : clearText.toCharArray()) {
            sb.append(process(character, true));
        }
        return sb.toString();
    }

    /**
     * The decryption method
     *
     * @param cipherText the encrypted text to decrypted
     * @return the clear text
     */
    public String decrypt(String cipherText) {
        StringBuilder sb = new StringBuilder();
        for (Character character : cipherText.toCharArray()) {
            sb.append(process(character, false));
        }
        return sb.toString();
    }
}
