package bg.springshop.springshop.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "liveRequests")
public class RequestLive extends BaseEntity{
    private String twoNames;
    private String address;
    private String dateAndHour;

    @Column(nullable = false)
    public String getTwoNames() {
        return twoNames;
    }

    public void setTwoNames(String twoNames) {
        this.twoNames = twoNames;
    }

    @Column(nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(nullable = false)
    public String getDateAndHour() {
        return dateAndHour;
    }

    public void setDateAndHour(String dateAndHour) {
        this.dateAndHour = dateAndHour;
    }
}
