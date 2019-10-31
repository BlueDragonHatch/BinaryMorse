public class MorseEncoding {
    public static final int charSpacing = 3;

    private static final char[] chars= new char[]{
            'a',
            'b',
            'c',
            'd',
            'e',
            'f',
            'g',
            'h',
            'i',
            'j',
            'k',
            'l',
            'm',
            'n',
            'o',
            'p',
            'q',
            'r',
            's',
            't',
            'u',
            'v',
            'w',
            'x',
            'y',
            'z',
            '0',
            '1',
            '2',
            '3',
            '4',
            '5',
            '6',
            '7',
            '8',
            '9',
            ' '
    };
    private static final int[] ints = new int[]{
            0b10111,
            0b111010101,
            0b11101011101,
            0b1110101,
            0b1,
            0b101011101,
            0b111011101,
            0b1010101,
            0b101,
            0b1011101110111,
            0b111010111,
            0b101110101,
            0b1110111,
            0b11101,
            0b11101110111,
            0b10111011101,
            0b1110111010111,
            0b1011101,
            0b10101,
            0b111,
            0b1010111,
            0b101010111,
            0b101110111,
            0b11101010111,
            0b1110101110111,
            0b11101110101,
            0b1110111011101110111,
            0b10111011101110111,
            0b101011101110111,
            0b1010101110111,
            0b10101010111,
            0b101010101,
            0b11101010101,
            0b1110111010101,
            0b111011101110101,
            0b11101110111011101,
            0b10111010111
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
