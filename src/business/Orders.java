
package business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
    }

    public boolean isSaved() {
        return saved;
    }
    
    
    public void update(Order x) {
        for (Order i : this) {
            if (i.getOrderCode().equalsIgnoreCase(x.getOrderCode())) {
                i = x;
                break;
            }
        }
    }
    
 
    public Order searchById(String orderCode) {
        Order result = null;
        Iterator<Order> it = this.iterator();
        while (it.hasNext() && result == null) {
            Order x = it.next();
            if (x.getOrderCode().equalsIgnoreCase(orderCode)) 
                result = x;
        }
        return result;
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
    
    
    @Override
    public void addNew(Order x){
        String newCode = x.generateOrderCode(); 
        x.setOrderCode(newCode);
        
        if(!this.isDuplicated(x))
            this.add(x);
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
