# Adding SSH hostnames
#### Copy this to ~/.ssh/config
#### Hostname Change as needed
```
Host namenode
Hostname ec2-52-90-13-204.compute-1.amazonaws.com
User ubuntu
IdentityFile ~/.ssh/HadoopKey.pem 

Host datanode1
Hostname ec2-52-87-117-48.compute-1.amazonaws.com
User ubuntu
IdentityFile ~/.ssh/HadoopKey.pem 

Host datanode2
Hostname ec2-54-152-22-210.compute-1.amazonaws.com
User ubuntu
IdentityFile ~/.ssh/HadoopKey.pem 

Host datanode3
Hostname ec2-52-90-33-107.compute-1.amazonaws.com
User ubuntu
IdentityFile ~/.ssh/HadoopKey.pem
```




## NameNode
`` ssh-keygen -f ~/.ssh/sshkey_rsa -t rsa -P "" ``

##### concat the generated key in .pub file to authorized key in all nodes.
`` cat ~/.ssh/sshkey_rsa.pub >> authorized_keys ``
