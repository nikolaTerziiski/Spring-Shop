package bg.springshop.springshop.service;

import bg.springshop.springshop.model.binding.RequestLiveCheckBindingModel;
import bg.springshop.springshop.model.view.LiveRequestAdminViewModel;

import java.util.List;

public interface LiveRequestsService {
    void registerLiveRequest(RequestLiveCheckBindingModel requestLiveCheckBindingModel);

    List<LiveRequestAdminViewModel> getAllLiveRequests();

    void deleteRequest(Long id);
}
