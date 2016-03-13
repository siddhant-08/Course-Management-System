
package trial0;
 /* Super Class-person- for faculty and students*/

import java.io.*;
class Person implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String name,address,mobile,email;

    public String get_name(){
        return name;
    }   
    public String get_address(){
        return address;
    }
    public String get_mobile(){
        return mobile;
    }
    public String get_email(){
        return email;
    }
    public void set_name(String s){
        name=s;
    }   
    public void set_address(String s){
        address=s;
    }
    public void set_mobile(String s){
        mobile=s;
    }
    public void set_email(String s){
        email=s;
    }
   
    public Person(String s1, String s2, String s3, String s4){
       name=s1;
       address=s2;
       mobile=s3;
       email=s4;
   }
}