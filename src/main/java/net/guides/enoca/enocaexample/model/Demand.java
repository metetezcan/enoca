package net.guides.enoca.enocaexample.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Demand {
    private int id;
    private int customerId;
    private String timestamp;
    private int productId;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Basic
    @Column(name = "Customer_id", nullable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerno) {
        this.customerId = customerno;
    }
    
    @Basic
    @Column(name = "Timestamp", nullable = false, length = 40)
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String mytime) {
        this.timestamp = mytime;
    }
    
    @Basic
    @Column(name = "Product_id", nullable = false)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productno) {
        this.productId = productno;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Demand demand = (Demand) o;
        return id == demand.id &&
                customerId == demand.customerId &&
                Objects.equals(timestamp, demand.timestamp) &&
                productId == demand.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, timestamp , productId);
    }
}
