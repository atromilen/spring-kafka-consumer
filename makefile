# Some util shortcuts for local development
start: run-app
stop: stop-app

## Tasks for start Spring Boot application

run-app:
	@echo -e "\n${@}" && ./gradlew bootRun


## Tasks for stopping application

stop-app:
	@echo -e "\n${@}" && ./gradlew -stop

