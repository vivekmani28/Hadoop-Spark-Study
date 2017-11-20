## Map Reduce Java Programs

1. Add All the Jar file given below in the Java classpath
```
	C:/hadoop/share/hadoop/common/*.jar
	C:/hadoop/share/hadoop/common/lib/*.jar
	C:/hadoop/share/hadoop/mapreduce/*.jar
	C:/hadoop/share/hadoop/mapreduce/lib/*.jar
	C:/hadoop/share/hadoop/yarn/*.jar
	C:/hadoop/share/hadoop/yarn/lib/*.jar
```
2. Create a jar file with all Java file in it
3. Put the input file in hdfs
	``` $hdfs dfs -put <local_file_path> <hdfs_file_path> ```
4. Run the map reduce Java program in hdfs
	``` $hadoop jar <Jar file name> <Class name> <Input file in HDFS> <Output file in HDFS> ```
