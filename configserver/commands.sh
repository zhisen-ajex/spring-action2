# Build
mvn clean compile install -DskipTests && \

# Docker Build
docker image build -t zs-config-server:1.0 . && \

# Push to stage registry
docker image tag zs-config-server:1.0 zhisen2/zs-config-server:1.0 && \
docker image push zhisen2/zs-config-server:1.0
