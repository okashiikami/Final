//class to hold usernames
import java.util.ArrayList;
import java.util.Scanner;
public class SuperUser{
	private String userName;
	private String password;
	private Scanner keyboard = new Scanner(System.in);
	private ArrayList<LoginInfo> info = new ArrayList();
	String[] siteNames = {"Facebook", "Instagram", "Google", "Yahoo","MiraCosta"};
	
	
	public SuperUser(String name, String pass){
		userName = name;
		password = pass;
		for(int i = 0; i < siteNames.length; i++){
			LoginInfo temp = new LoginInfo(null, null, siteNames[i]);
			info.add(temp);
		}	
	}
	
	public String getUser(){
		return userName;
	}
	public String getPass(){
		return password;
	}
	
	public void display(){
		System.out.println();
		System.out.println("~Main Menu~");
		System.out.println();
		System.out.println("Select the application for more information.\n" +
		"Type add to add a new application.\nType delete to remove an application.\n");
		int count = 1;
		for(LoginInfo i: info){
			System.out.println(count + " " + i.getSite());
			count ++;
		}
		System.out.println();
		System.out.println("Add,Delete or Logout");
		
		//String nextMove = keyboard.next();
//		add();
	}
	
	public void add(){
		System.out.println("What is the website name?:");
		String trash = keyboard.nextLine();
		String webName = keyboard.nextLine();
		System.out.println("The Username?:");
		String webUsername = keyboard.nextLine();
		System.out.println("The Password?:");
		String webPass = keyboard.nextLine();
		LoginInfo adding = new LoginInfo(webUsername, webPass, webName);
		info.add(adding);
		System.out.println("Application added!");
		display();
	}
	public void delete(){
		System.out.println("Which account # would you like to delete?");
		int userDelete = keyboard.nextInt();
		info.remove(userDelete - 1);
	}
	
	public void view(){
		//
	}
	public void edit(){
		//
	}
	public void logout(){
		//stores all SuperUser data into a RAF,
		//then brings up login screen again.
	}
	
	
		
	
	@Override
	public int hashCode(){
		return userName.hashCode();
	}
	
	@Override
	public boolean equals(Object other){
		if(other instanceof SuperUser){
			SuperUser toCompare = (SuperUser) other;
			return this.userName.equals(toCompare.getUser());
		}
		return false;
	}
		
}