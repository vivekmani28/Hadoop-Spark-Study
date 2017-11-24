import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MapperForACS2012 extends Mapper<Object, Text, Text, IntWritable> {
	public static int citizenFieldNo = 8;
    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
    	String[] fields = value.toString().split(",");
    	Text outKey = new Text();
    	outKey.set(fields[citizenFieldNo]);
    	context.write(outKey, new IntWritable(1));
    }
}
