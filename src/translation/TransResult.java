package translation;

public class TransResult {
	private String translatorName;
	private TransOption transOption;
	private String translatedText;
	private boolean isError;
	private String ErrorMessage;
	
	public void setTransOption(TransOption info) {
		transOption = info;
	}

	public TransOption getTransOption() {
		return transOption;
	}
	
	public String getTranslatorName() {
		return translatorName;
	}

	public void setTranslatorName(String translatorName) {
		this.translatorName = translatorName;
	}

	public String getTranslatedText() {
		return translatedText;
	}

	public void setTranslatedText(String translatedText) {
		this.translatedText = translatedText;
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public String getErrorMessage() {
		return ErrorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
}
