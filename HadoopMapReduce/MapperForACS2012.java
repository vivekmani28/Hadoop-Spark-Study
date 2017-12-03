import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MapperForACS2012 extends Mapper<Object, Text, Text, IntWritable> {
	private static String fieldName = "_";
	private static int fieldNo = 0;
    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
    	String[] fields = value.toString().split(",");
    	Text outKey = new Text();
    	outKey.set(fieldName + "_" + fields[fieldNo]);
    	context.write(outKey, new IntWritable(1));
    }
    
    public static void setField(String fldName, FieldsMaping field_dic){
    	if(field_dic != null && field_dic.isKeyPresent(fldName)){
        	fieldName = fldName;
    		fieldNo = field_dic.getFieldValues(fldName).index; 
    	}
    }
}
