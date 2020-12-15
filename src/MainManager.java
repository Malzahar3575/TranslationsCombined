import java.util.ArrayList;
import java.util.List;

import translation.*;
import translation.quality.*;

public class MainManager {

	private List<Translator> TranslatorArray = new ArrayList<Translator>();
	private TransQualityManager QualityManager = new TransQualityManager();

	public MainManager() {
		InitializeTranslators();
	}

	private void InitializeTranslators() {
		TranslatorArray.add(new GoogleTranslator());
		TranslatorArray.add(new NaverTranslator());
		TranslatorArray.add(new KakaoTranslator());
	}

	public TransCombineResult Translate(TransOption option) {
		
		TransCombineResult combineResult = new TransCombineResult();
		
		if (option.getText() == null || option.getText().isBlank()) {
			return combineResult;
		}
		
		for (Translator transtor : TranslatorArray) {
			TransResult transResult = transtor.Translate(option);
			TransQuality transQuality = QualityManager.Measure(transResult);
			
			TransInfo info = new TransInfo();
			info.setTransResult(transResult);
			info.setTransQuality(transQuality);
			combineResult.addTransInfo(info);
		}
		
		return combineResult;
	}

}
