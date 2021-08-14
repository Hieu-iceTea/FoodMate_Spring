package Hieu_iceTea.FoodMate_Spring.service.order;


import Hieu_iceTea.FoodMate_Spring.model.Order;
import Hieu_iceTea.FoodMate_Spring.model.User;
import Hieu_iceTea.FoodMate_Spring.repository.OrderRepository;
import Hieu_iceTea.FoodMate_Spring.service.base.BaseServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImplement extends BaseServiceImplement<Order, Integer> implements OrderService {

    //region Initialization - Autowired
    @Autowired
    private OrderRepository orderRepository;

    public OrderServiceImplement(OrderRepository repository) {
        super(repository);
    }

    @Override
    public List<Order> findAllByUserOrderByIdDesc(User user) {

        return orderRepository.findAllByUserOrderByIdDesc(user);

    }

    @Override
    public List<Order> findAllByUserIdOrderByIdDesc(int id) {

        return orderRepository.findAllByUserIdOrderByIdDesc(id);

    }
    //endregion

}
