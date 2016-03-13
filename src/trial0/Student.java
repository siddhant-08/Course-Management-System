package trial0;

import java.io.*;
class Student extends Person implements Serializable{
    
	private static final long serialVersionUID = 1L;
	private String org;
    
    public Student(String s1, String s2, String s3, String s4, String s5){
       super(s1,s2,s3,s4);
       org=s5;
   }
    public String get_org(){
        return org;
    }
    public void set_org(String s){
        org=s;
    }    
}