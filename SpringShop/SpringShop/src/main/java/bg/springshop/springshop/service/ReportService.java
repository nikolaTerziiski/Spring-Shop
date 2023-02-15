package bg.springshop.springshop.service;

import bg.springshop.springshop.model.binding.RegisterProblemBindingModel;
import bg.springshop.springshop.model.view.ReportAdministrativeViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ReportService {
    void registerReport(RegisterProblemBindingModel registerProblemBindingModel);

    List<ReportAdministrativeViewModel> reportsUnanswered();

    void deleteReport(Long id);
}
