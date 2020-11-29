package translation;

public abstract class Translator {
	TranslationLoggable logger;

	protected void LogDebug(String log) {
		if (logger != null) {
			logger.Debug(log);
		}
	}

	protected void LogInfo(String log) {
		if (logger != null) {
			logger.Info(log);
		}
	}

	protected void LogError(String log) {
		if (logger != null) {
			logger.Error(log);
		}
	}

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
