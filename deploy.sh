echo "Building app..."
./mvnw clean package

echo "Deploy files to server..."
scp -r target/bpfk.jar root@152.42.220.117:/var/www/be/

ssh root@152.42.220.117 <<EOF
pid=\$(sudo lsof -t -i :8080)

if [ -z "\$pid" ]; then
    echo "Start server..."
else
    echo "Restart server..."
    sudo kill -9 "\$pid"
fi
cd /var/www/be
java -jar bpfk.jar
EOF
exit
echo "Done!"