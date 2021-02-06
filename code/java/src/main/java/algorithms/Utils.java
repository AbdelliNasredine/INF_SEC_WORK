package algorithms;

public class Utils {
    public static int getOffset(char character) {
        return Character.getType(character) == Character.UPPERCASE_LETTER ? 65 : 97;
    }

    public static int getCharCodeOff(char character) {
        return character - getOffset(character);
    }

    public static char charFromCodeOff(int CharacterCode, int offset) {
        return (char) (CharacterCode + offset);
    }

}
