FROM maven:3.6.0

COPY . .

WORKDIR "/"

RUN mvn package 
CMD java -jar target/MyBankAccount2.0-0.0.1-SNAPSHOT.jar