package Student.com;

// Import Statements.
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import java.awt.Container;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.util.Objects;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class User_Login extends JFrame implements ActionListener {
    // Variable Declaration.
    JLabel name,password, photo; JButton cancel,login; JTextField t_name; JPasswordField t_password;
    Container c; Font font = new Font(Font.SANS_SERIF,Font.BOLD,20);
    ImageIcon img= new ImageIcon(Objects.requireNonNull(getClass().getResource("image\\login.jpg"))),
            img2 = new ImageIcon(Objects.requireNonNull(getClass().getResource("image\\student.png")));
    User_Login(){
        //Frame Adding.
        setBounds(300,100,800,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(img2.getImage());
        setTitle("User_Login");
        setResizable(false);
        revalidate();

        // Container Adding.
        c = this.getContentPane();
        c.setBackground(Color.white);
        c.setLayout(null);

        // Set Image
        photo = new JLabel(img);
        photo.setBounds(325,10,130,130);
        c.add(photo);

        // User_Name Label And Text Field Set In Frame.
        name = new JLabel("UserName : ");
        name.setBounds(150,150,125,50);
        name.setFont(font);
        c.add(name);
        t_name = new JTextField();
        t_name.setBounds(300,155,250,40);
        t_name.setFont(font);
        c.add(t_name);

        // Password Label And Password Field Set In Frame.
        password = new JLabel("Password : ");
        password.setBounds(150,225,125,50);
        password.setFont(font);
        c.add(password);
        t_password = new JPasswordField();
        t_password.setBounds(300,230,250,40);
        t_password.setFont(font);
        c.add(t_password);

        // Login Button Set In Frame.
        login  = new JButton("Login");
        login.setBounds(150,300,100,50);
        login.setBackground(Color.green);
        login.setFont(font);
        login.addActionListener(this);
        c.add(login);

        // Clear Button Set In Frame.
        cancel  = new JButton("Clear");
        cancel.setBounds(450,300,100,50);
        cancel.setBackground(Color.red);
        cancel.setFont(font);
        cancel.addActionListener(this);
        c.add(cancel);

        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        // Clear Button Action Declaration.
        if(e.getSource()==cancel){
            t_name.setText(null);
            t_password.setText(null);
        }

        // Login Button Action Declaration.
        if(e.getSource()==login){
            String user_name,user_password;     user_name = t_name.getText();       user_password = t_password.getText();
            if(user_password.equals("")||user_name.equals("")){
                JOptionPane.showMessageDialog(this,"Text Field Is Empty!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                try{
                    Connection con = JDBC.getConnection();
                    PreparedStatement query = con.prepareStatement("select email,password from admin");
                    ResultSet rs = query.executeQuery();
                    int xy = 0;
                            while(rs.next()){
                                if((user_name.equals(rs.getString("email")) &&
                                        user_password.equals(rs.getString("password")))){
                                    this.setVisible(false);
                                    new Home_Admin();
                                    JOptionPane.showMessageDialog(this,"Admin Login Successfully!");
                                    xy=1;
                                    break;
                                }
                            }
                    if(xy==0){
                        t_name.setText(null);
                        t_password.setText(null);
                        JOptionPane.showMessageDialog(this,"Enter Valid Email & Password!","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch(Exception a){
                    a.getStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new User_Login();
    }
}




