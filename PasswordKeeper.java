import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;
import java.io.*;
public class PasswordKeeper implements Serializable{
	private static ArrayList<SuperUser> users = new ArrayList();
	private static Scanner keyboard;
	
	public PasswordKeeper(){
//		try{
//			get(users);	
//		}catch(EOFException a){
//			System.out.println(a);
//		}catch(IOException b){
//			System.out.println(b);
//		}
//		catch(ClassNotFoundException c){
//			System.out.println(c);
//		}
	}
	
	public void login(){
		try{
			get(users);	
		}catch(EOFException a){
			System.out.println(a);
		}catch(IOException b){
			System.out.println(b);
		}
		catch(ClassNotFoundException c){
			System.out.println(c);
		}
		boolean loopDisplay = true;
		while(loopDisplay){
			//System.out.println(users.size());
			System.out.println("Welcome to the Password Keeper.\n");
			//System.out.println();
			System.out.println("1. Login\n2. Create Account\n3. Exit");
			keyboard = new Scanner(System.in);
			SuperUser temp;
			int answer = keyboard.nextInt();
			if(answer == 1){
				String existingUser = "";
				String existingPass = "";
				
				//System.out.println(users.size());
				System.out.println("Enter a Username followed by password:");
				existingUser = keyboard.next();
				existingPass = keyboard.next();
				temp = new SuperUser(existingUser,existingPass);
				//System.out.println(users.size());
				System.out.println();
				if(users.contains(temp)){
					System.out.println("Welcome Back!\n");
					loopDisplay = false;
					temp.display();
					try{
						save(users);	
					}catch(IOException e){
						System.out.println(e);
					}
					
				}
//				try{
//					save(users);	
//				}catch(IOException e){
//					System.out.println(e);
//				}	
			}
//			try{
//				save(users);
//			}catch(IOException e){
//				System.out.println(e);
//			}
			
			SuperUser tempNew = null;
			if(answer == 2){
				boolean cont = true;
				String newUser = "";
				String pass = "";
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
							tempNew = new SuperUser(newUser, pass);
							users.add(tempNew);
							cont = false;
						}
					}else{
						System.out.println("Username taken, please select another.");
					}
				}
				tempNew.display();
				try{
					save(users);	
				}catch(IOException e){
					System.out.println(e);
				}
			}
			
			if(answer == 3){
				try{
					save(users);	
				}catch(IOException e){
					System.out.println(e);
				}
				System.out.println(users.size());
				loopDisplay = false;
				System.out.println("Good-bye");
			}
			//System.out.println(users.size());
		}
		//save(users);
	}
	
	
	public boolean makeSure(String user){
		return (!users.contains(user));	
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
		
		public static void save(ArrayList<SuperUser> obj)throws IOException{

			File file = new File("userInformation.dat");
			//FileOutputStream fileOut = new FileOutputStream(file);
			//BufferedOutputStream buffedOut = new BufferedOutputStream(fileOut);
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file, true));
            out.writeObject(obj);
            out.close();
            //fileOut.close();
		}
		public static ArrayList<SuperUser> get(ArrayList<SuperUser> obj) throws IOException, ClassNotFoundException{
			FileInputStream fileIn = new FileInputStream("userInformation.dat");
			BufferedInputStream buffedInput = new BufferedInputStream(fileIn);
            ObjectInputStream in = new ObjectInputStream(buffedInput);
            ArrayList<SuperUser> returnList = (ArrayList<SuperUser>) in.readObject();
            in.close();
            fileIn.close();
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