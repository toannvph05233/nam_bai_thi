import model.Product;
import service.ProductService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;


public class Main extends JFrame {
    private JTextField txtId, txtName, txtPrice;
    private JButton btnThem, btnSua, btnXoa;
    private JTable table;
    private DefaultTableModel tableModel;

    private Connection connection;
    private ProductService productService;

    public Main() {
        setTitle("Quản lý quyền");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel chính
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thông tin quyền"));
        inputPanel.add(new JLabel("Mã:"));
        txtId = new JTextField();
        inputPanel.add(txtId);
        inputPanel.add(new JLabel("Tên:"));
        txtName = new JTextField();
        inputPanel.add(txtName);
        txtPrice = new JTextField();

        // Tạo JComboBox cho Nhóm quyền và thêm các giá trị mặc định
        inputPanel.add(new JLabel("Giá:"));
        inputPanel.add(txtPrice);


        // Panel nút
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);

        // Panel bảng
        JPanel tablePanel = new JPanel(new BorderLayout());
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã quyền");
        tableModel.addColumn("Tên quyền");
        tableModel.addColumn("Nhóm quyền");
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Đặt panel nhập liệu và panel nút vào một panel con
        JPanel inputButtonPanel = new JPanel(new BorderLayout());
        inputButtonPanel.add(inputPanel, BorderLayout.CENTER);
        inputButtonPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Đặt tất cả các panel vào panel chính
        mainPanel.add(inputButtonPanel, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);


//        try {
//            // Kết nối đến CSDL SQL Server
//            connection = SQLServerConnection.getConnection();
//            quyenService = new QuyenService(connection);
////            showTable();
//        } catch (SQLException e) {
//            System.out.println("Lỗi khi kết nối đến CSDL.");
//            e.printStackTrace();
//        }


        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtId.getText();
                String name = txtName.getText();
                String price = txtPrice.getText();

                if (!id.isEmpty() && !name.isEmpty() && !price.isEmpty()) {
                    // Thực hiện thêm quyền vào CSDL
//                    boolean isSuccess = quyenService.insertQuyen(maQuyen, tenQuyen, maNhomQuyen);
                    boolean isSuccess = true;

                    if (isSuccess) {
                        // Thêm dữ liệu vào bảng hiển thị quyền
                        tableModel.addRow(new Object[]{id, name, price});

                        // Xóa dữ liệu trong các ô nhập liệu
                        txtId.setText("");
                        txtName.setText("");
                        txtPrice.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Lỗi khi thêm quyền vào CSDL.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin.");
                }
            }
        });

        // Thêm sự kiện cho nút Sửa
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String id = txtId.getText();
                    String name = txtName.getText();
                    String price = txtPrice.getText();

                    if (!id.isEmpty() && !name.isEmpty() && !price.isEmpty()) {
                        // Thực hiện sửa trong CSDL
//                        boolean isSuccess = quyenService.updateQuyen(maQuyen, tenQuyen, nhomQuyen);
                        boolean isSuccess = true;

                        if (isSuccess) {
                            // Cập nhật dữ liệu trong bảng hiển thị
                            tableModel.setValueAt(id, selectedRow, 0);
                            tableModel.setValueAt(name, selectedRow, 1);
                            tableModel.setValueAt(price, selectedRow, 2);

                            // Xóa dữ liệu trong các ô nhập liệu
                            txtId.setText("");
                            txtName.setText("");
                            txtPrice.setText("");
                        } else {
                            JOptionPane.showMessageDialog(null, "Lỗi khi sửa quyền trong CSDL.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một hàng để sửa.");
                }
            }
        });

        // Thêm sự kiện cho nút Xóa
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String id = tableModel.getValueAt(selectedRow, 0).toString();

                    // Thực hiện xóa trong CSDL
//                    boolean isSuccess = quyenService.deleteQuyen(maQuyen);
                    boolean isSuccess = true;

                    if (isSuccess) {
                        // Xóa dữ liệu khỏi bảng hiển thị
                        tableModel.removeRow(selectedRow);
                        txtId.setText("");
                        txtName.setText("");
                        txtPrice.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Lỗi khi xóa quyền khỏi CSDL.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một hàng để xóa.");
                }
            }
        });
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Lấy dữ liệu từ bảng và hiển thị lên các ô nhập liệu
                    String id = tableModel.getValueAt(selectedRow, 0).toString();
                    String name = tableModel.getValueAt(selectedRow, 1).toString();
                    String price = tableModel.getValueAt(selectedRow, 2).toString();

                    txtId.setText(id);
                    txtName.setText(name);
                    txtPrice.setText(price);
                }
            }
        });


        add(mainPanel);
    }

    public void showTable(){
        List<Product> danhSachProduct = productService.getDanhSachProduct();
        for (Product product : danhSachProduct) {
            tableModel.addRow(new Object[]{product.getId(), product.getName(), product.getPrice()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
