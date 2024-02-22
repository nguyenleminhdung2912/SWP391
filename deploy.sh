echo "Building app..."
./mvnw clean package

echo "Deploy files to server..."
scp -r target/bpfk.jar root@165.22.51.191:/var/www/be/

ssh root@165.22.51.191 <<EOF
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