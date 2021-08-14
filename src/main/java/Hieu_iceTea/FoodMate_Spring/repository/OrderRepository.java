package Hieu_iceTea.FoodMate_Spring.repository;


import Hieu_iceTea.FoodMate_Spring.model.Order;
import Hieu_iceTea.FoodMate_Spring.model.User;

import java.util.List;


public interface OrderRepository extends BaseRepository<Order, Integer> {

    List<Order> findAllByUserOrderByIdDesc(User user);

    List<Order> findAllByUserIdOrderByIdDesc(int id);

}