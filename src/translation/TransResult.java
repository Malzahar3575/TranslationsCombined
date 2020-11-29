package translation;

import java.time.LocalTime;

public class TransResult {
	private String translatorName;
	private TransOption transOption;
	private String translatedText;
	private LocalTime time;

	

	public void setTransOption(TransOption info) {
		transOption = info;
	}

	public TransOption getTransOption() {
		return transOption;
	}
	
	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	public LocalTime getTime() {
		return time;
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
}
