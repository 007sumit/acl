package util;

import java.util.HashMap;
import java.util.Map;


public enum ActionType {
	READ(0),
	WRITE(1),
	DELETE(2);
	
	private final Integer value;

	// Attribute name to enum value
    private static final Map<Integer, ActionType> valueToType =
        new HashMap<Integer, ActionType>();
    
    // Initialize map
    static {
        for (ActionType type : values()) {
        	valueToType.put(type.getValue(), type);
        }
    }
    
	ActionType(int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	/**
	 * Get ActionType from provided value
	 * @param value		value corresponding to the ActionType
	 * @return			ActionType Enum
	 */
	public static ActionType fromValue(Integer value) {
        return valueToType.get(value);
    }
}
