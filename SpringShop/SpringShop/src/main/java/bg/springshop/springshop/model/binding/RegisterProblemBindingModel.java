package bg.springshop.springshop.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

public class RegisterProblemBindingModel {

    private String reportName;
    private String email;
    private String productDescription;
    private String problemDescription;

    public RegisterProblemBindingModel() {
    }

    @Length(min = 3, max = 50)
    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Length(min = 3)
    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Length(min = 10)
    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }
}
