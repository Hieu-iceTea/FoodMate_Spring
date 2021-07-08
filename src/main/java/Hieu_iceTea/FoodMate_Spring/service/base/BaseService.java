package Hieu_iceTea.FoodMate_Spring.service.base;


import Hieu_iceTea.FoodMate_Spring.model.BaseModel;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T extends BaseModel, ID extends Serializable> {

    List<T> findAll();

    List<T> findAllByOrderByIdDesc();

    T findById(ID id);

    void save(T item);

    void deleteById(ID id);

}