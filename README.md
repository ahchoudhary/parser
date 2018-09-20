# parser
wallethub take home exercise.

Assumptions made while implementing this application:
1. Default duration is 'daily'.
2. The blocked request have http status in 4** range, i.e any request that comes with htto status of 400 to 499 will be considered block. such ad 400, 403 etc. (The test was not clear about what is considered to block a IP).
3. mysql server connection details used for testing are
   -url: mysql://localhost:3306/parser
   -username root, password: password

To build application, please use './gradlew build' in the command line from project home directory.

To run the applicatiom you can use below command from project home directory.
java -jar ./build/libs/parser-0.0.1-SNAPSHOT.jar com.ef.parser --accesslog=/path/to/access.log --startDate=2017-01-01.13:00:00 --duration=hourly --threshold=100

For ask #1: in the take home test you have asked for the executable jar.
you can find it when you 'build/libs' directory a parser-0.0.1-SNAPSHOT.jar which you can use to run the above command.

For ask #2: this git repository has the entire code.

For ask #3: SQL schema from mysql is under '<project_home_directory>/sql_schema' folder.

For ask #4:  you have asked for a query used for SQL tests
I have use Spring JPA for the data Retrieval with out using native query. But if I have to use a native SQL query I can use below example.

select le.ip, le.log_details, count(*)  from log_entry le where le.entry_date_time between '2017-01-01 00:59:50' and '2017-01-01 23:59:50' group by le.ip, le.log_details having count(ip) >0;

