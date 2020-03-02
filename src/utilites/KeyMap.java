package utilites;

import java.util.HashMap;
import java.util.Map;

public class KeyMap extends HashMap<Integer, Boolean> {
	private Boolean startValue = false;
	public KeyMap() {
		this.put(87, startValue);
		this.put(65, startValue);
		this.put(68, startValue);
		this.put(32, startValue);
	}
	
	public Boolean oneIsTrue() {
		Integer boolCheck = 0;
		for (Map.Entry<Integer, Boolean> entry : this.entrySet()) {
			if (entry.getValue()) {
				boolCheck ++;
			}
			
		}
		if (boolCheck > 0) {
			return true;
		}
		else {
			return false;
		}
	}
}
