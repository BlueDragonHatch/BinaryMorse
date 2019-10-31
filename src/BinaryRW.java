import java.util.function.DoubleToIntFunction;

public class BinaryRW {

    public static void main(String[] args) {
        BinaryRW bin = new BinaryRW("Hello");

        bin.setPointerEnd();
        bin.write(61, 16);

        System.out.println(bin.toString());
    }

    StringBuilder builder;
    char curChar;
    int pointer;
    int pointerMax;

    public BinaryRW(){
        builder = new StringBuilder();
        pointer = 0;
        builder.append((char) 0);
        loadCurChar();
    }
    public BinaryRW(String input){
        builder = new StringBuilder(input);
        pointer = 0;
        loadCurChar();
    }

    // Pointer functions
    private int pointerRemainder(){
        return 16 - pointer % 16;
    }
    private int pointerIndex(){
        return pointer / 16;
    }
    private void movePointer(int delta){
        saveCurChar();

        pointer += delta;
        pointer = Math.max(0, pointer);

        while (pointerIndex() >= builder.length()){
            builder.append((char)0);
        }
        if (pointer >= pointerMax)
            pointerMax = pointer + 1;

        loadCurChar();
    }

    private void loadCurChar(){
        curChar = builder.charAt(pointerIndex());
    }
    private void saveCurChar()
    {
        builder.setCharAt(pointerIndex(), curChar);
    }

    public void setBit(int bit) {
        bit &= 1;
        curChar &= ~(1 << (pointerRemainder() - 1));
        curChar |= bit << (pointerRemainder() - 1);
    }
    public int getBit(){
        return (curChar >> pointerRemainder() - 1) & 1;
    }

    public void write(int bit){
        setBit(bit);
        movePointer(1);
    }
    public void write(int bits, int size){
        for (int i = size - 1; i >= 0; i--) {
            write(bits >> i);
        }
    }

    public int read() {
        int output = getBit();
        movePointer(1);
        return output;
    }
    public int read(int amount) {
        int output = 0;
        for (int i = 0; i < amount; i++) {
            output <<= 1;
            output |= read();
        }
        return output;
    }

    public void setPointer(int newPosition){
            saveCurChar();

            pointer = newPosition;
            pointer = Math.max(0, pointer);

            while (pointerIndex() >= builder.length()){
                builder.append((char)0);
            }
            if (pointer >= pointerMax)
                pointerMax = pointer + 1;

            loadCurChar();
        }
    public void setPointerStart()
    {
        saveCurChar();
        pointer = 0;
        loadCurChar();
    }
    public void setPointerEnd()
    {
        setPointer(pointerMax);
    }

    public boolean isPointerAtEnd() {
        return pointer == pointerMax - 1;
    }

    public String toString(){
        saveCurChar();
        return builder.toString();
    }
}
