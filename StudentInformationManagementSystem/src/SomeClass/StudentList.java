package SomeClass;

import java.awt.*;
import javax.swing.*;

public class StudentList extends JPanel{
    public StudentList(){
        JPanel panel = new JPanel();
        JPanel above = new JPanel();
        JLabel attend = new JLabel("参加考试学生名单");
        Font font = new Font("宋体", Font.BOLD, 20);
        attend.setFont(font);
        JTextArea attendList = new JTextArea(4, 30);
        attendList.setFont(font);
        above.add(attend);
        above.add(attendList);
        above.setLayout(new BoxLayout(above, BoxLayout.Y_AXIS));
        above.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel below = new JPanel();
        JSeparator separator1 = new JSeparator();
        JLabel admit = new JLabel("拟录取学生名单");
        admit.setFont(font);
        JSeparator separator2 = new JSeparator();
        JTextArea admitList = new JTextArea(4, 30);
        admitList.setFont(font);
        below.add(separator1);
        below.add(admit);
        below.add(separator2);
        below.add(admitList);
        below.setLayout(new BoxLayout(below, BoxLayout.Y_AXIS));
        above.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        panel.add(above);
        panel.add(below);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.add(panel);
    }
    private static void createAndShowGUI(){
        JFrame frame = new JFrame("学生名单");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new StudentList());
        frame.setSize(500, 400);
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
