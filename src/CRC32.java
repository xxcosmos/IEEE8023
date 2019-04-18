class CRC32 {
    //生成多项式 4B
    private static final long POLYNOMIAL = 0x01_04_C1_1D_B7L;

    static long getCheckNum(String data) {
        //生成多项式01字符串
        String polynomialBinary = Long.toBinaryString(POLYNOMIAL);
        int polynomialLength = polynomialBinary.length();
        int totalLength = data.length() + polynomialLength - 1;

        //在数据后面补0
        data = ByteUtil.zeroImplement(data, totalLength, false);
        //校验码初始化
        String result = ByteUtil.zeroImplement("", polynomialLength, false);

        char[] polynomialBinaryCharArray = polynomialBinary.toCharArray();
        char[] resultChars = result.toCharArray();
        char[] dataChars = data.toCharArray();
        //左移k+n位
        for (int i = 0; i < totalLength; i++) {
            //左移一位
            System.arraycopy(resultChars, 1, resultChars, 0, result.length() - 1);
            //最后一位用data填充
            resultChars[result.length() - 1] = dataChars[i];
            binaryDiv(resultChars, polynomialBinaryCharArray);

        }


        return Long.valueOf(String.valueOf(resultChars), 2);
    }

    /**
     * 两个数进行模二除法
     *
     * @param divided 除数
     * @param divisor 被除数
     */
    private static void binaryDiv(char[] divided, char[] divisor) {
        if (divided[0] == '1') {
            for (int i = 0; i < divided.length; i++) {
                //异或
                divided[i] = (divided[i] == divisor[i]) ? '0' : '1';
            }
        }
    }
}