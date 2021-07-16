package Hieu_iceTea.FoodMate_Spring.service.base;


import Hieu_iceTea.FoodMate_Spring.model.BaseModel;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T extends BaseModel, ID extends Serializable> {

    List<T> findAll();

    List<T> findAllByOrderByIdDesc();

    T findById(ID id);

    T save(T item);

    List<T> saveAll(List<T> items);

    void deleteById(ID id);

}
