import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class PasswordKeeper implements Serializable{
	private static ArrayList<SuperUser> users = new ArrayList();
	
	
	public void login(){
		boolean loopDisplay = true;
		while(loopDisplay){
			//System.out.println(users.size());
			System.out.println("Welcome to the Password Keeper.");
			System.out.println();
			System.out.println("1. Login\n2. Create Account\n3. Exit");
			Scanner keyboard = new Scanner(System.in);
			int answer = keyboard.nextInt();
			
			if(answer == 1){
				String existingUser = "";
				String existingPass = "";
				SuperUser temp = new SuperUser(existingUser,existingPass);
				System.out.println(users.size());
				System.out.println("Enter a Username followed by password:");
				existingUser = keyboard.next();
				existingPass = keyboard.next();
				//System.out.println(users.size());
				System.out.println();
				if(users.contains(temp)){
					get(temp);
					System.out.println("Welcome Back!\n");
					loopDisplay = false;
					temp.display();
				}	
			}
			
			SuperUser temp = null;
			if(answer == 2){
				boolean cont = true;
				String newUser = "";
				String pass = "";
				temp = new SuperUser(newUser, pass);
				System.out.println("Enter a Username:");
				String trash = keyboard.nextLine();
				newUser = keyboard.nextLine();
				System.out.println();
				while(cont){
					if(makeSure(newUser) == true){
						System.out.println("Enter in a Password.\nKeep in mind your" +
						" password must include at least one:\nlowercase letter.\n" +
						"uppercase letter.\nspecial character\nnumber\nand be greater" +
						" than 8 characters, but less than 15.\n");
						pass = keyboard.nextLine();
						if(passValid(pass) == true){
							System.out.println("Login Created!");
							users.add(temp);
							cont = false;
						}
					}else{
						System.out.println("Username taken, please select another.");
					}
				}
				save(temp);
				temp.display();
			}
			
			if(answer == 3){
				loopDisplay = false;
				System.out.println("Good-bye");
			}
			//System.out.println(users.size());
		}
		
	}
	
	public boolean makeSure(String user){
		if(!users.contains(user)){
			return true;
		}
		return false;	
	}
	
	public boolean passValid(String pass){
			boolean passes = false;
			String upper = "(.*[A-Z].*)";
			String lower = "(.*[a-z].*)";
			String numbers = "(.*[0-9].*)";
			String special = "(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)";
			
			if((pass.length()>15) || (pass.length() < 8)){
				System.out.println("Entry must contain over 8 characters\n" +
				"and less than 15.");
				passes = false;
			}if(!pass.matches(upper) || !pass.matches(lower)){
				System.out.println("Entry must contain at least one uppercase and lowercase");
				passes = false;
			}if(!pass.matches(numbers)){
                        System.out.println("Password should contain atleast one number.");
                        passes = false;
            }if(!pass.matches(special)){
                        System.out.println("Password should contain atleast one special character");
                        passes = false;
            }else{
            	passes = true;
            }
            return passes;	
		}
		
		public static void save(SuperUser arr){
			try{
				File file = new File("userInformation.dat");
				FileOutputStream fileOut = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(arr);
                out.close();
                fileOut.close();
			}catch(IOException i){
				i.printStackTrace();
			}
		}
		public static SuperUser get(SuperUser arr){
			SuperUser returnList = null;
			try{
				FileInputStream fileIn = new FileInputStream("userInformation.dat");
            	ObjectInputStream in = new ObjectInputStream(fileIn);
            	returnList = (SuperUser) in.readObject();
            	in.close();
            	fileIn.close();
			}catch(IOException e){
				e.printStackTrace();
			}catch(ClassNotFoundException a){
				a.printStackTrace();
			}
			return returnList;
		}
		
		
		
		
		
		
		
		
		
		
		
		
//		//In if reading in the arrayList
//    //Out if writing the arrayList
//    public static void serial(String action){
//        try {
//            File file = new File("userInformation.dat"); 
//            if (action.equals("out")){
//                FileOutputStream fileOut = new FileOutputStream(file);
//                ObjectOutputStream out = new ObjectOutputStream(fileOut);
//                out.writeObject(users);
//                out.close();
//                fileOut.close();
//            }else if (action.equals("in")){
//                FileInputStream fileIn = new FileInputStream(file);
//                ObjectInputStream in = new ObjectInputStream(fileIn);
//                try {
//                    //HAVE TO PUT THE DATATYPE HERE
//                    isPrime = (boolean[]) in.readObject();
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//                in.close();
//                fileIn.close();
//            }
//          } catch (IOException i) {
//             //i.printStackTrace();
//          } 
//    }
		
		
	}