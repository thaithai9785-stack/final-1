
package dispatcher;

import business.Customers;
import business.SetMenus;
import javax.xml.transform.OutputKeys;
import model.Customer;
import sun.security.mscapi.PRNG;
import tools.Inputter;

public class Main {
    public static void main(String[] args) {
        Inputter ndl = new Inputter();
        int choice = 0;
        Customers dskh = new Customers();
        SetMenus mn=new SetMenus();
        
        //--------làm menu---------//
        do {            
                
            choice = ndl.getInt("\n---------------------------\n"
                    + "1. Register customers.\n"
                    + "2. Update customer information.\n"
                    + "3. Search for customer information by name.\n"
                    + "4. Display feast menus.\n"
                    + "5. Place a feast order.\n"
                    + "6. Update order information.\n"
                    + "7. Save data to file.\n"
                    + "8. Display Customer or Order lists.\n"
                    + "Others- Quit.\n"
                    + "Your choice: ");

            
            switch (choice) {
                case 1:
                    System.out.println("Register customers:");
                    Customer x= ndl.getCustomerInfo();
                    dskh.addNew(x);
                    break;
                case 2:
                    dskh.updateCustomerInformation();
                    break;
                case 3:
                    dskh.searchCustomerByName();
                    break;
                case 4:
                    mn.showMenus();
                    break;
                case 5:
                    System.out.println("Ban da chon chuc nang Dat tiec");
                    break;
                case 6:
                    System.out.println("Ban da chon chuc nang Update Oder");
                    break;
                case 7:
                    System.out.println("Ban da chon chuc nang Save du lieu vao file");
                    break;
                case 8:
                    System.out.println("Ban da chon chuc nang Hien Customer hoac Oder");
                    dskh.showAll();
                    break;
                default:
                    System.out.println("Ket thuc chuong trinh");
                    break;
            }
        } while (choice >= 1 && choice <=8);
    }
    
}
