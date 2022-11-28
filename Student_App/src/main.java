import java.sql.*;
import java.util.*;

public class main {
	public static void main(String[] args) {
		Connection connection = null;
		String url = "jdbc:mariadb://localhost:3306/db_project";
		String user = "root";
		String pwd = "";

		Scanner in = new Scanner(System.in);
		
		
		int result = 0 ;
		boolean key = true; 
        boolean record  = true; 
       
		  try {
			  connection = DriverManager.getConnection(url, user, pwd); 
			  }
		  catch (SQLException e) { 
		  System.out.println("1");
		  }
		  System.out.println("Successfully connected to database");
		
		  
		  
		do {
		
			System.out.println("Student tables : ");
			System.out.println(" 1) Insert new student ");
			System.out.println(" 2) Display all the students  ");
			System.out.println(" 3) Alter GPA  ");
			System.out.println(" 4) Exit ");
			System.out.println(" ");
			System.out.print(" Choose an operation : ");

			
			  boolean isInt = false;
			  do {
				  try 
					{ 
					result = in.nextInt();
					isInt = false;
					}  
					catch (InputMismatchException e)  
					{ 
						in.nextLine();
						isInt = true;
						System.out.println("this is not an integer please enter the number again:"); 
					} 
				  
				  }while(isInt);
				  
			
			
			
			record = true ; 
			
			if (result == 1) {

				 
				while (record) {
					 key = true ; 
				  try { 
					  
					  Statement stmt = connection.createStatement(); 
					  Statement Primary_key= connection.createStatement(); 

					  
			        
				  System.out.print("StudenID: ");
				  boolean isStr = false;
				  int studentID = 0;
				  do {
				  String studentIDd =in.next();
				  try 
					{ 
					   studentID =Integer.parseInt(studentIDd); 
						isStr = false;
					}  
					catch (NumberFormatException e)  
					{ 
						isStr = true;
						System.out.println(studentIDd + " is not an integer OR the number is long please enter the id again:"); 
					} 
				  
				  }while(isStr);
				  
				 
				  
				  String sName; 
				  String department; 
				  do {
				  System.out.print("Student name: ");
				   sName = in.next();
				
				  }while (sName.length() > 20);
				  
				  
					  do {
						  
						  System.out.print("Department: ");
						   department = in.next();
					  }while(department.length() > 20);
					  
				  
				  
				  double gpa ; 
				  do {
				  System.out.print("GPA: ");
				  gpa = in.nextDouble();
				  if (gpa < 0 || gpa > 5) {
					  System.out.println("Please enter correct GPA");
				  }
				  }while(gpa < 0 || gpa > 5) ;
				  
				  
				  
				  
				  String sql_key = "Select StudentID from student" ; 
				  ResultSet rs = Primary_key.executeQuery(sql_key);
				  
				  while (rs.next()) {
					  
					  int id = rs.getInt("StudentID");
					  
					  if (studentID == id) {
						  System.out.println("You have a dublicate primary key, please try again with different ID");
						  key = false ; 
						  break ; 
					  }
					  
					  
				  }
				  
				  if (key) {
					  String sql =
					  "INSERT INTO student VALUES("+studentID+",'"+sName+"','"+department+"',"+gpa+
					  ")"; 
					  stmt.executeUpdate(sql);
					  System.out.println("Student - INSERTION");
					  System.out.println("StudenID: " + studentID);
					  System.out.println("Student name: " + sName);
					  System.out.println("Department: "+ department);
					  System.out.println("GPA: " + gpa);
				  }
				
				  System.out.print("Insert another record (Y/N) ?: " );
				  char again = in.next().charAt(0);
				  if (again == 'Y'  || again == 'y') {
					  record = true ; 
				  }else {
					  record = false; 
				  }
				  
		        } catch (SQLException e) {

		        e.printStackTrace() ;
		        }catch(InputMismatchException e) {
		        	System.out.println("please enter valid data in insert ");
					in.nextLine();
		        	break;
		        }
				
				
				
			}
				
			}

			

			else if (result == 2) {

              try {
				  Statement dispaly= connection.createStatement(); 				  
				  
				  String sql_key = "Select * from student" ; 
				  ResultSet rs = dispaly.executeQuery(sql_key);
				  System.out.println(" ");
				  System.out.println("Student - DISPLAY");
				  System.out.println("-------------------------------");

				  while (rs.next()) {
					  
					  int id = rs.getInt("StudentID");
					  String name = rs.getString("sName") ; 
					  String Department = rs.getString("Department") ; 
					  double gpa = rs.getDouble("GPA") ; 
					  
					  System.out.println("StudenID: " + id);
					  System.out.println("Student name: " + name);
					  System.out.println("Department: "+ Department);
					  System.out.println("GPA: " + gpa);
					  System.out.println("-------------------------------");

				  }

            } catch (Exception e) {
               	// TODO: handle exception
              }
				
				
				
			}

			else if (result == 3) {

                     try { 
                   	   Statement stmt = connection.createStatement(); 
            		  Statement gpa= connection.createStatement(); 				  
             				  
            		  String sql_gpa_before = "Select GPA from student" ; 
    				  ResultSet rs_before = gpa.executeQuery(sql_gpa_before);
   	  
    				  String sql_gpa_update = "Select GPA from student" ; 
    				  ResultSet rs_update = gpa.executeQuery(sql_gpa_update);
    				  
    				
    				 
    				  
    				  
    				  String sql_id = "Select StudentID from student" ; 
    				  ResultSet rs2 = gpa.executeQuery(sql_id);
    				  
    				  
    				  
    				  try {
    					  double getGPA_sc;
    					  do {
    					  System.out.print("Enter GPA between 0-5 : ");
        				   getGPA_sc = in.nextDouble();
    					  }while(getGPA_sc < 0 || getGPA_sc > 5) ; 
        				  
          			
          				  
          				  System.out.println(" ");
          			      System.out.println("GPA before altered : ");	
      				      System.out.println("-------------------------------");

          				  while (rs_before.next()) {
          					  
          					  double getGPA_db = rs_before.getDouble("GPA"); 
          					  System.out.println("GPA is : "+ getGPA_db);
          					
          				  }
          				
          				  while (rs_update.next() && rs2.next()) {
          					  
          					  double getGPA_db1 = rs_update.getDouble("GPA"); 
          					  int std_id = rs2.getInt("StudentID");
          					  
          					  
          					 if (getGPA_db1 > getGPA_sc ) {
          						  
          						  getGPA_db1 = getGPA_db1 - 0.1 ; 
          						  String sql = "UPDATE student SET GPA " + " = '" + (int)(Math.round(getGPA_db1 * 100))/100.0 + "' WHERE " + " StudentID = '" + std_id+"'" ; 
                                    stmt.executeUpdate(sql);    		
                                    
          					 
          					 }else if (getGPA_db1 < getGPA_sc) {
          						  getGPA_db1 = getGPA_db1 + 0.1 ; 
          						  
          						  String sql = "UPDATE student SET GPA " + " = '" +  (int)(Math.round(getGPA_db1 * 100))/100.0 + "' WHERE " + " StudentID = '" + std_id+"'" ; 
                                    stmt.executeUpdate(sql);   
          						 
          					 }
          					  
          					
          					  
          				  }
          				  
          				  
        				  String sql_gpa_after  = "Select GPA from student" ; 
        				  ResultSet rs_after = gpa.executeQuery(sql_gpa_after );
        				  
        				  System.out.println(" ");
        			      System.out.println("GPA after altered : ");	
    				      System.out.println("-------------------------------");    				  
        				  
        				  
                            while (rs_after.next()) {
        					  
        					  double getGPA_db = rs_after.getDouble("GPA"); 
        					  System.out.println("GPA is : "+ getGPA_db);
        					
        				  }
          				
          				
        				  
        				  
					} catch (InputMismatchException e) {
						System.out.println();
						System.out.println("Please enter valid number!");
						System.out.println();
						in.nextLine();

					}
    				  
    		
		        } catch (SQLException e) {
		        e.printStackTrace() ;
		        }
		        
				
			}
				
				
                     
				
				
			
			
			
			else if (result == 4) {
				System.out.println(" ");
				System.out.println("***************************");
				System.out.println("Thank you for using our APP");
				System.out.println("***************************");
				System.out.println(" ");

			}else {
				System.out.println();
				System.out.println("Please enter  number between 1-4 ");
				System.out.println();			
				}
			
		

			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
		
		} while (result != 4);
		
			 } 

	}


