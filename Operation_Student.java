package Student.com;

// Import Statements.
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import java.awt.Container;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.sql.Connection;

public class Operation_Student extends JFrame implements ActionListener{
        // Variable Declaration.
        JLabel name, father, mother, email, mobile, Class, password;    Container c;
        JTextField t_name, t_father, t_mother, t_email, t_mobile, t_Class, t_password, t_search;
        JButton cancel, login, signup, search;  Font font = new Font(Font.SANS_SERIF,Font.BOLD,20);
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("image\\student.png")));
        JRadioButton male, female, other; ButtonGroup gender;

        Operation_Student(){
            // Add Frame Declaration.
            setBounds(300,100,800,510);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Add_Student_Details");
            setIconImage(logo.getImage());
            setResizable(false);
            revalidate();

            // Add Container In Frame.
            c = this.getContentPane();
            c.setBackground(Color.white);
            c.setLayout(null);

            // Add Search Input Field & Button.
            t_search = new JTextField();
            t_search.setBounds(200,30,300,60);
            t_search.setBorder(BorderFactory.createTitledBorder("Email"));
            t_search.setBackground(Color.WHITE);
            t_search.setFont(font);
            c.add(t_search);
            search = new JButton("Search");
            search.setBounds(500,40,100,48);
            search.setFont(font);
            search.setBorder(null);
            search.setBackground(Color.darkGray);
            search.setForeground(Color.white);
            search.addActionListener(this);
            c.add(search);

            // Add User_Name Label & Input Field
            name = new JLabel("Name :");
            name.setBounds(30,130,110,40);
            name.setFont(font);
            c.add(name);
            t_name = new JTextField(null);
            t_name.setBounds(160,130,200,40);
            t_name.setFont(font);
            c.add(t_name);

            // Add Father_Name Label & Input Field
            father = new JLabel("Father : ");
            father.setBounds(400,130,110,40);
            father.setFont(font);
            c.add(father);
            t_father = new JTextField(null);
            t_father.setBounds(530,130,200,40);
            t_father.setFont(font);
            c.add(t_father);

            // Add Email_Id Label & Input Field
            email = new JLabel("Email :");
            email.setBounds(30,200,110,40);
            email.setFont(font);
            c.add(email);
            t_email = new JTextField(null);
            t_email.setBounds(160,200,200,40);
            t_email.setFont(font);
            c.add(t_email);

            // Add Mother_Name Label & Input Field
            mother = new JLabel("Mother : ");
            mother.setBounds(400,200,110,40);
            mother.setFont(font);
            c.add(mother);
            t_mother = new JTextField(null);
            t_mother.setBounds(530,200,200,40);
            t_mother.setFont(font);
            c.add(t_mother);

            // Add Mobile_Number Label & Input Field
            mobile = new JLabel("Mobile :");
            mobile.setBounds(30,270,110,40);
            mobile.setFont(font);
            c.add(mobile);
            t_mobile = new JTextField(null);
            t_mobile.setBounds(160,270,200,40);
            t_mobile.setFont(font);
            c.add(t_mobile);

            // Add Class_Detail Label & Input Field
            Class = new JLabel("Class : ");
            Class.setBounds(400,270,110,40);
            Class.setFont(font);
            c.add(Class);
            t_Class = new JTextField(null);
            t_Class.setBounds(530,270,200,40);
            t_Class.setFont(font);
            c.add(t_Class);

            // Add Password_Input Label & Input Field
            password = new JLabel("Password :");
            password.setBounds(30,340,110,40);
            password.setFont(font);
            c.add(password);
            t_password = new JTextField(null);
            t_password.setBounds(160,340,200,40);
            t_password.setFont(font);
            c.add(t_password);

            // Add Gender Male.
            male = new JRadioButton("Male");
            male.setBounds(400,340,100,40);
            male.setFont(font);
            male.setBackground(null);
            c.add(male);

            // Add Gender Female.
            female = new JRadioButton("Female");
            female.setBounds(510,340,100,40);
            female.setFont(font);
            female.setBackground(null);
            c.add(female);

            // Add Gender Other.
            other = new JRadioButton("Other");
            other.setBounds(630,340,100,40);
            other.setFont(font);
            other.setBackground(null);
            c.add(other);

            // Add Gender Radio Button Group Store Male, Female & Other.
            gender = new ButtonGroup();
            gender.add(male);
            gender.add(female);
            gender.add(other);

            // Add Update Button
            signup  = new JButton("Update");
            signup.setBounds(40,410,150,45);
            signup.setBackground(Color.green);
            signup.setFont(font);
            signup.addActionListener(this);
            c.add(signup);

            // Add Delete Button
            cancel  = new JButton("Delete");
            cancel.setBounds(325,410,150,45);
            cancel.setBackground(Color.red);
            cancel.setFont(font);
            cancel.addActionListener(this);
            c.add(cancel);

            // Add Home Or Back Button
            login  = new JButton("Back");
            login.setBounds(580,410,150,45);
            login.setBackground(Color.YELLOW);
            login.setFont(font);
            login.addActionListener(this);
            c.add(login);

            this.setVisible(true);
        }

        // Add Action Listeners.
        public void actionPerformed(ActionEvent e){
            // Add Back Or Home Button Declaration.
            if(e.getSource()==login){
                setVisible(false);
                new Home_Admin();
            }

            // Add Search Button Declaration.
            if(e.getSource()==search){
                String emailCheck = t_search.getText();
                String genderCheck ="";
                try{
                    Connection con = JDBC.getConnection();
                    PreparedStatement ps = con.prepareStatement("Select * from student where semail=?");
                    ps.setString(1,emailCheck);
                    ResultSet rs = ps.executeQuery();

                    while(rs.next()){
                        t_name.setText(rs.getString("sname"));
                        t_father.setText(rs.getString("sfather"));
                        t_mother.setText(rs.getString("smother"));
                        t_mobile.setText(rs.getString("smobile"));
                        t_email.setText(rs.getString("semail"));
                        t_password.setText(rs.getString("spassword"));
                        t_Class.setText(rs.getString("sclass"));
                        genderCheck = rs.getString("sgender");
                    }
                    if(genderCheck.equals(male.getText())){
                        male.setSelected(true);
                    }
                    else if(genderCheck.equals(female.getText())){
                        female.setSelected(true);
                    }
                    else if(genderCheck.equals(other.getText())){
                        other.setSelected(true);
                    }
                }
                catch(Exception z){
                    JOptionPane.showMessageDialog(this,"Database Connection Loss!","Error",JOptionPane.ERROR_MESSAGE);
                }
            }

            // Add Delete Button Declaration.
            if(e.getSource()==cancel){
                String emailCheck = t_search.getText();
                try{
                    Connection con = JDBC.getConnection();
                    PreparedStatement ps = con.prepareStatement("delete from student where semail=?");
                    ps.setString(1,emailCheck);
                    int xy = ps.executeUpdate();
                    if(xy>0) {
                        JOptionPane.showMessageDialog(this, "One Row Successfully Deleted");
                        t_email.setText(null);
                        t_search.setText(null);
                        t_Class.setText(null);
                        t_password.setText(null);
                        t_mobile.setText(null);
                        t_name.setText(null);
                        t_mother.setText(null);
                        t_father.setText(null);
                        gender.clearSelection();
                    }
                    else{
                        JOptionPane.showMessageDialog(this,"'Gmail Not Match' Error 404!","Alert",JOptionPane.WARNING_MESSAGE);
                    }
                }
                catch(Exception z){
                    JOptionPane.showMessageDialog(this,"Database Connection Loss!","Error",JOptionPane.ERROR_MESSAGE);
                }
            }

            // Add Update Button Declaration.
            if(e.getSource()==signup){
                String emailCheck = t_search.getText();
                String name = t_name.getText();
                String father = t_father.getText();
                String mother = t_mother.getText();
                String email = t_email.getText();
                String mobile = t_mobile.getText();
                String password = t_password.getText();
                String classCheck = t_Class.getText();
                String genderCheck="";
                if(male.isSelected()) genderCheck = male.getText();
                else if(female.isSelected()) genderCheck = female.getText();
                else if(other.isSelected()) genderCheck = other.getText();
                try{
                    String nota = "update student set sname=?,sfather=?,smother=?,semail=?,sclass=?,smobile=?,sgender=?,spassword=? where semail=?";
                    Connection con = JDBC.getConnection();
                    PreparedStatement ps = con.prepareStatement(nota);
                    ps.setString(1,name);
                    ps.setString(2,father);
                    ps.setString(3,mother);
                    ps.setString(4,email);
                    ps.setString(5,classCheck);
                    ps.setString(6,mobile);
                    ps.setString(7,genderCheck);
                    ps.setString(8,password);
                    ps.setString(9,emailCheck);
                    int ipl = ps.executeUpdate();
                    if(ipl>0){
                        JOptionPane.showMessageDialog(this, "Data Successfully Updated!");
                        t_email.setText(null);
                        t_search.setText(null);
                        t_Class.setText(null);
                        t_password.setText(null);
                        t_mobile.setText(null);
                        t_name.setText(null);
                        t_mother.setText(null);
                        t_father.setText(null);
                        gender.clearSelection();
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
