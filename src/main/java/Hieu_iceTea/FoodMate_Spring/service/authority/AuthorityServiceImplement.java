package Hieu_iceTea.FoodMate_Spring.service.authority;


import Hieu_iceTea.FoodMate_Spring.model.Authority;
import Hieu_iceTea.FoodMate_Spring.repository.AuthorityRepository;
import Hieu_iceTea.FoodMate_Spring.service.base.BaseServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthorityServiceImplement extends BaseServiceImplement<Authority, Integer> implements AuthorityService {

    //region Initialization - Autowired
    @Autowired
    private AuthorityRepository authorityRepository;

    public AuthorityServiceImplement(AuthorityRepository repository) {
        super(repository);
    }
    //endregion

}
