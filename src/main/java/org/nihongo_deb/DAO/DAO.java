package org.nihongo_deb.DAO;

import java.util.List;
import java.util.Optional;

/**
 * @author KAWAIISHY
 * @project FirstWebAppMVC
 * @created 24.07.2023
 */
public interface DAO<E, I>{
    List<E> getAll();
    Optional<E> getById(I id);
    void addOne(E entity);
    void update(I id, E entity);
    void delete(I id);
}
