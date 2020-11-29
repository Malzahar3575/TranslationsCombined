import translation.*;
import java.util.Scanner;


public class MainProgram {
	
	
	public static void main(String[] args) {
		MainManager manager = new MainManager();
		
		Scanner sc = new Scanner(System.in);
		String text = sc.nextLine();
		
		TransOption option = new TransOption();
		option.setSourceLanguage("ko");
		option.setTargetLanguage("en");
		option.setText(text);
		manager.Translate(option);
		
		sc.close();
	}

	

}
