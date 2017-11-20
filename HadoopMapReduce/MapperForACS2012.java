import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MapperForACS2012 extends Mapper<Object, Text, IntWritable, IntWritable> {
	private static int citizenFieldNo = 8;
    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
    	String[] fields = value.toString().split(",");
    	try{
    	Integer citField = Integer.parseInt(fields[citizenFieldNo]); 
    	context.write(new IntWritable(citField), new IntWritable(1));
    	}catch(NumberFormatException e){
    		context.write(new IntWritable(0), new IntWritable(1));
    	}
    }
}
