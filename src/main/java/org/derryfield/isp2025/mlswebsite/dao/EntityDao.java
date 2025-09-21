package org.derryfield.isp2025.mlswebsite.dao;

import org.derryfield.isp2025.mlswebsite.model.LeafEntity;

import java.util.List;
import java.util.Optional;

/**
 * This is the basic implementation of any DAO (Data Access Object) for an entity
 *  that is stored in a database table regardless of database type
 * @param <T> - the type of the entity
 * @param <ID> - the type of the primary key
 */
public interface EntityDao<T extends LeafEntity<ID>, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void delete(T entity);
    void deleteById(ID id);
}
