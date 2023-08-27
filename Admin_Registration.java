package Student.com;

// Import Statements.
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.Container;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.util.Objects;
import java.sql.Connection;
public class Admin_Registration extends JFrame implements ActionListener {
    // Variable Declaration.
    JLabel a_name,a_password, a_email, text; JTextField t_name, t_email; JPasswordField t_password;
     Font font = new Font(Font.SANS_SERIF,Font.BOLD,20);    Container c; JButton cancel,login, signup;
    ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("image\\student.png"))),
            adminLogo = new ImageIcon(Objects.requireNonNull(getClass().getResource("image\\adminLogo.jpg")));
    Admin_Registration() {
        // Add Frame Declaration.
        setBounds(300, 100, 800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(logo.getImage());
        setTitle("Add_Admin_Details");
        setResizable(false);
        this.revalidate();

        // Add Container In Frame.
        c = this.getContentPane();
        c.setBackground(Color.white);
        c.setLayout(null);

        // Add Symbol Image For Admin.
        text = new JLabel(adminLogo);
        text.setBounds(325,5,150,150);
        c.add(text);

        // Add User_Name Input Label And Input Field.
        a_name = new JLabel("UserName : ");
        a_name.setBounds(170,175,125,50);
        a_name.setFont(font);
        c.add(a_name);
        t_name = new JTextField();
        t_name.setBounds(320,180,250,40);
        t_name.setFont(font);
        c.add(t_name);

        // Add User_Email Input Label And Input Field.
        a_email = new JLabel("Email : ");
        a_email.setBounds(170,235,125,50);
        a_email.setFont(font);
        c.add(a_email);
        t_email = new JTextField();
        t_email.setBounds(320,240,250,40);
        t_email.setFont(font);
        c.add(t_email);

        // Add User_Password Input Label And Input Field.
        a_password = new JLabel("Password : ");
        a_password.setBounds(170,295,125,50);
        a_password.setFont(font);
        c.add(a_password);
        t_password = new JPasswordField();
        t_password.setBounds(320,300,250,40);
        t_password.setFont(font);
        c.add(t_password);

        // Add Admin_Detail Button.
        signup  = new JButton("Add");
        signup.setBounds(170,360,100,50);
        signup.setBackground(Color.green);
        signup.setFont(font);
        signup.addActionListener(this);
        c.add(signup);

        // Add Form Clear Button.
        cancel  = new JButton("Clear");
        cancel.setBounds(320,360,100,50);
        cancel.setBackground(Color.red);
        cancel.setFont(font);
        cancel.addActionListener(this);
        c.add(cancel);

        // Add Back Or Home Button.
        login  = new JButton("Back");
        login.setBounds(470,360,100,50);
        login.setBackground(Color.YELLOW);
        login.setFont(font);
        login.addActionListener(this);
        c.add(login);

        setVisible(true);
    }

    // Override The ActionListener Function.
    @Override
    public void actionPerformed(ActionEvent e){
        // Signup Button Declaration.

        if(e.getSource()==signup){
            String admin_name, admin_email, admin_password;
            admin_name = t_name.getText(); admin_email = t_email.getText(); admin_password = t_password.getText();

                if(admin_name.equals("")||admin_email.equals("")||admin_password.equals("")){
                    JOptionPane.showMessageDialog(this,"Authentication Fail!","Error",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    try{
                        Connection con = JDBC.getConnection();
                        PreparedStatement query = con.prepareStatement("insert into admin(name,email,password) values(?,?,?);");
                        query.setString(1,admin_name);
                        query.setString(2,admin_email);
                        query.setString(3,admin_password);
                        int x = query.executeUpdate();
                        if(x>0) {
                            t_name.setText(null);
                            t_email.setText(null);
                            t_password.setText(null);
                            JOptionPane.showMessageDialog(this,"Successfully Updated.");
                        }
                        else{
                            JOptionPane.showMessageDialog(this,"Authentication Fail!","Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    catch(Exception a){
                        a.getStackTrace();
                    }
                }
        }

        // Clear Button Declaration.
        if(e.getSource()==cancel){
            t_name.setText(null);
            t_email.setText(null);
            t_password.setText(null);
        }

        // Back Button Declaration.
        if(e.getSource()==login){
            this.setVisible(false);
            new Home_Admin();
        }
    }
}
