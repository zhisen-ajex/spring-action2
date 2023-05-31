# Docker Build
docker image build  -t zs-logstash:8.7.1 . && \

# Push to stage registry
docker image tag zs-logstash:8.7.1 zhisen2/zs-logstash:8.7.1 && \
docker image push zhisen2/zs-logstash:8.7.1
