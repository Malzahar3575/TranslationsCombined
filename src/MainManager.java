import java.util.ArrayList;
import translation.*;

public class MainManager {

	private ArrayList<Translator> TranslatorArray = new ArrayList<Translator>();
	private ArrayList<TransCombineResult> TransHistory = new ArrayList<TransCombineResult>();
	private GoogleCounter googleCounter = new GoogleCounter();
	
	public MainManager() {
		InitializeTranslators();
	}

	private void InitializeTranslators() {
		TranslatorArray.add(new GoogleTranslator());
		TranslatorArray.add(new NaverTranslator());
		TranslatorArray.add(new KakaoTranslator());
	}

	private void addTransCombineResult(TransCombineResult result) {
		TransHistory.add(result);
	}

	public TransCombineResult Translate(TransOption option) {
		TransCombineResult combineResult = new TransCombineResult();
		for(Translator transtor : TranslatorArray) {
			TransResult transResult = transtor.Translate(option);
			long count = 0;
			try {
				count = googleCounter.Count("\"" + transResult.getTranslatedText() + "\"");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			TransInfo info = new TransInfo();
			info.setTransResult(transResult);
			info.setUsedCount(count);
			combineResult.addTransInfo(info);
			
		}
		this.addTransCombineResult(combineResult);
		return combineResult;
	}

}
