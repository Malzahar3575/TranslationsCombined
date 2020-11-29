import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class GoogleCounter {
	public long Count(String text) throws Exception {
		long count = 0;
		String url = "https://www.google.com/search?q=" + URLEncoder.encode(text, "UTF-8");
		Document document = Jsoup
                .connect(url)
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
		
		return count;
	}
}
