FROM nginx:alpine

COPY nginx.conf /etc/nginx/nginx.conf

ENV NGINX_SCRAPE_URI=http://<nginx_ip>:8090/metrics
ENV WEB_TELEMETRY_PATH=/metrics

EXPOSE 8090
