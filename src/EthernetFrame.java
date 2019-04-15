public class EthernetFrame {

    //前导码 7B
    static final long PREAMBLE = 0xAA_AA_AA_AA_AA_AA_AAL;
    //帧前定界符 1B
    static final long DELIMITER = 0xABL;
    //目的地址 6B
    static final long DESTINATION = 0x80_00_FF_60_2C_DCL;
    //原地址 6B
    static final long SOURCE = 0x80_00_FE_85_3A_5FL;

    private int length;
    private String data;
    private String binaryDataString;
    private String checkNum;





    EthernetFrame(String data) {
        this.data = data;
        //一个字符一字节，加上目的地址6B 源地址6B 校验码4B 长度2B
        this.length = data.length() + 18;
        binaryDataString = ByteUtil.convertCharStringToBinaryString(data);
        checkNum = Long.toHexString(CRC32.calculate(this));
    }

    public String getCheckNum() {
        return checkNum;
    }


    public int getLength() {
        return length;
    }

    public String getBinaryDataString() {
        return binaryDataString;
    }

    String getBinaryString() {
        StringBuilder sb = new StringBuilder();
        //目的地址
        String destinationCompleted = ByteUtil.completeBinaryStringWithZero(Long.toBinaryString(DESTINATION), 6 * 8, true);
        sb.append(destinationCompleted);
        //原地址
        String sourceCompleted = ByteUtil.completeBinaryStringWithZero(Long.toBinaryString(SOURCE), 6 * 8, true);
        sb.append(sourceCompleted);

        String dataBinary = binaryDataString;
        if (data.length() < 46) {
            //数据小于最小帧长46 在后面补零
            length = 46 + 18;
            dataBinary = ByteUtil.completeBinaryStringWithZero(dataBinary, 46 * 8, false);
        }
        //长度 2Byte
        String lengthCompleted = ByteUtil.completeBinaryStringWithZero(Long.toBinaryString(length), 2 * 8, true);

        sb.append(lengthCompleted).append(dataBinary);
        return sb.toString();
    }


}
