package dao;

import java.util.List;
import java.util.Optional;

    public interface DAO<T> {

        List<T> findAll() throws Exception;
        Optional<T> findById(int id) throws Exception;
        int save(T item) throws Exception;
        void removeById(int id) throws Exception;
    }
