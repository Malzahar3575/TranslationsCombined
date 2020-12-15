import java.util.ArrayList;
import java.util.List;

public class TransCombineResult {
	List<TransInfo> TransInfoList = new ArrayList<TransInfo>();

	public void addTransInfo(TransInfo info) {
		TransInfoList.add(info);
	}

	public List<TransInfo> getTransInfoList() {
		return TransInfoList;
	}
	

}
