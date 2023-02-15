package bg.springshop.springshop.model.view;

public class LiveRequestAdminViewModel {

    private Long id;
    private String twoNames;
    private String address;
    private String dateAndHour;

    public String getTwoNames() {
        return twoNames;
    }

    public void setTwoNames(String twoNames) {
        this.twoNames = twoNames;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateAndHour() {
        return dateAndHour;
    }

    public void setDateAndHour(String dateAndHour) {
        this.dateAndHour = dateAndHour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
