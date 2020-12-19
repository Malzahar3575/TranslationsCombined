import translation.*;
import java.util.Scanner;


public class MainProgram {
	
	
	public static void main(String[] args) {
		MainManager manager = new MainManager();
		Scanner sc = new Scanner(System.in);
		TransOption option = new TransOption();
		
		while(true){
			System.out.println("������ �� �������ּ���.");
			System.out.println("1: �ѱ��� -> ����");
			System.out.println("2: ���� -> �ѱ���");
			System.out.println("99: ����");			
			
			int number = -1;
			try {
				String input = sc.nextLine();
				number = Integer.parseInt(input);
			}catch(Exception e) {
				
			}
			if (number == 1) {
				option.setSourceLanguage(Language.KOR);
				option.setTargetLanguage(Language.ENG);
			}
			else if(number == 2) {
				option.setSourceLanguage(Language.ENG);
				option.setTargetLanguage(Language.KOR);
			}
			else if(number == 99) {
				System.out.println("����Ǿ����ϴ�.");
				break;
			}
			else {
				System.out.println("�߸��� �Է��Դϴ�.");
				continue;
			}
			
			System.out.println("������ ������ �Է��� �ּ���.");
			
			String text = null;
			while(true) {
				text = sc.nextLine();
				if (text.isBlank() == false) {
					break;
				}
			}
			System.out.println("��ø� ��ٷ��ּ���. �������Դϴ�...");
			option.setText(text);
			
			TransCombineResult result = manager.Translate(option);
			for(TransInfo info : result.getTransInfoList()) {
				if (info.getTransResult().isError()) {
					System.out.format("Translator Name: %s / An error has occurred. (error message: %s)%n", 
							info.getTransResult().getTranslatorName(),
							info.getTransResult().getErrorMessage());
				}
				else {
					System.out.format("Translator Name: %s / text: %s / count: %,d %n", info.getTransResult().getTranslatorName(),
									info.getTransResult().getTranslatedText(),
									info.getTransQuality().getUsedCount());
				}
			}
		}
		sc.close();
	}
}
