#!/bin/bash
sed -i 's/IP_DOMAIN/'$IP_DOMAIN'/g' src/main/resources/Docker.properties
echo "IP INS Task" $IP_DOMAIN $INS $INSERT_TASK
sed -i 's/INS/'$INS'/g' src/main/resources/Docker.properties
gradle -Penv=docker $INSERT_TASK