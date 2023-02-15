package bg.springshop.springshop.service.impl;

import bg.springshop.springshop.model.binding.RequestLiveCheckBindingModel;
import bg.springshop.springshop.model.entity.Report;
import bg.springshop.springshop.model.entity.RequestLive;
import bg.springshop.springshop.model.view.LiveRequestAdminViewModel;
import bg.springshop.springshop.model.view.ReportAdministrativeViewModel;
import bg.springshop.springshop.repository.ReportRepository;
import bg.springshop.springshop.repository.RequestLiveRepository;
import bg.springshop.springshop.service.LiveRequestsService;
import org.junit.runner.Request;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LiveRequestsServiceImpl implements LiveRequestsService {
    private ModelMapper modelMapper;
    private RequestLiveRepository requestLiveRepository;

    public LiveRequestsServiceImpl(ModelMapper modelMapper,
                                   RequestLiveRepository requestLiveRepository) {
        this.modelMapper = modelMapper;
        this.requestLiveRepository = requestLiveRepository;
    }
    @Override
    public void registerLiveRequest(RequestLiveCheckBindingModel requestLiveCheckBindingModel) {
        RequestLive requestLive = modelMapper.map(requestLiveCheckBindingModel, RequestLive.class);

        this.requestLiveRepository.save(requestLive);
    }

    @Override
    public List<LiveRequestAdminViewModel> getAllLiveRequests() {
        List<RequestLive> requestsLive = this.requestLiveRepository.findAll();
        List<LiveRequestAdminViewModel> reportsUnanswered = requestsLive.stream().map(e -> {
            LiveRequestAdminViewModel report = modelMapper.map(e, LiveRequestAdminViewModel.class);
            return report;

        }).collect(Collectors.toList());

        return reportsUnanswered;
    }

    @Override
    public void deleteRequest(Long id) {
        this.requestLiveRepository.deleteById(id);
    }
}
