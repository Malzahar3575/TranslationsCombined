import translation.*;
import java.util.Scanner;


public class MainProgram {
	
	
	public static void main(String[] args) {
		MainManager manager = new MainManager();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("번역할 문장을 입력해주세요");
		String text = sc.nextLine();
		
		TransOption option = new TransOption();
		option.setSourceLanguage(Language.KOR);
		option.setTargetLanguage(Language.ENG);
		option.setText(text);
		
		TransCombineResult result = manager.Translate(option);
		for(TransInfo info : result.getTransInfoList()) {
			System.out.format("Translator Name: %s / text: %s / count: %,d %n", info.getTransResult().getTranslatorName(),
								info.getTransResult().getTranslatedText(),
								info.getUsedCount());
		}
		sc.close();
	}

	

}
