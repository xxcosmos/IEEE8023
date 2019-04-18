

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<FrameVO> frameVOList = FrameService.getFrameVOList("hello world");

        for (FrameVO frameVO : frameVOList) {
            System.out.println(frameVO);
        }
    }
}
