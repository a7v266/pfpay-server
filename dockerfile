FROM java:8

EXPOSE 8080

COPY start.sh /pfpay/
COPY build/libs/pfpay-server.jar /pfpay/

WORKDIR /pfpay/

ENTRYPOINT ["/bin/bash", "-c", "/pfpay/start.sh"]
