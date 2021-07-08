package com.example.FoodMate_Spring.service.base;

import com.example.FoodMate_Spring.model.BaseModel;
import com.example.FoodMate_Spring.repository.BaseRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public class BaseServiceImplement<T extends BaseModel, ID extends Serializable> implements BaseService<T, ID> {

    private final BaseRepository<T, ID> repository;

    public BaseServiceImplement(BaseRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public List<T> findAllByOrderByIdDesc() {
        return repository.findAllByOrderByIdDesc();
    }

    @Override
    public T findById(ID id) {
        Optional<T> itemOptional = repository.findById(id);

        T item;

        if (itemOptional.isPresent()) {
            item = itemOptional.get();
        } else {
            throw new RuntimeException("Did not find item id - " + id);
        }

        return item;
    }

    @Override
    public void save(T item) {
        repository.save(item);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

}
