package bg.springshop.springshop.model.entity;

import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "reports")
public class Report extends BaseEntity{

    private String reportName;
    private String email;
    private String productDescription;
    private String problemDescription;
    private boolean isAsnwered;

    @Column(nullable = false)
    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false)
    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Column(nullable = false)
    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    @Column(nullable = false)
    public boolean isAsnwered() {
        return isAsnwered;
    }

    public void setAsnwered(boolean asnwered) {
        isAsnwered = asnwered;
    }
}
