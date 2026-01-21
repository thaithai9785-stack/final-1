
package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class Order implements Serializable{
    private String orderCode;
    private String customerId;
    private String province;
    private String menuId;
    private int numOfTables;
    private Date evenDate;

    public Order() {
    }

    public Order(String orderCode, String customerId, String province, String menuId, int numOfTables, Date evenDate) {
        this.orderCode = orderCode;
        this.customerId = customerId;
        this.province = province;
        this.menuId = menuId;
        this.numOfTables = numOfTables;
        this.evenDate = evenDate;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public int getNumOfTables() {
        return numOfTables;
    }

    public void setNumOfTables(int numOfTables) {
        this.numOfTables = numOfTables;
    }

    public Date getEvenDate() {
        return evenDate;
    }

    public void setEvenDate(Date evenDate) {
        this.evenDate = evenDate;
    }

    @Override
    public String toString() {
        return "Order{" + "orderCode=" + orderCode + ", customerId=" + customerId + ", province=" + province + ", menuId=" + menuId + ", numOfTables=" + numOfTables + ", evenDate=" + evenDate + '}';
    }
    
    
    //sinh mã tự động
    public String generateOrderCode(){
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        return sdf.format(now);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.customerId);
        hash = 83 * hash + Objects.hashCode(this.menuId);
        hash = 83 * hash + Objects.hashCode(this.evenDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (!Objects.equals(this.customerId, other.customerId)) {
            return false;
        }
        if (!Objects.equals(this.menuId, other.menuId)) {
            return false;
        }
        return Objects.equals(this.evenDate, other.evenDate);
    }
      
    
    
    
    
}
