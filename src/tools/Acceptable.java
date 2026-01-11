
package tools;


public interface Acceptable {
    public final String CUS_ID_VALID = "^[CcGgKk]\\d{4}$";
    public final String NAME_VALID = "^.{2,25$}";
    public final String PHONE_VALID = "^[0]\\d{9}$";
    public final String INTEGER_VALID = "^[+-]?\\d+$";
    public final String POSITIVE_INT_VALID = "^[1-9]\\d*$";
    public final String DOUBLE_VALID = "^[+-]?\\d+(\\.\\d+)?";
    public final String POSITIVE_DOUBLE_VALID = "^[0-9]\\d+?(\\.\\d+)?$";
    public final String EMAIL_VALID = "^[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z]{2,}$";
    
    public static boolean isValid(String data, String pattern){
        return data.matches(pattern);
    }
    
}
