# EStock

A financial tool to track stock values

## Deployments

1. Be sure the `-SNAPSHOT` is removed in the `pom.xml`
2. Compile the app with maven commands
3. Move the compiled `.jar` file from `target` to `docker/prod/app-data` and rename it `app.jar`
4. Stop the docker containers if are already started
   1. If the containers not already built, build its
   2. Else, start

## Commands

### maven

```bash
# Compile application in target/estock-api-<version>[-SNAPSHOT].jar
./mvnw clean compile package -DskipTests -f pom.xml
```

### Docker

```bash
# Build containers (only the first time or if the docker-compose configuration changed)
docker-compose -p estock --env-file docker/prod/.env -f ./docker/prod/docker-compose.yml build

# Start containers (start app)
docker-compose -p estock --env-file docker/prod/.env -f ./docker/prod/docker-compose.yml up -d

# Stop containers (stop app)
docker-compose -p estock -f ./docker/prod/docker-compose.yml stop

# Remove containers (remove all data in database)
docker-compose -p estock -f ./docker/prod/docker-compose.yml down
```

You can put all needed environment variables in docker/prod/.env file, it's ignored by git.

### Database dumps

```bash
# Dump database
# The dump file will be available in docker/prod/db-data/estock.dump
pg_dump -Fc estock -U estock > /tmp/estock.dump

# Restore database from dump file
# The dump file must be in docker/prod/db-data/estock.dump
pg_restore -c --if-exists -d estock -U estock /tmp/estock.dump
```
