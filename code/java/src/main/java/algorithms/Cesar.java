package algorithms;

public final class Cesar {

    private final int k;

    public Cesar(int k){
        this.k = k;
    }

    public String encrypt(String clearText){
        StringBuilder sb = new StringBuilder();
        for(char character : clearText.toCharArray()){
            // if character isn't an alphabet then keep it without encryption
            // Upper case characters
            if((character >= 65 && character <= 90 ) ){
                int newCharacter =  character - 64;
                newCharacter = (newCharacter + k) % 26;
                newCharacter += 64;
                sb.append((char) newCharacter);
            }

            // lower case characters
            if((character >= 97 && character <= 122 )) {
                int newCharacter =  character - 96;
                newCharacter = (newCharacter + k) % 26;
                newCharacter += 96;
                sb.append((char) newCharacter);
            }
        }
        return sb.toString();
    }

    public String decrypt(String cipherText){
        return "";
    }
}
