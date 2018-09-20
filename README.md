# parser
wallethub take home exercise.

Assumptions made while implementing this application:
1. Default duration is 'daily'.
2. The blocked request have http status in 4** range, i.e any request that comes with htto status of 400 to 499 will be considered block. such ad 400, 403 etc.
3. mysql server connection details used for testing are
   -url: mysql://localhost:3306/parser
   -username root, password: password

To build application, please use './gradlew build' in the command line from project home directory.

To run the applicatiom you can use below command from project home directory.
java -jar ./build/libs/parser-0.0.1-SNAPSHOT.jar com.ef.parser --accesslog=/path/to/access.log --startDate=2017-01-01.13:00:00 --duration=hourly --threshold=100




