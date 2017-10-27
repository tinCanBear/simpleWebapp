FROM jetty:9.2.10
MAINTAINER gilad "gilad@gilad.com"
ADD ./target/root /var/lib/jetty/webapps/root