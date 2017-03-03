package SomeClass;

import javax.swing.*;
import java.awt.*;

public class QueryRecord extends JPanel{
    public QueryRecord() {
        JPanel panel = new JPanel();
        JPanel above = new JPanel();
        JLabel title = new JLabel("学生信息查询");
        Font font = new Font("宋体", Font.BOLD, 18);
        title.setFont(new Font("宋体", Font.BOLD, 20));
        above.add(title);
        above.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel middle = new JPanel();
        JLabel label = new JLabel("请输入查询姓名");
        label.setFont(font);
        JTextField queryBar = new JTextField(20);
        queryBar.setFont(font);
        middle.add(label);
        middle.add(queryBar);
        middle.setLayout(new BoxLayout(middle, BoxLayout.X_AXIS));
        middle.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel below = new JPanel();
        JTextArea information = new JTextArea(5, 35);
        information.setFont(font);
        below.add(information);
        below.setLayout(new FlowLayout(FlowLayout.CENTER));

        panel.add(above);
        panel.add(middle);
        panel.add(below);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.add(panel);
    }
    private static void createAndShowGUI(){
        JFrame frame = new JFrame("学生信息查询");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new QueryRecord());
        frame.setSize(500, 300);
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
