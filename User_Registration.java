package Student.com;

// Import Statements.
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Container;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Objects;
import java.sql.Connection;
public class User_Registration extends JFrame implements ActionListener {
    // Variable Declaration.
    JLabel name, father, mother, email, mobile, Class, password, photo; Container c;
    JTextField t_name, t_father, t_mother, t_email, t_mobile, t_Class; JPasswordField t_password;
    JButton cancel, login, signup; Font font = new Font(Font.SANS_SERIF,Font.BOLD,20);
    ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getResource("image\\add-user.jpg"))) ,
            logo= new ImageIcon(Objects.requireNonNull(getClass().getResource("image\\student.png")));
    JRadioButton male, female, other; ButtonGroup gender;

    User_Registration(){
        // Add Frame Declaration.
        setBounds(300,100,800,510);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(logo.getImage());
        setTitle("Add_Student_Details");
        setResizable(false);
        revalidate();

        // Add Container In Frame.
        c = this.getContentPane();
        c.setBackground(Color.white);
        c.setLayout(null);

        // ----------------Icon_Image For A Signature.------------
        photo = new JLabel(img);
        photo.setBounds(350,0,100,100);
        c.add(photo);

        // Add User_Name Label And Text Field
        name = new JLabel("Name :");
        name.setBounds(30,130,110,40);
        name.setFont(font);
        c.add(name);
        t_name = new JTextField(null);
        t_name.setBounds(160,130,200,40);
        t_name.setFont(font);
        c.add(t_name);

        // Add Father_Name Label And Text Field
        father = new JLabel("Father : ");
        father.setBounds(400,130,110,40);
        father.setFont(font);
        c.add(father);
        t_father = new JTextField(null);
        t_father.setBounds(530,130,200,40);
        t_father.setFont(font);
        c.add(t_father);

        // Add Email_Name Label And Text Field
        email = new JLabel("Email :");
        email.setBounds(30,200,110,40);
        email.setFont(font);
        c.add(email);
        t_email = new JTextField(null);
        t_email.setBounds(160,200,200,40);
        t_email.setFont(font);
        c.add(t_email);

        // Add Mother_Name Label And Text Field
        mother = new JLabel("Mother : ");
        mother.setBounds(400,200,110,40);
        mother.setFont(font);
        c.add(mother);
        t_mother = new JTextField(null);
        t_mother.setBounds(530,200,200,40);
        t_mother.setFont(font);
        c.add(t_mother);

        // Add Mobile_Number Label And Text Field
        mobile = new JLabel("Mobile :");
        mobile.setBounds(30,270,110,40);
        mobile.setFont(font);
        c.add(mobile);
        t_mobile = new JTextField(null);
        t_mobile.setBounds(160,270,200,40);
        t_mobile.setFont(font);
        c.add(t_mobile);

        // Add Class_Input Label And Text Field
        Class = new JLabel("Class : ");
        Class.setBounds(400,270,110,40);
        Class.setFont(font);
        c.add(Class);
        t_Class = new JTextField(null);
        t_Class.setBounds(530,270,200,40);
        t_Class.setFont(font);
        c.add(t_Class);

        // Password Field.
        password = new JLabel("Password :");
        password.setBounds(30,340,110,40);
        password.setFont(font);
        c.add(password);
        t_password = new JPasswordField(null);
        t_password.setBounds(160,340,200,40);
        t_password.setFont(font);
        c.add(t_password);

        // Gate Gender Male
        male = new JRadioButton("Male");
        male.setBounds(400,340,100,40);
        male.setFont(font);
        male.setBackground(null);
        c.add(male);
        // Gate Gender Female
        female = new JRadioButton("Female");
        female.setBounds(510,340,100,40);
        female.setFont(font);
        female.setBackground(null);
        c.add(female);
        // Gate Gender Other
        other = new JRadioButton("Other");
        other.setBounds(630,340,100,40);
        other.setFont(font);
        other.setBackground(null);
        c.add(other);
        // Add Radio Button In A Group Male, Female, & Other
        gender = new ButtonGroup();
        gender.add(male);
        gender.add(female);
        gender.add(other);

        // Add Student Data Button.
        signup  = new JButton("Add");
        signup.setBounds(40,410,150,45);
        signup.setBackground(Color.green);
        signup.setFont(font);
        signup.addActionListener(this);
        c.add(signup);

        // Add Clear Form Button.
        cancel  = new JButton("Clear");
        cancel.setBounds(325,410,150,45);
        cancel.setBackground(Color.red);
        cancel.setFont(font);
        cancel.addActionListener(this);
        c.add(cancel);

        // Add Home Or Back Button.
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
        // Add Student Data Button Action Declaration.
        if(e.getSource()==signup){
            String s_name,  s_father, s_mother, s_email, s_class, s_mobile,  s_gender, s_password;
            s_name=t_name.getText(); s_email=t_email.getText(); s_father=t_father.getText(); s_mother=t_mother.getText();
            s_mobile=t_mobile.getText(); s_class=t_Class.getText(); s_password=t_password.getText();
            if(male.isSelected())           s_gender=male.getText();
            else if(female.isSelected())    s_gender=female.getText();
            else                              s_gender=other.getText();

            if(s_name.equals("") || s_email.equals("") || s_class.equals("") || s_mobile.equals("") || s_mother.equals("") || s_father.equals("") || s_password.equals("")){
                JOptionPane.showMessageDialog(this,"Uploaded Error 404!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                try{
                    Connection con = JDBC.getConnection();
                    PreparedStatement query = con.prepareStatement("insert into student(sname, sfather, smother, semail, sclass, smobile, sgender, spassword ) values(?,?,?,?,?,?,?,?)");
                    query.setString(1,s_name);
                    query.setString(2,s_father);
                    query.setString(3,s_mother);
                    query.setString(4,s_email);
                    query.setString(5,s_class);
                    query.setString(6,s_mobile);
                    query.setString(7,s_gender);
                    query.setString(8,s_password);
                    int check = query.executeUpdate();
                    if(check>0){
                        JOptionPane.showMessageDialog(this,"SuccessFully Uploaded!");
                        System.out.println(s_name+"\t"+s_email+"\t"+s_father+"\t"+s_mother+"\t"+s_mobile+"\t"+s_class+"\t"+s_password+"\t"+s_gender);
                        t_Class.setText(null); t_name.setText(null); t_father.setText(null); t_mobile.setText(null); t_mother.setText(null);
                        t_password.setText(null); t_email.setText(null);
                    }
                    else{
                        JOptionPane.showMessageDialog(this,"Uploaded Error 404!","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch(Exception a){
                    a.getStackTrace();
                }
            }
        }

        // Add Form Clear Data Button Declaration.
        if(e.getSource()==cancel){
            t_Class.setText(null); t_name.setText(null); t_father.setText(null); t_mobile.setText(null); t_mother.setText(null);
            t_password.setText(null); t_email.setText(null);
            gender.clearSelection();
        }

        // Add Back Or Home Button Declaration.
        if(e.getSource()==login){
            setVisible(false);
            new Home_Admin();
        }
    }
}
