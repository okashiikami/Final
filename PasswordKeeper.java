import java.util.HashMap;
import java.util.Scanner;
import java.io.Serializable;
import java.io.*;
public class PasswordKeeper{
	
	private static Scanner keyboard = new Scanner(System.in);
	private HashMap<String, SuperUser> map;
	
	public PasswordKeeper(){
		map = new HashMap<String, SuperUser>();
	}
	
	public void login(){
		try{
			get();	
		}catch(EOFException a){
			System.out.println("You are the First User! Create an account!");
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
			int answer = keyboard.nextInt();
			if(answer == 1){
				String existingUser = "";
				String existingPass = "";
				System.out.println("Enter a Username followed by password:");
				existingUser = keyboard.next();
				existingPass = keyboard.next();
				SuperUser temp = new SuperUser(existingUser,existingPass);
				System.out.println();
				if(map.containsKey(existingUser)){
					System.out.println("Welcome Back!\n");
					map.get(existingUser).display();
					try{
						System.out.println("Saving.");
						save(map);	
					}catch(IOException e){
						System.out.println(e);
					}
					
				}	
			}
			if(answer == 2){
				SuperUser tempNew = null;
				boolean cont = true;
				String newUser;
				String pass;
				while(cont){
					System.out.println("Enter a Username:");
					String trash = keyboard.nextLine();
					newUser = keyboard.nextLine();
					System.out.println();
					System.out.println("Enter in a Password.\nKeep in mind your" +
					" password must include at least one:\nlowercase letter.\n" +
					"uppercase letter.\nspecial character\nnumber\nand be greater" +
					" than 8 characters, but less than 15.\n");
					pass = keyboard.nextLine();
					if(passValid(pass) == true){
						if(map.containsKey(newUser) == false){
							tempNew = new SuperUser(newUser, pass);
							map.put(newUser, tempNew);
							System.out.println("Login Created!");
							System.out.println(map.size());
							map.get(newUser).display();
							cont = false;
						}else{
							System.out.println("Sorry, the username" +
							" has been taken, please select another.");
						}
					}
				}
				try{
					System.out.println("Saving.");
					save(map);	
				}catch(IOException e){
					System.out.println(e);
				}
			}
			
			if(answer == 3){
				loopDisplay = false;
				System.out.println("Good-bye!");
			}
		}
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
		public void save(HashMap<String, SuperUser> obj)throws IOException{

			File file = new File("userInformation.dat");
			FileOutputStream fileOut = new FileOutputStream(file, false);
			BufferedOutputStream buffedOutput = new BufferedOutputStream(fileOut);
            ObjectOutputStream out = new ObjectOutputStream(buffedOutput);
            out.writeObject(obj);
            out.close();
            fileOut.close();
		}
		public HashMap<String, SuperUser> get() throws IOException, ClassNotFoundException{
			FileInputStream fileIn = new FileInputStream("userInformation.dat");
			BufferedInputStream buffedInput = new BufferedInputStream(fileIn);
            ObjectInputStream in = new ObjectInputStream(buffedInput);
            map = (HashMap<String, SuperUser>) in.readObject();
            in.close();
            fileIn.close();
			return map;
		}	
	}