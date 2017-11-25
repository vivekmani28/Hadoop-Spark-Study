import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class MainACS2012 extends Configured implements Tool{
	//extends Configured implements Tool
	public static void main(String[] args) throws Exception {

		int res = ToolRunner.run(new Configuration(), new MainACS2012(), args);
	    
	    System.exit(res);
	  }

	@Override
	public int run(String[] arg0) throws Exception {
	    if (arg0.length != 3) {
		      System.err.println("Usage: MainACS2012 <in> <out>");
		      System.exit(2);
		}

	    FieldsMaping field = new FieldsMaping();
	    field.populateFromFile(arg0[2]);
	    
	    MapperForACS2012.setField("CIT", field);
	    ReducerForACS2012.setFieldsMap(field);
	    
		Configuration conf = this.getConf();
		Job job = new Job(conf, "ACS2012");
	
	    job.setJarByClass(MainACS2012.class);
	    
	    job.setMapperClass(MapperForACS2012.class);
	    //job.setCombinerClass(ReducerForACS2012.class);
	    job.setReducerClass(ReducerForACS2012.class);
	    
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(IntWritable.class);
	    
	    FileInputFormat.addInputPath(job, new Path(arg0[0]));
	    FileOutputFormat.setOutputPath(job, new Path(arg0[1]));
	    
		return job.waitForCompletion(true) ? 0 : 1;
	}
	
}
