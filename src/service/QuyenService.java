package service;

import model.Quyen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuyenService {
    private Connection connection;

    public QuyenService(Connection connection) {
        this.connection = connection;
    }

    public List<Quyen> getDanhSachQuyen() {
        List<Quyen> danhSachQuyen = new ArrayList<>();

        try {
            String sql = "SELECT MaQuyen, TenQuyen, NhomQuyen FROM Quyen";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String maQuyen = resultSet.getString("MaQuyen");
                String tenQuyen = resultSet.getString("TenQuyen");
                String maNhomQuyen = resultSet.getString("NhomQuyen");

                Quyen quyen = new Quyen();
                quyen.setMaQuyen(maQuyen);
                quyen.setTenQuyen(tenQuyen);
                quyen.setMaNhomQuyen(maNhomQuyen);

                danhSachQuyen.add(quyen);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách quyền từ CSDL.");
            e.printStackTrace();
        }

        return danhSachQuyen;
    }

    // Hàm thêm quyền vào CSDL
    public boolean insertQuyen(String maQuyen, String tenQuyen, String maNhomQuyen) {
        try {
            String sql = "INSERT INTO Quyen (MaQuyen, TenQuyen, NhomQuyen) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maQuyen);
            preparedStatement.setString(2, tenQuyen);
            preparedStatement.setString(3, maNhomQuyen);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm quyền vào CSDL.");
            e.printStackTrace();
            return false;
        }
    }


    // Hàm sửa quyền trong CSDL
    public boolean updateQuyen(String maQuyen, String tenQuyen, String nhomQuyen) {
        try {
            String sql = "UPDATE Quyen SET TenQuyen = ?, NhomQuyen = ? WHERE MaQuyen = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tenQuyen);
            preparedStatement.setString(2, nhomQuyen);
            preparedStatement.setString(3, maQuyen);
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
    public boolean deleteQuyen(String maQuyen) {
        try {
            String sql = "DELETE FROM Quyen WHERE MaQuyen = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maQuyen);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa quyền khỏi CSDL.");
            e.printStackTrace();
            return false;
        }
    }
}

