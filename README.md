# COMP2240 Operating Systems
## Assignment 2 (Due: 23/09/22)

### Overview (Assignment contains three problems)
<b>Problem 1:</b> There are two islands containing farmers (North and South) with a bridge connecting the two islands. Only one farmer is allowed to cross the bridge at a time. Using java semaphores implement mutual exclusion to ensure only one farmer is able to use the bridge at a time.
#### Running Program
```javac P1.java``` <br />
```java P1 input.txt``` <br />
"input.txt" is a text file containing the number of farmers from each island

<b>Problem 2:</b> There is an ice-cream parlour containing 5 seats. A customer is able to sit a seat at any time and will take a certain time to finish their ice-cream. Customers serves each customer in the order they arrive in but has a certain rule to customer arrival. If a customer arrvies when all 5 seats are taken they must wait untill all seats are free before they can take a seat. Using java semaphores ensure the parlours rule is enforced and ensure the solution is starvation free.
#### Running Program
```javac P2.java``` <br />
```java P2 input.txt``` <br/>
"input.txt" is a text file containting the number of customers and each customer information

<b>Problem 3:</b>  (Simillar to P2) There is an ice-cream parlour containing 5 seats. A customer is able to sit a seat at any time and will take a certain time to finish their ice-cream. Customers serves each customer in the order they arrive in but has a certain rule to customer arrival. If a customer arrvies when all 5 seats are taken they must wait untill all seats are free before they can take a seat. Using java monitors ensure the parlours rule is enforced and ensure the solution is starvation free.
#### Running Program
```javac P3.java``` <br />
```java P3 input.txt``` <br/>
"input.txt" is a text file containting the number of customers and each customer information
