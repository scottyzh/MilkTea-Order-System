package viewWithSingleton;



import entity.Order;
import entity.OrdersManage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class GuiSearch {
    private JFrame f;
    private JPanel p3;
    private JLabel l0,l1,l2,l3,l4;
    private JTextField t0,t1,t2,t3,t4;
    private JButton back,submit,clear;

    private static GuiSearch guiSearch= null;

    public static synchronized GuiSearch getInstance() {
        if (guiSearch == null)
            guiSearch = new GuiSearch();
        return guiSearch;
    }


    private GuiSearch(){
        f = new JFrame("查找订单");
        f.setSize(297, 299);
        f.setLocationRelativeTo(null);
        p3 = new JPanel();
        p3.setBounds(24, 206, 247, 33);
        l3 = new JLabel("email:");

        // Buttons
        back = new JButton("返回");
        clear = new JButton("清空输入");
        submit = new JButton("搜索");


        p3.add(back);
        p3.add(clear);
        p3.add(submit);
        f.getContentPane().add(p3);
        f.getContentPane().setLayout(null);


        // Labels
        l0 = new JLabel("查找订单：");
        l0.setBounds(24, 21, 65, 15);
        l1 = new JLabel("订单内容：");
        l1.setBounds(23, 54, 65, 15);
        l2 = new JLabel("价格：");
        l2.setBounds(24, 92, 53, 15);
        l3 = new JLabel("客户类型：");
        l3.setBounds(24, 133, 65, 15);
        l4 = new JLabel("状态：");
        l4.setBounds(24, 168, 65, 15);
        f.getContentPane().add(l1);
        f.getContentPane().add(l2);
        f.getContentPane().add(l3);
        f.getContentPane().add(l0);
        f.getContentPane().add(l4);


        //文本
        t0 = new JTextField(20);
        t0.setBounds(87, 18, 142, 21);
        t1 = new JTextField(20);
        t1.setBounds(87, 89, 77, 21);
        t1.setText("待查找");
        t1.setEditable(false);
        t2 = new JTextField(20);
        t2.setBounds(87, 51, 184, 21);
        t2.setText("待查找");
        t2.setEditable(false);
        t3 = new JTextField(20);
        t3.setText("待查找");
        t3.setEditable(false);
        t3.setBounds(87, 131, 77, 21);
        t4 = new JTextField(20);
        t4.setText("待查找");
        t4.setEditable(false);
        t4.setBounds(87, 165, 77, 21);
        f.getContentPane().add(t4);
        f.getContentPane().add(t3);
        f.getContentPane().add(t1);
        f.getContentPane().add(t0);
        f.getContentPane().add(t2);

        f.setVisible(true);

        buttonActionListener();
    }

    //监听器设置
    public void buttonActionListener(){
        //返回按钮
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                f.setVisible(false);
            }
        });

        //清空输入按钮
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t0.setText("");
            }
        });

        //提交按钮
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String num = t0.getText();
                try {
                    Integer p =Integer.valueOf(num);
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(f,"请输入正确格式的订单号！","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ArrayList<Order> orders= new ArrayList<Order>();
                File file = new File("orders.txt");
                OrdersManage.Load(file,orders);
                int k = 0;
                for(int i = 0;i<orders.size();i++){
                    Order temp = (Order) orders.get(i);
                    if(temp.getOrderNum() == Integer.valueOf(num)){
                        t2.setText(temp.getDev());
                        t1.setText(String.valueOf(temp.getPrice()));
                        t3.setText(temp.getUserIdentity());
                        t4.setText(temp.getState());
                        k++;
                    }
                }
                if(k==0){
                    JOptionPane.showMessageDialog(f,"输入的订单号不存在！","Error",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    public  void freamSetVisible(){
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new GuiSearch();
    }
}