
package business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import model.Order;
import tools.Inputter;

public class Orders extends HashSet<Order> implements Workable<Order>{
    private String pathFile;
    private boolean saved;
    private Inputter inputter;

    public Orders() {
        super();
        this.saved = true;
        this.pathFile = "./feast_order.dat";
        this.inputter=new Inputter();
        
        Order o1 = new Order();
        o1.setOrderCode("20260101100001");
        o1.setCustomerId("C0001");
        o1.setProvince("Hanoi");
        o1.setMenuId("PW001");
        o1.setNumOfTables(5);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            o1.setEvenDate(sdf.parse("15/06/2026"));
        } catch (Exception e) {
            o1.setEvenDate(new Date());
        }
        this.add(o1);

        Order o2 = new Order();
        o2.setOrderCode("20260102100002");
        o2.setCustomerId("C0002");
        o2.setProvince("Ho Chi Minh");
        o2.setMenuId("PW002");
        o2.setNumOfTables(10);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            o2.setEvenDate(sdf.parse("20/07/2026"));
        } catch (Exception e) {
            o2.setEvenDate(new Date());
        }
        this.add(o2);

        Order o3 = new Order();
        o3.setOrderCode("20260103100003");
        o3.setCustomerId("C0003");
        o3.setProvince("Da Nang");
        o3.setMenuId("PW003");
        o3.setNumOfTables(8);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            o3.setEvenDate(sdf.parse("25/08/2026"));
        } catch (Exception e) {
            o3.setEvenDate(new Date());
        }
        this.add(o3);
    }

    public boolean isSaved() {
        return saved;
    }
    
    public void addNew(Order x){
        String newcode = x.generateOrderCode();
        x.setOrderCode(newcode);
        
        if(!this.saved)
            return ; 
    }
    
    
    
    @Override
    public void update(Order x) {
        for (Order i : this) {
            if (i.getOrderCode().equalsIgnoreCase(x.getOrderCode())) {
                i.setCustomerId(x.getCustomerId());
                i.setEvenDate(x.getEvenDate());
                i.setNumOfTables(x.getNumOfTables());
                i.setMenuId(x.getMenuId());
                break;
            }
        }
    }
    
    //cach 1
    /*public Order searchById(String orderCode) {
        Order result = null;
        Iterator<Order> it = this.iterator();
        while (it.hasNext() && result == null) {
            Order x = it.next();
            if (x.getOrderCode().equalsIgnoreCase(orderCode)) 
                result = x;
        }
        return result;
    }*/
    
    //cach 2
    public Order searchById(String orderCode){
        for (Order o : this) {
         if(o.getOrderCode().equalsIgnoreCase(orderCode))
             return o;
        }
        return null;
    }

    public void showAll(){
        showAll(this);
    }
    
    public void showAll(Collection<Order> l){
        for (Order i : l) {
            System.out.println(i);
        }
    }

    public boolean isDuplicated(Order x){
        return this.contains(x);
    }
    
    public void updateCode(Order x) {
        for (Order i : this) {
            if (i.getOrderCode().equalsIgnoreCase(x.getOrderCode())) {
                i = x;
                break;
            }
        }
    }
    
    
    
    
    
    public void saveToFile() {
        try {
            // 0. kiểm tra nếu đã lưu rồi thì không ghi nữa
            if (this.saved) {
                return;
            }
            FileOutputStream fos = null;

            //1. Tạo File object
            File f = new File(this.pathFile);
            //2. Tạo FileObjectStream ánh xạ tới File object
            fos = new FileOutputStream(f);
            //3. Tạo ObjectOutputStream để chuyển dữ liệu xuống thiết bị
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // 4. Lặp để ghi dữ liệu
            for (Order i : this) {
                oos.writeObject(i);
            }
            //5. Đóng các object tương ứng
            oos.close();
            fos.close();
            //6. Ghi nhận trạng thái mới
            this.saved = true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    
    
    
    
}
