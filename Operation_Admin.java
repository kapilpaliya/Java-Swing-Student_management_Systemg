package Student.com;

// Import Statements.
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import java.awt.Container;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.sql.Connection;

public class Operation_Admin extends JFrame implements ActionListener {


    // Variable Declaration.
    JLabel a_name, a_password, a_email;     JTextField t_name, t_password, t_email, t_search;    Container c;
    JButton cancel, login, signup, search;    Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
    ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("image\\student.png")));

    Operation_Admin() {
        // Add Frame Declaration.
        setBounds(300, 100, 800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Operation_Admin");
        setIconImage(logo.getImage());
        setResizable(false);
        this.revalidate();

        // Add Container In Frame.
        c = this.getContentPane();
        c.setBackground(Color.white);
        c.setLayout(null);

        // Add Search Text Field And Button
        t_search = new JTextField();
        t_search.setBounds(180,30,300,60);
        t_search.setBorder(BorderFactory.createTitledBorder("Email"));
        t_search.setBackground(Color.WHITE);
        t_search.setFont(font);
        c.add(t_search);
        search = new JButton("Search");
        search.setBounds(480,40,100,48);
        search.setFont(font);
        search.setBorder(null);
        search.setBackground(Color.darkGray);
        search.setForeground(Color.white);
        search.addActionListener(this);
        c.add(search);

        // Add User_Name Label And Input Field.
        a_name = new JLabel("UserName : ");
        a_name.setBounds(170, 175, 125, 50);
        a_name.setFont(font);
        c.add(a_name);
        t_name = new JTextField();
        t_name.setBounds(320, 180, 250, 40);
        t_name.setFont(font);
        c.add(t_name);

        // Add Email_Id Label And Input Field.
        a_email = new JLabel("Email : ");
        a_email.setBounds(170, 235, 125, 50);
        a_email.setFont(font);
        c.add(a_email);
        t_email = new JTextField();
        t_email.setBounds(320, 240, 250, 40);
        t_email.setFont(font);
        c.add(t_email);

        // Add Password Field Label And Input Field.
        a_password = new JLabel("Password : ");
        a_password.setBounds(170, 295, 125, 50);
        a_password.setFont(font);
        c.add(a_password);
        t_password = new JTextField();
        t_password.setBounds(320, 300, 250, 40);
        t_password.setFont(font);
        c.add(t_password);

        // Add Update Button.
        signup = new JButton("Update");
        signup.setBounds(170, 360, 100, 50);
        signup.setBackground(Color.green);
        signup.setFont(font);
        signup.addActionListener(this);
        c.add(signup);

        // Add Delete Button.
        cancel = new JButton("Delete");
        cancel.setBounds(320, 360, 100, 50);
        cancel.setBackground(Color.red);
        cancel.setFont(font);
        cancel.addActionListener(this);
        c.add(cancel);

        // Add Back Or Home Button.
        login = new JButton("Back");
        login.setBounds(470, 360, 100, 50);
        login.setBackground(Color.YELLOW);
        login.setFont(font);
        login.addActionListener(this);
        c.add(login);

        setVisible(true);
    }

    // Override The ActionListener Function.
    @Override
    public void actionPerformed(ActionEvent e) {
        // Back Or Home Button Declaration.
        if (e.getSource() == login) {
            this.setVisible(false);
            new Home_Admin();
        }

        // Delete Button Declaration.
        if(e.getSource()==cancel){
            String emailCheck = t_search.getText();
            try{
                Connection con = JDBC.getConnection();
                PreparedStatement ps = con.prepareStatement("delete from admin where email=?");
                ps.setString(1,emailCheck);
                int xy = ps.executeUpdate();
                if(xy>0) {
                    JOptionPane.showMessageDialog(this, "One Row Successfully Deleted");
                    t_email.setText(null);
                    t_search.setText(null);
                    t_password.setText(null);
                    t_name.setText(null);
                }
                else{
                    JOptionPane.showMessageDialog(this,"'Gmail Not Match' Error 404!","Alert",JOptionPane.WARNING_MESSAGE);
                }
            }
            catch(Exception z){
                JOptionPane.showMessageDialog(this,"Database Connection Loss!","Error",JOptionPane.ERROR_MESSAGE);
            }
        }

        // Search Button Declaration.
        if(e.getSource()==search){
            String emailCheck = t_search.getText();
            try{
                Connection con = JDBC.getConnection();
                PreparedStatement ps = con.prepareStatement("Select * from admin where email=?");
                ps.setString(1,emailCheck);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    t_name.setText(rs.getString("name"));
                    t_email.setText(rs.getString("email"));
                    t_password.setText(rs.getString("password"));
                }
            }
            catch(Exception z){
                JOptionPane.showMessageDialog(this,"Database Connection Loss!","Error",JOptionPane.ERROR_MESSAGE);
            }
        }

        // Update Button Declaration.
        if(e.getSource()==signup){
            String emailCheck = t_search.getText();
            String name = t_name.getText();
            String email = t_email.getText();
            String password = t_password.getText();
            try{
                String nota = "update admin set name=?,email=?,password=? where email=?";
                Connection con = JDBC.getConnection();
                PreparedStatement ps = con.prepareStatement(nota);
                ps.setString(1,name);
                ps.setString(2,email);
                ps.setString(3,password);
                ps.setString(4,emailCheck);
                int ipl = ps.executeUpdate();
                if(ipl>0){
                    JOptionPane.showMessageDialog(this, "Data Successfully Updated!");
                    t_email.setText(null);
                    t_search.setText(null);
                    t_password.setText(null);
                    t_name.setText(null);
                }
                else{
                    JOptionPane.showMessageDialog(this,"'Gmail Not Match' Error 404!","Alert",JOptionPane.WARNING_MESSAGE);
                }
            }
            catch(Exception z){
                JOptionPane.showMessageDialog(this,"Database Connection Loss!","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}