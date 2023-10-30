FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD=Ensine_1234
ENV MYSQL_DATABASE=ensineme
ENV MYSQL_USER=ensine_admin
ENV MYSQL_PASSWORD=Ensine_1234

COPY ./Java-ORM/ensine/banco.sql /docker-entrypoint-initdb.d/

EXPOSE 3306
