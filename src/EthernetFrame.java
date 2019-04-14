public class EthernetFrame {

    //前导码 7B
    static final long PREAMBLE = 0xAA_AA_AA_AA_AA_AA_AAL;
    //帧前定界符 1B
    static final long DELIMITER = 0xABL;
    //目的地址 6B
    private static final long DESTINATION = 0x80_00_FF_60_2C_DCL;
    //原地址 6B
    private static final long SOURCE = 0x80_00_FE_85_3A_5FL;

    private int length;
    private String data;


    EthernetFrame(String data) {
        this.data = data;
        //一个字符一字节，加上目的地址6B 源地址6B 校验码4B 长度2B
        this.length = data.length() + 18;
    }


    public int getLength() {
        return length;
    }

    public String getData() {
        return data;
    }


    String getBinaryString() {
        StringBuilder sb = new StringBuilder();
        //目的地址
        String destinationCompleted = ByteUtil.completeBinaryStringWithZero(Long.toBinaryString(DESTINATION), 6 * 8, true);
        sb.append(destinationCompleted);
        //原地址
        String sourceCompleted = ByteUtil.completeBinaryStringWithZero(Long.toBinaryString(SOURCE), 6 * 8, true);
        sb.append(sourceCompleted);

        String binaryDataString = ByteUtil.convertCharStringToBinaryString(data);
        if (data.length() < 46) {
            //数据小于最小帧长46
            length = 46 + 18;
            binaryDataString = ByteUtil.completeBinaryStringWithZero(binaryDataString, 46 * 8, false);
        }
        //长度 2Byte
        String lengthCompleted = ByteUtil.completeBinaryStringWithZero(Long.toBinaryString(length), 2 * 8, true);

        sb.append(lengthCompleted).append(binaryDataString);
        return sb.toString();
    }


}
