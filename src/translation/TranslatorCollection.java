package translation;

import java.util.ArrayList;

public class TranslatorCollection {
	private ArrayList<Translator> TranslatorArray = new ArrayList<Translator>();

	public void addTranslator(Translator translator) {
		TranslatorArray.add(translator);
	}

	public TranslationResultCollection Translate(TranslationInfo info) {
		TranslationResultCollection resultCollection = new TranslationResultCollection();
		for (Translator translator : TranslatorArray) {
			TranslationResult result = translator.Translate(info);
			resultCollection.addResult(result);			
		}
		return resultCollection;
	}
}
