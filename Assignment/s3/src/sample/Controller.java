package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller implements Initializable {
      public TextField name;
      public TextField phone;
      public TextField address;
      public TextField income;
      public TextArea infoTable;
    public final static String connectString = "jdbc:mysql://localhost:3306/test";
    public final static String username = "root";
    public final static String password = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(connectString,username,password);
            Statement stt = conn.createStatement();
            String txt_sql = "select * from nhanvien";
            ResultSet rs = stt.executeQuery(txt_sql);
            String txt="";
            while (rs.next()){
                txt += rs.getString("namestaff");
                txt += " ---- "+rs.getString("phone");
                txt += " ---- "+rs.getString("address");
                txt += " ---- "+rs.getInt("income");
                txt += "\n";
            }
            infoTable.setText(txt);
        }catch (ClassNotFoundException cn){
            System.out.println("Class not found");
        }catch (SQLException se){
            System.out.println("Connect error");
        }
    }

    public void submit(){
          String name=this.name.getText();
          String phoneNumber=this.phone.getText();
          String address=this.address.getText();
          String income=this.income.getText();
          Integer incomeResult=Integer.parseInt(income);
          try {
              Class.forName("com.mysql.jdbc.Driver");
              Connection conn = DriverManager.getConnection(connectString,username,password);
              Statement stt = conn.createStatement();
              String txt_sql_add="insert into nhanvien(namestaff,phone,address,income)" +
                                "values('"+name+"','"+phoneNumber+"','"+address+"','"+incomeResult+"')";
              stt.execute(txt_sql_add);
              String txt_sql = "select * from nhanvien";
              ResultSet rs = stt.executeQuery(txt_sql);
              String txt="";
              while (rs.next()){
                  txt += rs.getString("namestaff");
                  txt += " ---- "+rs.getString("phone");
                  txt += " ---- "+rs.getString("address");
                  txt += " ---- "+rs.getInt("income");
                  txt += "\n";
              }
              infoTable.setText(txt);
              this.name.setText("");
              this.address.setText("");
              this.phone.setText("");
              this.income.setText("");
          }catch (ClassNotFoundException cn){
              System.out.println("Class not found");
          }catch (SQLException se){
              System.out.println("Connect error");
          }


      }
}
