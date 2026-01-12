
package business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import model.Customer;


public class Customers extends HashMap<String , Customer>{
    private String pathFile;
    private boolean Saved;

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
    public void showAll(Collection<Customer> l){
        for (Customer i : l) {
            System.out.println(i);
        }
   
    }
    
}
