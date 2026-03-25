package model.repository;

import model.entity.Cake;

import java.util.List;

public interface CakeRepository {
    int add(Cake cake);
    void update(Cake cake);
    void delete(Cake cake);
    List<Cake> findAll();
    List<Cake> findAllSortedByName();
    Cake findByName(String name);
}
