# Paint shop problem

Uses a brute force recursive approach to generate all potential solution candidates of size number_of_colours. 

Each candidate is tested against customer preferences and acceptable solutions are added to a set of valid solutions.

The solution set is then sorted based on most gloss / least matte options and the first solution is returned.

Backtracking is not used as the partial may not take into account solutions where a later option would provide a valid solution. 

For e.g. a customer with preferences 1 M 3 G 5 G, the program would not generate a solution starting with 'G' as it doesn't satisfy the customer preference constraint (1 M).

# Requirements

mvn:package returns an executable jar file with packaged dependencies (slf4j, some commons librarys) which should be used.

Logging level set to ERROR. Please update the simplelogger.properties file and repackage to change level.

Compiled with **Java 8**.
