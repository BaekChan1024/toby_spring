FROM mysql:8.0

COPY ./init.sql /docker-entrypoint-initdb.d/

ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=toby
ENV MYSQL_USER=user
ENV MYSQL_PASSWORD=password

EXPOSE 3306

CMD ["mysqld"]
