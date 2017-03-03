package StudentInformationManageInterface;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.List;

public class StudentGUI {
    public static JFrame win;
    public static List<Student> attend = new ArrayList<Student>();
    public static List<Student> admit = new ArrayList<Student>();
    public static List<Student>tempAttend = Collections.synchronizedList(attend);
    public static List<Student>tempAdmit = Collections.synchronizedList(admit);
    public static Font font = new Font("宋体", 1, 18);
    public static File file;

    public static void CreateAndShowGUI(JFrame window){
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(600, 400);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static void Login(){
        win = new JFrame("用户登录界面");
        JPanel panel = new JPanel();
        JPanel above = new JPanel();
        JLabel title = new JLabel("用户登录");
        title.setFont(new Font("宋体", Font.BOLD, 20));
        above.add(title);
        above.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel middle = new JPanel();
        JLabel nameLabel = new JLabel("请输入用户名");
        nameLabel.setFont(font);
        JTextField userName = new JTextField(20);
        userName.setFont(font);
        JPanel namePanel = new JPanel();
        namePanel.add(nameLabel);
        namePanel.add(userName);
        namePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JLabel passwordLabel = new JLabel("请输入密码  ");
        passwordLabel.setFont(font);
        JPasswordField passwordInput = new JPasswordField(20);
        passwordInput.setFont(font);
        JPanel passwordPanel = new JPanel();
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordInput);
        passwordPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        middle.add(namePanel);
        middle.add(passwordPanel);
        middle.setLayout(new GridLayout(3, 1));

        JPanel below = new JPanel();
        JButton button1 = new JButton("确定");
        button1.setFont(font);
        JPanel button1Panel = new JPanel();
        button1Panel.add(button1);
        button1Panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        button1Panel.setBorder(BorderFactory.createEmptyBorder(15, 5, 5, 40));
        JButton button2 = new JButton("重置");
        button2.setFont(font);
        JPanel button2Panel = new JPanel();
        button2Panel.add(button2);
        button2Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        button2Panel.setBorder(BorderFactory.createEmptyBorder(15, 40, 5, 5));
        //这是用户登录界面按钮的监听事件
        JFrame win2 = new JFrame("学生信息管理系统");
        class buttonListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                if(e.getSource() == button1){
                    String name = userName.getText();
                    String password = String.valueOf(passwordInput.getPassword());
                    if(name.equals("admin") && password.equals("123456")){
                        System.out.println("Teacher login successfully");
                        win.setVisible(false);
                        CreateAndShowGUI(win2);
                        MenuBar(win2, name, tempAttend, tempAdmit);
                    }else if(name.equals("2015150059") && password.equals("150059")){
                        System.out.println("Student login successfully");
                        win.setVisible(false);
                        CreateAndShowGUI(win2);
                        MenuBar2(win2, name);
                    }else{
                        int action = JOptionPane.showConfirmDialog(null, "password is error");
                        if(action == JOptionPane.YES_NO_OPTION){
                            passwordInput.setText("");
                        }
                    }
                }else{
                    userName.setText("");
                    passwordInput.setText("");
                }
            }
        }
        button1.addActionListener(new buttonListener());
        button2.addActionListener(new buttonListener());
        below.add(button1Panel);
        below.add(button2Panel);
        below.setLayout(new GridLayout(1,2));

        panel.add(above);
        panel.add(middle);
        panel.add(below);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        win.add(panel);
        CreateAndShowGUI(win);
    }

    public static void MenuBar(JFrame win, String name, List<Student> attend, List<Student> admit) {
        win.getContentPane().removeAll();
        win.repaint();
        JPanel panel = new JPanel();
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("欢迎~" + name);
        title.setFont(font);
        titlePanel.add(title);
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        ImageIcon image = new ImageIcon("res/szu.jpg");
        JLabel label = new JLabel(image);
        label.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
        panel.add(titlePanel);
        panel.add(label);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 25, 5));

        JMenuBar menuBar = new JMenuBar();
        JMenu File = new JMenu("File");
        JMenu Edit = new JMenu("Edit");
        JMenu Help = new JMenu("Help");
        JMenuItem Add = new JMenuItem("Add");
        JMenuItem Delete = new JMenuItem("Delete");
        JMenuItem Find = new JMenuItem("Find");
        JMenuItem Inquire = new JMenuItem("Inquire");
        JMenuItem Consult = new JMenuItem("Consult");
        JMenuItem List = new JMenuItem("List");
        JMenuItem Open = new JMenuItem("Open");
        JMenuItem Save = new JMenuItem("Save");
        JMenuItem Back = new JMenuItem("Back");
        JMenuItem Exit = new JMenuItem("Exit");

        class ItemListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                if (event.getSource() == Add) {
                    AddRecord(win, tempAttend, tempAdmit);
                } else if (event.getSource() == Delete) {
                    DeleteRecord(win, tempAttend, tempAdmit);
                } else if (event.getSource() == Find) {
                    QueryRecord(win, tempAttend, tempAdmit);
                } else if(event.getSource() == Inquire){
                    InquireResult(win);
                } else if(event.getSource() == Consult){
//                    ConsultationResult(win, tempAttend, tempAdmit);
                    try {
                        new ServerThread(win, tempAttend);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (event.getSource() == List) {
                    ShowList(win, tempAttend, tempAdmit);
                } else if(event.getSource() == Open) {
                    OpenFile(win, tempAttend, tempAdmit);
                } else if(event.getSource() == Save) {
                    SaveFile(win, tempAttend, tempAdmit);
                } else if (event.getSource() == Back) {
                    MenuBar(win, name, attend, admit);
                } else if (event.getSource() == Exit) {
                    Login();
                }
            }
        }
        ItemListener item = new ItemListener();
        Add.addActionListener(item);
        Delete.addActionListener(item);
        Find.addActionListener(item);
        Inquire.addActionListener(item);
        Inquire.setEnabled(false);
        Consult.addActionListener(item);
        List.addActionListener(item);
        Open.addActionListener(item);
        Save.addActionListener(item);
        Back.addActionListener(item);
        Exit.addActionListener(item);
        Edit.add(Add);
        Edit.add(Delete);
        Edit.add(Find);
        Edit.add(Inquire);
        Edit.add(Consult);
        Edit.add(List);
        File.add(Open);
        File.add(Save);
        File.add(Back);
        Help.add(Exit);
        menuBar.add(File);
        menuBar.add(Edit);
        menuBar.add(Help);
        win.setJMenuBar(menuBar);
        win.add(panel);
        CreateAndShowGUI(win);
    }

    public static void MenuBar2(JFrame win, String name){
        win.getContentPane().removeAll();
        win.repaint();
        JPanel panel = new JPanel();
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("欢迎~" + name);
        title.setFont(font);
        titlePanel.add(title);
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        ImageIcon image = new ImageIcon("res/szu.jpg");
        JLabel label = new JLabel(image);
        label.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
        panel.add(titlePanel);
        panel.add(label);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 25, 5));

        JMenuBar menuBar = new JMenuBar();
        JMenu Edit = new JMenu("Edit");
        JMenuItem Inquire = new JMenuItem("Inquire");
        JMenuItem Exit = new JMenuItem("Exit");
        class ItemListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                if(event.getSource() == Inquire) {
                    InquireResult(win);
                } else if (event.getSource() == Exit) {
                    win.setVisible(false);
                    Login();
                }
            }
        }
        ItemListener item = new ItemListener();
        Inquire.addActionListener(item);
        Exit.addActionListener(item);
        Edit.add(Inquire);
        Edit.add(Exit);
        menuBar.add(Edit);
        win.setJMenuBar(menuBar);
        win.add(panel);
        CreateAndShowGUI(win);
    }

    public static void AddRecord(JFrame win, List<Student> attend, List<Student> admit){
        win.getContentPane().removeAll();
        win.repaint();
        JPanel panel0 = new JPanel();
        JLabel label = new JLabel("增加考生记录的操作界面");
        label.setFont(new Font("宋体", 1, 20));
        panel0.add(label);
        panel0.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("请输入姓名:     ");
        label1.setFont(font);
        panel1.add(label1);
        JTextField textField1 = new JTextField(20);
        panel1.add(textField1);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel1.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        JPanel panel2 = new JPanel();
        JLabel label2 = new JLabel("请输入性别:     ");
        label2.setFont(font);
        panel2.add(label2);
        JTextField textField2 = new JTextField(20);
        panel2.add(textField2);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel2.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JPanel panel3 = new JPanel();
        JLabel label3 = new JLabel("请输入专业:     ");
        label3.setFont(font);
        panel3.add(label3);
        JTextField textField3 = new JTextField(20);
        panel3.add(textField3);
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
        panel3.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JPanel panel4 = new JPanel();
        JLabel label4 = new JLabel("请输入毕业院校: ");
        label4.setFont(font);
        panel4.add(label4);
        JTextField textField4 = new JTextField(20);
        panel4.add(textField4);
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
        panel4.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JSeparator separator1 = new JSeparator();
        JPanel panel5 = new JPanel();
        String[] str = {"   政治","   数学","   英语","   专业课程"};
        JPanel panel5Center = new JPanel();
        panel5Center.setLayout(new BorderLayout());
        JPanel panel5Below = new JPanel();
        panel5Below.setLayout(new GridLayout(1,4));
        JTextField[] scoreText = new JTextField[4];
        for(int i = 0; i < str.length; ++i){
            JLabel subjectLabel = new JLabel(String.valueOf(str[i]));
            subjectLabel.setFont(font);
            subjectLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
            scoreText[i] = new JTextField(6);
            scoreText[i].setFont(font);

            JPanel scoreTextPanel = new JPanel();
            scoreTextPanel.add(scoreText[i], BorderLayout.WEST);
            JPanel subjectPanel = new JPanel();
            subjectPanel.add(subjectLabel);
            subjectPanel.add(scoreTextPanel);
            subjectPanel.setLayout(new BoxLayout(subjectPanel,BoxLayout.Y_AXIS));
            subjectPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

            JPanel subjectPanel2 = new JPanel();
            subjectPanel2.setLayout(new BorderLayout());
            subjectPanel2.add(subjectPanel, BorderLayout.NORTH);
            panel5Below.add(subjectPanel2);
        }
        panel5Center.add(panel5Below,BorderLayout.CENTER);
        panel5.add(panel5Center, BorderLayout.CENTER);

        JSeparator separator2 = new JSeparator();
        JPanel panel6 = new JPanel();
        JButton button1 = new JButton("确定");
        button1.setFont(font);
        JPanel button1Panel = new JPanel();
        button1Panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        button1Panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 40));
        JButton button2 = new JButton("重置");
        button2.setFont(font);
        JPanel button2Panel = new JPanel();
        button2Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        button2Panel.setBorder(BorderFactory.createEmptyBorder(5, 40, 5, 5));
        //这是在AddRecord添加考生界面下的鼠标点击响应事件
        class MyMouse extends MouseAdapter{
            public void mouseClicked(MouseEvent event){
                if(event.getSource() == button1){
                    String name = String.valueOf(textField1.getText());
                    String sex = String.valueOf(textField2.getText());
                    String profession = String.valueOf(textField3.getText());
                    String school = String.valueOf(textField4.getText());
                    int Political = Integer.valueOf(scoreText[0].getText());
                    int English = Integer.valueOf(scoreText[1].getText());
                    int Math = Integer.valueOf(scoreText[2].getText());
                    int Professional = Integer.valueOf(scoreText[3].getText());
                    int score = Political + English + Math + Professional;
                    Student stu = new Student(name, sex, profession, school, Political, English, Math, Professional, score);
                    attend.add(stu);
                    if(stu.Political >= 60 && stu.English >= 60 && stu.Math >= 60 && stu.Professional >=60 && stu.score >= 320) {
                        admit.add(stu);
                    }
                    System.out.println("Add successfully");
                    AddRecord(win, attend, admit);
                }else{
                    AddRecord(win, attend, admit);
                }
            }
        }
        button1.addMouseListener(new MyMouse());
        button2.addMouseListener(new MyMouse());
        button1Panel.add(button1);
        button2Panel.add(button2);
        panel6.add(button1Panel);
        panel6.add(button2Panel);
        panel6.setLayout(new GridLayout(1, 2));

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
        win.add(panel);
        win.validate();
        CreateAndShowGUI(win);
    }

    public static void DeleteRecord(JFrame win, List<Student> attend, List<Student> admit){
        win.getContentPane().removeAll();
        win.repaint();
        JPanel panel = new JPanel();
        JPanel above = new JPanel();
        JLabel title = new JLabel("学生删除界面");
        title.setFont(new Font("宋体", Font.BOLD, 20));
        above.add(title);
        above.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel middle = new JPanel();
        JLabel deleteTitle = new JLabel("请输入待删除学生的姓名");
        deleteTitle.setFont(font);
        JTextField deleteBar = new JTextField(20);
        deleteBar.setFont(font);
        deleteBar.setMaximumSize(new Dimension(150,30));
        middle.add(deleteTitle);
        middle.add(deleteBar);
        middle.setLayout(new BoxLayout(middle, BoxLayout.X_AXIS));

        JPanel below = new JPanel();
        JButton button1 = new JButton("确定");
        button1.setFont(font);
        JPanel button1Panel = new JPanel();
        button1Panel.add(button1);
        button1Panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        button1Panel.setBorder(BorderFactory.createEmptyBorder(50, 5, 5, 40));
        JButton button2 = new JButton("重置");
        button2.setFont(font);
        JPanel button2Panel = new JPanel();
        button2Panel.add(button2);
        button2Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        button2Panel.setBorder(BorderFactory.createEmptyBorder(50, 40, 5, 5));
        //这是在删除学生界面下的鼠标点击响应事件
        class MyMouse extends MouseAdapter{
            public void mouseClicked(MouseEvent event){
                boolean flag = true;
                if(event.getSource() == button1){
                    int action = JOptionPane.showConfirmDialog(null, "你确定删除此信息吗?");
                    if(action == JOptionPane.YES_NO_OPTION){
                        for(int i = 0; i < attend.size(); ++i){
                            if(attend.get(i).name.equals(deleteBar.getText())){
                                attend.remove(i);
                                flag = false;
                            }
                        }
                        for(int i = 0; i < admit.size(); ++i){
                            if(admit.get(i).name.equals(deleteBar.getText())){
                                admit.remove(i);
                            }
                        }
                        if(flag){
                            JOptionPane.showConfirmDialog(null, "查无此信息");
                        }else{
                            JOptionPane.showConfirmDialog(null, "成功删除此信息");
                            System.out.println("Delete successfully");
                            DeleteRecord(win, attend, admit);
                        }
                    }else if(action == JOptionPane.NO_OPTION){

                    }
                }else{
                    DeleteRecord(win, attend, admit);
                }
            }
        }
        button1.addMouseListener(new MyMouse());
        button2.addMouseListener(new MyMouse());
        below.add(button1Panel);
        below.add(button2Panel);
        below.setLayout(new GridLayout(1,2));

        panel.add(above);
        panel.add(middle);
        panel.add(below);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        win.add(panel);
        win.validate();
        CreateAndShowGUI(win);
    }

    public static void QueryRecord(JFrame win, List<Student> attend, List<Student> admit){
        win.getContentPane().removeAll();
        win.repaint();
        JPanel panel = new JPanel();
        JPanel above = new JPanel();
        JLabel title = new JLabel("学生信息查询");
        title.setFont(new Font("宋体", Font.BOLD, 20));
        above.add(title);
        above.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel middle = new JPanel();
        JLabel label = new JLabel("请输入查询姓名");
        label.setFont(font);
        JTextField queryBar = new JTextField(20);
        queryBar.setFont(font);
        queryBar.setMaximumSize(new Dimension(200,30));
        middle.add(label);
        middle.add(queryBar);
        middle.setLayout(new BoxLayout(middle, BoxLayout.X_AXIS));
        middle.setBorder(BorderFactory.createEmptyBorder(0, 5, 40, 5));

        JPanel below = new JPanel();
        JTextArea information = new JTextArea(5, 35);
        information.setFont(font);
        JScrollPane jsp = new JScrollPane(information);
        below.add(jsp);
        below.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel bottom = new JPanel();
        JButton button1 = new JButton("确定");
        button1.setFont(font);
        JPanel button1Panel = new JPanel();
        button1Panel.add(button1);
        button1Panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        button1Panel.setBorder(BorderFactory.createEmptyBorder(50, 5, 5, 40));
        JButton button2 = new JButton("取消");
        button2.setFont(font);
        JPanel button2Panel = new JPanel();
        button2Panel.add(button2);
        button2Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        button2Panel.setBorder(BorderFactory.createEmptyBorder(50, 40, 5, 5));
        //这是在QueryRecord查询学生信息界面下的鼠标点击响应事件
        class MyMouse extends MouseAdapter {
            public void mouseClicked(MouseEvent event) {
                boolean flag = true;
                if(event.getSource() == button1){
                    if(queryBar.getText() != null){
                        for(int i = 0; i < attend.size(); ++i){
                            if(attend.get(i).name.equals(queryBar.getText())){
                                String stu="姓名："+attend.get(i).name+" "+"   性别："+attend.get(i).sex+"   专业："+
                                        attend.get(i).profession+"   毕业学校：" +attend.get(i).school+"\n政治："+attend.get(i).Political+
                                        "   数学："+attend.get(i).Math+"   英语："+attend.get(i).English+
                                        "   专业课程："+attend.get(i).Professional+" 总分："+attend.get(i).score+"\n";
                                information.append(stu);
                                System.out.println("Query successfully");
                                flag = false;
                                break;
                            }
                        }
                        if(flag){
                            JOptionPane.showConfirmDialog(null, "查无此学生信息");
                        }
                    }else{
                        JOptionPane.showConfirmDialog(null, "内容不能为空");
                    }
                }else{
                    QueryRecord(win, attend, admit);
                }
            }
        }
        button1.addMouseListener(new MyMouse());
        button2.addMouseListener(new MyMouse());
        bottom.add(button1Panel);
        bottom.add(button2Panel);
        bottom.setLayout(new GridLayout(1,2));

        panel.add(above);
        panel.add(middle);
        panel.add(below);
        panel.add(bottom);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        win.add(panel);
        CreateAndShowGUI(win);
    }

    public static void InquireResult(JFrame win){
        win.getContentPane().removeAll();
        win.repaint();
        JPanel panel = new JPanel();
        JPanel above = new JPanel();
        JLabel title = new JLabel("考试成绩远程查询");
        title.setFont(new Font("宋体", Font.BOLD, 20));
        above.add(title);
        above.setLayout(new FlowLayout(FlowLayout.CENTER));
        above.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JPanel below = new JPanel();
        JTextArea information = new JTextArea(8, 43);
        information.setFont(font);
        JScrollPane jsp = new JScrollPane(information);
        below.add(jsp);
        below.setLayout(new FlowLayout(FlowLayout.CENTER));
        below.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JPanel middle = new JPanel();
        JPanel leftPanel = new JPanel();
        JLabel label = new JLabel("请输入你的姓名: ");
        label.setFont(font);
        JTextField inquireBar = new JTextField(16);
        inquireBar.setFont(font);
        inquireBar.setMaximumSize(new Dimension(160,30));
        leftPanel.add(label);
        leftPanel.add(inquireBar);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(2, 35, 0, 0));
        JPanel rightPanel = new JPanel();
        JButton button = new JButton("查询");
        button.setFont(new Font("宋体", Font.BOLD, 16));

        class ItemListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                System.out.println("客户端正在启动...");
                try{
                    if(inquireBar.getText().equals("")){
                        JOptionPane.showMessageDialog(win.getContentPane(), "请输入要查询的学生姓名!", "警告", JOptionPane.WARNING_MESSAGE);
                    }
                    Socket client = new Socket("localhost", 3090);
                    System.out.println("客户端启动成功");
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                    bw.write(inquireBar.getText() + "\r\n");
                    bw.flush();

                    BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    String msg = null, text = "";
                    while((msg = br.readLine()) != null){
                        text += msg + "\r\n";
                    }
                    information.setText(text);
                    bw.close();
                    br.close();
                    client.close();
                }catch (IOException ioe){
                    System.out.println("客户端启动失败");
                    ioe.printStackTrace();
                }
            }
        }
        button.addActionListener(new ItemListener());
        rightPanel.add(button);
        rightPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        middle.add(leftPanel);
        middle.add(rightPanel);
        middle.setLayout(new BoxLayout(middle, BoxLayout.X_AXIS));
        middle.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

        panel.add(above);
        panel.add(middle);
        panel.add(below);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        win.add(panel);
        CreateAndShowGUI(win);
    }

