#!/bin/bash
cd /home/ec2-user/Back-end/expensesTracker || exit

echo "🔄 Pulling latest code..."
git pull origin master

echo "🔨 Building with Maven..."
mvn clean package -DskipTests

echo "🛑 Stopping old app..."
pkill -f 'expensesTracker-0.0.1-SNAPSHOT.jar'

echo "🚀 Starting new app..."
nohup java -jar target/expensesTracker-0.0.1-SNAPSHOT.jar > app.log 2>&1 &

echo "✅ Deployment complete!"