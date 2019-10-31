public class MorseEncoding {
    private static final char[] chars= new char[]{
            'a',
            'b'
    };
    private static final int[] ints = new int[]{
            0b1011,
            0b11010101
    };

    public static char decode(int number){
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] == number)
                return chars[i];
        }
        return (char)0;
    }

    public static int encode(char character){
        for (int i = 0; i < ints.length; i++) {
            if (chars[i] == character)
                return ints[i];
        }
        return 0;
    }
}
