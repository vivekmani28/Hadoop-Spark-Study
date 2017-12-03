import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FieldsMaping {
	public static HashMap<String, FieldInfo> populateFromFile(String filename){
		HashMap<String, FieldInfo> map = new HashMap<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			while((line = reader.readLine()) != null){
				String[] fields = line.trim().split(",");
				if(fields.length == 2)
					map.put(fields[1], new FieldInfo(Integer.parseInt(fields[0]), fields[1], null, "distinct"));
				else
					map.put(fields[1], new FieldInfo(Integer.parseInt(fields[0]), fields[1], null, fields[2]));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return map;
	}
	
}