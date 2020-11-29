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
import com.google.gson.*;

public class NaverTranslator extends Translator {

	@Override
	public TransResult Translate(TransOption option) {
		String clientId = "7b54Eu06VoUSVpRTFiF7";// ���ø����̼� Ŭ���̾�Ʈ ���̵�";
		String clientSecret = "5UlT3V9wQr";// ���ø����̼� Ŭ���̾�Ʈ ��ũ����";

		String apiURL = "https://openapi.naver.com/v1/papago/n2mt";

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);

		String responseBody = post(apiURL, requestHeaders, option);

		this.LogDebug(responseBody);

		TransResult result = new TransResult();

		JsonObject jsonObj = (JsonObject) JsonParser.parseString(responseBody);
		
		result.setTranslatorName("Naver");
		result.setResultText(
				jsonObj.getAsJsonObject("message").getAsJsonObject("result").get("translatedText").getAsString());
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
		String postParams = "source=" + option.getSourceLanguage() + "&target=" + option.getTargetLanguage() + "&text="
				+ text;
		try {
			con.setRequestMethod("POST");
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
