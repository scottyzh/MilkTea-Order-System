package entity;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class OrdersManage {

    public static void save(File file, ArrayList orders){
        try {
            ObjectOutputStream ob = new ObjectOutputStream(new FileOutputStream(file));
            Iterator iterator = orders.iterator();
            while (iterator.hasNext()){
                Order temp = (Order) iterator.next();
                ob.writeObject(temp);
            }
            ob.writeObject(null);
            ob.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Load(File file, ArrayList orders){
        orders.clear();
        try {
            if(file.length()!=0){
                ObjectInputStream obi = new ObjectInputStream(new FileInputStream(file));
                Order temp;
                int i=0;
                while((temp=(Order) obi.readObject())!=null){
                    orders.add(temp);

                    i++;
                }
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
