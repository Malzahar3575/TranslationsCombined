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
	}

	private void addTransCombineResult(TransCombineResult result) {
		TransHistory.add(result);
		
		for(TransInfo info : result.getTransInfoList()) {
			System.out.format("Translator Name: %s / text: %s / count: %,8d%n", info.getTransResult().getTranslatorName(),
								info.getTransResult().getResultText(),
								info.getUsedCount());
		}
		
	}

	public void Translate(TransOption option) {
		
		TransCombineResult combineResult = new TransCombineResult();
		for(Translator transtor : TranslatorArray) {
			TransResult transResult = transtor.Translate(option);
			long count = 0;
			try {
				count = googleCounter.Count("\"" + transResult.getResultText() + "\"");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			TransInfo info = new TransInfo();
			info.setTransResult(transResult);
			info.setUsedCount(count);
			combineResult.addTransInfo(info);
			
		}
		this.addTransCombineResult(combineResult);
	}

}
