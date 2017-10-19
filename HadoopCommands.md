## Format the HDFS
hdfs namenode -format

## Start the DFS service
$HADOOP_HOME/sbin/start-dfs.sh

### Browse the HDFS in your Web Browser
### Change <namenode_public_dns> => ec2-52-90-13-204.compute-1.amazonaws.com
<namenode_public_dns>:50070

### Start YARN on NameNode
$HADOOP_HOME/sbin/start-yarn.sh
$HADOOP_HOME/sbin/mr-jobhistory-daemon.sh start historyserver


### Run JPS on NameNode
jps
#####Output:
	4454 ResourceManager
 	3490 NameNode
	3709 SecondaryNameNode
	4776 Jps
	4738 JobHistoryServer


##Run JPS on DataNodes
jps
#####Output:
	322 DataNode
	812 Jps
	659 NodeManager
