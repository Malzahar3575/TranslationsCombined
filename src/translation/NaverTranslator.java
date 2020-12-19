package translation;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.*;

public class NaverTranslator extends Translator {

	@Override
	public TransResult Translate(TransOption option) {
		TransResult result = new TransResult();
		result.setTranslatorName("Naver");
		try {
			String clientId = "7b54Eu06VoUSVpRTFiF7";// ���ø����̼� Ŭ���̾�Ʈ ���̵�";
			String clientSecret = "5UlT3V9wQr";// ���ø����̼� Ŭ���̾�Ʈ ��ũ����";

			String apiURL = "https://openapi.naver.com/v1/papago/n2mt";

			Map<String, String> requestHeaders = new HashMap<>();
			requestHeaders.put("X-Naver-Client-Id", clientId);
			requestHeaders.put("X-Naver-Client-Secret", clientSecret);

			String text;

			text = URLEncoder.encode(option.getText(), "UTF-8");

			String sourceLangCode = getLanguageCode(option.getSourceLanguage());
			String targetLangCode = getLanguageCode(option.getTargetLanguage());

			String postParams = "source=" + sourceLangCode + "&target=" + targetLangCode + "&text=" + text;

			String responseBody = post(apiURL, requestHeaders, postParams, "POST");

			

			JsonObject jsonObj = (JsonObject) JsonParser.parseString(responseBody);
			
			result.setTranslatedText(
					jsonObj.getAsJsonObject("message").getAsJsonObject("result").get("translatedText").getAsString());
			result.setTransOption(option);
		} catch (Exception e) {
			result.setError(true);
			result.setErrorMessage(e.getMessage());
		}
		return result;
	}
}
