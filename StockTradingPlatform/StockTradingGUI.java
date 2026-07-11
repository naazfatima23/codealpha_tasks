import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StockTradingGUI extends JFrame implements ActionListener {

    JLabel lblTitle, lblStock, lblPrice, lblQuantity, lblTotal;

    JTextField txtStock, txtPrice, txtQuantity, txtTotal;

    JButton btnBuy, btnSell, btnClear, btnUpdate, btnDelete;

    JTable table;
    DefaultTableModel model;

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public StockTradingGUI() {

        setTitle("Stock Trading Platform");
        setSize(1000,800);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(new Color(220,235,255));

        lblTitle = new JLabel("STOCK TRADING PLATFORM");
        lblTitle.setBounds(220,20,400,30);
        lblTitle.setFont(new Font("Arial",Font.BOLD,22));
        lblTitle.setForeground(new Color(0,51,153));
        add(lblTitle);

        lblStock = new JLabel("Stock Name");
        lblStock.setBounds(40,80,100,25);
        add(lblStock);

        txtStock = new JTextField();
        txtStock.setBounds(150,80,180,30);
        add(txtStock);

        lblPrice = new JLabel("Price");
        lblPrice.setBounds(40,130,100,25);
        add(lblPrice);

        txtPrice = new JTextField();
        txtPrice.setBounds(150,130,180,30);
        add(txtPrice);

        lblQuantity = new JLabel("Quantity");
        lblQuantity.setBounds(40,180,100,25);
        add(lblQuantity);

        txtQuantity = new JTextField();
        txtQuantity.setBounds(150,180,180,30);
        add(txtQuantity);

        lblTotal = new JLabel("Total");
        lblTotal.setBounds(40,230,100,25);
        add(lblTotal);

        txtTotal = new JTextField();
        txtTotal.setBounds(150,230,180,30);
        txtTotal.setEditable(false);
        add(txtTotal);

        btnBuy = new JButton("Buy");
        btnBuy.setBounds(420,150,120,35);
        btnBuy.setBackground(new Color(40,167,69));
        btnBuy.setForeground(Color.WHITE);
        btnBuy.addActionListener(this);
        add(btnBuy);

        btnSell = new JButton("Sell");
        btnSell.setBounds(570,150,120,35);
        btnSell.setBackground(new Color(220,53,69));
        btnSell.setForeground(Color.WHITE);
        btnSell.addActionListener(this);
        add(btnSell);

        btnClear = new JButton("Clear");
        btnClear.setBounds(710,150,120,35);
        btnClear.setBackground(new Color(0,123,255));
        btnClear.setForeground(Color.WHITE);
        btnClear.addActionListener(this);
        add(btnClear);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(860,150,120,35);
        btnUpdate.setBackground(new Color(255,193,7));
        btnUpdate.setForeground(Color.BLACK);
        btnUpdate.addActionListener(this);
        add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(1000,150,120,35);
        btnDelete.setBackground(new Color(108,117,125));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.addActionListener(this);
        add(btnDelete);



        String columns[] = {"ID","Stock","Price","Quantity","Total"};

        model = new DefaultTableModel(columns,0);

        table = new JTable(model);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(40,300,700,220);
        add(sp);
         
        con = DBConnection.getConnection();
        loadTable();

        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(70,130,180));
        table.getTableHeader().setForeground(Color.WHITE);

        table.addMouseListener(new MouseAdapter() {

       public void mouseClicked(MouseEvent e) {
 
        int row = table.getSelectedRow();

        txtStock.setText(model.getValueAt(row,1).toString());
        txtPrice.setText(model.getValueAt(row,2).toString());
        txtQuantity.setText(model.getValueAt(row,3).toString());
        txtTotal.setText(model.getValueAt(row,4).toString());

       }

       });

        setVisible(true);
    }
  @Override
public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnBuy) {

        try {

            String stock = txtStock.getText();

            double price = Double.parseDouble(txtPrice.getText());

            int quantity = Integer.parseInt(txtQuantity.getText());

            double total = price * quantity;

            txtTotal.setText(String.valueOf(total));

            pst = con.prepareStatement(
                "INSERT INTO stocks(stock_name,price,quantity,total) VALUES(?,?,?,?)");

            pst.setString(1, stock);
            pst.setDouble(2, price);
            pst.setInt(3, quantity);
            pst.setDouble(4, total);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Stock Purchased Successfully!");

            loadTable();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this,
                    ex.getMessage());

        }

    }

    else if (e.getSource() == btnClear) {

        txtStock.setText("");
        txtPrice.setText("");
        txtQuantity.setText("");
        txtTotal.setText("");

    }

    else if (e.getSource() == btnSell) {

        JOptionPane.showMessageDialog(this,
                "Stock sold Successfully! ");

    }
   
     else if(e.getSource()==btnUpdate){

    try{

        int row = table.getSelectedRow();

        int id = Integer.parseInt(model.getValueAt(row,0).toString());

        double total = Double.parseDouble(txtPrice.getText()) *
                       Integer.parseInt(txtQuantity.getText());

        pst = con.prepareStatement(
        "UPDATE stocks SET stock_name=?,price=?,quantity=?,total=? WHERE id=?");

        pst.setString(1,txtStock.getText());
        pst.setDouble(2,Double.parseDouble(txtPrice.getText()));
        pst.setInt(3,Integer.parseInt(txtQuantity.getText()));
        pst.setDouble(4,total);
        pst.setInt(5,id);

        pst.executeUpdate();

        loadTable();

        JOptionPane.showMessageDialog(this,"Record Updated");

    }catch(Exception ex){

        JOptionPane.showMessageDialog(this,ex.getMessage());

    }

   }     


   else if(e.getSource()==btnDelete){

    try{

        int row = table.getSelectedRow();

        int id = Integer.parseInt(model.getValueAt(row,0).toString());

        pst = con.prepareStatement(
                "DELETE FROM stocks WHERE id=?");

        pst.setInt(1,id);

        pst.executeUpdate();

        loadTable();

        JOptionPane.showMessageDialog(this,
                "Record Deleted");

    }catch(Exception ex){

        JOptionPane.showMessageDialog(this,
                ex.getMessage());

    }

}

}
 
 public void loadTable() {

    try {

        model.setRowCount(0);

        pst = con.prepareStatement("SELECT * FROM stocks");

        rs = pst.executeQuery();

        while (rs.next()) {

            model.addRow(new Object[]{

                    rs.getInt("id"),
                    rs.getString("stock_name"),
                    rs.getDouble("price"),
                    rs.getInt("quantity"),
                    rs.getDouble("total")

            });

        }

    } catch (Exception e) {

        e.printStackTrace();

    }

}

public static void main(String[] args) {

    new StockTradingGUI();

}
}