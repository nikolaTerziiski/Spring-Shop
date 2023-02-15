package bg.springshop.springshop.service.impl;

import bg.springshop.springshop.model.binding.RegisterProblemBindingModel;
import bg.springshop.springshop.model.entity.Report;
import bg.springshop.springshop.model.view.ReportAdministrativeViewModel;
import bg.springshop.springshop.repository.ReportRepository;
import bg.springshop.springshop.service.ReportService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
    private ModelMapper modelMapper;
    private ReportRepository reportRepository;

    public ReportServiceImpl(ModelMapper modelMapper,
                             ReportRepository reportRepository) {
        this.modelMapper = modelMapper;
        this.reportRepository = reportRepository;
    }

    @Override
    public void registerReport(RegisterProblemBindingModel registerProblemBindingModel) {

        Report report = modelMapper.map(registerProblemBindingModel, Report.class);
        report.setAsnwered(false);
        this.reportRepository.save(report);
    }

    @Override
    public List<ReportAdministrativeViewModel> reportsUnanswered() {
        List<Report> reportsAll = this.reportRepository.findAll();
        List<ReportAdministrativeViewModel> reportsUnanswered = reportsAll.stream().filter(e->!e.isAsnwered()).map(e -> {
                ReportAdministrativeViewModel report = modelMapper.map(e, ReportAdministrativeViewModel.class);
                return report;

        }).collect(Collectors.toList());

        return reportsUnanswered;
    }

    @Override
    public void deleteReport(Long id) {
        this.reportRepository.deleteById(id);
    }
}
