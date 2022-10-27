# Cloud Parking

## Docker PostgreSQL
docker run --name db_spring_boot_parking -p 5432:5432 -e POSTGRES_DB=db_parking -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123456 -d postgres