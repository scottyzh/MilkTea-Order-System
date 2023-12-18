package viewWithSingleton;

import composite.Menu;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GuiMenu extends JFrame{

    private GuiMenu(){
        setSize(250, 260);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("商品菜单");
        getContentPane().setLayout(null);
        JTextArea textArea = new JTextArea();
        textArea.setBounds(10, 10, 210, 200);
        getContentPane().add(textArea);
        PrintStream oldPrintStream = System.out; //将原来的System.out交给printStream 对象保存
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos)); //设置新的out
        Menu menu = new Menu();
        Menu.show();
        System.setOut(oldPrintStream); //恢复原来的System.out
        textArea.setText(bos.toString());
        textArea.setEditable(false);

    }

    private static GuiMenu guiMenu= null;

    public static synchronized GuiMenu getInstance() {
        if (guiMenu == null)
            guiMenu = new GuiMenu();
        return guiMenu;
    }

    public void freamSetVisible(){
        setVisible(true);
    }

    public static void main(String[] args) {
        new GuiMenu();
    }

}
