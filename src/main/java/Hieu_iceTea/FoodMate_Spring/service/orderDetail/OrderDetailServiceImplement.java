package Hieu_iceTea.FoodMate_Spring.service.orderDetail;


import Hieu_iceTea.FoodMate_Spring.model.OrderDetail;
import Hieu_iceTea.FoodMate_Spring.repository.OrderDetailRepository;
import Hieu_iceTea.FoodMate_Spring.service.base.BaseServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderDetailServiceImplement extends BaseServiceImplement<OrderDetail, Integer> implements OrderDetailService {

    //region Initialization - Autowired
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImplement(OrderDetailRepository repository) {
        super(repository);
    }
    //endregion

}
