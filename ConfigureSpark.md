## Spark Set-up

1. Launch 3(1 Master + 2 Slaves) EC2 instances and obtain the hostnames.

2. Generate AWS key pair and download the *.pem(private key) file, to be used as password for logging in to the nodes.

3. Log into all the nodes and install the below,

4. Install latest Java version
`sudo apt-get -y install openjdk-8-jdk-headless`

5. Install Spark

	`wget https://d3kbcqa49mib13.cloudfront.net/spark-2.2.0-bin-hadoop2.7.tgz
	tar xvzf spark-2.2.0-bin-hadoop2.7.tgz -C /opt
	ln -fs spark-2.2.0-bin-hadoop2.7 /opt/spark`

6. Modify the ~/.bashrc file to include SPARK_HOME environment variable by adding the below lines

	`export SPARK_HOME=/opt/spark
	PATH=$PATH:$SPARK_HOME/bin
	export PATH`

	Run `spark-submit --version` to verify the installation

		Welcome to
			  ____              __
			 / __/__  ___ _____/ /__
			_\ \/ _ \/ _ `/ __/  '_/
		   /___/ .__/\_,_/_/ /_/\_\   version 2.2.0
			  /_/

		Using Scala version 2.11.8, OpenJDK 64-Bit Server VM, 1.8.0_131
		Branch
		Compiled by user jenkins on 2017-06-30T22:58:04Z
		Revision
		Url
		Type --help for more information.

7. In the master machine execute the below command to bring up the Master node,

	`/opt/spark/sbin/start-master.sh`

8. Execute the below in the slave machines to bring up the slave nodes. The master nodes address needs to be properly specified in the command

	`/opt/spark/sbin/start-slave.sh spark://ip-172-31-32-227.us-east-2.compute.internal:7077`

9. Now we have the master and slave nodes running and the logs for the same can be obtained in the `/opt/spark/logs/` path of the respective machines. 
