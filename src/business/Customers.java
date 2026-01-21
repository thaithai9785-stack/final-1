
package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.plaf.synth.Region;
import model.Customer;


public class Customers extends HashMap<String , Customer> implements Workable<Customer>{ 
    private String pathFile;
    private boolean Saved;
    private final String TABLE_HEADER = ("|------------------------------------------------------------------|\n"
                + "|  ID  |        Full Name         |    Phone  |        Gmail       |\n"
                + "|------|--------------------------|-----------|--------------------|");
    
     private final String TABLE_FOOTER =("|------------------------------------------------------------------|");
    

    public Customers() {
        super();
        this.Saved= true;
        this.pathFile= "./customers.dat";
    }

    public boolean isSaved() {
        return Saved;
    }
    
    public void addNew(Customer x){
        this.putIfAbsent(x.getName(), x);
        //put or putIfAbsent
        this.Saved = false;
    }
    
    public void update(Customer x){
        Customer z = this.get(x.getId());
        if (z!=null)
            z=x;
    }
    
    public Customer searchById(String id){
        return this.get(id);
    }
    
    public List<Customer> fitlerByName(String name){
     List<Customer> result = new ArrayList<>();
        for (Customer i : this.values()) {
            if(i.getName().toLowerCase().contains(name.toLowerCase()))
                result.add(i);
        }
        return result;
    }
    
    public void showAll(){
        showAll(this.values());
    }
    public void showAll(Collection<Customer> l) {
        System.out.println(TABLE_HEADER);
        for (Customer i : l) {
            System.out.println(i);
        }
        System.out.println(TABLE_FOOTER);
    }
    
        
    public void saveToFile() {
        
        try {
            if (this.Saved) {
                return;
            }
            FileOutputStream fos = null;
            //1. tạo file object
            File f = new File(this.pathFile);
            //2. Tạo fileObjectStream ánh  xạ tới fife Object
            fos = new FileOutputStream(f);
            //3. Tạo ObjectOutputStream để chuyển dữ liệu xuống thiết bị
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            //4. lặp ghi dữ liệu
            for (Customer i : this.values()) {
                oos.writeObject(i);
            }
            //5. Đóng các object tương ứng sau khi xử lí
            oos.close();
            fos.close();
            //6. ghi nhận trạng thái
            this.Saved = true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public final void readFromFile(){
        FileInputStream fis = null;
        1. taofile 
                File
    }

}
