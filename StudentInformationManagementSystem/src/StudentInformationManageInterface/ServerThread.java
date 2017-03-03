package StudentInformationManageInterface;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.List;

public class ServerThread implements Runnable{
    private JFrame win;
    private List<Student> attend;
    private ServerSocket server;
    private JLabel Title;
    private JTextArea information;
    public ServerThread(JFrame win, List<Student> attend) throws IOException {
        this.win = win;
        this.attend = attend;
        this.server = null;
        this.Title = new JLabel();
        this.information = new JTextArea();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI(win);
            }
        });
    }

    public void createAndShowGUI(JFrame win){
        win.getContentPane().removeAll();
        win.repaint();
        JPanel panel = new JPanel();
        JPanel inner = new JPanel();
        Title.setText("与 某某同学 沟通结果如下");
        Title.setFont(new Font("宋体", Font.BOLD, 18));
        inner.add(Title);
        inner.setLayout(new FlowLayout(FlowLayout.CENTER));
        inner.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
        JPanel below = new JPanel();
        information = new JTextArea(8, 40);
        information.setFont(new Font("宋体", Font.BOLD, 18));
        JScrollPane jsp = new JScrollPane(information);
        below.add(jsp);
        below.setLayout(new FlowLayout(FlowLayout.CENTER));

        panel.add(inner);
        panel.add(below);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        win.add(panel);
        win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        win.setSize(600, 400);
        win.setLocationRelativeTo(null);
        win.setVisible(true);

        try {
            server = new ServerSocket(3090);
            new Thread(this).start();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void run() {
        Socket socket = null;
        BufferedReader br = null;
        try {
            System.out.println("服务器正在启动...");
            while(true){
                socket = server.accept();
                System.out.println("服务器启动成功");
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String msg = null, stu = null;
                if((msg = br.readLine()) != null) {
                    Title.setText("与 " + msg + " 同学沟通结果如下:");
                    Title.setFont(new Font("宋体", Font.BOLD, 18));
                    for (int i = 0; i < attend.size(); ++i) {
                        if (attend.get(i).name.equals(msg)) {
                            stu = "姓名：" + attend.get(i).name + " " + "   性别：" + attend.get(i).sex + "   专业：" +
                                    attend.get(i).profession + "   毕业学校：" + attend.get(i).school + "\n政治：" + attend.get(i).Political +
                                    "   数学：" + attend.get(i).Math + "   英语：" + attend.get(i).English +
                                    "   专业课程：" + attend.get(i).Professional + " 总分：" + attend.get(i).score + "\n";
                            System.out.println("Remote query successfully");
                            break;
                        }
                    }
                    information.append(msg + " 查询了成绩，已反馈成绩。" + "\n");
                }
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                if(stu != null) {
                    bw.write(stu + "\r\n");
                }else{
                    bw.write("Could not found.\r\n");
                }
                bw.flush();
                br.close();
                bw.close();
                socket.close();
            }
        }catch(IOException ioe){
            System.out.println("服务器启动失败");
            ioe.printStackTrace();
        }
    }

}
