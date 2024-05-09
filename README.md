After starting the SpringBoot application, navigate to http://localhost:8080

The program expects a csv file with data in the format:

EmpID, ProjectID, DateFrom, DateTo

After selecting(uploading) a file, and clicking the Calulate bitton the input is validated and an error is shown if the data is corrupted.

If the data is valid, the program finds the pair of employees who worked together on the same project the longest 
and displays the workers' ids, the project id and the days they worked on that project in a table.

Sample test files with valid data could be found in the project in the folder testFiles.
