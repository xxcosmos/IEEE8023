public class FrameVO {
    //前导码 7B
    private long preamble;
    //帧前定界符 1B
    private long delimiter;
    //目的地址 6B
    private long destination;
    //原地址 6B
    private long source;

    private int id;
    private int length;
    private String data;
    private long checkNum;

    public FrameVO(long preamble, long delimiter, long destination, long source) {
        this.preamble = preamble;
        this.delimiter = delimiter;
        this.destination = destination;
        this.source = source;
    }

    public void setCheckNum(long checkNum) {
        this.checkNum = checkNum;
    }

    public long getDestination() {
        return destination;
    }

    public long getSource() {
        return source;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        String tempData = data;
        tempData = tempData.replaceAll(" ", "");
        String s = "Frame [" + id + "] start..." + "\n"
                + "前导码(7B)：" + Long.toHexString(preamble).toUpperCase() + "\n"
                + "帧前定界符(1B)：" + Long.toHexString(delimiter).toUpperCase() + "\n"
                + "目的地址(6B)：" + Long.toHexString(destination).toUpperCase() + "\n"
                + "源地址(6B)：" + Long.toHexString(source).toUpperCase() + "\n"
                + "长度：" + length + "\n"
                + "数据：" + tempData + "\n"
                + "校验码：" + Long.toHexString(checkNum).toUpperCase() + "\n";

        return s;
    }
}
