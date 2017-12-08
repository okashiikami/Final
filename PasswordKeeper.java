import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;
import java.io.*;
public class PasswordKeeper{
	private static ArrayList<SuperUser> users;
	private static Scanner keyboard;
	
	public PasswordKeeper(){
		users = new ArrayList();
	}
	
	public void login(){
		try{
			get();	
		}catch(EOFException a){
			System.out.println("You are the First User!");
		}catch(IOException b){
			System.out.println(b);
		}
		catch(ClassNotFoundException c){
			System.out.println(c);
		}
		boolean loopDisplay = true;
		while(loopDisplay){
			System.out.println("Welcome to the Password Keeper.\n");
			System.out.println("1. Login\n2. Create Account\n3. Exit");
			keyboard = new Scanner(System.in);
			int answer = keyboard.nextInt();
			if(answer == 1){
				String existingUser = "";
				String existingPass = "";
				System.out.println("Enter a Username followed by password:");
				existingUser = keyboard.next();
				existingPass = keyboard.next();
				SuperUser temp = new SuperUser(existingUser,existingPass);
				System.out.println();
				if(users.contains(temp)){
					System.out.println("Welcome Back!\n");
					temp.display();
					
				}	
			}
			
			if(answer == 2){
				SuperUser tempNew = null;
				boolean cont = true;
				String newUser = "";
				String pass = "";
				System.out.println("Enter a Username:");
				String trash = keyboard.nextLine();
				newUser = keyboard.nextLine();
				System.out.println();
				System.out.println(users.size());
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
							System.out.println(users.size());
							cont = false;
						}
					}else{
						System.out.println("Username taken, please select another.");
					}
				}
				tempNew.display();
			}
			
			if(answer == 3){
				try{
					System.out.println("Saving.");
					save(users);	
				}catch(IOException e){
					System.out.println(e);
				}
				System.out.println(users.size());
				loopDisplay = false;
				System.out.println("Good-bye");
			}
		}
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
			FileOutputStream fileOut = new FileOutputStream(file, false);
			BufferedOutputStream buffedOutput = new BufferedOutputStream(fileOut);
            ObjectOutputStream out = new ObjectOutputStream(buffedOutput);
            out.writeObject(obj);
            out.close();
            fileOut.close();
		}
		public static ArrayList<SuperUser> get() throws IOException, ClassNotFoundException{
			FileInputStream fileIn = new FileInputStream("userInformation.dat");
			BufferedInputStream buffedInput = new BufferedInputStream(fileIn);
            ObjectInputStream in = new ObjectInputStream(buffedInput);
            users = (ArrayList<SuperUser>) in.readObject();
            in.close();
            fileIn.close();
			return users;
		}	
	}