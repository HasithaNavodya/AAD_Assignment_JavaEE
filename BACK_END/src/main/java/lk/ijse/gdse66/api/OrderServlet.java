package lk.ijse.gdse66.api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.gdse66.bo.BOFactory;
import lk.ijse.gdse66.bo.custom.OrderBO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "orders", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    OrderBO orderBO = BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ORDER_BO);

    DataSource connectionPool;

    @Override
    public void init() throws ServletException {
        try {
            InitialContext ic = new InitialContext();
            connectionPool = (DataSource) ic.lookup("java:/comp/env/jdbc/PosAssignmentEe");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String function = req.getParameter("function");

        if (function.equals("getLastId")){
            try (Connection connection = connectionPool.getConnection()){
                String lastId = orderBO.getLastId(connection);
                resp.getWriter().write(lastId);
            } catch (SQLException e) {
                e.printStackTrace();
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }

//            String id = req.getParameter("id");
//            try (Connection connection = connectionPool.getConnection()){
//                OrderDTO orderDTO = orderBO.getOrderById(connection, id);
//
//                Jsonb jsonb = JsonbBuilder.create();
//                String json = jsonb.toJson(orderDTO);
//                resp.getWriter().write(json);
//            } catch (JsonbException e) {
//                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
//            } catch (IOException e) {
//                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
//            } catch (SQLException e) {
//                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
//            }
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("order");
        try (Connection connection = connectionPool.getConnection()){

            Jsonb jsonb = JsonbBuilder.create();

            OrderDTO orderDTO = jsonb.fromJson(req.getReader(), OrderDTO.class);
            System.out.println(orderDTO);

            boolean isTransactionDone = orderBO.placeOrder(connection, orderDTO);
            if(isTransactionDone){
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }else{
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "failed transaction");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "transaction failed");
        }
    }


}
