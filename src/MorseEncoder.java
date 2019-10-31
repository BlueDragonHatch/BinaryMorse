public class MorseEncoder {
    private static final int charSpacing = 2;

    boolean encoding;
    BinaryRW bin;

    public MorseEncoder(boolean encode){
        encoding = encode;
        bin = new BinaryRW();
    }
    public MorseEncoder(String string){
        encoding = false;
        bin = new BinaryRW(string);
    }

    public void append(char character){
        if (encoding){
            int encodedChar = MorseEncoding.encode(character) << charSpacing;
            int size = size(character);

            bin.write(encodedChar, size);
        }
        else{
            bin.write(character, 16);
        }
    }
    public void append(String string){
        for (char character: string.toCharArray()) {
            append(character);
        }
    }

    public String toString(){
        if (encoding)
            return bin.toString();
        else {
            StringBuilder builder = new StringBuilder();

            int numstorage = 0;

            while (!bin.isPointerAtEnd()){
                numstorage <<= 1;
                numstorage |= bin.read();

                if ((numstorage & ~(~0 << (charSpacing + 2))) == ((1 << (charSpacing + 2)) & 1))
                    builder.append(MorseEncoding.decode(numstorage >> (charSpacing + 1)));

                numstorage &= 1;
            }

            return builder.toString();
        }
    }

    private int size(int number){
        return 32 - Integer.numberOfLeadingZeros(number);
    }
}
