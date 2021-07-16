package Hieu_iceTea.FoodMate_Spring.service.user;


import Hieu_iceTea.FoodMate_Spring.model.User;
import Hieu_iceTea.FoodMate_Spring.repository.UserRepository;
import Hieu_iceTea.FoodMate_Spring.service.base.BaseServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImplement extends BaseServiceImplement<User, Integer> implements UserService {

    //region Initialization - Autowired
    @Autowired
    private UserRepository userRepository;

    public UserServiceImplement(UserRepository repository) {
        super(repository);
    }
    //endregion


    //region Method
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails userDetails) { //principal != "anonymousUser"
                return userRepository.findByUsername(userDetails.getUsername());
            }
        }

        return null;
    }
    //endregion

}
