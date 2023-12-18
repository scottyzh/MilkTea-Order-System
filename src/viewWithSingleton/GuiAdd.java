package viewWithSingleton;


import Proxy.PorxyPrice;
import Proxy.ProductPrice;
import Proxy.RealPrice;
import decorator.*;
import entity.OrdersManage;
import entity.Order;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GuiAdd extends JFrame  {
    ArrayDeque<Drink> drinks = new ArrayDeque<>();

    File file = new File("orders.txt");
    private JPanel contentPane;
    JRadioButton jrb,jrb1,jrb2,jrb3,rb1,rb2;
    JButton back,bt,bt2,bt3,bt4;
    String identity = null;
    JCheckBox cb1,cb2,cb3,cb4;
    JTextArea textArea;
    JLabel l0,l1,l2;

    DeCaf deCaf = null;
    InstantCaf instantCaf = null;
    WhiteTea whiteTea = null;
    BlackTea blackTea = null;
    Milk milk = null;
    Soy soy =null;
    Sugar sugar = null;


    //单例模式
    private static GuiAdd guiAdd = null;

    public static synchronized GuiAdd getInstance() {
        if (guiAdd == null)
            guiAdd = new GuiAdd();
        return guiAdd;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GuiAdd frame = new GuiAdd();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private GuiAdd() {
        setVisible(true);
        setTitle("添加订单");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        setSize(406,327);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        bt = new JButton("商品菜单");
        bt.setBounds(46, 20, 114, 23);
        contentPane.add(bt);
        bt3 = new JButton("确认选择");
        bt3.setBounds(25, 179, 93, 23);
        contentPane.add(bt3);
        bt2 = new JButton("提交订单");
        bt2.setVerticalAlignment(SwingConstants.BOTTOM);
        bt2.setBounds(25, 245, 93, 23);
        contentPane.add(bt2);
        back = new JButton("返回");
        back.setBounds(325, 10, 65, 15);
        contentPane.add(back);
        bt4 = new JButton("撤回选择");
        bt4.setBounds(25, 212, 93, 23);
        contentPane.add(bt4);

        l0 = new JLabel("咖啡：");
        l0.setBounds(25, 73, 54, 15);
        contentPane.add(l0);
        l1 = new JLabel("茶：");
        l1.setBounds(25, 111, 54, 15);
        contentPane.add(l1);
        l2 = new JLabel("配料：");
        l2.setBounds(25, 143, 54, 15);
        contentPane.add(l2);

        ButtonGroup bg = new ButtonGroup();
        ButtonGroup bg2 = new ButtonGroup();

        jrb = new JRadioButton("无因咖啡");
        jrb.setBounds(94, 69, 103, 23);
        contentPane.add(jrb);
        jrb1 = new JRadioButton("速溶咖啡");
        jrb1.setBounds(210, 69, 121, 23);
        contentPane.add(jrb1);
        jrb2 = new JRadioButton("白茶");
        jrb2.setBounds(95, 107, 103, 23);
        contentPane.add(jrb2);
        jrb3 = new JRadioButton("黑茶");
        jrb3.setBounds(210, 107, 121, 23);
        contentPane.add(jrb3);

        bg.add(jrb);
        bg.add(jrb1);
        bg.add(jrb2);
        bg.add(jrb3);


        cb1 = new JCheckBox("糖分");
        cb1 .setBounds(85, 139, 93, 23);
        contentPane.add(cb1 );
        cb2 = new JCheckBox("牛奶");
        cb2.setBounds(180, 139, 93, 23);
        contentPane.add(cb2);
        cb3 = new JCheckBox("豆浆");
        cb3.setBounds(275, 139, 93, 23);
        contentPane.add(cb3);



        rb1 = new JRadioButton("VIP客户—88折");
        rb1.setBounds(210, 10, 115, 23);
        contentPane.add(rb1);
        rb2 = new JRadioButton("普通客户");
        rb2.setBounds(210, 31, 121, 23);
        contentPane.add(rb2);

        bg2.add(rb1);
        bg2.add(rb2);

        textArea = new JTextArea();
        textArea.setBounds(150, 178, 218, 100);
        contentPane.add(textArea);

        bg.add(jrb);
        bg.add(jrb1);
        bg.add(jrb2);
        bg.add(jrb3);

        //调用监听器
        buttonActionListener();

        }

        //监听器设置
        public void buttonActionListener(){

            //返回按钮
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GuiMain gt = GuiMain.getInstance();
                    gt.freamSetVisible();
                    gt.loadOrders();
                }
            });

            //设置相应——菜单
            bt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GuiMenu guiMenu = GuiMenu.getInstance();
                    guiMenu.freamSetVisible();
                }
            });

            //提交按钮
            bt2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<Order> orders = new ArrayList<>();
                    OrdersManage.Load(file,orders);
                    if(drinks.isEmpty()){
                        JOptionPane.showMessageDialog(GuiAdd.getInstance(),"该订单为空，提交失败","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if(rb1.isSelected()){
                        setIdentity("vip");
                    }else if(rb2.isSelected()){
                        setIdentity("普通客户");
                    }

                    Drink drink = drinks.peek();

                    //调用代理模式里的类
                    ProductPrice realProductPrice = new RealPrice(drink.cost());
                    ProductPrice proxyProduct = new PorxyPrice(realProductPrice, identity);
                    double realPrice = proxyProduct.getPrice();
                    Order order = new Order(getOrderNum(),drink.getDes(),realPrice,identity,"未付款");
                    JOptionPane.showMessageDialog(GuiAdd.getInstance(),drink.getDes()+"\n当前客户是"+identity+"，\n原价为"+drink.cost()+"\n当前价格为："
                            + realPrice,"提交成功！",JOptionPane.INFORMATION_MESSAGE);
                    textArea.setText("");
                    orders.add(order);
                    drinks.pop();
                    OrdersManage.save(file,orders);
                }
            });

            //确认按钮
            bt3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!rb1.isSelected()&&!rb2.isSelected()){
                        JOptionPane.showMessageDialog(GuiAdd.getInstance(),"未选择用户类型！","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(drinks.size()==1){
                        JOptionPane.showMessageDialog(GuiAdd.getInstance(),"存在未待提交订单！请先提交\n或者撤回订单","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(!jrb.isSelected() && !jrb1.isSelected() &&
                            !jrb2.isSelected() && !jrb3.isSelected()){
                        JOptionPane.showMessageDialog(GuiAdd.getInstance(),"您未选中主饮品！","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(jrb.isSelected()){
                        Drink deCaf =  new DeCaf();
                        drinks.push(deCaf);
                        addDecorator(deCaf);
                    }
                    if(jrb1.isSelected()){
                        Drink instantCaf =  new InstantCaf();
                        drinks.push(instantCaf);
                        addDecorator(instantCaf);
                    }
                    if(jrb2.isSelected()){
                        Drink whiteTea =  new WhiteTea();
                        drinks.push(whiteTea);
                        addDecorator(whiteTea);
                    }
                    if(jrb3.isSelected()){
                        Drink blackTea =  new BlackTea();
                        drinks.push(blackTea);
                        addDecorator(blackTea);
                    }

                    //将控制台的输出打印在文本域上
                    PrintStream oldPrintStream = System.out;
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    System.setOut(new PrintStream(bos)); //设置新的out
                    System.out.println(drinks.peek().getDes());
                    System.setOut(oldPrintStream); //恢复原来的System.out
                    textArea.setText(bos.toString());
                }
            });

            //撤回操作
            bt4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(drinks.size()==0){
                        JOptionPane.showMessageDialog(GuiAdd.getInstance(),"订单为空，无法执行撤回操作！","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    drinks.pop();
                    textArea.setText("");
                }
            });


        }

        //随机数生产
        public int getOrderNum(){

            Random rand=new Random();//生成随机数
            String cardNnumer="";
            for(int a=0;a<8;a++){
                cardNnumer+=rand.nextInt(10);//生成8位数字
            }
            return Integer.valueOf(cardNnumer);
        }

        //添加饮品配料
     public void addDecorator(Drink drink){
        if(cb1.isSelected()){
            Sugar sugar = new Sugar(drinks.peek());
            drinks.pop();
            drinks.push(sugar);
        }
        if(cb2.isSelected()){
            Milk milk = new Milk(drinks.peek());
            drinks.pop();
            drinks.push(milk);
        }
        if(cb3.isSelected()){
            Soy soy = new Soy(drinks.peek());
            drinks.pop();
            drinks.push(soy);
        }
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }


}

