package viewWithSingleton;

import entity.Order;
import entity.OrderList;
import entity.OrdersManage;
import memento.Caretaker;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

public class GuiMain {
    File file = new File("orders.txt");
    ArrayList<Order> orders = new ArrayList<>();
    OrderList orderList = new OrderList();
    Caretaker caretaker = new Caretaker();

    // Components
    private JFrame f;
    private JButton back,addNew,search,delete,withdraw,exit,update;
    private JPanel p1,p2,p3;
    private JScrollPane sp;
    private JTable t;
    static DefaultTableModel tm ;
    private JDialog d;
    private JLabel lab;
    static JComboBox comb;

//应用单例模式
    private static GuiMain guiMain = null;

    public static synchronized GuiMain getInstance() {
        if (guiMain == null)
            guiMain = new GuiMain();
        return guiMain;
    }

    private GuiMain(){
        OrdersManage.Load(file,orders);
        f = new JFrame("查看所有订单");
        f.setSize(550,569);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setLayout(null);

        addNew = new JButton("增加新订单");
        search = new JButton("查找订单");
        delete = new JButton("删除订单");
        withdraw = new JButton("撤回删除");
        update = new JButton("修改订单");
        exit = new JButton("退出系统");

        p1 = new JPanel();
        p2 = new JPanel();


        p1.setBounds(37, 10, 440, 33);
        p1.add(addNew);
        p1.add(search);
        p1.add(delete);
        p1.add(withdraw);
        f.getContentPane().add(p1);

        update.setBounds(290, 500, 100, 23);
        exit.setBounds(403, 500, 100, 23);

        tm =createTable();
        //滚动条
        sp = new JScrollPane(t);
        sp.setBounds(37, 53, 452, 442);
        f.getContentPane().add(sp);
        sp.setColumnHeaderView(p2);

        f.getContentPane().add(exit);
        f.getContentPane().add(update);
        f.setVisible(true);

        //设置监听器
        buttonActionListener();
        tableActionListener();

    }

