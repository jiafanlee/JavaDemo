package SomeClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteRecord extends JPanel{
    public DeleteRecord(){
        JPanel panel = new JPanel();
        JPanel above = new JPanel();
        JLabel title = new JLabel("学生删除界面");
        Font font = new Font("宋体", Font.BOLD, 18);
        title.setFont(new Font("宋体", Font.BOLD, 20));
        above.add(title);
        above.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel middle = new JPanel();
        JLabel deleteTitle = new JLabel("请输入待删除学生的姓名");
        deleteTitle.setFont(font);
        JTextField deleteBar = new JTextField(15);
        deleteBar.setFont(font);
        middle.add(deleteTitle);
        middle.add(deleteBar);
        middle.setLayout(new BoxLayout(middle, BoxLayout.X_AXIS));

        JPanel below = new JPanel();
        JButton button1 = new JButton("确认");
        button1.setFont(font);
        button1.setName("ensure");
        button1.addActionListener(new DeleteRecord.buttonActionListener(button1.getName()));
        JPanel button1_panel = new JPanel();
        button1_panel.add(button1);
        button1_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        button1_panel.setBorder(BorderFactory.createEmptyBorder(15, 5, 5, 40));
        JButton button2 = new JButton("取消");
        button2.setFont(font);
        button2.setName("cancel");
        button2.addActionListener(new DeleteRecord.buttonActionListener(button2.getName()));
        JPanel button2_panel = new JPanel();
        button2_panel.add(button2);
        button2_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        button2_panel.setBorder(BorderFactory.createEmptyBorder(15, 40, 5, 5));
        below.add(button1_panel);
        below.add(button2_panel);
        below.setLayout(new GridLayout(1,2));

        panel.add(above);
        panel.add(middle);
        panel.add(below);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.add(panel);
    }
    private class buttonActionListener implements ActionListener{
        String buttonName;
        buttonActionListener(String buttonName){
            this.buttonName = buttonName;
        }
        public void actionPerformed(ActionEvent e){
            if(buttonName == "ensure"){
                JOptionPane.showConfirmDialog(null, "delete success.");
            }else{

            }
        }
    }
    private static void createAndShowGUI(){
        JFrame frame = new JFrame("学生删除界面");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new DeleteRecord());
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
