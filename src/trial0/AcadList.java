package trial0;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.*;
import java.util.*;
import java.util.List;
import java.io.*;
import javax.swing.*;

class AcadList extends JFrame implements Serializable{
	private static final long serialVersionUID = 1L;
	public  transient JPanel panel;
	private static transient SimpleDateFormat format= new SimpleDateFormat("dd-MM-yyyy",Locale.US);
	private  transient Course temp;
	
	private List<Course> courses1=new ArrayList<Course>();
	private List<Course> courses2=new ArrayList<Course>();
	private List<Course> courses3=new ArrayList<Course>();
	private List<Course> courses4=new ArrayList<Course>();
	private List<Course> courses5=new ArrayList<Course>();
	//TODO
	//some exception handlings
	
	public static void main(String[] args) {
		format.setLenient(false);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AcadList t = new AcadList();
					
					t.myread();
					
					int x=t.askArea();
					
					t.work(x);
					
					t.mywrite();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public void myread(){
    	File f= new File("14CS10045_A2_Q1.ser");
        if(f.exists() && !f.isDirectory()){
        	try{
            	FileInputStream fin = new FileInputStream(f);
            	ObjectInputStream in = new ObjectInputStream(fin);
            	courses1=(ArrayList<Course>)in.readObject();
            	courses2=(ArrayList<Course>)in.readObject();
            	courses3=(ArrayList<Course>)in.readObject();
            	courses4=(ArrayList<Course>)in.readObject();
            	courses5=(ArrayList<Course>)in.readObject();
            	in.close();
            	fin.close();
        	}catch(IOException e){
            	e.printStackTrace();
        	}catch(ClassNotFoundException c){
            	c.printStackTrace();
        	}
        }
    }
    public void mywrite(){
    	try{
            FileOutputStream fout = new FileOutputStream("14CS10045_A2_Q1.ser");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(courses1);    
            out.writeObject(courses2);    
            out.writeObject(courses3);    
            out.writeObject(courses4);    
            out.writeObject(courses5);    
            out.flush();
            out.close();
            fout.close();
        }    
        catch(IOException ex){
            System.out.println("Courses not  saved");
            ex.printStackTrace();
        }
    }
    
    public AcadList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350,350);
		setVisible(false);
	}
	
    public int myIntRead(AcadList x, JTextField y){
		String line = y.getText();
        Integer z=-1;
		try {
        	z=Integer.parseInt(line);
		}
        catch (NumberFormatException e){
        	y.setText("");
        	JOptionPane.showMessageDialog(x,
                    "Enter Integer Value!!", "Invalid Input!",
                    JOptionPane.ERROR_MESSAGE);
        }
		return z;
	}
	
	public int askArea(){
		Object[] options={"Course","Faculty","Participant","Exit"};
		int choice=-1;
		choice=JOptionPane.showOptionDialog(null, 
				"Where do you want to work?", "Choose", 
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[0]);
		if(choice == -1 || choice==3)
			System.exit(0);
		return choice;
	}
	
	public void work(int x){
		if(x==0){
			work_on_courses();
        }
        else if(x==1){
            work_on_faculty();
        }
        else if(x==2){
            work_on_student();
        }
        else if(x==3);
		//work(askArea());
	}
	public void work_on_courses(){
		//TODO edit course
		Object[] options={"Create new course","View all Courses","View participants of a course","Edit Course"};
		int choice=-1;
		choice=JOptionPane.showOptionDialog(null, 
				"What do you want to do in courses", "Course Choices", 
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[0]);
		if(choice == -1)
			System.exit(0);
		/**-----------TODO----------------------**/
		if(choice==0){
			new_course_ip(select_year());
		}
		else if(choice==1){
			display_course();
		}
		else if(choice==2){
			display_parts();
		}
		else if(choice==3){
			edit_course();
		}
	}
	public void work_on_faculty(){
		Object[] options={"Create","Edit","Delete"};
		int choice=-1;
		choice=JOptionPane.showOptionDialog(null, 
				"What do you want to do in faculty", "Faculty Choices", 
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[0]);
		if(choice == -1)
			System.exit(0);
		if(choice==0)
			new_fac_ip();
		else if(choice==1)
			alter_fac();
		else if(choice==2)
			delete_fac();
	}
	
	public void work_on_student(){
		Object[] options={"Create","Edit","Delete"};
		int choice=-1;
		choice=JOptionPane.showOptionDialog(null, 
				"What do you want to do in Participant", "Participant Choices", 
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[0]);
		if(choice == -1)
			System.exit(0);
		if(choice==0)
			new_stud_ip();
		else if(choice==1)
			alter_stud();
		else if(choice==2)
			delete_stud();
	
	}
	/*-------------------------------------------------------------------------*/
	public int select_year(){
		Object[] options={"2015-16","14-15","13-14","12-13","11-12"};
		int choice=-1;
		choice=JOptionPane.showOptionDialog(null, 
				"Select which year the course belongs", "Course-> Year Choices", 
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[0]);
		if(choice==-1){
			System.exit(0);
		}
		return choice;
	}
	/*--------------------------------------------------------------*/
	/*----------------------------------------------------------------*/
	// COURSE
	public void new_course_ip(int choose){
		panel=new JPanel(new SpringLayout());

		JLabel label1=new JLabel("name", JLabel.TRAILING);
		JTextField cName=new JTextField(10);
		
		JLabel label2=new JLabel("fee",JLabel.TRAILING);
		JTextField cFee=new JTextField(10);
		
		JLabel label3=new JLabel("date",JLabel.TRAILING);
		JTextField cDate=new JTextField(10);
		
		JLabel label4=new JLabel("duration",JLabel.TRAILING);
		JTextField cDur=new JTextField(10);
		
		JLabel label5 =new JLabel("Hit Button!");
		JButton getval=new JButton("Create Course!");
		
		panel.add(label1);
		panel.add(cName);
		panel.add(label2);
		panel.add(cFee);
		panel.add(label3);
		panel.add(cDate);
		panel.add(label4);
		panel.add(cDur);
		panel.add(label5);
		panel.add(getval);
		
		SpringUtilities.makeCompactGrid(panel,
										5, 2, 
										6, 6,        
										6, 6);
		setContentPane(panel);
		setVisible(true);
		AcadList x=this;
		
		getval.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String name=cName.getText();
				if(name.equals("")){
					JOptionPane.showMessageDialog(x,
							"Fill Name!!", "Invalid Input!",
							JOptionPane.ERROR_MESSAGE);
	
				}
				Integer fee=myIntRead(x,cFee);
				Integer dur=myIntRead(x,cDur);
				
				Date date = null;
				
	            String line = cDate.getText();
	            
	            String a="01-07-";
	            String b="31-04-";
	            int aa=	2015 - choose;
	            int bb=	2016 - choose;
	            a+= String.valueOf(aa);
	            b+= String.valueOf(bb);
	            
	            Date dl=new Date();
	            //Date dr=new Date();
	            Date dr=dl;
	            try{
	            	dl=format.parse(a);
	            	try{
	            		dr=format.parse(b);
	            	}catch(ParseException ey){
	            		
	            	}
	            }
	            catch(ParseException ex){
	            	
	            }
	            if(dur!=-1 && fee!=-1){
	            try {
	                date = format.parse(line);
	                if(date.compareTo(dl)*dr.compareTo(date) > 0){  
	                	temp=new Course(name,fee,date,dur);
				        if(choose==0)
				        	courses1.add(temp);
				        else if(choose==1)
				        	courses2.add(temp);
				        else if(choose==2)
				        	courses3.add(temp);
				        else if(choose==3)
				        	courses4.add(temp);
				        else if(choose==4)
				        	courses5.add(temp);

						mywrite();
				        setVisible(false);
				        work(askArea());
		            }
	                else{
	                	cDate.setText("");
		            	JOptionPane.showMessageDialog(x,
	                            "Enter date between " + dl + " & " + dr , "Invalid Input!",
	                            JOptionPane.ERROR_MESSAGE);
	                }
	            }    
	            catch (ParseException ex){
	            	cDate.setText("");
	            	JOptionPane.showMessageDialog(x,
                            "Enter date in format dd-MM-yyyy", "Invalid Input!",
                            JOptionPane.ERROR_MESSAGE);
		        }}
			}
			
		});	
	}	
	/*-------------------------------------------------*/
	/*-------------------------------------------------*/
	public void new_fac_ip(){
		
		panel=new JPanel(new SpringLayout());
		
		JLabel label1=new JLabel("Name", JLabel.TRAILING);
		JTextField fName=new JTextField(15);
		
		JLabel label2=new JLabel("Email",JLabel.TRAILING);
		JTextField fEmail=new JTextField(15);
		
		JLabel label3=new JLabel("Address",JLabel.TRAILING);
		JTextField fAdd=new JTextField(15);
				
		JLabel label4=new JLabel("Mobile",JLabel.TRAILING);
		JTextField fMob=new JTextField(15);
		
		JLabel label5 =new JLabel("Department");
		JTextField fDep=new JTextField(15);
		
		JLabel label6 =new JLabel("Click button");
		JButton getval=new JButton("Create Faculty!");
		
		
		panel.add(label1);
		panel.add(fName);
		panel.add(label2);
		panel.add(fEmail);
		panel.add(label3);
		panel.add(fAdd);
		panel.add(label4);
		panel.add(fMob);
		panel.add(label5);
		panel.add(fDep);
		panel.add(label6);
		panel.add(getval);
		
		SpringUtilities.makeCompactGrid(panel,
										6, 2, 
										6, 6,        
										6, 6);
		setContentPane(panel);
		setVisible(true);
		
		AcadList y=this;
		
		getval.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String name=fName.getText();
				String email=fEmail.getText();
				String addr= fAdd.getText();
				String mob=fMob.getText();
				String dep=fDep.getText();
				if(name.equals("") || email.equals("") ||
						addr.equals("") || mob.equals("")
						|| dep.equals("")){
					JOptionPane.showMessageDialog(y,
							"Fill all columns!!", "Invalid Input!",
							JOptionPane.ERROR_MESSAGE);
				}
				else{
					Faculty temp=new Faculty(name,addr,mob,email,dep);
					
					
					int x= select_year(); 
					
					List<String> coursenames=new ArrayList<String>();
					List<Course> facCourses=new ArrayList<Course>();
					if(x==0){
						facCourses=courses1;
					}
					else if(x==1){
						facCourses=courses2;
					}
					else if(x==2){
						facCourses=courses3;
					}
					else if(x==3){
						facCourses=courses4;
					}
					else if(x==4){
						facCourses=courses5;
					}
					if(facCourses.size()==0){
						JOptionPane.showMessageDialog(null,
			                    "No course in that year!!", "Invalid Input!",
			                    JOptionPane.ERROR_MESSAGE);
					}
					else{
						for(Course course :facCourses){
							coursenames.add(course.get_coursename());
						}
						String[] options={"OK","Cancel"};
						
						@SuppressWarnings({ "unchecked", "rawtypes" })
						JComboBox combo=new JComboBox(coursenames.toArray());
						
						int selection=JOptionPane.showOptionDialog(y, 
								combo,
								"Choose Course", 
								JOptionPane.DEFAULT_OPTION,
								JOptionPane.PLAIN_MESSAGE,
								null,
								options,options[0]);
						int in;
						in=(int)combo.getSelectedIndex();
						if(selection==0 && in!=-1){
							 
							 Course toAddCourse=facCourses.get(in);
							 int kh=1;
								
							 if(toAddCourse.get_cc()==null){
								 String[] khoptions={"Coordinator","Instructor"};
								 kh=JOptionPane.showOptionDialog(y, 
											"Instructor or Coordinator ? ",
											"Faculty-> position", 
											JOptionPane.DEFAULT_OPTION,
											JOptionPane.PLAIN_MESSAGE,
											null,
											khoptions,khoptions[0]);
							 }
							 if(kh==1){
								 toAddCourse.add_inst(temp);
							 }else{
								 toAddCourse.set_cc(temp);
							 }
							 mywrite();
							 setVisible(false);
						     work(askArea());
						}
					}
				}	
			}	
		});
	}
	/*---------------------------------------------------*/
	/*----------------------------------------------------*/
	public void new_stud_ip(){
		
		panel=new JPanel(new SpringLayout());
		
		JLabel label1=new JLabel("Name", JLabel.TRAILING);
		JTextField sName=new JTextField(15);
		
		JLabel label2=new JLabel("Email",JLabel.TRAILING);
		JTextField sEmail=new JTextField(15);
		
		JLabel label3=new JLabel("Address",JLabel.TRAILING);
		JTextField sAdd=new JTextField(15);
		
		JLabel label4=new JLabel("Mobile",JLabel.TRAILING);
		JTextField sMob=new JTextField(15);

		JLabel label5 =new JLabel("Organisation");
		JTextField sOrg=new JTextField(15);
		
		JLabel label6 =new JLabel("Click button");
		JButton getval=new JButton("Create Participant!");
		
		
		panel.add(label1);
		panel.add(sName);
		panel.add(label2);
		panel.add(sEmail);
		panel.add(label3);
		panel.add(sAdd);
		panel.add(label4);
		panel.add(sMob);
		panel.add(label5);
		panel.add(sOrg);
		panel.add(label6);
		panel.add(getval);
				
		SpringUtilities.makeCompactGrid(panel,
										6, 2, 
										6, 6,        
										6, 6);
		setContentPane(panel);
		setVisible(true);
		
		AcadList y=this;
		
		getval.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String name=sName.getText();
				String email=sEmail.getText();
				String addr= sAdd.getText();
				String mob=sMob.getText();
				String org=sOrg.getText();
				if(name.equals("") || email.equals("") ||
						addr.equals("") || mob.equals("")
						|| org.equals("")){
					JOptionPane.showMessageDialog(y,
							"Fill all columns!!", "Invalid Input!",
							JOptionPane.ERROR_MESSAGE);
					
				}
				else{
					Student temp=new Student(name,addr,mob,email,org);
					
					int x= select_year(); 
					
					List<String> coursenames=new ArrayList<String>();
					List<Course> studCourses=new ArrayList<Course>();
					if(x==0)
						studCourses=courses1;
					else if(x==1)
						studCourses=courses2;
					else if(x==2)
						studCourses=courses3;
					else if(x==3)
						studCourses=courses4;
					else if(x==4)
						studCourses=courses5;
					if(studCourses.size()==0){
						JOptionPane.showMessageDialog(null,
			                    "No course available!!", "Invalid Input!",
			                    JOptionPane.ERROR_MESSAGE);
					}
					else{
						for(Course course :studCourses){
							coursenames.add(course.get_coursename());
						}
						String[] options={"OK","Cancel"};
						
						@SuppressWarnings({ "unchecked", "rawtypes" })
						JComboBox combo=new JComboBox(coursenames.toArray());
						
						int selection=JOptionPane.showOptionDialog(y, 
								combo,
								"Choose Course", 
								JOptionPane.DEFAULT_OPTION,
								JOptionPane.PLAIN_MESSAGE,
								null,
								options,options[0]);
						int in;
						if(selection==0){
							 in=(int)combo.getSelectedIndex();
							 
							 Course toAddCourse=studCourses.get(in);
							 toAddCourse.add_participant(temp);
							 
							 mywrite();
							 setVisible(false);
						     work(askArea());
						}
					}
				}
			}	
		});
	}	
	/*-----------------------------------------*/
	/*--------------------------------------------*/
	public void display_course(){
		int x=select_year();

		List<String> coursenames=new ArrayList<String>();
		List<Course> dispCourses=new ArrayList<Course>();
		if(x==0)
			dispCourses=courses1;
		else if(x==1)
			dispCourses=courses2;
		else if(x==2)
			dispCourses=courses3;
		else if(x==3)
			dispCourses=courses4;
		else if(x==4)
			dispCourses=courses5;
		
		for(Course course :dispCourses)
			coursenames.add(course.get_coursename());
		
		String[] options={"OK","Cancel"};
		
		if(coursenames.size()==0){
			JOptionPane.showMessageDialog(null,
                    "No Courses in this academic year!!", "Invalid Input!",
                    JOptionPane.ERROR_MESSAGE);
			work(askArea());
		}
		else{
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			JComboBox combo=new JComboBox(coursenames.toArray());
			
			int selection=JOptionPane.showOptionDialog(null, 
					combo,
					"Choose Course", 
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE,
					null,
					options,options[0]);
			int in=-1;
			in=(int)combo.getSelectedIndex();
			if(selection==0 && in!=-1){
				Course toView= dispCourses.get(in);
				panel=new JPanel(new SpringLayout());

				JLabel label1=new JLabel("Name", JLabel.TRAILING);
				JTextField cName=new JTextField(toView.get_coursename(),10);
				
				JLabel label2=new JLabel("Fee",JLabel.TRAILING);
				JTextField cFee=new JTextField(String.valueOf(toView.get_fee()),10);
				
				JLabel label3=new JLabel("date",JLabel.TRAILING);
				JTextField cDate=new JTextField(format.format(toView.get_date()),10);
				
				JLabel label4=new JLabel("duration",JLabel.TRAILING);
				JTextField cDur=new JTextField(String.valueOf(toView.get_duration()),10);
				
				JLabel label5 =new JLabel("Hit Button!");
				JButton getval=new JButton("Exit");
				
				panel.add(label1);
				panel.add(cName);
				panel.add(label2);
				panel.add(cFee);
				panel.add(label3);
				panel.add(cDate);
				panel.add(label4);
				panel.add(cDur);
				panel.add(label5);
				panel.add(getval);
				cName.setEditable(false);
				cFee.setEditable(false);
				cDate.setEditable(false);
				cDur.setEditable(false);
				
				SpringUtilities.makeCompactGrid(panel,
												5, 2, 
												6, 6,        
												6, 6);
				setContentPane(panel);
				setVisible(true);
				getval.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
								setVisible(false);
						        work(askArea());
				            }   
				});	

			}	 
		}
	}
	public void edit_course(){
		int x=select_year();

		List<String> coursenames=new ArrayList<String>();
		List<Course> dispCourses=new ArrayList<Course>();
		if(x==0)
			dispCourses=courses1;
		else if(x==1)
			dispCourses=courses2;
		else if(x==2)
			dispCourses=courses3;
		else if(x==3)
			dispCourses=courses4;
		else if(x==4)
			dispCourses=courses5;
		
		for(Course course :dispCourses)
			coursenames.add(course.get_coursename());
		
		String[] options={"OK","Cancel"};
		
		if(coursenames.size()==0){
			JOptionPane.showMessageDialog(null,
                    "No Courses in this academic year!!", "Invalid Input!",
                    JOptionPane.ERROR_MESSAGE);
			work(askArea());
		}
		else{
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			JComboBox combo=new JComboBox(coursenames.toArray());
			
			int selection=JOptionPane.showOptionDialog(null, 
					combo,
					"Choose Course", 
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE,
					null,
					options,options[0]);
			int in=-1;
			in=(int)combo.getSelectedIndex();
			if(selection==0 && in!=-1){
				Course toEdit= dispCourses.get(in);
				panel=new JPanel(new SpringLayout());

				JLabel label1=new JLabel("Name", JLabel.TRAILING);
				JTextField cName=new JTextField(toEdit.get_coursename(),10);
				
				JLabel label2=new JLabel("Fee",JLabel.TRAILING);
				JTextField cFee=new JTextField(String.valueOf(toEdit.get_fee()),10);
				
				JLabel label3=new JLabel("date",JLabel.TRAILING);
				JTextField cDate=new JTextField(format.format(toEdit.get_date()),10);
				
				JLabel label4=new JLabel("duration",JLabel.TRAILING);
				JTextField cDur=new JTextField(String.valueOf(toEdit.get_duration()),10);
				
				JLabel label5 =new JLabel("Hit Button!");
				JButton getval=new JButton("Exit");
				
				panel.add(label1);
				panel.add(cName);
				panel.add(label2);
				panel.add(cFee);
				panel.add(label3);
				panel.add(cDate);
				panel.add(label4);
				panel.add(cDur);
				panel.add(label5);
				panel.add(getval);
				
				SpringUtilities.makeCompactGrid(panel,
												5, 2, 
												6, 6,        
												6, 6);
				setContentPane(panel);
				setVisible(true);
				AcadList y=this;
				getval.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						String name=cName.getText();
						if(name.equals("")){
							JOptionPane.showMessageDialog(y,
									"Fill Name!!", "Invalid Input!",
									JOptionPane.ERROR_MESSAGE);
			
						}
						Integer fee=myIntRead(y,cFee);
						Integer dur=myIntRead(y,cDur);
						
						Date date = null;
						
			            String line = cDate.getText();
			            
			            String a="01-07-";
			            String b="31-04-";
			            a+=(2015-x);
			            b+=(2016-x);
			            Date dl=new Date();
			            try{
			            	dl=format.parse(a);
			            }
			            catch(ParseException ex){
			            	
			            }
			            
			            Date dr=new Date();
			            try{
			            	dr=format.parse(b);
			            }
			            catch(ParseException exx){
			            	
			            }
			            try {
			                date = format.parse(line);
			                if(date.compareTo(dl)*dr.compareTo(date) > 0){  
			                	toEdit.set_coursename(name);
								toEdit.set_duration(dur);
								toEdit.set_date(date);
								toEdit.set_fee(fee);
								
			                	mywrite();
						        setVisible(false);
						        work(askArea());
				            }
			                else{
			                	cDate.setText("");
				            	JOptionPane.showMessageDialog(y,
			                            "Enter date between " + dl + " & " + dr, "Invalid Input!",
			                            JOptionPane.ERROR_MESSAGE);
			                }
			            }    
			            catch (ParseException ex){
			            	cDate.setText("");
			            	JOptionPane.showMessageDialog(y,
		                            "Enter date in format dd-MM-yyyy", "Invalid Input!",
		                            JOptionPane.ERROR_MESSAGE);
				        }	
			         }
				});	

			}	 
		}
	}

	/*----------------------------------------------------*/
	/*----------------------------------------------------*/
	public void display_parts(){
		int x=select_year();
		List<String> coursenames=new ArrayList<String>();
		List<Course> partCourses=new ArrayList<Course>();
		if(x==0)
			partCourses=courses1;
		else if(x==1)
			partCourses=courses2;
		else if(x==2)
			partCourses=courses3;
		else if(x==3)
			partCourses=courses4;
		else if(x==4)
			partCourses=courses5;
		if(partCourses.size()==0){
			JOptionPane.showMessageDialog(null,
                    "No Courses in this academic year!!", "Invalid Input!",
                    JOptionPane.ERROR_MESSAGE);
			work(askArea());
		}
		else{
			for(Course course :partCourses){
				coursenames.add(course.get_coursename());
			}
			String[] options={"OK","Cancel"};
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			JComboBox combo=new JComboBox(coursenames.toArray());
			
			int selection=JOptionPane.showOptionDialog(null, 
					combo,
					"Choose Course", 
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE,
					null,
					options,options[0]);
			int in=-1;
			in=(int)combo.getSelectedIndex();
			if(selection==0 && in!=-1){
				 
				 Course viewPar=partCourses.get(in);
				 List<Student> pars= viewPar.get_participants();
				 panel =new JPanel();
				 setContentPane(panel);
				 JTextArea dispArea=new JTextArea(15,30);
				 panel.add(new JScrollPane(dispArea));
				 int cnt=1;
				 for(Student z:pars){
					 String y= cnt + "  Name- " + z.get_name() + "\nemail- " + z.get_email() + " mobile- " + z.get_mobile() + "\n----------------\n";
					 cnt++;
					 dispArea.append(y);
				 }
				 JButton close=new JButton("Exit");
				 panel.add(close);
				 setVisible(true);
				 close.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e ){
						setVisible(false);
						work(askArea());
					}
				});
			}
		}	
	}
	/*------------------------------------------------------------*/
	/*------------------------------------------------------------*/
	public void alter_fac(){
		int x= select_year(); 
		
		List<String> coursenames=new ArrayList<String>();
		List<Course> facCourses=new ArrayList<Course>();
		if(x==0){
			facCourses=courses1;
		}
		else if(x==1){
			facCourses=courses2;
		}
		else if(x==2){
			facCourses=courses3;
		}
		else if(x==3){
			facCourses=courses4;
		}
		else if(x==4){
			facCourses=courses5;
		}
		
		for(Course course :facCourses){
			coursenames.add(course.get_coursename());
		}
		if(facCourses.size()==0){
			JOptionPane.showMessageDialog(null,
                    "No Courses in this academic year!!", "Invalid Input!",
                    JOptionPane.ERROR_MESSAGE);
			work(askArea());
		}
		else{
			String[] options={"OK","Cancel"};
		
				
			@SuppressWarnings({ "unchecked", "rawtypes" })
			JComboBox combo=new JComboBox(coursenames.toArray());
			
			int selection=JOptionPane.showOptionDialog(null, 
					combo,
					"Choose Course", 
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE,
					null,
					options,options[0]);
			int in=-1;
			in=(int)combo.getSelectedIndex();
			 
			if(selection==0 && in!=-1){
				 Course alterFacCourse=facCourses.get(in);
				 //Course temp2=alterFacCourse;
				 List<Faculty> facs= alterFacCourse.get_instructors();
				 List<String> facNames=new ArrayList<String>();
				 
				 for(Faculty fac:facs){
					 facNames.add(fac.get_name());
				 }
				 String[] choices={"OK","Cancel"};
					
				 @SuppressWarnings({ "unchecked", "rawtypes" })
					JComboBox combo2=new JComboBox(facNames.toArray());
					
					int select=JOptionPane.showOptionDialog(null, 
							combo2,
							"Choose Faculty", 
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.PLAIN_MESSAGE,
							null,
							choices,choices[0]);
					int in2=-1;
					in2=(int)combo2.getSelectedIndex();
					
					if(select==0 && in2!=-1){
						Faculty toEdit=facs.get(in2);
						
						panel=new JPanel(new SpringLayout());
						setContentPane(panel);
						JLabel label1=new JLabel("Name", JLabel.TRAILING);
						JTextField fName=new JTextField(toEdit.get_name(),15);
						
						JLabel label2=new JLabel("Email",JLabel.TRAILING);
						JTextField fEmail=new JTextField(toEdit.get_email(),15);
						
						JLabel label3=new JLabel("Address",JLabel.TRAILING);
						JTextField fAdd=new JTextField(toEdit.get_address(),15);
						
						JLabel label4=new JLabel("Mobile",JLabel.TRAILING);
						JTextField fMob=new JTextField(toEdit.get_mobile(),15);
						
						JLabel label5 =new JLabel("Department");
						JTextField fDep=new JTextField(toEdit.get_dep(),15);
						
						JLabel label6 =new JLabel("Click button");
						JButton getval=new JButton("Edit Faculty!");
						
						panel.add(label1);
						panel.add(fName);
						panel.add(label2);
						panel.add(fEmail);
						panel.add(label3);
						panel.add(fAdd);
						panel.add(label4);
						panel.add(fMob);
						panel.add(label5);
						panel.add(fDep);
						panel.add(label6);
						panel.add(getval);
						
						SpringUtilities.makeCompactGrid(panel,
														6, 2, 
														6, 6,        
														6, 6);
						setContentPane(panel);
						setVisible(true);
						
						AcadList y=this;
						getval.addActionListener(new ActionListener(){
							@Override
							public void actionPerformed(ActionEvent e) {
								String name=fName.getText();
								String email=fEmail.getText();
								String addr= fAdd.getText();
								String mob=fMob.getText();
								String dep=fDep.getText();
								if(name.equals("") || email.equals("") ||
										addr.equals("") || mob.equals("")
										|| dep.equals("")){
									JOptionPane.showMessageDialog(y,
											"Fill all columns!!", "Invalid Input!",
											JOptionPane.ERROR_MESSAGE);
								}
								toEdit.set_name(name);
								toEdit.set_address(addr);
								toEdit.set_email(email);
								toEdit.set_mobile(mob);
								toEdit.set_dep(dep);
								mywrite();
								setVisible(false);
								work(askArea());
							}
						});	
					}
				}
	
		}
	}	
	/*------------------------------------------*/
	/*------------------------------------------*/
	public void delete_fac(){
		int x= select_year(); 
		
		List<String> coursenames=new ArrayList<String>();
		List<Course> facCourses=new ArrayList<Course>();
		if(x==0){
			facCourses=courses1;
		}
		else if(x==1){
			facCourses=courses2;
		}
		else if(x==2){
			facCourses=courses3;
		}
		else if(x==3){
			facCourses=courses4;
		}
		else if(x==4){
			facCourses=courses5;
		}
		if(facCourses.size()==0){
			JOptionPane.showMessageDialog(null,
                    "No Courses in this academic year!!", "Invalid Input!",
                    JOptionPane.ERROR_MESSAGE);
			work(askArea());
		}
		else{
			for(Course course :facCourses)
				coursenames.add(course.get_coursename());
			
			String[] options={"OK","Cancel"};
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			JComboBox combo=new JComboBox(coursenames.toArray());
			
			int selection=JOptionPane.showOptionDialog(null, 
					combo,
					"Choose Course", 
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE,
					null,
					options,options[0]);
			int in=-1;
			in=(int)combo.getSelectedIndex();
			 
			if(selection==0 && in!=-1){
				 Course delFacCourse=facCourses.get(in);
				 
				 List<Faculty> facs= delFacCourse.get_instructors();
				 List<String> facNames=new ArrayList<String>();
				 
				 for(Faculty fac:facs){
					 facNames.add(fac.get_name());
				 }
				 String[] choices={"OK","Cancel"};
					
				 @SuppressWarnings({ "unchecked", "rawtypes" })
					JComboBox combo2=new JComboBox(facNames.toArray());
					
					int select=JOptionPane.showOptionDialog(null, 
							combo2,
							"Choose Course", 
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.PLAIN_MESSAGE,
							null,
							choices,choices[0]);
					int in2=-1;
					in2=(int)combo2.getSelectedIndex();
					
					if(select==0 && in2!=-1){
						Faculty toDel=facs.get(in2);
						delFacCourse.delete_instructors(toDel);
						mywrite();
						work(askArea());
					}
			}
		}		
	}	
	/*--------------------------------------------*/
	/*--------------------------------------------*/
	public void alter_stud(){
		int x= select_year(); 
		
		List<String> coursenames=new ArrayList<String>();
		List<Course> studCourses=new ArrayList<Course>();
		if(x==0){
			studCourses=courses1;
		}
		else if(x==1){
			studCourses=courses2;
		}
		else if(x==2){
			studCourses=courses3;
		}
		else if(x==3){
			studCourses=courses4;
		}
		else if(x==4){
			studCourses=courses5;
		}
		if(studCourses.size()==0){
			JOptionPane.showMessageDialog(null,
                    "No Courses in this academic year!!", "Invalid Input!",
                    JOptionPane.ERROR_MESSAGE);
			work(askArea());
		}
		else{
			for(Course course :studCourses)
				coursenames.add(course.get_coursename());
			String[] options={"OK","Cancel"};
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			JComboBox combo=new JComboBox(coursenames.toArray());
			
			int selection=JOptionPane.showOptionDialog(null, 
					combo,
					"Choose Course", 
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE,
					null,
					options,options[0]);
			int in=-1;
			in=(int)combo.getSelectedIndex();
			 
			if(selection==0 && in!=-1){
				 Course alterStudCourse=studCourses.get(in);
				 //Course temp2=alterFacCourse;
				 List<Student> studs= alterStudCourse.get_participants();
				 List<String> studNames=new ArrayList<String>();
				 
				 for(Student stud:studs){
					 studNames.add(stud.get_name());
				 }
				 String[] choices={"OK","Cancel"};
					
				 @SuppressWarnings({ "unchecked", "rawtypes" })
					JComboBox combo2=new JComboBox(studNames.toArray());
					
					int select=JOptionPane.showOptionDialog(null, 
							combo2,
							"Choose Student", 
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.PLAIN_MESSAGE,
							null,
							choices,choices[0]);
					int in2=-1;
					in2=(int)combo2.getSelectedIndex();
					 
					if(select==0 && in2!=-1){
						
						Student toEdit=studs.get(in2);
						panel=new JPanel(new SpringLayout());
						setContentPane(panel);
						JLabel label1=new JLabel("Name", JLabel.TRAILING);
						
						JTextField sName=new JTextField(toEdit.get_name(),15);
						
						JLabel label2=new JLabel("Email",JLabel.TRAILING);
						JTextField sEmail=new JTextField(toEdit.get_email(),15);
						
						
						JLabel label3=new JLabel("Address",JLabel.TRAILING);
						JTextField sAdd=new JTextField(toEdit.get_address(),15);
						
						JLabel label4=new JLabel("Mobile",JLabel.TRAILING);
						JTextField sMob=new JTextField(toEdit.get_mobile(),15);
						
						JLabel label5 =new JLabel("Organisation");
						JTextField sOrg=new JTextField(toEdit.get_org(),15);
						
						JLabel label6 =new JLabel("Click button");
						JButton getval=new JButton("Edit Participant!");
						
						panel.add(label1);
						panel.add(sName);
						panel.add(label2);
						panel.add(sEmail);
						panel.add(label3);
						panel.add(sAdd);
						panel.add(label4);
						panel.add(sMob);
						panel.add(label5);
						panel.add(sOrg);
						panel.add(label6);
						panel.add(getval);
						
						SpringUtilities.makeCompactGrid(panel,
														6, 2, 
														6, 6,        
														6, 6);
						setContentPane(panel);
						setVisible(true);
						
						AcadList y=this;
						getval.addActionListener(new ActionListener(){
							@Override
							public void actionPerformed(ActionEvent e) {
								String name=sName.getText();
								String email=sEmail.getText();
								String addr= sAdd.getText();
								String mob=sMob.getText();
								String org=sOrg.getText();
								if(name.equals("") || email.equals("") ||
										addr.equals("") || mob.equals("")
										|| org.equals("")){
									JOptionPane.showMessageDialog(y,
											"Fill all columns!!", "Invalid Input!",
											JOptionPane.ERROR_MESSAGE);
								}
								toEdit.set_name(name);
								toEdit.set_address(addr);
								toEdit.set_email(email);
								toEdit.set_mobile(mob);
								toEdit.set_org(org);
								mywrite();
								myread();
								setVisible(false);
								work(askArea());
							}
						});	
					}
				}
		}
	}
	/*------------------------------------------*/
	/*------------------------------------------*/
	public void delete_stud(){
		int x= select_year(); 
		
		List<String> coursenames=new ArrayList<String>();
		List<Course> studCourses=new ArrayList<Course>();
		if(x==0){
			studCourses=courses1;
		}
		else if(x==1){
			studCourses=courses2;
		}
		else if(x==2){
			studCourses=courses3;
		}
		else if(x==3){
			studCourses=courses4;
		}
		else if(x==4){
			studCourses=courses5;
		}
		if(studCourses.size()==0){
			JOptionPane.showMessageDialog(null,
                    "No Courses in this academic year!!", "Invalid Input!",
                    JOptionPane.ERROR_MESSAGE);
			work(askArea());
		}
		else{
			for(Course course :studCourses){
				coursenames.add(course.get_coursename());
			}
			String[] options={"OK","Cancel"};
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			JComboBox combo=new JComboBox(coursenames.toArray());
			
			int selection=JOptionPane.showOptionDialog(null, 
					combo,
					"Choose Course", 
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE,
					null,
					options,options[0]);
			int in=-1;
			in=(int)combo.getSelectedIndex();
			 
			if(selection==0 && in!=-1){
				 Course delStudCourse=studCourses.get(in);
				 
				 List<Student> studs= delStudCourse.get_participants();
				 List<String> studNames=new ArrayList<String>();
				 
				 for(Student stud:studs){
					 studNames.add(stud.get_name());
				 }
				 String[] choices={"OK","Cancel"};
					
				 	@SuppressWarnings({ "unchecked", "rawtypes" })
					JComboBox combo2=new JComboBox(studNames.toArray());
					
					int select=JOptionPane.showOptionDialog(null, 
							combo2,
							"Choose Course", 
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.PLAIN_MESSAGE,
							null,
							choices,choices[0]);
					int in2=-1;
					in2=(int)combo2.getSelectedIndex();
					 
					if(select==0 && in2!=-1){
						Student toDel=studs.get(in2);
						delStudCourse.delete_participants(toDel);
						mywrite();
						work(askArea());
					}
				}	

		}
	
	}
}	