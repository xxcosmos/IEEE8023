import java.util.ArrayList;
import java.util.List;

public class FrameService {
    //前导码 7B
    private static final long PREAMBLE = 0xAA_AA_AA_AA_AA_AA_AAL;
    //帧前定界符 1B
    private static final long DELIMITER = 0xABL;
    //目的地址 6B
    private static final long DESTINATION = 0x80_00_FF_60_2C_DCL;
    //原地址 6B
    private static final long SOURCE = 0x80_00_FE_85_3A_5FL;

    private static List<String> getDataList(String data) {
        List<String> dataList = new ArrayList<>();

        if (data.length() < 46) {
            int length = data.length();
            StringBuilder dataBuilder = new StringBuilder(data);
            for (int i = 0; i < 46 - length; i++) {
                dataBuilder.append((char) 0x00);
            }
            data = dataBuilder.toString();
        }

        if (data.length() <= 1500) {
            dataList.add(data);
            return dataList;
        }

        while (data.length() > 1500) {
            dataList.add(data.substring(0, 1500));
            data = data.substring(1500);
        }

        return dataList;
    }

    public static List<FrameVO> getFrameVOList(String data) {
        List<String> dataList = getDataList(data);
        List<FrameVO> frameVOList = new ArrayList<>();
        for (String frameData : dataList) {
            FrameVO frameVO = new FrameVO(PREAMBLE, DELIMITER, DESTINATION, SOURCE);
            frameVO.setId(dataList.indexOf(frameData) + 1);
            frameVO.setData(frameData);
            frameVO.setLength(frameData.length() + 18);

            String binaryString = getBinaryString(frameVO);
            long checkNum = CRC32.getCheckNum(binaryString);
            frameVO.setCheckNum(checkNum);

            frameVOList.add(frameVO);
        }
        return frameVOList;

    }

    private static String getBinaryString(FrameVO frameVO) {

        StringBuilder sb = new StringBuilder();
        //目的地址
        sb.append(ByteUtil.zeroImplement(Long.toBinaryString(frameVO.getDestination()), 6 * 8, true));
        //原地址
        sb.append(ByteUtil.zeroImplement(Long.toBinaryString(frameVO.getSource()), 6 * 8, true));

        sb.append(ByteUtil.zeroImplement(Integer.toBinaryString(frameVO.getLength()), 2 * 8, true));

        String binaryData = ByteUtil.convertCharStringToBinaryString(frameVO.getData());

        sb.append(binaryData);

        return sb.toString();
    }
}
