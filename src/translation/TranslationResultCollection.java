package translation;

import java.util.ArrayList;

public class TranslationResultCollection {
	private ArrayList<TranslationResult> ResultArray = new ArrayList<TranslationResult>();
	
	public void addResult(TranslationResult result)
	{
		ResultArray.add(result);
	}
}
