package org.derryfield.isp2025.mlswebsite.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NonNull;
import org.derryfield.isp2025.mlswebsite.model.LeafEntity;

import java.util.List;
import java.util.Optional;

/**
 * This is the base dao type that handles implemention the EntityDao
 *  hibernate managed databases (e.g. mysql).
 * @param <T> - The type of the object we are returning
 * @param <ID> - The type of the primary key
 */
public abstract class HibernateDao<T extends LeafEntity<ID>, ID> implements EntityDao<T, ID>{

    @Getter
    protected EntityManager entityManager;

    @Getter
    private final Class<T> entityClass;

    protected HibernateDao(EntityManager entityManager, @NonNull final Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    public CriteriaBuilder getCriteriaBuilder() {
        if(this.entityManager == null) {
            throw new RuntimeException("Entity manager is null when creating CriteriaBuilder");
        }
        return entityManager.getCriteriaBuilder();
    }

    @Override
    public T save(@NonNull final T entity) {
        // if we have not set the id then it's a new entity, and we need to insert
        if(entity.getId() == null) {
            entityManager.persist(entity);
            return entity;
        }
        // otherwise we need to update
        return entityManager.merge(entity);
    }

    @Override
    public Optional<T> findById(@NonNull final ID id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @Override
    public List<T> findAll() {
        CriteriaQuery<T> query = createCriteriaQuery();
        Root<T> root = query.from(entityClass);
        query.select(root);
        return executeQuery(query);
    }

    @Override
    public void delete(@NonNull final T entity) {
        entityManager.remove(getOrAttachEntity(entity));
    }

    @Override
    public void deleteById(@NonNull final ID id) {
        findById(id).ifPresent(this::delete);
    }

    protected final CriteriaQuery<T> createCriteriaQuery() {
        return getCriteriaBuilder().createQuery(entityClass);
    }

    protected final List<T> executeQuery(@NonNull final CriteriaQuery<T> query) {
        return entityManager.createQuery(query).getResultList();
    }

    /**
     * Entities aren't always already tracked by the entityManager. This
     *  will ensure that any entity we operate on is attached
     * @param entity - the entity that may or may not be attached
     * @return a tracked entity
     */
    protected final T getOrAttachEntity(@NonNull final T entity) {
        if (entityManager.contains(entity)) {
            return entity;
        }
        return entityManager.merge(entity);
    }
}
