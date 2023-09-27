package service;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private Connection connection;

    public ProductService(Connection connection) {
        this.connection = connection;
    }

    public List<Product> getDanhSachProduct() {
        List<Product> danhSachProduct = new ArrayList<>();

        try {
            String sql = "SELECT id, name, price FROM Product";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String price = resultSet.getString("price");

                Product product = new Product();
                product.setId(id);
                product.setName(name);
                product.setPrice(price);

                danhSachProduct.add(product);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách quyền từ CSDL.");
            e.printStackTrace();
        }

        return danhSachProduct;
    }

    // Hàm thêm quyền vào CSDL
    public boolean insertProduct(String id, String name, String price) {
        try {
            String sql = "INSERT INTO Product (id, name, price) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, price);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm vào CSDL.");
            e.printStackTrace();
            return false;
        }
    }


    // Hàm sửa quyền trong CSDL
    public boolean updateProduct(String id, String name, String price) {
        try {
            String sql = "UPDATE Product SET name = ?, name = ? WHERE price = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, price);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi sửa quyền trong CSDL.");
            e.printStackTrace();
            return false;
        }
    }

    // Hàm xóa quyền khỏi CSDL
    public boolean deleteProduct(String id) {
        try {
            String sql = "DELETE FROM Product WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa khỏi CSDL.");
            e.printStackTrace();
            return false;
        }
    }
}

