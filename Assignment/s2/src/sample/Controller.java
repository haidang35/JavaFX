package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TextField name;
    public TextField age;
    public TextField mark;
    public TextArea textForm;
    public ArrayList<Student> list= new ArrayList<>();

    public static Boolean flag=true;
    public static String txtBig="";
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{

            String txt2="";
            String txt="";
            FileInputStream fis= new FileInputStream("student_data.txt");
            DataInputStream dis= new DataInputStream(fis);
            for(;txt2!=null;){
                txt2=dis.readLine();
//                System.out.println(txt2+"\n");
                txtBig += txt2+"\r\n";

            }
//            System.out.println(txt);
            textForm.setText(txtBig);


        }catch(FileNotFoundException f){
            System.out.println("CAN'T NOT FIND FILE");
        }catch (IOException io){
            System.out.println("ERROR FILE");
        }
    }
    public void submit(){
        String n=name.getText();
        String a=age.getText();
        int ag=Integer.parseInt(a);
        String m=mark.getText();
        double ma=Double.parseDouble(m);
        String txt="";
        String txt2="";
        if(!n.isEmpty() && !a.isEmpty() && !m.isEmpty()){
            Student std= new Student();
            std.setName(n);
            std.setAge(ag);
            std.setMark(ma);
            list.add(std);
            for(Student arr: list){
                txt += "Name: "+arr.getName() + " --- Age: " + arr.getAge() +" --- Mark: " +arr.getMark() + "\r\n";

            }

            try{
                FileOutputStream fos=new FileOutputStream("student_data.txt");
                DataOutputStream dos=new DataOutputStream(fos);
                dos.writeBytes(txtBig);
                dos.writeBytes(txt);
                FileInputStream fis= new FileInputStream("student_data.txt");
                DataInputStream dis= new DataInputStream(fis);
                String txt3="";
                for(;txt2!=null;){
                    txt2=dis.readLine();
                    System.out.println(txt2);
                    txt3 += txt2 + "\r\n";
                }
                textForm.setText(txt3);

            }catch(FileNotFoundException f){
                System.out.println("CAN'T NOT FIND FILE");
            }catch (IOException io){
                System.out.println("ERROR FILE");
            }
            name.setText("");
            age.setText("");
            mark.setText("");
        }



    }



    public void arrange(){
        if(flag){
            Collections.sort(list, new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return o2.getMark()-o1.getMark()<0?-1:0;
                }
            });
            String txt="";
            for(Student arr: list){
                txt += "Name: "+arr.getName() + " --- Age: " + arr.getAge() +" --- Mark: " +arr.getMark() + "\r\n";
            }
            textForm.setText(txt);
            flag = false;


        }else if(!flag){
            Collections.sort(list, new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return o2.getMark()-o1.getMark()>0?-1:0;
                }
            });
            String txt="";
            for(Student arr: list){
                txt += "Name: "+arr.getName() + " --- Age: " + arr.getAge() +" --- Mark: " +arr.getMark() + "\r\n";
            }
            textForm.setText(txt);
            flag=true;
        }
    }
}
