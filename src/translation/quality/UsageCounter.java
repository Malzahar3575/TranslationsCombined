package translation.quality;

import translation.TransResult;

public abstract class UsageCounter {
	public abstract long Measure(TransResult transResult);
}
