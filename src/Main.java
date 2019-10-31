public class Main {
    public static void main(String[] args) {
        MorseEncoder enc = new MorseEncoder(true);

        enc.append("abca");

        System.out.println(enc.toString());

        MorseEncoder dec = new MorseEncoder(enc.toString());

        System.out.println(dec.toString());
    }
}
