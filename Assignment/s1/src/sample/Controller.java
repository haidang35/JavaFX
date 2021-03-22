package sample;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Controller {
     public TextField name;
     public TextField age;
     public TextField mark;
     public TextArea textForm;
     public ArrayList<Student> list= new ArrayList<>();

     public static Boolean flag=true;
     public void submit(){
        String n=name.getText();
        String a=age.getText();
        int ag=Integer.parseInt(a);
        String m=mark.getText();
        double ma=Double.parseDouble(m);
        if(!n.isEmpty() && !a.isEmpty() && !m.isEmpty()){
            Student std= new Student();
            std.setName(n);
            std.setAge(ag);
            std.setMark(ma);
            String txt="";
            list.add(std);
            for(Student arr: list){
                txt += "Name: "+arr.getName() + " --- Age: " + arr.getAge() +" --- Mark: " +arr.getMark() + "\r\n";
            }
            textForm.setText(txt);
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
