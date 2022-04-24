#!/usr/bin/env bash

mvn clean package

echo 'Copy files'

scp -i ~/.ssh/id_rsa_numbus \
  target/sweater-1.0-SNAPSHOT.jar \
  numbus@192.168.0.108:/home/numbus

echo 'Restart server'

ssh -i ~/.ssh/id_rsa_numbus numbus@192.168.0.108 << EOF

pgrep java | xargs kill -9
nohup java -jar sweater-1.0-SNAPSHOT.jar > log.txt &

EOF

echo 'End'