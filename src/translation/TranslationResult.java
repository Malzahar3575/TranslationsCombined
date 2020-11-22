package translation;

import java.time.LocalTime;

public class TranslationResult {
	private TranslationInfo translationInfo;
	private String resultText;
	private LocalTime time;

	public void setResultText(String text) {
		resultText = text;
	}

	public String getResultText() {
		return resultText;
	}

	public void setTranslationInfo(TranslationInfo info) {
		translationInfo = info;
	}

	public TranslationInfo getTranslationInfo() {
		return translationInfo;
	}
	
	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	public LocalTime getTime() {
		return time;
	}
}
