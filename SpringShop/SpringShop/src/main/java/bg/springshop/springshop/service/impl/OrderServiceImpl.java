package bg.springshop.springshop.service.impl;

import bg.springshop.springshop.model.entity.Order;
import bg.springshop.springshop.model.entity.Product;
import bg.springshop.springshop.model.entity.User;
import bg.springshop.springshop.model.view.OrderProfileViewModel;
import bg.springshop.springshop.repository.OrderRepository;
import bg.springshop.springshop.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository,
                            ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Order createOrder(User user) {
        Order order = new Order();

        order.setProducts(user.getShoppingCart().getProducts());
        order.setUser(user);
        this.orderRepository.save(order);

        return order;
    }

    @Override
    public List<OrderProfileViewModel> ordersForUser(User user) {

        List<Order> orders = this.orderRepository.findAll().stream().filter(e-> Objects.equals(e.getUser().id, user.id)).collect(Collectors.toList());

        List<OrderProfileViewModel> orderProfileViewModels = orders.stream().map(e -> {
            BigDecimal price = e.getProducts().stream().map(Product::getPrice).reduce(BigDecimal.ONE, BigDecimal::add);
            OrderProfileViewModel orderProfileViewModel = new OrderProfileViewModel(e.getProducts(), e.getCreated(), price);
            return orderProfileViewModel;
        }).collect(Collectors.toList());

        return orderProfileViewModels;
    }
}
