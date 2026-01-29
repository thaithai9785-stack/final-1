
package dispatcher;

import business.Customers;
import business.Orders;
import business.SetMenus;
import javax.xml.transform.OutputKeys;
import model.Customer;
import model.Order;
import sun.security.mscapi.PRNG;
import tools.Inputter;

public class Main {
    public static void main(String[] args) {
        Inputter ndl = new Inputter();
        int choice = 0;
        Customers dskh = new Customers();
        SetMenus mn=new SetMenus();
        Orders dsdh = new Orders();
        
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
                    String CusID = ndl.getCusId("Id");
                    Customer c= dskh.searchById(CusID);
                    if(c ==null)
                        System.out.println("ko tim thay");
                    else{
                        System.out.println("current info: \n"+c);
                        System.out.println("update: ");
                        Customer newInfo = ndl.getCustomerInfoToUpdate(CusID);
                        dskh.update(newInfo);
                        System.out.println("update thanh cong");
                    }
                    break;
                case 3:
                    String name = ndl.getName("name");
                    dskh.searchByName(name);
                    break;     
                case 4:
                    mn.showMenus();
                    break;  
                case 5:
                    Order order = ndl.getOrderInfo();
                    Customer existing = dskh.searchById(order.getCustomerId());
                    if(existing == null)
                        System.out.println("Khong tim thay khach hang");
                    else if(dsdh.isDuplicated(order))
                        System.out.println("don hang bi trung");
                    else{
                        dsdh.addNew(order);
                        System.out.println("dat hang thanh cong");
                    }
                    dsdh.showAll();
                    break;  
                case 6:
                    String orderCode = ndl.getString("Enter order code to update: ");
                    Order existingOrder = dsdh.searchById(orderCode);
                    if (existingOrder == null) {
                        System.out.println("Order not found!");
                    } else {
                        System.out.println("Current order information:");
                        System.out.println(existingOrder);
                        System.out.println("Update new information:");
                        Order updatedOrder = ndl.getOrderInfoToUpdate(orderCode);
                        dsdh.update(updatedOrder);
                        System.out.println("Order updated successfully!");
                    }
                    break;
                case 7:
                    dskh.saveToFile();
                    dsdh.saveToFile();
                    System.out.println("Data saved successfully!");
                    break;
                case 8:
                    int displayChoice = 0;
                    do {
                        displayChoice = ndl.getInt(
                                "------------------------------------------\n"
                                + "\"1.Display customer list.\n"
                                + "\"2.Display order list.\n"
                                + "\"Others- Back to main menu.\n"
                                + "------------------------------------------\n"
                                + "Your choice: ");
                        switch (displayChoice) {
                            case 1:
                                System.out.println("DANH SACH KHACH HANG:");
                                dskh.showAll();
                                break;
                            case 2:
                                System.out.println("DANH SACH DON HANG:");
                                dsdh.showAll();
                                break;
                            default:
                                System.out.println("Back to main menu...");
                        }
                    } while (displayChoice >= 1 && displayChoice <= 2);
                    break;

                default:
                    System.out.println("End program");
                    break;
            }
        } while (choice >= 1 && choice <=8);
    }
    
}
