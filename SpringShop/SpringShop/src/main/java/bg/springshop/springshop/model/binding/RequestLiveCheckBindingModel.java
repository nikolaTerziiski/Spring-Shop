package bg.springshop.springshop.model.binding;

public class RequestLiveCheckBindingModel {

    private String twoNames;
    private String address;
    private String dateAndHour;

    public String getDateAndHour() {
        return dateAndHour;
    }

    public void setDateAndHour(String dateAndHour) {
        this.dateAndHour = dateAndHour;
    }

    public RequestLiveCheckBindingModel() {
    }

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
}
