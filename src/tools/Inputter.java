
package tools;

import java.util.Scanner;

public class Inputter {
    private Scanner ndl;

    public Inputter() {
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
        return Double.parseDouble(tam);
    }

    public String inputAndLoop(String mess, String pattern, boolean isLoop) {
        String result = "";
        boolean more = true;
        do {
            result = getString(mess);
            more = !Acceptable.isValid(result, pattern);
            if (more && (isLoop && result.length() > 0)) {
                System.out.println("Data is invalid!. Re-enter...");
            }
        } while (isLoop && more);
        return result.trim();
    }
}
