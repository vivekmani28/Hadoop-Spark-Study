#!/bin/bash
yearn="2012"
nyr="1-Year/"
path="https://www2.census.gov/programs-surveys/acs/data/pums/"
slash="/"
folderPath=$path$yearn$slash$nyr
types=("h" "p")
ftype="csv"
states=("al" "ak" "az" "ar" "ca" "co" "ct" "de" "fl" "ga" "hi" "id" "il" "in" "ia" "ks" "ky" "la" "me" "md" "ma" "mi" "mn" "ms" "mo" "mt" "ne" "nv" "nh" "nj" "nm" "ny" "nc" "nd" "oh" "ok" "or" "pa" "ri" "sc" "sd" "tn" "tx" "ut" "vt" "va" "wa" "wv" "wi" "wy" "us")
# Create a git branch called testcases

for ty in ${types[@]}
do
	dir="/pums/"
  dir=$dir$ty$slash$yearn$slash$nyr$slash
  hdfs dfs -mkdir -p $dir
  for state in ${states[@]}
  do 
  
  fileName="csv_"$ty$state".zip"
  hadoopPath=$slash$yearn$slash$nyr
  wget -U 'Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.8.1.6) Gecko/20070802 SeaMonkey/1.1.4' $folderPath$fileName
  unzip $fileName
  rm *.pdf
  hdfs dfs -put *.csv $dir
  rm $fileName
  rm *.csv
  done
done
echo "Everything is done!"
