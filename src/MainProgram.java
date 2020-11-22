import translation.*;

import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class MainProgram {
	
	
	public static void main(String[] args) {
//		MainManager main = new MainManager();
//		
//		System.out.println("번역어를 입력해주세요.");
//		Scanner sc = new Scanner(System.in);
//		String text = sc.nextLine();
		
//		TranslationInfo info = new TranslationInfo();
//		info.setSourceLanguage("ko");
//		info.setTargetLanguage("en");
//		info.setText(text);
		
//		NaverTranslator translator = new NaverTranslator();
//		TranslationResult result = translator.Translate(info);
		
//		GoogleTranslator translator = new GoogleTranslator();
//		TranslationResult result = translator.Translate(info);
		
//		System.out.println(result.getResultText());
		
		String url = "https://www.google.com/search?q=jeb+bush+barack+obama";
		try {
		Document document = Jsoup //
		                   .connect(url) //
		                   .userAgent("Mozilla/5.0 (Windows; U; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727)") //
		                   .get();
		
		Element divResultStats = document.select("div#result-stats").first();
		String result = document.select("div#result-stats").text();
		if (divResultStats==null) {
		    throw new RuntimeException("Unable to find results stats.");
		}
		
		System.out.println(divResultStats.text());
		}catch(Exception e)
		{
			
		}
	}

	

}
