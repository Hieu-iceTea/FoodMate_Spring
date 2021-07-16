package Hieu_iceTea.FoodMate_Spring.repository;


import Hieu_iceTea.FoodMate_Spring.model.User;


public interface UserRepository extends BaseRepository<User, Integer> {

    User findByUsername(String username);

}