package Hieu_iceTea.FoodMate_Spring.service.user;


import Hieu_iceTea.FoodMate_Spring.model.User;
import Hieu_iceTea.FoodMate_Spring.service.base.BaseService;


public interface UserService extends BaseService<User, Integer> {

    User findByUsername(String username);

    User getCurrentUser();

}
