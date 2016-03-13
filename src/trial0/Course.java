package trial0;

import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

 class Course implements Serializable
{
	private transient static final long serialVersionUID = 1L;
	private String coursename;
    private int fee;
    private Date date;
    private int duration;
    private Faculty cc;
    public transient DateFormat format =new SimpleDateFormat("dd-MM-yyyy",Locale.US);    
    private List<Faculty> instructors= new ArrayList<Faculty>();
    private List<Student> participants= new ArrayList<Student>();
    
    public Course(String s, int f, Date d, int durn /* Faculty t*/){
        coursename=s;
        fee=f;
        date=d;
        duration=durn;
    }
    public void set_coursename(String s){
        coursename=s;
    }
    public void set_fee(int f){
        fee=f;
    }
    public void set_date(Date d){
        date=d;
    }
    public void set_duration(int d){
        duration=d;
    }

    public void set_cc(Faculty a){
        cc=a;
    }

    public void add_inst(Faculty x){
        instructors.add(x);
    }   

    public void add_participant(Student x){
        participants.add(x);
    }

    public String get_coursename(){
        return coursename;
    }
    public int get_fee(){
        return fee;
    }
    public Date get_date(){
        return date;
    }
    public int get_duration(){
        return duration;
    }
    public Faculty get_cc(){
        return cc;
    }

    public List<Faculty> get_instructors(){
        return instructors;
    }
    public List<Student> get_participants(){
        return participants;
    }
   
    public void delete_participants(Student x){    
            participants.remove(x);
    }
    public void delete_instructors(Faculty x){
            instructors.remove(x);
    }
    public void display_course_name(){
        System.out.println(coursename);
    }    
}