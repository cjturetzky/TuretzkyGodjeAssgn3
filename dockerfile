FROM openjdk:16.0.2

WORKDIR /root

COPY . /root/

COPY start.sh /root/start.sh
RUN chmod +x /root/start.sh

CMD ["/root/start.sh"]