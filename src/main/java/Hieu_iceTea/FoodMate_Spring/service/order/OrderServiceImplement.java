package Hieu_iceTea.FoodMate_Spring.service.order;


import Hieu_iceTea.FoodMate_Spring.model.Order;
import Hieu_iceTea.FoodMate_Spring.repository.OrderRepository;
import Hieu_iceTea.FoodMate_Spring.service.base.BaseServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImplement extends BaseServiceImplement<Order, Integer> implements OrderService {

    //region Initialization - Autowired
    @Autowired
    private OrderRepository orderRepository;

    public OrderServiceImplement(OrderRepository repository) {
        super(repository);
    }
    //endregion

}
