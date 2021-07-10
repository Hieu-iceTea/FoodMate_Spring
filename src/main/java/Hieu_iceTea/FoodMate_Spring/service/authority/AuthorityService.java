package Hieu_iceTea.FoodMate_Spring.service.authority;


import Hieu_iceTea.FoodMate_Spring.model.Authority;
import Hieu_iceTea.FoodMate_Spring.model.User;
import Hieu_iceTea.FoodMate_Spring.service.base.BaseService;


public interface AuthorityService extends BaseService<Authority, Integer> {

    void updateUserAuthority(User user, String[] arrAuthorities);

}
