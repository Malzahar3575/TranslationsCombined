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
				number = sc.nextInt();
			}catch(Exception e) {
				
			}
			if (sc.hasNextLine()) {
				sc.nextLine();
			}
			if (number == 1) {
				option.setSourceLanguage(Language.KOR);
				option.setTargetLanguage(Language.ENG);
				break;
			}
			else if(number == 2) {
				option.setSourceLanguage(Language.ENG);
				option.setTargetLanguage(Language.KOR);
				break;
			}
			else if(number == 99) {
				return;
			}
			else {
				System.out.println("�߸� �� �Է��Դϴ�.");
			}
		}
		
		System.out.println("������ ������ �Է����ּ���");
		String text = sc.nextLine();

		option.setText(text);
		
		TransCombineResult result = manager.Translate(option);
		for(TransInfo info : result.getTransInfoList()) {
			System.out.format("Translator Name: %s / text: %s / count: %,d %n", info.getTransResult().getTranslatorName(),
								info.getTransResult().getTranslatedText(),
								info.getTransQuality().getUsedCount());
		}
		sc.close();
	}

	

}
