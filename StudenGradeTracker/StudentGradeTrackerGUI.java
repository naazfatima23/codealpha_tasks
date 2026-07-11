import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StudentGradeTrackerGUI extends JFrame implements ActionListener {

    JTable table;
    DefaultTableModel model;
    JScrollPane scrollPane;

    JLabel lblTitle, lblName, lblMarks;
    JLabel lblAverage, lblHighest, lblLowest;

    JTextField txtName, txtMarks;

    JButton btnAdd, btnCalculate, btnClear;

    JTextArea textArea;

    ArrayList<Student> students = new ArrayList<>();

    public StudentGradeTrackerGUI() {

        setTitle("Student Grade Tracker");
        setSize(800,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new java.awt.Color(220, 235, 255));        


        lblTitle = new JLabel("STUDENT GRADE TRACKER");
        lblTitle.setBounds(120, 20, 350, 30);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(new Color(0, 51, 153));
        add(lblTitle);

        lblName = new JLabel("Student Name:");
        lblName.setBounds(30, 70, 120, 25);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(160, 70, 200, 25);
        txtName.setBackground(new Color(255,255,240));
        add(txtName);

        lblMarks = new JLabel("Marks:");
        lblMarks.setBounds(30, 110, 120, 25);
        add(lblMarks);

        txtMarks = new JTextField();
        txtMarks.setBounds(160, 110, 200, 25);
        txtMarks.setBackground(new Color(255,255,240));
        add(txtMarks);

        btnAdd = new JButton("Add Student");
        btnAdd.setBounds(30, 160, 140, 30);
        btnAdd.addActionListener(this);
        btnAdd.setBackground(new Color(0,153,76));
        btnAdd.setForeground(Color.WHITE);
        add(btnAdd);

        btnCalculate = new JButton("Calculate");
        btnCalculate.setBounds(190, 160, 120, 30);
        btnCalculate.addActionListener(this);
        btnCalculate.setBackground(new Color(0,102,204));
        btnCalculate.setForeground(Color.WHITE);
        add(btnCalculate);

        btnClear = new JButton("Clear");
        btnClear.setBounds(330, 160, 100, 30);
        btnClear.addActionListener(this);
        btnClear.setBackground(new Color(220,53,69));
        btnClear.setForeground(Color.WHITE);
        add(btnClear);

       String[] columns = {"Student Name", "Marks", "Grade", "Result"};
       model = new DefaultTableModel(columns, 0);
       table = new JTable(model);
       scrollPane = new JScrollPane(table);
       scrollPane.setBounds(30,210,430,180);
       add(scrollPane);
       table.getTableHeader().setBackground(new Color(70,130,180));
       table.getTableHeader().setForeground(Color.WHITE);
       table.getTableHeader().setFont(new Font("Arial", Font.BOLD,14));
       table.setRowHeight(25);
       table.setFont(new Font("Arial",Font.PLAIN, 14));

        lblAverage = new JLabel("Average Marks : ");
        lblAverage.setBounds(30, 420, 250, 25);
        add(lblAverage);

        lblHighest = new JLabel("Highest Marks : ");
        lblHighest.setBounds(30, 450, 250, 25);
        add(lblHighest);

        lblLowest = new JLabel("Lowest Marks : ");
        lblLowest.setBounds(30, 480, 250, 25);
        add(lblLowest);

        setVisible(true);
    }
 @Override
    public void actionPerformed(ActionEvent e) {

        // Add Student
        if (e.getSource() == btnAdd) {

            try {
                String name = txtName.getText();
                int marks = Integer.parseInt(txtMarks.getText());

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Please enter student name.");
                    return;
                }

                if (marks < 0 || marks > 100) {
                    JOptionPane.showMessageDialog(this,
                            "Marks should be between 0 and 100.");
                    return;
                }

                Student s = new Student(name, marks);
                students.add(s);

                model.addRow(new Object[]{
                 name,
                 marks,
                 s.getGrade(),
                 s.getResult()});

                txtName.setText("");
                txtMarks.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Please enter valid marks.");
            }
        }

        // Calculate Result
        else if (e.getSource() == btnCalculate) {

            if (students.size() == 0) {
                JOptionPane.showMessageDialog(this,
                        "No student data found.");
                return;
            }

            int total = 0;
            int highest = students.get(0).getMarks();
            int lowest = students.get(0).getMarks();

            for (Student s : students) {

                total += s.getMarks();

                if (s.getMarks() > highest)
                    highest = s.getMarks();

                if (s.getMarks() < lowest)
                    lowest = s.getMarks();
            }

            double average = (double) total / students.size();

            lblAverage.setText("Average Marks : "
                    + String.format("%.2f", average));

            lblHighest.setText("Highest Marks : " + highest);

            lblLowest.setText("Lowest Marks : " + lowest);
        }

        // Clear Button
        else if (e.getSource() == btnClear) {

            students.clear();

            model.setRowCount(0);

            txtName.setText("");
            txtMarks.setText("");

            lblAverage.setText("Average Marks : ");
            lblHighest.setText("Highest Marks : ");
            lblLowest.setText("Lowest Marks : ");
        }
    }
public static void main(String[] args) {

        new StudentGradeTrackerGUI();

    }
}