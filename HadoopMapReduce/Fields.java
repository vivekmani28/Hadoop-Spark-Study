import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Fields {
	private HashMap<String, FieldValues> map;
	
	public Fields(){
		map = new HashMap<>();
	}
	
	public void put(String field, int index, String value, String type){
		map.put(field, new FieldValues(index, value, type));
	}
	
	public void remove(String field){
		map.remove(field);
	}
	
	public FieldValues getFieldValues(String field){
		return map.get(field);
	}
	
	public void populateFromFile(String filename){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			while((line = reader.readLine()) != null){
				String[] fields = line.trim().split(",");
				if(fields.length == 2)
					put(fields[1], Integer.parseInt(fields[0]), null, "distinct");
				else
					put(fields[1], Integer.parseInt(fields[0]), null, fields[2]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void print(){
		System.out.println();
		int i = 0;
		for(String key: map.keySet()){
			System.out.println(++i + ") " + key + " => " + map.get(key));
		}
	}
	
}
class FieldValues{
	public int index;
	public String value;
	public FieldType type;
	
	public FieldValues(int index, String value, FieldType type) {
		this.index = index;
		this.value = value;
		this.type = type;
	}
	public FieldValues(int index, String value, String type) {
		this.index = index;
		this.value = value;
		this.type = getFieldType(type);
	}
	private FieldType getFieldType(String type){
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
		return this.index + " " + this.value + " " + this.type;
	}
}