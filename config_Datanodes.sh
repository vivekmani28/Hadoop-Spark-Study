#!/bin/bash -e

#Copying new config to all nodes
host_ip=$2
host_Name=$1
node_Name=$3

echo ${node_Name} >> /usr/local/hadoop/etc/hadoop/slaves
while read name
do
        scp -o StrictHostKeyChecking=no  ~/.ssh/config ${name}:~/.ssh/config
done < /usr/local/hadoop/etc/hadoop/slaves

#Adding new datanode to etc hosts
sudo -- sh -c "printf \"$host_ip $host_Name\n\" >> /etc/hosts"

