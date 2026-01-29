
package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import tools.Inputter;


public class Customers extends HashMap<String , Customer> implements Workable<Customer>{ 
    private String pathFile;
   private Inputter inputter;
    private boolean Saved;
    private final String TABLE_HEADER = ("|------------------------------------------------------------------|\n"
                + "|  ID  |        Full Name         |    Phone  |        Gmail       |\n"
                + "|------|--------------------------|-----------|--------------------|");
    
     private final String TABLE_FOOTER =("|------------------------------------------------------------------|");
    

    public Customers() {
        super();
        this.inputter = new Inputter();
        this.Saved= true;
        this.pathFile= "./customers.dat";
        
        this.put("C0001", new Customer("C0001", "Nguyen Van A", "0123456789", "vana@gmail.com"));
        this.put("C0002", new Customer("C0002", "Tran Thi B", "0987654321", "btran@gmail.com"));
        this.put("C0003", new Customer("C0003", "Le Van C", "0789654321", "cvan@gmail.com"));
    }

    public boolean isSaved() {
        return Saved;
    }
    
    //func 1
    public void addNew(Customer x){
        this.putIfAbsent(x.getId().toUpperCase(), x);
        //put or putIfAbsent
        this.Saved = false;
    }
  

    
    @Override
    public void update(Customer x) {
        Customer existing = this.get(x.getId());
        if(existing != null){
            existing.setName(x.getName());
            existing.setEmail(x.getEmail());
            existing.setPhone(x.getPhone());
        }
    }
    
    
    public Customer searchById(String id){
        return this.get(id.toUpperCase());
    }
    
    public List<Customer> filterByName(String name){
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

        try {
            FileInputStream fis = null;
            //1. taofile
            File f = new File(pathFile);
            if(!f.exists()) System.out.println("File not found");
            fis = new FileInputStream(f);
            ObjectInputStream ois= new ObjectInputStream(fis);
            while (fis.available()>0) {
                Customer x = (Customer) ois.readObject();
                this.put(x.getId(), x);
            }
            fis.close();
            ois.close();
            this.Saved = true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Customers.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    
    
    /* while (fis.available()>0) {
                Customer x = (Customer) ois.readObject();
                this.put(x.getId(), x);
            }
    còn du li?u thì làm ti?p
            */
    
    
    
    
    public void updateCustomerInformation() {
        String choice = "";
        Scanner sc = new Scanner(System.in);
        do {
            String customerId = inputter.getCusId("Enter customer ID to update: ");
            Customer existingCustomer = searchById(customerId);
            if (existingCustomer == null) {
                System.out.println("Customer not found!");
            } else {
                System.out.println("Current info: \n" + existingCustomer);
                System.out.println("Updating new information...");
                Customer newInfo = inputter.getCustomerInfoToUpdate(customerId);
                //Copy thông tin
                update(newInfo);
                System.out.println("Customer updated successfully!");
            }
            //hoi ng dung co muon tiep tuc ko
            System.out.print("Do you want to continue updating another customer? (Y/N): ");
            choice = sc.nextLine();
        } while (choice.equalsIgnoreCase("Y"));
    }

    
    //cach 1
//    public void searchCustomerByName() {
//        System.out.println("You choose search customer by name");
//        String name = inputter.getString("Enter name to search: ");
//
//        List<Customer> result = this.filterByName(name);
//
//        if (result.isEmpty()) {
//            System.out.println("No one matches the search criteria!");
//        } else {
//            //Lambda Expression
//            Collections.sort(result, (c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
//            showAll(result);
//        }
//    }
    
    
    

  
     //Cách 2: tối ưu
    public void searchByName(String name) {
        System.out.println("Result");
        int count = 0;
        for (Customer i : this.values()) {
            if(i.getName().toLowerCase().contains(name.toLowerCase())){
                System.out.println(i);
                count ++;
            }
        }
        if(count ==0) System.out.println("ko tim thay ten");
    }
    

    
    
    
    
    
    
}
