package translation;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class KakaoTranslator extends Translator {
	@Override
	public String getLanguageCode(Language language) {
		if (language == Language.KOR)
			return "kr";
		else if (language == Language.ENG)
			return "en";
		else
			return null;
	}

	@Override
	public TransResult Translate(TransOption option) {
		String clientId = "KakaoAK 119c07ed94ab8daa6f048e31a557eb13";// ���ø����̼� Ŭ���̾�Ʈ ���̵�";
		String clientSecret = "5UlT3V9wQr";// ���ø����̼� Ŭ���̾�Ʈ ��ũ����";

		String apiURL = "https://dapi.kakao.com/v2/translation/translate?";

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("Authorization", clientId);
		requestHeaders.put("Content-Type", "application/x-www-form-urlencoded");

		String responseBody = post(apiURL, requestHeaders, option);

		this.LogDebug(responseBody);

		TransResult result = new TransResult();

		JsonObject jsonObj = (JsonObject) JsonParser.parseString(responseBody);

		result.setTranslatorName("Kakao");
		result.setTranslatedText(jsonObj.get("translated_text").getAsString());
		result.setTransOption(option);
		return result;
	}

	private String post(String apiUrl, Map<String, String> requestHeaders, TransOption option) {
		HttpURLConnection con = connect(apiUrl);
		String text;
		try {
			text = URLEncoder.encode(option.getText(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("���ڵ� ����", e);
		}
		String sourceLangCode = getLanguageCode(option.getSourceLanguage());
		String targetLangCode = getLanguageCode(option.getTargetLanguage());

		String postParams = "src_lang=" + sourceLangCode + "&target_lang=" + targetLangCode + "&query=" + text;
		try {
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			con.setDoOutput(true);
			try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
				wr.write(postParams.getBytes());
				wr.flush();
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // ���� ����
				return readBody(con.getInputStream());
			} else { // ���� ����
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API ��û�� ���� ����", e);
		} finally {
			con.disconnect();
		}
	}

	private HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL�� �߸��Ǿ����ϴ�. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("������ �����߽��ϴ�. : " + apiUrl, e);
		}
	}

	private String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API ������ �дµ� �����߽��ϴ�.", e);
		}
	}

}
