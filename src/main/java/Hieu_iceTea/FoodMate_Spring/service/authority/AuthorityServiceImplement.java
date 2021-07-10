package Hieu_iceTea.FoodMate_Spring.service.authority;


import Hieu_iceTea.FoodMate_Spring.model.Authority;
import Hieu_iceTea.FoodMate_Spring.model.User;
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

    @Override
    public void updateUserAuthority(User user, String[] arrAuthorities) {
        // 01. Xóa hết dữ liệu cũ:
        authorityRepository.deleteAll(user.getAuthorities());

        if (arrAuthorities == null) { // nếu NULL thì bỏ qua 'Thêm dữ liệu mới' => tương dương xóa hết
            return;
        }

        // 02. Thêm dữ liệu mới:
        for (String strAuthority : arrAuthorities) {
            Authority authority = new Authority();

            authority.setAuthority(strAuthority);
            authority.setUser(user);

            authorityRepository.save(authority);
        }
    }
    //endregion

}
