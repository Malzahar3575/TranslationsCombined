package translation.quality;

import translation.TransResult;

public class TransQualityManager {
	private UsageCounter usageCounter = new GoogleCounter();
	
	public TransQuality Measure(TransResult transResult) {
		TransQuality quality = new TransQuality();
		
		try {
			long usedCount = usageCounter.Measure(transResult);
			quality.setUsedCount(usedCount); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quality;
	}
}
