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
                int keyCode = Utils.getCharCodeOff(key.charAt(keyIndex));
                int offset = Utils.getOffset(character);
                int newCharCode = character - offset;
                newCharCode = (newCharCode + keyCode) % 26;
                sb.append(Utils.charFromCodeOff(newCharCode, offset));
                keyIndex = (keyIndex + 1) % keySize;
            }else {
                sb.append(character);
            }
        }
        return sb.toString();
    }

    public String decrypt(String cipherText){
        StringBuilder sb = new StringBuilder();
        int keySize = key.length();
        int keyIndex = 0;
        for (Character character : cipherText.toCharArray()) {
            if(Character.isAlphabetic(character)) {
                int keyCode = Utils.getCharCodeOff(key.charAt(keyIndex));
                int offset = Utils.getOffset(character);
                int newCharCode = character - offset;
                newCharCode = (newCharCode - keyCode + 26) % 26;
                sb.append(Utils.charFromCodeOff(newCharCode, offset));
                keyIndex = (keyIndex + 1) % keySize;
            }else {
                sb.append(character);
            }
        }
        return sb.toString();
    }

}