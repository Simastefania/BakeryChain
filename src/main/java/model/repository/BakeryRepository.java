package model.repository;

import model.domain.Bakery;

import java.util.List;

public interface BakeryRepository {

    void add(Bakery bakery);

    void update(Bakery bakery);

    void delete(int id);

    List<Bakery> findAll();

    Bakery findById(int id);
}
