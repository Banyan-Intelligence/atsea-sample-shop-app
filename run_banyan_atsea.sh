mkdir banyan_atsea
cd banyan_atsea
git clone -b dev https://github.com/Banyan-Intelligence/atsea-sample-shop-app.git
cd atsea-sample-shop-app
sudo cat prmetheus_config.txt >> /etc/prometheus/prometheus.yml
sudo systemctl restart prometheus.service
sudo docker compose up --build
