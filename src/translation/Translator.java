package translation;

import java.io.IOException;

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

	public abstract TranslationResult Translate(TranslationInfo translationInfo);
}
