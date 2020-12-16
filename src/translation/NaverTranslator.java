package translation;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.*;

public class NaverTranslator extends Translator {

	@Override
	public TransResult Translate(TransOption option) {
		String clientId = "7b54Eu06VoUSVpRTFiF7";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "5UlT3V9wQr";// 애플리케이션 클라이언트 시크릿값";

		String apiURL = "https://openapi.naver.com/v1/papago/n2mt";

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);

		String text;
		try {
			text = URLEncoder.encode(option.getText(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("인코딩 실패", e);
		}
		String sourceLangCode = getLanguageCode(option.getSourceLanguage());
		String targetLangCode = getLanguageCode(option.getTargetLanguage());
		
		String postParams = "source=" + sourceLangCode + "&target=" + targetLangCode + "&text=" + text;
		
		String responseBody = post(apiURL, requestHeaders, postParams, "POST");

		TransResult result = new TransResult();

		JsonObject jsonObj = (JsonObject) JsonParser.parseString(responseBody);
		
		result.setTranslatorName("Naver");
		result.setTranslatedText(
				jsonObj.getAsJsonObject("message").getAsJsonObject("result").get("translatedText").getAsString());
		result.setTransOption(option);
		return result;
	}
}
