package translation;

public class TransOption {
	private Language sourceLanguage;
	private Language targetLanguage;
	private String text;

	public Language getSourceLanguage() {
		return sourceLanguage;
	}

	public void setSourceLanguage(Language language) {
		sourceLanguage = language;
	}

	public Language getTargetLanguage() {
		return targetLanguage;
	}

	public void setTargetLanguage(Language language) {
		targetLanguage = language;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
