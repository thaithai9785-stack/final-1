
package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import model.Order;

public class Inputter {
    private Scanner ndl;

    public Inputter() {
        this.ndl = new Scanner(System.in);
    }

    public Scanner getNdl() {
        return ndl;
    }
    
    public String getString(String mess) {
        System.out.print(mess);
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

    
    public int getInt1(String mess) {
        int kq = 0;
        String tam = getString(mess);
        if (Acceptable.isValid(tam, Acceptable.INTEGER_VALID)) {
            kq = Integer.parseInt(tam);
        }
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
                System.out.println("Data is incorrect!");
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
    
     //func 1
    public Customer getCustomerInfo() {
        Customer x = new Customer();
        x.setId(inputAndLoop("Customer ID: ", Acceptable.CUS_ID_VALID,true));
        x.setName(inputAndLoop("Customer Name: ", Acceptable.NAME_VALID,true));
        x.setPhone(inputAndLoop("Customer Phone: ", Acceptable.PHONE_VALID,true));
        x.setEmail(inputAndLoop("Customer Email: ", Acceptable.EMAIL_VALID,true));
        return x;
    }
    
    
    
    public Customer getCustomerInfoToUpdate(String existingId) {
        Customer x = new Customer();
        x.setId(existingId);
        x.setName(inputAndLoop("Customer name: ", Acceptable.NAME_VALID, true));
        x.setPhone(inputAndLoop("Phone number: ", Acceptable.PHONE_VALID, true));
        x.setEmail(inputAndLoop("Customer email: ", Acceptable.EMAIL_VALID, true));
        return x;
    }
    
  public Date getEventDate(String mess) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // Bắt buộc nhập ngày tháng tồn tại thực tế
        // Dùng vòng lặp: Sai thì lặp lại, Đúng thì return thoát luôn
        while (true) {
            try {
                // Bước 1: Nhập chuỗi
                String dateStr = getString(mess);                
                // Bước 2: Chuyển chuỗi thành ngày (Parse)
                Date date = sdf.parse(dateStr);              
                // Bước 3: Kiểm tra xem ngày có phải Tương Lai không?
                Date today = new Date(); // Lấy ngày giờ hiện tại                
                if (date.after(today)) {
                    // Nếu ngày nhập vào (date) nằm sau ngày hôm nay (today)
                    return date; // -> Hợp lệ, trả về kết quả
                } else {
                    System.out.println("Invalid! Date must be in the future.");
                }
            } catch (Exception e) {
                // Bắt lỗi nhập sai định dạng (ví dụ nhập chữ, hoặc ngày 32/1)
                System.out.println("Invalid format! Please use dd/MM/yyyy");
            }
        }
    }
  
 
 
   public Order getOrderInfo() {
        Order x = new Order();
        x.setCustomerId(inputAndLoop("Customer ID: ", Acceptable.CUS_ID_VALID, true));
        x.setMenuId(inputAndLoop("Menu ID: ", Acceptable.MENU_ID_VALID, true));
        x.setNumOfTables(getInt("Enter number of tables: "));
        x.setEvenDate(getEventDate("Enter event date (dd/MM/yyyy): "));
        return x;
    }
   
   
   public Order getOrderInfoToUpdate(String existingOrderCode) {
        Order x = new Order();
        x.setOrderCode(existingOrderCode);
        x.setCustomerId(inputAndLoop("Customer ID: ", Acceptable.CUS_ID_VALID, true));
        x.setMenuId(inputAndLoop("Menu ID: ", Acceptable.MENU_ID_VALID, true));
        x.setNumOfTables(getInt("Enter number of tables: "));
        x.setEvenDate(getEventDate("Enter event date (dd/MM/yyyy): "));
        return x;
    }

 
  
    
}

    

