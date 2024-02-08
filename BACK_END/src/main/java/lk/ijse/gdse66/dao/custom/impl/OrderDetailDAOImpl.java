package lk.ijse.gdse66.dao.custom.impl;

//import lk.ijse.gdse66.dao.custom.OrderDetailsDAO;
//import lk.ijse.gdse66.dao.util.CrudUtil;
//import lk.ijse.gdse66.entity.OrderDetail;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;

import lk.ijse.gdse66.dao.custom.OrderDetailsDAO;
import lk.ijse.gdse66.dao.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailsDAO {
    @Override
    public boolean save(Connection connection, OrderDetail entity) throws SQLException {
        String sql = "INSERT INTO order_details (order_id, item_code, unit_price, qty) VALUES(?,?,?,?)";
        return CrudUtil.execute(connection, sql, entity.getOrder_id(), entity.getItem_code(), entity.getUnit_price(), entity.getQty());
    }

    @Override
    public boolean update(Connection connection, OrderDetail entity) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<OrderDetail> getAll(Connection connection) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Connection connection, String id) throws SQLException {
        return false;
    }

    @Override
    public OrderDetail findBy(Connection connection, String id) throws SQLException {
        return null;
    }

    @Override
    public List<OrderDetail> getAllById(Connection connection, String id) throws SQLException {
        String sql = "SELECT * FROM order_details WHERE order_id = ?";
        ResultSet rs = CrudUtil.execute(connection, sql, id);

        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        while( rs.next() ) {
            OrderDetail orderDetail = new OrderDetail(
                    rs.getString(1),
                    rs.getBigDecimal(3),
                    rs.getInt(4)
            );
            orderDetailList.add(orderDetail);
        }
        return orderDetailList;
    }
}
