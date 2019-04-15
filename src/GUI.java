import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                    EthernetFrame frame = new EthernetFrame(data);
                    String pre = "0x";
                    String preamble = Long.toHexString(EthernetFrame.PREAMBLE).toUpperCase();
                    String delimiter = Long.toHexString(EthernetFrame.DELIMITER).toUpperCase();
                    String destination = Long.toHexString(EthernetFrame.DESTINATION).toUpperCase();
                    String source = Long.toHexString(EthernetFrame.SOURCE).toUpperCase();
                    String length = Integer.toHexString(frame.getLength()).toUpperCase();
                    String dataBinaryString = frame.getBinaryDataString();
                    String checkNum = frame.getCheckNum().toUpperCase();

                    StringBuilder s = new StringBuilder();
                    for (int i = 0; i < dataBinaryString.length(); i += 4) {
                        String x = "" + dataBinaryString.charAt(i) + dataBinaryString.charAt(i + 1) + dataBinaryString.charAt(i + 2) + dataBinaryString.charAt(i + 3);
                        s.append(Integer.toHexString(Integer.valueOf(x, 2)));
                    }

                    String information = "前导码(7B)：" + "\n" + pre + preamble + "\n\n"
                            + "帧前定界符(1B)：" + "\n" + pre + delimiter + "\n\n"
                            + "目的地址(6B)：" + "\n" + pre + destination + "\n\n"
                            + "源地址(6B)：" + "\n" + pre + source + "\n\n"
                            + "长度字段(2B)：" + "\n" + pre + length + "\n\n"
                            + "数据字段(最小64B)：" + "\n" + pre + s.toString().toUpperCase() + "\n\n"
                            + "校验字段(4B)：" + "\n" + pre + checkNum + "\n\n";
                    textArea1.setText(information);


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
