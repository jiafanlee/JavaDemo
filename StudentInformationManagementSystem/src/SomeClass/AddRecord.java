package SomeClass;

import javax.swing.*;
import java.awt.*;

public class AddRecord extends JPanel{
    private JFrame frame = new JFrame("增加考生记录的操作界面");
    public AddRecord(){
        JPanel panel0 = new JPanel();
        JLabel label = new JLabel("增加考生记录的操作界面");
        label.setFont(new Font("宋体", 1, 20));       //设置汉字的字体
        panel0.add(label);
        //panel0采用流式布局以便让标题居中
        panel0.setLayout(new FlowLayout(FlowLayout.CENTER));
        //以下采用盒型布局BoxLayout对输入学生基本信息部分进行布局
        Font font = new Font("宋体", 1, 18);
        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("请输入姓名:     ");
        label1.setFont(font);
        panel1.add(label1);
        JTextField textField1 = new JTextField(20);
        panel1.add(textField1);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        //设置panel之间的间距，使之间距变大些
        panel1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        //以下从panel2到panel4都类似于panel1的做法
        JPanel panel2 = new JPanel();
        JLabel label2 = new JLabel("请输入性别:     ");
        label2.setFont(font);
        panel2.add(label2);
        JTextField textField2 = new JTextField(20);
        panel2.add(textField2);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel panel3 = new JPanel();
        JLabel label3 = new JLabel("请输入专业:     ");
        label3.setFont(font);
        panel3.add(label3);
        JTextField textField3 = new JTextField(20);
        panel3.add(textField3);
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
        panel3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel panel4 = new JPanel();
        JLabel label4 = new JLabel("请输入毕业院校: ");
        label4.setFont(font);
        panel4.add(label4);
        JTextField textField4 = new JTextField(20);
        panel4.add(textField4);
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
        panel4.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        //于是输入学生基本信息部分解决了
        //添加分割线
        JSeparator separator1 = new JSeparator();
        //接下来是采用网格布局对输入学生成绩部分进行布局
        JPanel panel5 = new JPanel();
        JLabel label5 = new JLabel("政治:");
        label5.setFont(font);
        panel5.add(label5);
        JLabel label6 = new JLabel("数学:");
        label6.setFont(font);
        panel5.add(label6);
        JLabel label7 = new JLabel("英语:");
        label7.setFont(font);
        panel5.add(label7);
        JLabel label8 = new JLabel("专业:");
        label8.setFont(font);
        panel5.add(label8);
        JTextField textField6 = new JTextField();
        panel5.add(textField6);
        JTextField textField7 = new JTextField();
        panel5.add(textField7);
        JTextField textField8 = new JTextField();
        panel5.add(textField8);
        JTextField textField9 = new JTextField();
        panel5.add(textField9);
        panel5.setLayout(new GridLayout(2,4));
        panel5.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JSeparator separator2 = new JSeparator();
        //然后再来解决两个button按钮，这里依然是采用了盒型布局
        JPanel panel6 = new JPanel();
        JButton button1 = new JButton("确定");
        button1.setFont(font);
        panel6.add(button1);
        JLabel label10 = new JLabel("               ");
        panel6.add(label10);
        JButton button2 = new JButton("取消");
        button2.setFont(font);
        panel6.add(button2);
        panel6.setLayout(new BoxLayout(panel6,BoxLayout.X_AXIS));
        panel6.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        //最后将所有的组件或容器都加到一个大的容器panel中
        JPanel panel = new JPanel();
        panel.add(panel0);
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        panel.add(separator1);
        panel.add(panel5);
        panel.add(separator2);
        panel.add(panel6);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.add(panel);
    }

    private static void createAndShowGUI(){
        JFrame frame = new JFrame("增加考生记录的操作界面");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new AddRecord());
        frame.setSize(550,320);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
