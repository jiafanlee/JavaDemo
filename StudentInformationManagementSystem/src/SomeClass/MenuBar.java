package SomeClass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JFrame{
    public JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu help = new JMenu("Help");
        JMenuItem Add = new JMenuItem("Add");
        JMenuItem Delete = new JMenuItem("Delete");
        JMenuItem Find = new JMenuItem("Find");
        JMenuItem List = new JMenuItem("List");
        Add.addActionListener(new MenuBar.menuItemActionListener(Add.getName()));
        Delete.addActionListener(new MenuBar.menuItemActionListener(Delete.getName()));
        Find.addActionListener(new MenuBar.menuItemActionListener(Find.getName()));
        List.addActionListener(new MenuBar.menuItemActionListener(List.getName()));
        edit.add(Add);
        edit.add(Delete);
        edit.add(Find);
        edit.add(List);
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(help);
        return menuBar;
    }
    private class menuItemActionListener implements ActionListener{
        String menuItemName;
        menuItemActionListener(String buttonName){
            this.menuItemName = menuItemName;
        }
        public void actionPerformed(ActionEvent e){
            if(menuItemName == "Add"){
                new AddRecord();
                JOptionPane.showConfirmDialog(null, "hello");
                setVisible(false);
            }else if(menuItemName == "Delete"){
                new DeleteRecord();
                setVisible(false);
            }else if(menuItemName == "Find"){
                new QueryRecord();
                setVisible(false);
            }else if(menuItemName == "List"){
                new StudentList();
                setVisible(false);
            }
        }
    }
    private static void createAndShowGUI(){
        JFrame frame = new JFrame("菜单栏界面");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MenuBar menuBar = new MenuBar();
        frame.setJMenuBar(menuBar.createMenuBar());
        frame.setSize(500,300);
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

