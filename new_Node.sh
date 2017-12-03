#!/bin/bash -e

node_Name=$1
host_Name=$2
host_ip=$3

#Changing permission for pem file
chmod 600 /home/ubuntu/.ssh/HadoopSpark.pem

#Adding New node to local ssh config file
printf "Host namenode \nHostname ec2-13-58-233-170.us-east-2.compute.amazonaws.com \nUser ubuntu \nIdentityFile ~/.ssh/HadoopSpark.pem\n\n" > /home/ubuntu/.ssh/config
printf "Host $node_Name \nHostname $host_Name \nUser ubuntu \nIdentityFile ~/.ssh/HadoopSpark.pem\n\n" > /home/ubuntu/.ssh/config_tmp

#Adding the new node to namenode config
cat /home/ubuntu/.ssh/config_tmp | ssh -o StrictHostKeychecking=no namenode "cat >> ~/.ssh/config"

#Copying namenode pub keys to new node
scp namenode:~/.ssh/sshkey_rsa.pub /tmp/key_temp.$$
cat /tmp/key_temp.$$ >> ~/.ssh/authorized_keys


#Copying new config to all other datanodes and adding the new node to namenode etc hosts
ssh -o StrictHostKeychecking=no namenode ~/scripts/config_Datanodes.sh ${host_Name} ${host_ip} ${node_Name}

#Installing Java, Hadoop
sudo apt-get -y install openjdk-8-jdk-headless

wget http://apache.mirrors.tds.net/hadoop/common/hadoop-2.8.1/hadoop-2.8.1.tar.gz -P ~/Downloads/Hadoop
sudo tar zxvf ~/Downloads/Hadoop/hadoop-2.8.1.tar.gz -C /usr/local
sudo mv /usr/local/hadoop-* /usr/local/hadoop
sudo rm -f ~/Downloads/Hadoop/hadoop-2.8.1.tar.gz

#Changing .profile

printf "export JAVA_HOME=/usr \nexport PATH=\$PATH:\$JAVA_HOME/bin \nexport HADOOP_HOME=/usr/local/hadoop \nexport PATH=\$PATH:\$HADOOP_HOME/bin \nexport HADOOP_CONF_DIR=\$HADOOP_HOME/etc/hadoop" >> ~/.profile

. ~/.profile

sudo chown ubuntu -R $HADOOP_CONF_DIR
sudo chown -R ubuntu $HADOOP_HOME

printf "export JAVA_HOME=/usr\n" >> $HADOOP_CONF_DIR/hadoop-env.sh

scp -o StrictHostKeychecking=no namenode:/usr/local/hadoop/etc/hadoop/core-site.xml /usr/local/hadoop/etc/hadoop/
scp -o StrictHostKeychecking=no datanode1:/usr/local/hadoop/etc/hadoop/hdfs-site.xml /usr/local/hadoop/etc/hadoop/hdfs-site.xml
scp -o StrictHostKeychecking=no datanode1:/usr/local/hadoop/etc/hadoop/yarn-site.xml /usr/local/hadoop/etc/hadoop/yarn-site.xml

mkdir -p $HADOOP_HOME/hadoop_data/hdfs/datanode

#Start Services in newly added node. Do not stop hdfs or yarn
$HADOOP_HOME/sbin/hadoop-daemon.sh start datanode
$HADOOP_HOME/sbin/yarn-daemon.sh start nodemanager
