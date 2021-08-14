package Hieu_iceTea.FoodMate_Spring.service.order;


import Hieu_iceTea.FoodMate_Spring.model.Order;
import Hieu_iceTea.FoodMate_Spring.model.User;
import Hieu_iceTea.FoodMate_Spring.service.base.BaseService;

import java.util.List;


public interface OrderService extends BaseService<Order, Integer> {

    List<Order> findAllByUserOrderByIdDesc(User user);

    List<Order> findAllByUserIdOrderByIdDesc(int id);

}
