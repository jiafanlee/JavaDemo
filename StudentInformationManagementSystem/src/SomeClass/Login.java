package SomeClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JPanel{
    JTextField userName = new JTextField(20);
    JPasswordField passwordInput = new JPasswordField(20);
    public Login(){
        JPanel panel = new JPanel();
        JPanel above = new JPanel();
        JLabel title = new JLabel("用户登录");
        Font font = new Font("宋体", Font.BOLD, 18);
        title.setFont(new Font("宋体", Font.BOLD, 20));
        above.add(title);
        above.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel middle = new JPanel();
        JLabel nameLabel = new JLabel("请输入用户名");
        nameLabel.setFont(font);
        userName.setFont(font);
        JLabel passwordLabel = new JLabel("请输入密码   ");
        passwordLabel.setFont(font);
        passwordInput.setFont(font);
        middle.add(nameLabel);
        middle.add(userName);
        middle.add(new JLabel());
        middle.add(new JLabel());
        middle.add(passwordLabel);
        middle.add(passwordInput);
        middle.setLayout(new GridLayout(3, 2));
        middle.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 25));

        JPanel below = new JPanel();
        JButton button1 = new JButton("确认");
        button1.setFont(font);
        button1.setName("determine");
        button1.addActionListener(new buttonActionListener(button1.getName()));
        JPanel button1_panel = new JPanel();
        button1_panel.add(button1);
        button1_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        button1_panel.setBorder(BorderFactory.createEmptyBorder(15, 5, 5, 40));
        JButton button2 = new JButton("重置");
        button2.setFont(font);
        button2.setName("reset");
        button2.addActionListener(new buttonActionListener(button2.getName()));
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
            if(buttonName == "determine"){
                String password = String.valueOf(passwordInput.getPassword());
                String name = userName.getText();
                if(name.equals("2015150059") && password.equals("150059")){
                    System.out.println("Correct!");

                }else{
                    JOptionPane.showConfirmDialog(null, "The password is '150059'.");
                }
            }else{
                userName.setText("");
                passwordInput.setText("");
            }
        }
    }
    private static void createAndShowGUI(){
        JFrame frame = new JFrame("登录界面");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Login());
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
