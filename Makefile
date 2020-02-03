APP_NAME=drodriguezh/java-demo-app

build:
	docker build -t $(APP_NAME) .

run:
	docker run --rm -p 8081:8081 --env SCOPE_DSN=$(SCOPE_DSN_JAVA_DEMO_APP) --name java-demo-app $(APP_NAME)