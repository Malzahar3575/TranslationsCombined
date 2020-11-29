import java.util.ArrayList;

public class TransCombineResult {
	ArrayList<TransInfo> TransInfoList = new ArrayList<TransInfo>();

	public void addTransInfo(TransInfo info) {
		TransInfoList.add(info);
	}

	public ArrayList<TransInfo> getTransInfoList() {
		return TransInfoList;
	}
	

}
