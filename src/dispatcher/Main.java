
package dispatcher;

import business.Customers;
import model.Customer;
import tools.Inputter;

public class Main {
    public static void main(String[] args) {
        Inputter ndl = new Inputter();
        int choice = 0;
        Customers dskh = new Customers();
        
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
        + "Others- Quit.\n");
            
            switch (choice) {
                case 1:
                    System.out.println("Ban da chon chuc nang Them khach hang moi");
                    Customer x= ndl.getCustomerInfo();
                    dskh.addNew(x);
                    break;
                case 2:
                    System.out.println("Ban da chon chuc nang Update khach hang moi ");
                    break;
                case 3:
                    System.out.println("Ban da chon chuc nang Tim khach hang ");
                    break;
                case 4:
                    System.out.println("Ban da chon chuc nang Hien danh sach theo ten");
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
                    break;
                default:
                    break;
            }
        } while (choice >= 1 && choice <=8);
    }
    
}
