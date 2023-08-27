package Student.com;

// Import Statement.
import net.proteanit.sql.DbUtils;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Container;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class Show_Student extends JFrame implements ActionListener {
    // Variable Declaration.
    Container c; JTable table; JScrollPane scrollPane; JButton login;
    ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("image\\student.png")));

    Show_Student(){
        // Frame Adding Statement.
        setBounds(300,100,800,510);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(logo.getImage());
        setTitle("Show_Student_Data");
        setResizable(false);
        revalidate();

        // Container Adding Statement.
        c = this.getContentPane();
        c.setBackground(Color.white);
        c.setLayout(null);

        // Show Student Data In Tabular Form.
        table = new JTable();
        table.setBounds(0,0,800,400);
        table.setFont(new Font("Arial",Font.BOLD,15));
        table.setBackground(Color.white);

        // Call User Define Function To Store Data.
        showData();
        scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0,0,800,400);
        scrollPane.setBackground(Color.white);
        c.add(scrollPane);

        // Add Home Or Back Button
        login  = new JButton("Back");
        login.setBounds(580,410,150,45);
        login.setBackground(Color.YELLOW);
        login.setFont(new Font("Arial",Font.BOLD,20));
        login.addActionListener(this);
        c.add(login);

        setVisible(true);
    }

    // ActionListener Methode Override To Back Button.
    public void actionPerformed(ActionEvent e){
        // Add Action To Redirect In Home Page.
        if(e.getSource()==login){
            this.setVisible(false);
            new Home_Admin();
        }
    }

    // Create Methode To Display Student Data.
    public void showData(){
        try{
            Connection con = JDBC.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from student");
            ResultSet rs = ps.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
            e.getStackTrace();
        }
    }
}
