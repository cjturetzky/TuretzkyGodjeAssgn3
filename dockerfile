FROM openjdk:18

WORKDIR /root

COPY . /root/

COPY start.sh /root/start.sh
RUN chmod +x /root/start.sh

CMD ["/root/start.sh"]