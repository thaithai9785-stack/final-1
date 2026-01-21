
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

    //Kiểm tra dữ liệu nhập vào có hợp lệ hay không
    public String inputAndLoop(String mess, String pattern, boolean isLoop){
        boolean more = true;
        String result="";
        do{
            result = getString(mess);
            more = !Acceptable.isValid(result, pattern);
            
            if(more)
                System.out.println("Data if incorrect!");
        } while(more && isLoop);
        return result;
    }
    
    //Nhập dữ liệu thông tin khách hàng từ bàn phím
    public String getCusId(String mess) {
        return inputAndLoop("Customer ID: ", Acceptable.CUS_ID_VALID, true);
    }

    public String getName(String mess) {
        return inputAndLoop("Customer Name: ", Acceptable.NAME_VALID, true);
    }

    public String getPhone(String mess) {
        return inputAndLoop("Phone number [10 digits]: ", Acceptable.PHONE_VALID, true);
    }

    public String getEmail(String mess) {
        return inputAndLoop("Email address: ", Acceptable.EMAIL_VALID, true);
    }

    public String getMenuId(String mess) {
        return inputAndLoop("Menu ID: ", Acceptable.MENU_ID_VALID, true);
    }

    public String getProvince(String mess) {
        return inputAndLoop("Province: ", Acceptable.PROVINCE_VALID, true);
    }
    
    
    public Customer getCustomerInfo() {
        Customer x = new Customer();
        x.setId(inputAndLoop("Customer ID: ", Acceptable.CUS_ID_VALID,true));
        x.setName(inputAndLoop("Customer Name: ", Acceptable.NAME_VALID,true));
        x.setPhone(inputAndLoop("Customer Phone: ", Acceptable.PHONE_VALID,true));
        x.setEmail(inputAndLoop("Customer Email: ", Acceptable.EMAIL_VALID,true));
        return x;
    }
    
    
    
}
