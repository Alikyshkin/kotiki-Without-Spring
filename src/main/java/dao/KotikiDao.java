package dao;

import model.Kotiki;

import java.util.List;

public interface KotikiDao {
    Kotiki findById(int id);

    void save(Kotiki kotiki);

    void update(Kotiki kotiki);

    void delete(Kotiki kotiki);

    Kotiki findOwnerById(int id);

    List<Kotiki> findAll();
}
