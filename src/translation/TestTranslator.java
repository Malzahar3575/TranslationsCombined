package translation;

public class TestTranslator extends Translator {

	@Override
	public TranslationResult Translate(TranslationInfo translationInfo) {
		TranslationResult result = new TranslationResult();
		result.setResultText("testResult");
		
		
		return result;
	}

}
