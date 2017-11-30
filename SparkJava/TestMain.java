import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFlatMapFunction;

import scala.Tuple2;

public class TestMain {
	private static String[] fieldNames = {"CIT"};
	private static String path = "E:/DIC/SparkJava/SparkJavaTest/src/main/resources/";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    HashMap<String, FieldInfo> fieldMaps = FieldsMaping.populateFromFile(path + "Input/acs12_1yr_p.csv");
		
		SparkConf conf = new SparkConf();
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> lines = sc.textFile(path + "Input/ss12pusa.csv");
		
		JavaPairRDD<String, Integer> key_val = lines.flatMapToPair(new PairFlatMapFunction<String, String, Integer>() {

			@Override
			public Iterator<Tuple2<String, Integer>> call(String arg0) throws Exception {
				List<Tuple2<String, Integer>> key_one = new ArrayList<>();
				String[] vals = arg0.split(",");
				for(String fldName: fieldNames){
					int fieldIndex = fieldMaps.get(fldName).index; 
					key_one.add(new Tuple2<String, Integer>(fldName + "_" + vals[fieldIndex], 1));
				}
				return key_one.iterator();
			}
		});
		
		JavaPairRDD<String, Integer> answer = key_val.reduceByKey(new Function2<Integer, Integer, Integer>() {
			
			@Override
			public Integer call(Integer arg0, Integer arg1) throws Exception {
				return arg0 + arg1;
			}
		});
		
		answer.saveAsTextFile(path + "Output/out.txt");
	}

}
