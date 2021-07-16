package Hieu_iceTea.FoodMate_Spring.service.user;


import Hieu_iceTea.FoodMate_Spring.model.User;
import Hieu_iceTea.FoodMate_Spring.repository.UserRepository;
import Hieu_iceTea.FoodMate_Spring.service.base.BaseServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImplement extends BaseServiceImplement<User, Integer> implements UserService {

    //region Initialization - Autowired
    @Autowired
    private UserRepository userRepository;

    public UserServiceImplement(UserRepository repository) {
        super(repository);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    //endregion

}
