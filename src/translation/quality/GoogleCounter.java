package translation.quality;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import translation.TransResult;


public class GoogleCounter extends UsageCounter{
	
	public long Measure(TransResult transResult){
		long count = 0;
		
		if (transResult.getTranslatedText() == null || transResult.getTranslatedText().isBlank()) {
			return count;
		}
		
		String text = "\"" + transResult.getTranslatedText() + "\"";
		String url;

		try {
			url = "https://www.google.com/search?q=" + URLEncoder.encode(text, "UTF-8");
			Document document = Jsoup.connect(url)
	                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36") //
	                .get();
			String noResult = document.select("div#topstuff").text();
			if (noResult.contains(text)) {
				return count;
			}
			
			String result = document.select("div#result-stats").text();
			String pattern = "[,\\d]+";
			Matcher match = Pattern.compile(pattern).matcher(result);
			if (match.find()) {
				count = Long.parseLong(match.group().replaceAll(",", ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
}
