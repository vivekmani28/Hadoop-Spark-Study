import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FieldsMaping {
	private HashMap<String, FieldInfo> map;
	
	public FieldsMaping(){
		map = new HashMap<>();
	}
	
	public void put(String fieldName, int index, String value, String type){
		// value = "[1=Born in the U.S., 2= Born in Puerto Rico, Guam, the U.S. Virgin Islands,.or the Northern Marianas, 3= Born abroad of American parent(s), 4: U.S. citizen by naturalization, 5: Not a citizen of the U.S.]";
		map.put(fieldName, new FieldInfo(index, fieldName, value, type));
	}
	
	public void remove(String fieldName){
		map.remove(fieldName);
	}
	public boolean isKeyPresent(String fieldName){
		return map.containsKey(fieldName);
	}
	public FieldInfo getFieldValues(String fieldName){
		return map.get(fieldName);
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
	public String getRepValue(String fieldName, String value){
		return map.get(fieldName).getValue(value);
	}
	public void print(){
		System.out.println();
		int i = 0;
		for(String key: map.keySet()){
			System.out.println(++i + ") " + key + " => " + map.get(key));
		}
	}
	
}