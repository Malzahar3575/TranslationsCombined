import translation.TransResult;

public class TransInfo {
	private TransResult transResult;
	private long usedCount;

	public TransResult getTransResult() {
		return transResult;
	}

	public void setTransResult(TransResult transResult) {
		this.transResult = transResult;
	}

	public long getUsedCount() {
		return usedCount;
	}

	public void setUsedCount(long usedCount) {
		this.usedCount = usedCount;
	}
}
