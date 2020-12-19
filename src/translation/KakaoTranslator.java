package translation;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class KakaoTranslator extends Translator {
	@Override
	protected String getLanguageCode(Language language) {
		if (language == Language.KOR)
			return "kr";
		else if (language == Language.ENG)
			return "en";
		else
			return null;
	}

	@Override
	public TransResult Translate(TransOption option) {
		TransResult result = new TransResult();
		result.setTranslatorName("Kakao");
		try {
			String clientId = "KakaoAK 119c07ed94ab8daa6f048e31a557eb13";
			String apiURL = "https://dapi.kakao.com/v2/translation/translate?";

			Map<String, String> requestHeaders = new HashMap<>();
			requestHeaders.put("Authorization", clientId);
			requestHeaders.put("Content-Type", "application/x-www-form-urlencoded");

			String text;

			text = URLEncoder.encode(option.getText(), "UTF-8");

			String sourceLangCode = getLanguageCode(option.getSourceLanguage());
			String targetLangCode = getLanguageCode(option.getTargetLanguage());
			String postParams = "src_lang=" + sourceLangCode + "&target_lang=" + targetLangCode + "&query=" + text;

			String responseBody = post(apiURL, requestHeaders, postParams, "GET");

			JsonObject jsonObj = (JsonObject) JsonParser.parseString(responseBody);

			result.setTranslatedText(jsonObj.get("translated_text").getAsString());
			result.setTransOption(option);
		} catch (Exception e) {
			result.setError(true);
			result.setErrorMessage(e.getMessage());
		}
		return result;
	}
}
