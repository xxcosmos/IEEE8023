import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        EthernetFrame ethernetFrame = new EthernetFrame("hello world");
        System.out.println(Long.toHexString(CRC32.calculate(ethernetFrame)));
//        char[] resultChars = "abcdefg".toCharArray();
//        System.arraycopy(resultChars, 1, resultChars, 0, resultChars.length-1);
//        System.out.println(resultChars);
    }
}
