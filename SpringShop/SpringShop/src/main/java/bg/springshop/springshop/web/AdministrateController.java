package bg.springshop.springshop.web;

import bg.springshop.springshop.model.view.LiveRequestAdminViewModel;
import bg.springshop.springshop.model.view.ReportAdministrativeViewModel;
import bg.springshop.springshop.service.LiveRequestsService;
import bg.springshop.springshop.service.ReportService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdministrateController {

    private ReportService reportService;
    private LiveRequestsService liveRequestsService;

    public AdministrateController(ReportService reportService,
                                  LiveRequestsService liveRequestsService) {

        this.reportService = reportService;
        this.liveRequestsService = liveRequestsService;
    }

    @GetMapping("/administrative")
    public String administrative(Model model) {
        List<ReportAdministrativeViewModel> reports = this.reportService.reportsUnanswered();
        List<LiveRequestAdminViewModel> liveRequests = this.liveRequestsService.getAllLiveRequests();

        boolean isEmptyReports = true;
        boolean isEmptyLive = true;

        if(reports.size() != 0) {
            model.addAttribute("reports", reports);
            isEmptyReports = false;
        }

        if(liveRequests.size() != 0) {
            model.addAttribute("liveRequests", reports);
            isEmptyLive = false;
        }

        model.addAttribute("isEmptyReports", isEmptyReports);
        model.addAttribute("isEmptyLive", isEmptyLive);

        model.addAttribute("reports", reports);
        model.addAttribute("liveRequests", liveRequests);

        return "administrative";
    }

    @GetMapping("/administrative/report/delete/{id}")
    public String reportDelete(@PathVariable Long id) {

        this.reportService.deleteReport(id);
        return "redirect:/administrative";
    }

    @GetMapping("/administrative/liveRequest/delete/{id}")
    public String liveRequestDelete(@PathVariable Long id) {

        this.liveRequestsService.deleteRequest(id);
        return "redirect:/administrative";
    }
}
