package algorithms;

public final class Viginer {

    private final String key;

    public Viginer(String key) {
        this.key = key;
    }

    public String encrypt(String clearText) {
        StringBuilder sb = new StringBuilder();
        int keySize = key.length();
        int keyIndex = 0;
        for (Character character : clearText.toCharArray()) {
            if(Character.isAlphabetic(character)) {
                int keyCode = key.charAt(keyIndex);
                int offset = Character.getType(character) == Character.UPPERCASE_LETTER ? 65 : 97;
                int newCharCode = character - offset;
                newCharCode += keyCode;
                newCharCode %= 26;
                newCharCode += offset;
                keyIndex = (keyIndex + 1) % keySize;
                sb.append((char) newCharCode);
            }else {
                sb.append(character);
            }
        }
        return sb.toString();
    }

}