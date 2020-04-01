#!/usr/bin/env bash
echo ============= test ====================
psid=0
javaps=`jps -l | grep demo0819`
 if [ -n "$javaps" ]; then
    psid=`echo $javaps | awk '{print $1}'`
 else
    psid=0
 fi
 if [ $psid -ne 0 ]; then
    echo -n "啦 Stopping demo0819 啦啦 clala ...(pid=$psid) "
    `kill -9 $psid`
    if [ $? -eq 0 ]; then
       echo "[OK]"
    else
       echo "[Failed]"
    fi
 else
    echo "================================"
    echo "info: is not running"
    echo "================================"
 fi
echo '准备自动启动「demo0819」服务'
cd /java/git_site_self/demo0819
echo 'git pull ...'
git pull
echo 'mvn clean package ...'
mvn clean package -Dmaven.test.skip=true
echo '进入 target 目录'
cd /java/git_site_self/demo0819/target
echo 'run java -jar 。。。'
nohup java -XX:ParallelGCThreads=2 -Djava.compiler=NONE -jar demo0819-1.1-SNAPSHOT.jar --spring.profiles.active=dev &
sleep 5
tail -f nohup.out
sleep 30
exit 0
