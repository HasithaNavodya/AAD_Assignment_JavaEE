package lk.ijse.gdse66.bo.custom.impl;

import lk.ijse.gdse66.bo.custom.CustomerBO;
import lk.ijse.gdse66.dao.DAOFactory;
import lk.ijse.gdse66.dao.custom.CustomerDAO;
import lk.ijse.gdse66.dto.CustomerDTO;
import lk.ijse.gdse66.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER_DAO);

    @Override
    public boolean saveCustomer(Connection connection, CustomerDTO dto) throws SQLException {
        return customerDAO.save(connection, new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getSalary()));
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers(Connection connection) throws SQLException {
        ArrayList<Customer> customersList = customerDAO.getAll(connection);

        ArrayList<CustomerDTO> customerDTOList = new ArrayList<CustomerDTO>();

        for(Customer customer : customersList){
            CustomerDTO dto = new CustomerDTO(
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getSalary()
            );

            customerDTOList.add(dto);
        }
        return customerDTOList;
    }

    @Override
    public boolean updateCustomer(Connection connection, CustomerDTO dto) throws SQLException {
        return customerDAO.update(connection, new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getSalary()));
    }

    @Override
    public boolean removeCustomer(Connection connection, String id) throws SQLException {
        return customerDAO.delete(connection, id);
    }

    @Override
    public CustomerDTO getCustomerById(Connection connection, String id) throws SQLException {
        Customer customer = customerDAO.findBy(connection, id);

        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getSalary()
        );
    }
}
