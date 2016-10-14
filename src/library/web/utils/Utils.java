package library.web.utils;

import java.util.ArrayList;
import java.util.List;

public class Utils {

	public static <T> List<T> getSelected(List<Boolean> selFlag,
			List<T> objects) {
		List<T> selected = new ArrayList<T>();
		int elemCnt = 0;
		for (int i = 0; i < selFlag.size(); i++) {
			if (!selFlag.get(i).booleanValue()) {
				selected.add(objects.get(elemCnt));
			} else {
				elemCnt++;
			}
		}
		return selected;
	}
}
