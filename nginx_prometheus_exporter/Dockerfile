FROM alpine:latest

WORKDIR /app

COPY nginx-prometheus-exporter_0.10.0_linux_amd64.tar.gz /tmp/

RUN tar -xzf /tmp/nginx-prometheus-exporter_0.10.0_linux_amd64.tar.gz -C /usr/local/bin/ 

EXPOSE 9113
