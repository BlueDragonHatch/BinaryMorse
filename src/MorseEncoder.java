public class MorseEncoder {
    private boolean encoding;
    private BinaryRW bin;

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
            int encodedChar = MorseEncoding.encode(character) << MorseEncoding.charSpacing;
            int size = size(encodedChar);

            //System.out.println("Encodedchar: " + encodedChar);
            //System.out.println("size: " + size);

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

            bin.setPointer(0);
            int numstorage = 0;
            int lastOne = 0;

            while (!bin.isPointerAtEnd()){
                //System.out.println(bin.getPointer());
                numstorage <<= 1;
                numstorage |= bin.read();
                lastOne++;
                //System.out.println(numstorage);

                //System.out.println("Matching?");
                //System.out.println((numstorage & ~(~0 << (MorseEncoding.charSpacing + 2))));
                //System.out.println(((1 << (MorseEncoding.charSpacing + 2)) | 1));

                if ((numstorage & ~(~0 << (MorseEncoding.charSpacing + 2))) == ((1 << (MorseEncoding.charSpacing + 1)) | 1)) {
                    builder.append(MorseEncoding.decode(numstorage >> (MorseEncoding.charSpacing + 1)));
                    //System.out.println("HI: " + MorseEncoding.decode(numstorage >> (MorseEncoding.charSpacing + 1)));
                    numstorage &= 1;
                    //System.out.println(numstorage);
                }

                if ((numstorage & 1) == 1){
                    //System.out.println("happened");
                    lastOne = 0;
                }
            }

            //System.out.println("ss: " + numstorage);
            //System.out.println("sss: " + lastOne);
            builder.append(MorseEncoding.decode(numstorage >> (lastOne)));
            //System.out.println("HI: " + MorseEncoding.decode(numstorage >> (lastOne)));

            return builder.toString();
        }
    }

    private int size(int bits){
        return 32 - Integer.numberOfLeadingZeros( bits );
    }
}
