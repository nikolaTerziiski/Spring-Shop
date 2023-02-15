package bg.springshop.springshop.service;

import bg.springshop.springshop.model.entity.Order;
import bg.springshop.springshop.model.entity.User;
import bg.springshop.springshop.model.view.OrderProfileViewModel;

import java.util.List;

public interface OrderService {
    public Order createOrder(User user);

    public List<OrderProfileViewModel> ordersForUser(User user);
}
