## Spark Java Programs


1. Create a jar file with all Java file and spark core jar
3. Put the input file in hdfs
	``` $hdfs dfs -put <local_file_path> <hdfs_file_path> ```
4. Run the map reduce Java program in hdfs
	``` $spark-submit --class <MainClass> --master <master ip> <jar>```
