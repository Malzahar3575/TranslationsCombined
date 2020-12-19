package translation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.apache.commons.lang.StringEscapeUtils;

public class GoogleTranslator extends Translator {

	@Override
	public TransResult Translate(TransOption option) {
		TransResult result = new TransResult();
		result.setTranslatorName("Google");
		try {
			StringBuilder response = new StringBuilder();

			String sourceLangCode = getLanguageCode(option.getSourceLanguage());
			String targetLangCode = getLanguageCode(option.getTargetLanguage());

			String urlStr = "https://script.google.com/macros/s/AKfycbxWGbVLDsldxsFpn2AM5rr_effrWNSHFXS1TMwR6zhTyX24AXUH/exec"
					+ "?q=" + URLEncoder.encode(option.getText(), "UTF-8") + "&target=" + targetLangCode + "&source="
					+ sourceLangCode;
			URL url = new URL(urlStr);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			
			result.setTranslatedText(StringEscapeUtils.unescapeHtml(response.toString()));
			result.setTransOption(option);
		} catch (Exception e) {
			result.setError(true);
			result.setErrorMessage(e.getMessage());
		}
		return result;
	}

}
