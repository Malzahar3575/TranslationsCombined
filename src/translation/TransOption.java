package translation;

public class TransOption {
	private String sourceLanguage;
	private String targetLanguage;
	private String text;

	public String getSourceLanguage() {
		return sourceLanguage;
	}

	public void setSourceLanguage(String language) {
		sourceLanguage = language;
	}

	public String getTargetLanguage() {
		return targetLanguage;
	}

	public void setTargetLanguage(String language) {
		targetLanguage = language;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
