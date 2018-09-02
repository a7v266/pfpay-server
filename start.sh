#!/bin/bash

java -Xms2g -Xmx2g \
-Dfile.encoding=UTF-8 \
-Duser.language=ru \
-Duser.country=RU \
-Djava.security.egd=file:/dev/./urandom \
-jar pfpay-server.jar
