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
      
      context.write(key, result);
    }

	public static void setFieldsMap(FieldsMaping flds){
		fieldDic = flds;
	}
}
