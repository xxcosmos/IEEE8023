class ByteUtil {
    /**
     * 在长度小于length的二进制字符串补0,
     * isBefore为true 则在前面补0 false 在后面补0
     *
     * @param s      二进制字符串
     * @param length 需要的位数
     * @return 补0后的二进制字符串
     */
    static String completeBinaryStringWithZero(String s, int length, boolean isBefore) {
        if (s.length() >= length) {
            //字符串的长度大于需要的位数
            return s;
        }

        StringBuilder sb = new StringBuilder(s);

        for (int i = s.length(); i < length; i++) {
            if (isBefore) {
                //在字符串的前面补length-s.length个0
                sb.insert(0, '0');
            } else {
                //在字符串的后面补0
                sb.append('0');
            }
        }

        return sb.toString();
    }


    /**
     * 将输入的字符串的每一个字符，转换成一个8位的二进制串，用0补
     *
     * @param s 输入的数据
     * @return 得到的二进制字符串
     */
    static String convertCharStringToBinaryString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(completeBinaryStringWithZero(Integer.toBinaryString(s.charAt(i)), 8, true));
        }

        return sb.toString();
    }

}