//    public static void ConsultationResult(JFrame win, List<Student> attend, List<Student> admit){
//        win.getContentPane().removeAll();
//        win.repaint();
//        JPanel panel = new JPanel();
//        JPanel inner = new JPanel();
//        JLabel Title = new JLabel("与同学沟通如下: ");
//        Title.setFont(font);
//        inner.add(Title);
//        inner.setLayout(new FlowLayout(FlowLayout.CENTER));
//        inner.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
//        JPanel below = new JPanel();
//        JTextArea information = new JTextArea(8, 40);
//        information.setFont(font);
//        JScrollPane jsp = new JScrollPane(information);
//        below.add(jsp);
//        below.setLayout(new FlowLayout(FlowLayout.CENTER));
//
//        panel.add(inner);
//        panel.add(below);
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        win.add(panel);
//        CreateAndShowGUI(win);
//
//        Thread th = new Thread(new StudentGUI());
//        th.start();
//    }
//    @Override
//    public void run() {
//        ServerSocket server = null;
//        Socket socket = null;
//        BufferedReader br = null;
//        try {
//            server = new ServerSocket(8090);
//            System.out.println("服务器正在启动...");
//            while(true){
//                socket = server.accept();
//                System.out.println("服务器启动成功");
//                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                String msg = null, stu = null;
//                if((msg = br.readLine()) != null) {
//                    for (int i = 0; i < attend.size(); ++i) {
//                        if (attend.get(i).name.equals(msg)) {
//                            stu = "姓名：" + attend.get(i).name + " " + "   性别：" + attend.get(i).sex + "   专业：" +
//                                    attend.get(i).profession + "   毕业学校：" + attend.get(i).school + "\n政治：" + attend.get(i).Political +
//                                    "   数学：" + attend.get(i).Math + "   英语：" + attend.get(i).English +
//                                    "   专业课程：" + attend.get(i).Professional + " 总分：" + attend.get(i).score + "\n";
//                            System.out.println("Remote query successfully");
//                            break;
//                        }
//                    }
//                }
//                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//                if(stu != null) {
//                    bw.write(stu);
//                }else{
//                    bw.write("Could not found.");
//                }
//                bw.flush();
//                br.close();
//                bw.close();
//                socket.close();
//            }
//        }catch(IOException ioe){
//            System.out.println("服务器启动失败");
//            ioe.printStackTrace();
//        }
//    }

    public static void ShowList(JFrame win, List<Student> attend, List<Student> admit){
        win.getContentPane().removeAll();
        win.repaint();
        JPanel panel = new JPanel();
        JPanel above = new JPanel();
        JLabel attendTitle = new JLabel("参加考试学生名单");
        attendTitle.setFont(font);
        JTextArea attendList = new JTextArea(4, 30);
        attendList.setFont(font);
        JScrollPane jsp = new JScrollPane(attendList);
        for(int i = 0; i < attend.size(); ++i){
            String stu="姓名："+attend.get(i).name+" "+"  性别："+attend.get(i).sex+"  专业："+
                    attend.get(i).profession+"  毕业学校：" +attend.get(i).school+"\n政治："+attend.get(i).Political+
                    "  数学："+attend.get(i).Math+"  英语："+attend.get(i).English+
                    "  专业课程："+attend.get(i).Professional+"  总分："+attend.get(i).score+"\n";
            attendList.append(stu);
        }
        JPanel attendPanel = new JPanel();
        attendPanel.add(attendTitle);
        attendPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        above.add(attendPanel);
        above.add(jsp);
        above.setLayout(new BoxLayout(above, BoxLayout.Y_AXIS));
        above.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel below = new JPanel();
        JSeparator separator1 = new JSeparator();
        JLabel admitTitle = new JLabel("拟录取学生名单");
        admitTitle.setFont(font);
        JSeparator separator2 = new JSeparator();
        JTextArea admitList = new JTextArea(4, 30);
        admitList.setFont(font);
        JScrollPane jsp2 = new JScrollPane(admitList);
//        try{
//            FileInputStream out = new FileInputStream(file);
//            InputStreamReader isr = new InputStreamReader(out);
//            int ch = 0;
//            while((ch = isr.read()) != -1){
//                admitList.append(String.valueOf((char)ch));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        for(int i = 0; i < admit.size(); ++i){
            String stu="姓名："+attend.get(i).name+" "+"  性别："+attend.get(i).sex+"  专业："+
                    attend.get(i).profession+"  毕业学校：" +attend.get(i).school+"\n政治："+attend.get(i).Political+
                    "  数学："+attend.get(i).Math+"  英语："+attend.get(i).English+
                    "  专业课程："+attend.get(i).Professional+"  总分："+attend.get(i).score+"\n";
            admitList.append(stu);
        }
        below.add(separator1);
        JPanel admitPanel = new JPanel();
        admitPanel.add(admitTitle);
        admitPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        below.add(admitPanel);
        below.add(separator2);
        below.add(jsp2);
        below.setLayout(new BoxLayout(below, BoxLayout.Y_AXIS));
        below.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        panel.add(above);
        panel.add(below);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        win.add(panel);
        CreateAndShowGUI(win);
    }

    //这是点击File菜单下的Open子菜单时调用的函数，用于打开文件并读取
    public static void OpenFile(JFrame win, List<Student> attend, List<Student> admit){
        FileDialog openFile = new FileDialog(win, "open file", FileDialog.LOAD);
        openFile.setVisible(true);
        String path = openFile.getDirectory();
        String fileName = openFile.getFile();
        String text = "";
        //设置文件路径与文件名
        file = new File(path, fileName);
        //BufferedReader作为缓冲流，利用BufferedReader的readLin()可以一次读取一行内容
        try {
            BufferedReader buff = new BufferedReader(new FileReader(file));
            String line = null;
            while( (line = buff.readLine())!= null) {
                text += (line +"\r\n");
            }
            buff.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException("文件读取失败！");
        }
        try{
            FileInputStream out = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(out);
            int ch = 0;
            String allChar = "";
            while((ch = isr.read()) != -1){
                allChar += (char)ch;
            }
//            String[] stustr = allChar.split("\\s+|\t|\n|\r\n");
            String[] stustr = allChar.split("\n|\r\n");
            for(int i = 0; i < stustr.length; ++i) {
                Student stu = new Student(stustr[i]);
                attend.add(stu);
                if (stu.Political >= 60 && stu.English >= 60 && stu.Math >= 60 && stu.Professional >= 60 && stu.score >= 320) {
                    admit.add(stu);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //这是点击File菜单下的Save子菜单时调用的函数，用于向文件写入并保存
    public static void SaveFile(JFrame win, List<Student> attend, List<Student> admit){
        FileDialog saveFile = new FileDialog(win, "save file", FileDialog.SAVE);
        saveFile.setVisible(true);
        String path = saveFile.getDirectory();
        String fileName = saveFile.getFile();
        file = new File(path, fileName);
        try{
            BufferedWriter buff = new BufferedWriter(new FileWriter(file));
            String text = "";
            for(int i = 0; i < attend.size(); ++i) {
                text += "姓名：" + attend.get(i).name + " " + "  性别：" + attend.get(i).sex + "  专业：" +
                        attend.get(i).profession + "  毕业学校：" + attend.get(i).school + "\r\n政治：" + attend.get(i).Political +
                        "  数学：" + attend.get(i).Math + "  英语：" + attend.get(i).English +
                        "  专业课程：" + attend.get(i).Professional + "  总分：" + attend.get(i).score + "\r\n";
            }
            buff.write(text);
            buff.close();
        }catch(IOException e){
            throw new RuntimeException("文件保存失败！");
        }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Login();
            }
        });
    }
}
