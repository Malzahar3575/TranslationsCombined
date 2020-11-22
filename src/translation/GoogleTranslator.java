package translation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GoogleTranslator extends Translator {

	@Override
	public TranslationResult Translate(TranslationInfo info) {
		TranslationResult result = new TranslationResult();
		StringBuilder response = new StringBuilder();
		try {

			String urlStr = "https://script.google.com/macros/s/AKfycbxWGbVLDsldxsFpn2AM5rr_effrWNSHFXS1TMwR6zhTyX24AXUH/exec"
					+ "?q=" + URLEncoder.encode(info.getText(), "UTF-8") + "&target=" + info.getTargetLanguage()
					+ "&source=" + info.getSourceLanguage();
			URL url = new URL(urlStr);
			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			
		} catch (Exception e) {

		}
		
		result.setResultText(response.toString());

		return result;
	}

}
