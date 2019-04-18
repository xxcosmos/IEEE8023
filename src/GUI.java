import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI {
    private JTextField textField1;
    private JButton Button1;
    private JTextArea textArea1;
    private javax.swing.JPanel JPanel;

    public GUI() {
        Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data = textField1.getText();
                if (data == null || "".equals(data)) {
                    textField1.setText("请输入需要封装的数据");
                } else {
                    List<FrameVO> frameVOList = FrameService.getFrameVOList(data);
                    StringBuilder information = new StringBuilder();
                    for (FrameVO frameVO : frameVOList) {
                        information.append(frameVO.toString()).append("\n");
                    }


                    textArea1.setText(information.toString());


                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("IEEE802.3以太网帧封装");
        frame.setSize(5000, 4000);

        frame.setContentPane(new GUI().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
