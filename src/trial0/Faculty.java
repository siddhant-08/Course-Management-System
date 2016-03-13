package trial0;

import java.io.*;
class Faculty extends Person implements Serializable{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dep;
    public Faculty(String s1, String s2, String s3, String s4, String s5){
       super(s1,s2,s3,s4);
       dep=s5;
   }
    public void set_dep(String s){
        dep=s;
    }
    public String get_dep(){
        return dep;
    } 
}