keytool -genkey -alias cosrssl -keyalg RSA -keysize 2048 -keystore cors-ssl-keystore.p12 -validity 3650 -storetype
PKCS12
pass: ***

sudo docker build -t cors-app .

(burda direct dosyayı bağladım ama docker volume kullanmak lazım)

docker run \
-v /home/atesel/Projects/cors-docker-ws:/config \
-e KEY_STORE_PATH=/config/cors-ssl-keystore.p12  \
-e KEY_STORE_PASSWORD=cors123456 \
-p 8443:8443 cors-app