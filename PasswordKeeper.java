import java.util.ArrayList;
import java.util.Scanner;

public class PasswordKeeper{
	private ArrayList<SuperUser> users = new ArrayList();
	
	
	public void login(){
		System.out.println(users.size());
		System.out.println("Welcome to the Password Keeper.");
		System.out.println();
		System.out.println("1. Login\n2. Create Account");
		Scanner keyboard = new Scanner(System.in);
		int answer = keyboard.nextInt();
		
		if(answer == 1){
			String existingUser = "";
			String existingPass = "";
			SuperUser temp = new SuperUser(existingUser,existingPass);
			//enter username, use in makesure method
			System.out.println(users.size());
			System.out.println("Enter a Username followed by password:");
			existingUser = keyboard.next();
			existingPass = keyboard.next();
			System.out.println(users.size());
			
			//TODO: fix contains
			System.out.println();
			if(users.contains(temp)){
				System.out.println("2");
				if(temp.getPass().equals(existingPass)){
					System.out.println("Login Successful.");
					temp.display();
				}
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
			temp.display();
			String next = keyboard.nextLine();
		}
		
		System.out.println(users.size());
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
	}