package dao;

import dao.map.RowMapper;

import java.util.List;
import java.util.Optional;

public abstract class AbstractDAOimpl<T> extends AbstractQueryExecutor<T> implements DAO<T> {

    private final String tableName;

    public AbstractDAOimpl(RowMapper<T> rowMapper, String tableName) {
        super(rowMapper);
        this.tableName = tableName;
    }

    @Override
    public List<T> findAll() throws Exception {
        String query = "SELECT * FROM " + tableName;
        return executeQuery(query);
    }

    @Override
    public Optional<T> findById(int id) throws Exception {
        String query = "SELECT * FROM " + tableName + " WHERE id=?";
        return executeQueryForSingleResult(query, id);
    }

    @Override
    public void removeById(int id) throws Exception {
        String deleteQuery = "DELETE FROM " + tableName + " WHERE id=?";
        executeUpdateQuery(deleteQuery, id);
    }
}
