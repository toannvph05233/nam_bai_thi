import connect_CSDL.SQLServerConnection;
import model.Quyen;
import service.QuyenService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class Main extends JFrame {
    private JTextField txtMaQuyen, txtTenQuyen;
    private JComboBox<String> cboNhomQuyen;
    private JButton btnThem, btnSua, btnXoa;
    private JTable table;
    private DefaultTableModel tableModel;

    private Connection connection;
    private QuyenService quyenService;

    public Main() {
        setTitle("Quản lý quyền");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel chính
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thông tin quyền"));
        inputPanel.add(new JLabel("Mã quyền:"));
        txtMaQuyen = new JTextField();
        inputPanel.add(txtMaQuyen);
        inputPanel.add(new JLabel("Tên quyền:"));
        txtTenQuyen = new JTextField();
        inputPanel.add(txtTenQuyen);

        // Tạo JComboBox cho Nhóm quyền và thêm các giá trị mặc định
        inputPanel.add(new JLabel("Nhóm quyền:"));
        String[] nhomQuyenValues = {"Quản trị mạng", "Bán hàng", "Kho vận", "Kế Toán"};
        cboNhomQuyen = new JComboBox<>(nhomQuyenValues);
        inputPanel.add(cboNhomQuyen);

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
                String maQuyen = txtMaQuyen.getText();
                String tenQuyen = txtTenQuyen.getText();
                String maNhomQuyen = cboNhomQuyen.getSelectedItem().toString();

                if (!maQuyen.isEmpty() && !tenQuyen.isEmpty() && !maNhomQuyen.isEmpty()) {
                    // Thực hiện thêm quyền vào CSDL
//                    boolean isSuccess = quyenService.insertQuyen(maQuyen, tenQuyen, maNhomQuyen);
                    boolean isSuccess = true;

                    if (isSuccess) {
                        // Thêm dữ liệu vào bảng hiển thị quyền
                        tableModel.addRow(new Object[]{maQuyen, tenQuyen, maNhomQuyen});

                        // Xóa dữ liệu trong các ô nhập liệu
                        txtMaQuyen.setText("");
                        txtTenQuyen.setText("");
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
                    String maQuyen = txtMaQuyen.getText();
                    String tenQuyen = txtTenQuyen.getText();
                    String nhomQuyen = cboNhomQuyen.getSelectedItem().toString();

                    if (!maQuyen.isEmpty() && !tenQuyen.isEmpty() && !nhomQuyen.isEmpty()) {
                        // Thực hiện sửa trong CSDL
//                        boolean isSuccess = quyenService.updateQuyen(maQuyen, tenQuyen, nhomQuyen);
                        boolean isSuccess = true;

                        if (isSuccess) {
                            // Cập nhật dữ liệu trong bảng hiển thị
                            tableModel.setValueAt(maQuyen, selectedRow, 0);
                            tableModel.setValueAt(tenQuyen, selectedRow, 1);
                            tableModel.setValueAt(nhomQuyen, selectedRow, 2);

                            // Xóa dữ liệu trong các ô nhập liệu
                            txtMaQuyen.setText("");
                            txtTenQuyen.setText("");
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
                    String maQuyen = tableModel.getValueAt(selectedRow, 0).toString();

                    // Thực hiện xóa trong CSDL
//                    boolean isSuccess = quyenService.deleteQuyen(maQuyen);
                    boolean isSuccess = true;

                    if (isSuccess) {
                        // Xóa dữ liệu khỏi bảng hiển thị
                        tableModel.removeRow(selectedRow);
                        txtMaQuyen.setText("");
                        txtTenQuyen.setText("");
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
                    String maQuyen = tableModel.getValueAt(selectedRow, 0).toString();
                    String tenQuyen = tableModel.getValueAt(selectedRow, 1).toString();
                    String nhomQuyen = tableModel.getValueAt(selectedRow, 2).toString();

                    txtMaQuyen.setText(maQuyen);
                    txtTenQuyen.setText(tenQuyen);
                }
            }
        });


        add(mainPanel);
    }

    public void showTable(){
        List<Quyen> danhSachQuyen = quyenService.getDanhSachQuyen();
        for (Quyen quyen : danhSachQuyen) {
            tableModel.addRow(new Object[]{quyen.getMaQuyen(), quyen.getTenQuyen(), quyen.getMaNhomQuyen()});
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
