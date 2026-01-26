
package business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SetMenu;


public class SetMenus extends ArrayList<SetMenu> {
    private String pathFile;

    public SetMenus() {
        super();
        this.pathFile="./FeastMenu.csv";
        this.readFromFile();
    }
    
    public void showMenus() {
        Collections.sort(this, (c1,c2) -> Double.compare(c1.getPrice(), c2.getPrice()));
        
        // 2. Hiển thị ra màn hình 
        System.out.println("List of Set Menus for ordering party:");
        System.out.println("---------------------------------------");
        
        for (SetMenu menu : this) {
            System.out.println("Code : " + menu.getMenuId());
            System.out.println("Name : " + menu.getMenuName());
            System.out.printf("Price : %,.0f Vnd\n", menu.getPrice()); 
            System.out.println("Ingredients:");
        
            //Thay dấu # thành xuống dòng
            String formattedIng = menu.getIngredients().replace("#", "\n");
             
            System.out.println(formattedIng);
            System.out.println("---------------------------------------");
        }
    }
    
    public void readFromFile() {
        FileReader fr = null;
        try {
            //1.tạo file
             File f = new File(pathFile);
            //2.ánh xạ vào .. dựa vào ?
            fr = new FileReader(f);
            //3.tạo bộ đệm để vận chuyển FileReader
            BufferedReader br = new BufferedReader(fr);
            //4.lặp để đọc dữ liệu
            String tam = "";
            while ((tam = br.readLine()) != null) {
                SetMenu x = TextToObject(tam);
                if (x!=null)
                    this.add(x);
            }   br.close();
         
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SetMenus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SetMenus.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(SetMenus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

       

    private SetMenu TextToObject(String tam) {
         SetMenu x = new SetMenu();
        String[] temp = tam.split(",");
        try {
            if (temp.length == 4) {
                x.setMenuId(temp[0]);
                x.setMenuName(temp[1]);
                x.setPrice(Double.parseDouble(temp[2]));
                x.setIngredients(temp[3]);
            }
        } catch (Exception e) {
            x=null;
        }
      
        return x;
    }
}


