
package tools;

import java.util.Scanner;
import model.Customer;

public class Inputter {
    private Scanner ndl;

    public Inputter() {
        this.ndl = new Scanner(System.in);
    }

    public Scanner getNdl() {
        return ndl;
    }
    
    public String getString(String mess) {
        System.out.println(mess);
        return this.ndl.nextLine();
    }

    
    public int getInt(String mess) {
        int kq = 0;
        String tam = getString(mess);
        if (Acceptable.isValid(tam, Acceptable.INTEGER_VALID)) {
            kq = Integer.parseInt(tam);
        }
        return kq;
    }

    public double getDouble(String mess) {
        String tam = getString(mess);
        double kq=0;
        if (Acceptable.isValid(tam, Acceptable.DOUBLE_VALID))
            kq = Double.parseDouble(tam);
        return kq;
    }

  

    public String inputAndLoop(String mess, String partern){
        boolean more = true;
        String result="";
        do{
            result = getString(mess);
            more = !Acceptable.isValid(result, partern);
            
            if(more)
                System.out.println("Data if incorrect...");
        } while(more);
        return result;
    }
    
    public Customer getCustomerInfo() {
        Customer x = new Customer();
        x.setId(inputAndLoop("Customer ID: ", Acceptable.CUS_ID_VALID));
        x.setName(inputAndLoop("Customer Name: ", Acceptable.NAME_VALID));
        x.setPhone(inputAndLoop("Customer Phone: ", Acceptable.PHONE_VALID));
        x.setEmail(inputAndLoop("Customer Email: ", Acceptable.EMAIL_VALID));
        return x;
    }
}
