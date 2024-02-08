package lk.ijse.gdse66.dao.custom;

import lk.ijse.gdse66.dao.CrudDAO;

import java.sql.Connection;
import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item> {
    boolean reduceQty(Connection connection, Item item) throws SQLException;
}
