package translation;

public abstract class Translator {

	protected String getLanguageCode(Language language) {
		if (language == Language.KOR)
			return "ko";
		else if (language == Language.ENG)
			return "en";
		else
			return null;
	}

	public abstract TransResult Translate(TransOption option);
}
