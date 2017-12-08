//class to hold usernames
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.Scanner;
public class SuperUser implements Serializable{
	private String userName;
	private String password;
	private static Scanner keyboard = new Scanner(System.in);
	private ArrayList<LoginInfo> info = new ArrayList();
	String[] siteNames = {"Facebook", "Instagram", "Google", "Yahoo","MiraCosta"};
	
	
	public SuperUser(String name, String pass){
		userName = name;
		password = pass;
		for(int i = 0; i < siteNames.length; i++){
			LoginInfo loginTemp = new LoginInfo("", "", siteNames[i]);
			info.add(loginTemp);
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
		System.out.println("Select the application for more information and to edit.\n" +
		"Type add to add a new application.\nType delete to remove an application.\n");
		int count = 1;
		for(LoginInfo i: info){
			System.out.println(count + " " + i.getSite());
			count ++;
		}
		System.out.println();
		System.out.println("Add,Delete or Logout");
		
		String next = keyboard.next();
		if(next.equalsIgnoreCase("add")){
			add();
		}else if(next.equalsIgnoreCase("delete")){
			delete();
		}else if(numberCheck(next)){
			int choice = (int) Integer.parseInt(next) - 1;
			edit(choice);
		}else if(next.equalsIgnoreCase("logout")){
			;
		}
		
	}
	public boolean numberCheck(String in){
		try{
			Integer.parseInt(in);
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}
	public void add(){
		System.out.println("What is the website name?:");
		String trash = keyboard.nextLine();
		String webName = keyboard.nextLine();
		System.out.println("The Username?:");
		String webUsername = keyboard.nextLine();
		System.out.println("The Password?:");
		String webPass = keyboard.nextLine();
		info.add(new LoginInfo(webUsername, webPass, webName));
		System.out.println("Application added!");
		System.out.println("Returning to menu.");
		try{
			TimeUnit.SECONDS.sleep(5);	
		}catch(InterruptedException e){
			Thread.currentThread().interrupt();
		}
		display();
	}
	public void delete(){
		System.out.println("Which account # would you like to delete?");
		int userDelete = keyboard.nextInt();
		info.remove(userDelete - 1);
		System.out.println("Account removed.");
		System.out.println("Returning to menu.");
		try{
			TimeUnit.SECONDS.sleep(3);	
		}catch(InterruptedException e){
			Thread.currentThread().interrupt();
		}
		display();
	}
	
	public void edit(int index){
		System.out.println(info.get(index));
		System.out.println("Would you like to change the information? y/n");
		String trash = keyboard.nextLine();
		String yes = keyboard.nextLine();
		
		if(String.valueOf(yes.charAt(0)).equalsIgnoreCase("y")){
			System.out.println("Username?");
			String username = keyboard.nextLine();
			System.out.println("Password?");
			String password = keyboard.nextLine();
			info.set(index, new LoginInfo(username, password,info.get(index).getSite()));
			System.out.println("Information Updated!\n");
			//System.out.println(info.get(index));
			System.out.println("Returning to menu.");
			try{
				TimeUnit.SECONDS.sleep(3);	
			}catch(InterruptedException e){
				Thread.currentThread().interrupt();
			}
			display();
		}else{
			System.out.println("Returning to menu.");
			try{
				TimeUnit.SECONDS.sleep(3);	
			}catch(InterruptedException e){
				Thread.currentThread().interrupt();
			}
			display();
		}	
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