/*
 *Username/Password Keeper program.
 *
 *program opens up asks for a login as well as a way to remember it.and security
 *questions (stored as strings in a list or array and pulled at random)
 *will display a list of applications as well as an add feature
 *by selecting the app for the first time it will ask what the username and 
 *password is, noting to write the password exactly. then saves the information
 *under that application. when the user selects the app again, the display will
 *show the username and password for the account as well as an edit function
 *with a security question that they would have selected earlier if they want to
 *edit.
 *There is also a create new option to add in another application that isnt
 *pre-existing. this screen will ask for application name (possibly url, or
 *tries to fetch the url and validate it), once found it asks for username and password once more
 *and saves it so that it is at the main screen again
 *all apps are sorted by a-z order for easy finding.
 *
*/
import java.util.ArrayList;
import java.io.*;
public class finalProject{
	
	public static void main(String[] args){
		
		//ArrayList<SuperUser> users = new ArrayList();
		//SuperUser test = new SuperUser("fuck", "Ih8app13!");
		//test.display();
		
		PasswordKeeper program = new PasswordKeeper();
		program.login();
		
	}	
}