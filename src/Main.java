import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        boolean running = true;

        while (running){
            String[] text = in.nextLine().split(":", 2);
            String command = text[0];;
            String arg;

            if (text.length == 2){
                arg = text[1];
            }
            else
                arg = "";

            switch (command){
                case "exit":
                case "break":
                    running = false;
                    break;
                case "encode":
                case "e":
                    System.out.println(encode(arg));
                    break;
                case "decode":
                case "d":
                    System.out.println(decode(arg));
                    break;
            }

        }
    }

    private static String decode(String text){
        MorseEncoder decoder = new MorseEncoder(text);
        return decoder.toString();
    }

    private static String encode(String text){
        MorseEncoder encoder = new MorseEncoder(true);
        encoder.append(text);
        return encoder.toString();
    }

}
