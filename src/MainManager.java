import java.util.ArrayList;
import translation.*;

public class MainManager {
	
	private TranslatorCollection Translators = new TranslatorCollection();

	public MainManager() {
		InitializeTranslators();
	}
	
	private void InitializeTranslators() {		
		Translators.addTranslator(new TestTranslator());
	}
	
	public void Translate(TranslationInfo info)
	{
		
	}
	
}