    //配置按钮监听器
    public void buttonActionListener(){
        //退出按钮监听器
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //修改按钮监听器
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(f,"双击表格文本框,进行修改。\n修�耐瓿珊蟀椿爻导４嫘薷�","提示",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        //删除按钮监听器
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //建个数组保存删除前的状态
                ArrayList <Order> newOrder = new ArrayList<>();
                for(int i =0;i<orders.size();i++){
                    newOrder.add(orders.get(i));
                }

                String str = JOptionPane.showInputDialog(f,"请输入要删除的订单号：","输入",JOptionPane.PLAIN_MESSAGE);

                //检验是否输入数字
                try {
                    Integer p =Integer.valueOf(str);
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(f,"请输入正确格式的订单号！","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int k = 0;
                for(int i =0;i<orders.size();i++){
                    if(orders.get(i).getOrderNum() == Integer.valueOf(str)){
                        int confirm = JOptionPane.showConfirmDialog(f,"已找到"+str+"所对应的订单，是否删除？","确认",JOptionPane.YES_NO_CANCEL_OPTION);
                        if(confirm == 0){
                            orderList.setOrders(newOrder);
                            caretaker.add(orderList.saveOrdersMemento());
                            orders.remove(i);
                            tm = updateTable();
                            k++;
                            return;
                        }else{
                            return;
                        }
                    }

                }
                if(k == 0){
                    JOptionPane.showMessageDialog(f,"找不到该订单！","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });

        //查找按钮监听器
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiSearch guiSearch = GuiSearch.getInstance();
                guiSearch.freamSetVisible();
            }
        });

        //撤回按钮监听器
        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(caretaker.mementoListSize()==0){
                    JOptionPane.showMessageDialog(f,"不存在上一步操作，无法撤回删除","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                orderList.getOrdersFromMemento(caretaker.getLast());
                orders = orderList.getOrders();
                tm = updateTable();
            }
        });

        //新增订单按钮监听器
        addNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiAdd.getInstance();
                f.setVisible(false);
                tm = updateTable();
            }
        });
    }

    //表格更改监听器，实现保存修改的表格内容
    public void tableActionListener(){
        t.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int type = e.getType();// 获得事件的类型
                int row = e.getFirstRow() + 1;// 获得触发此次事件的表格行索引
                int column = e.getColumn() + 1;// 获得触发此次事件的表格列索引
                if (type == TableModelEvent.UPDATE) {
                    switch (column){
                        case 1:
                            String update = (String) t.getValueAt(row-1, column-1);
                            (orders.get(row-1)).setOrderNum(Integer.valueOf(update));
                            break;
                        case 2:
                            update = (String) t.getValueAt(row-1, column-1);
                            (orders.get(row-1)).setDev(update);
                            break;
                        case 3:
                            update = (String) t.getValueAt(row-1, column-1);
                            (orders.get(row-1)).setPrice(Double.valueOf(update));
                            break;
                        case 4:
                            update = (String) t.getValueAt(row-1, column-1);
                            (orders.get(row-1)).setUserIdentity(update);
                            break;
                        case 5:
                            update = (String) t.getValueAt(row-1, column-1);
                            (orders.get(row-1)).setState(update);
                            break;
                    }

                }
                OrdersManage.save(file,orders);
            }
        });
    }


    //初始化表格
    public DefaultTableModel createTable(){
        String[] col = {"订单号", "描述","价格","顾客类型","订单状态"};
        String[][] info = new String[orders.size()][col.length];

        for(int i=0; i < orders.size();i++){
            Order item = orders.get(i);
            info[i][0] = String.valueOf(item.getOrderNum());
            info[i][1] = item.getDev();
            info[i][2] = String.valueOf(item.getPrice());
            info[i][3] = item.getUserIdentity();
            info[i][4] = item.getState();
        }

        DefaultTableModel tm = new DefaultTableModel(info,col);
        t=new JTable(tm);

        String[] items = {"未付款","待取货","完成"};
        comb = new JComboBox(items);
        //comb 绑定到第5列
        TableColumnModel tcm = t.getTableHeader().getColumnModel();
        TableColumn tc01 = tcm.getColumn(4);
        tc01.setCellEditor(new DefaultCellEditor(comb));
        t.setAutoCreateColumnsFromModel(true);
        t.setAutoCreateRowSorter(true);
        t.setFillsViewportHeight(true);
        return tm;
    }

    //更新表格数据
    public DefaultTableModel updateTable(){
        String[][] inf = new String[orders.size()][5];
        Vector<String> columnNames = new Vector<String>();
        columnNames.add("订单号");
        columnNames.add("描述");
        columnNames.add("价格");
        columnNames.add("顾客类型");
        columnNames.add("订单状态");
        Vector<Vector<String>> data = new Vector<Vector<String>>();
        for(int i=0; i < orders.size();i++){
            Order item = orders.get(i);
            Vector<String> row = new Vector<String>();
            row.add(String.valueOf(item.getOrderNum()));
            row.add(item.getDev());
            row.add(String.valueOf(item.getPrice()));
            row.add(item.getUserIdentity());
            row.add(item.getState());
            data.add(row);
        }
        tm.setDataVector(data,columnNames);
        return tm;
    }

    //为订单orders载入保存在txt的数据
    public void loadOrders(){
        OrdersManage.Load(file,orders);
        String[][] inf = new String[orders.size()][5];
        Vector<String> columnNames = new Vector<String>();
        columnNames.add("订单号");
        columnNames.add("描述");
        columnNames.add("价格");
        columnNames.add("顾客类型");
        columnNames.add("订单状态");

        OrdersManage.Load(file,orders);
        Vector<Vector<String>> studentData = new Vector<Vector<String>>();
        for(int i=0; i < orders.size();i++){
            Order item = orders.get(i);
            Vector<String> row1 = new Vector<String>();
            row1.add(String.valueOf(item.getOrderNum()));
            row1.add(item.getDev());
            row1.add(String.valueOf(item.getPrice()));
            row1.add(item.getUserIdentity());
            row1.add(item.getState());
            studentData.add(row1);
        }
        GuiMain.getInstance().tm.setDataVector(studentData,columnNames);
    }

    //界面开启显示
    public void freamSetVisible(){
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new GuiMain();
    }

}


