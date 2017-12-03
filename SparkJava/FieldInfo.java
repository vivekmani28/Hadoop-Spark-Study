import java.io.Serializable;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class FieldInfo implements Serializable{
	public int index;
	public String key;
	public HashMap<String, String> value_map;
	public FieldType type;
	
	public FieldInfo(int index, String key, String value, FieldType type) {
		this.index = index;
		this.key = key;
		this.value_map = new HashMap<String, String>();
		this.type = type;
		populateHashMap(value);
	}
	public FieldInfo(int index, String key, String value, String type) {
		this(index, key, value, getFieldType(type));
	}
	public String getValue(String value){
		
		if(this.value_map != null && this.value_map.containsKey(value))
			return this.value_map.get(value);
		return key + "_" + value +"_Unknown";
		
	}
	private void populateHashMap(String key_value){
		if(key_value == null)
			return;
		Pattern pattern = Pattern.compile("([^\\[,=:]+)[=:]([^,\\]]+)[,\\]]*");
		Matcher matcher = pattern.matcher(key_value);
		while(matcher.find()){
			value_map.put(matcher.group(1).trim(), matcher.group(2));
		}
	}
	private static FieldType getFieldType(String type){
		switch(type){
		case "continuous":
			return FieldType.CONTINUOUS;
		case "time":
			return FieldType.TIME;
		case "income":
			return FieldType.INCOME;
		case "years":
			return FieldType.YEARS;
		case "weight":
			return FieldType.WEIGHT;
		default:
			return FieldType.DISTINCT;
				
		}
	}
	public String toString(){
		return this.index + " " + this.key + " " + this.type;
	}
}