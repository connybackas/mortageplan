# mortageplan

Code test assignment for a trainee employment at Crosskey AB.

Calculation in this project is made with doubles which is not safe. Bigdecimal should be used instead but one of the requirements in this assignment was that no java math dependencies were allowed to be used. 

Clone project to a directory of your choice and run 'gradle build' to compile. This automatically runs the tests as well. If you want to run tests again you should issue the command 'gradle clean test'.

When the project is built you can run the program with 'java  -jar build/libs/mortageplan.jar prospects.txt'. You can change prospects.txt to a file of your own choice, just make sure you follow the 'Customer,Total loan,Interest,Years' pattern or the ProspectParser class might not add the prospect to the list being calculated. 
