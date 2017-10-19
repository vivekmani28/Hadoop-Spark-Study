#Install and Configure Hadoop and Java

### Install JDK 7
sudo add-apt-repository ppa:openjdk-r/ppa 
sudo apt-get update
sudo apt-get install openjdk-7-jdk
java -version

### Download Hadoop 2.8.1
wget http://apache.mirrors.tds.net/hadoop/common/hadoop-2.8.1/hadoop-2.8.1.tar.gz -P ~/Downloads/Hadoop
sudo tar zxvf ~/Downloads/Hadoop/hadoop-2.8.1.tar.gz -C /usr/local
sudo mv /usr/local/hadoop-* /usr/local/hadoop

### put this in ~/.profile
'''
# Environment variable for java and hadoop
export JAVA_HOME=/usr
export PATH=$PATH:$JAVA_HOME/bin
export HADOOP_HOME=/usr/local/hadoop
export PATH=$PATH:$HADOOP_HOME/bin
export HADOOP_CONF_DIR=$HADOOP_HOME/etc/hadoop
'''
### run .profile
. ~/.profile

### Change ownership to ubuntu user. 
sudo chown ubuntu -R $HADOOP_CONF_DIR

#NameNode and DataNode

#### <namenode_public_dns> => ec2-52-90-13-204.compute-1.amazonaws.com
#### add this to #$HADOOP_CONF_DIR/hadoop-env.sh
export JAVA_HOME=/usr

#### add this to $HADOOP_CONF_DIR/core-site.xml
'''
<configuration>
  <property>
    <name>fs.defaultFS</name>
    <value>hdfs://<namenode_public_dns>:9000</value>
  </property>
</configuration>
'''
#NameNode

### Add this to $HADOOP_CONF_DIR/hdfs-site.xml
'''
<configuration>
  <property>
    <name>dfs.replication</name>
    <value>3</value>
  </property>
  <property>
    <name>dfs.datanode.data.dir</name>
    <value>file:///usr/local/hadoop/hadoop_data/hdfs/namenode</value>
  </property>
</configuration>
'''
###ADD all nodes to host
sudo chown ubuntu /etc/hosts

## etc/hosts 
### private_ip dns
#### Change as needed
'''
172.31.92.156 ec2-52-90-13-204.compute-1.amazonaws.com
172.31.94.208 ec2-52-87-117-48.compute-1.amazonaws.com
172.31.91.231 ec2-54-152-22-210.compute-1.amazonaws.com
172.31.82.206 ec2-52-90-33-107.compute-1.amazonaws.com
'''

sudo chown root /etc/hosts

## Create Hadoop NameNode
sudo mkdir -p $HADOOP_HOME/hadoop_data/hdfs/namenode

## Configure Master and slaves
echo "namenode" | cat >> $HADOOP_CONF_DIR/masters
sudo rm $HADOOP_CONF_DIR/slaves

echo "datanode1" | cat >> $HADOOP_CONF_DIR/slaves
echo "datanode2" | cat >> $HADOOP_CONF_DIR/slaves
echo "datanode3" | cat >> $HADOOP_CONF_DIR/slaves

## Change the ownership
sudo chown -R ubuntu $HADOOP_HOME

#DataNode

## Add this to $HADOOP_CONF_DIR/hdfs-site.xml
'''
<configuration>
  <property>
    <name>dfs.replication</name>
    <value>3</value>
  </property>
  <property>
    <name>dfs.datanode.data.dir</name>
    <value>file:///usr/local/hadoop/hadoop_data/hdfs/datanode</value>
  </property>
</configuration>
'''
## Create Hadoop datanode
sudo mkdir -p $HADOOP_HOME/hadoop_data/hdfs/datanode

## Change ownership
sudo chown -R ubuntu $HADOOP_HOME