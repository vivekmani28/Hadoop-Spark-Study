import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class ReducerForACS2012 extends Reducer<Text, IntWritable, Text, IntWritable> {
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
      
      keyText.set(getCitizenshipStatus(key.toString()));
      context.write(keyText, result);
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
}
