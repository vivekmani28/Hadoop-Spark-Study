import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class ReducerForACS2012 extends Reducer<Text, IntWritable, Text, IntWritable> {
	private static FieldsMaping fieldDic;
    private IntWritable result = new IntWritable();
    private Text keyText = new Text();
    public void reduce(Text key, Iterable<IntWritable> values, 
                       Context context
                       ) throws IOException, InterruptedException {
      int sum = 0;
      for (IntWritable val : values) {
        sum += val.get();
      }
      result.set(sum);
      
      keyText.set(getValue(key.toString()));
      context.write(keyText, result);
      // context.write(key, result);
    }

	private String getCitizenshipStatus(String citField) {
		switch(citField){
		case "1":
			return "Born in the U.S.";
		case "2":
			return ".Born in Puerto Rico, Guam, the U.S. Virgin Islands,.or the Northern Marianas";
		case "3":
			return "Born abroad of American parent(s)";
		case "4":
			return "U.S. citizen by naturalization";
		case "5":
			return "Not a citizen of the U.S.";
		default:
			return "Unknown-" + citField;
				
		}
	}
	
	private String getValue(String key){
		String[] name_value = key.split("_");
		if(name_value.length == 2 && fieldDic != null){
			return fieldDic.getRepValue(name_value[0], name_value[1]);
		}
		return "Unknown-" + key;
	}
	
	public static void setFieldsMap(FieldsMaping flds){
		fieldDic = flds;
	}
}
