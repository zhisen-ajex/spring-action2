# Docker Build
docker image build --platform -t zs-logstash:1.2.0 . && \

# Push to stage registry
docker image tag ajex-aone-logstash:1.2.0 asia-south2-docker.pkg.dev/middleware-test-339406/images/ajex/ajex-aone-logstash:1.2.0 && \
docker image push asia-south2-docker.pkg.dev/middleware-test-339406/images/ajex/ajex-aone-logstash:1.2.0

# Push to production registry
# docker image tag ajex-aone-logstash:1.2.0 asia-south1-docker.pkg.dev/ajex-production/images/ajex/ajex-aone-logstash:dev-1.2.0 && \
# docker image push asia-south1-docker.pkg.dev/ajex-production/images/ajex/ajex-aone-logstash:dev-1.2.0