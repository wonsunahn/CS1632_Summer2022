- [CS 1632 - Software Quality Assurance](#cs-1632---software-quality-assurance)
  * [Deliverable 2](#deliverable-2)
  * [Running the Program](#running-the-program)
  * [Running Unit Tests](#running-unit-tests)
  * [Development Methodology](#development-methodology)
  * [Expected Outcome](#expected-outcome)
  * [Verifying the Test Cases](#verifying-the-test-cases)
  * [Additional Requirements](#additional-requirements)
- [Grading](#grading)
- [Submission](#submission)
- [GradeScope Feedback](#gradescope-feedback)
- [Groupwork Plan](#groupwork-plan)
- [Resources](#resources)

# CS 1632 - Software Quality Assurance
Summer Semester 2022

* DUE: July 19, 2022 11:30 AM 
* Last day of late submission: August 5, 2022 11:30 AM

**GitHub Classroom Link:** https://classroom.github.com/a/Vl_kP9Nz

## Deliverable 2

For this assignment, your group will write code and unit tests for an
authorized reproduction of Coffee Maker Quest.  

Requirements for this program is that you mimic the behavior of the program
coffeemaker.jar given in this folder exactly.  This is a version of the Coffee
Maker Quest game we tested for Deliverable 1, but with defects removed.

Some of the work has already been done for you.  Classes such as
CoffeeMakerQuest.java, Config.java, Game.java, Player.java, Room.java, and
TestRunner.java are already complete.  You need only modify
CoffeeMakerQuestImpl.java and CoffeeMakerQuestTest.java.  As in the
exercise, the places where you need to modify code are marked by the // TODO
comments.  DO NOT TOUCH the already complete classes as they will be used AS
IS during grading.  Here is a brief rundown of the classes:

* CoffeeMakerQuest.java - the interface for the CoffeeMakerQuest game engine
* Config.java - allows configuration of bug injection into various classes
* Game.java - contains the main method; generates rooms and runs the game using the CoffeeMakerQuest engine
* Player.java - player object with inventory information
* Room.java - room object with furnishings and items
* TestRunner.java - the runner for the JUnit test class CoffeeMakerQuestTest
* CoffeeMakerQuestImpl.java - an implementation of CoffeeMakerQuest (_modify_)
* CoffeeMakerQuestTest.java - JUnit test class CoffeeMakerQuest (_modify_)

## Running the Program

Let's try compiling the game and running using the Maven build system:

```
mvn compile
```

```
mvn exec:java -D"exec.mainClass"="edu.pitt.cs.Game"
```

When you run it as-is, you will suffer an exception and crash:

```
...
Coffee Maker Quest 1.0

[WARNING]
java.lang.NullPointerException
    at edu.pitt.cs.Game.main (Game.java:32)
    at org.codehaus.mojo.exec.ExecJavaMojo$1.run (ExecJavaMojo.java:254)
    at java.lang.Thread.run (Thread.java:748)
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.803 s
[INFO] Finished at: 2022-07-07T02:39:01-04:00
[INFO] ------------------------------------------------------------------------
...
```

That is because you have not completed implementing CoffeeMakerQuestImpl.java.
When you are done implementing, you should get identical behavior as running
the canonical jar file:

```
java -jar coffeemaker.jar
```

## Running Unit Tests

As in the Exercise, invoke the Maven 'test' phase:

```
mvn test
```

You should get output that looks like this:

```
...
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running edu.pitt.cs.CoffeeMakerQuestTest
Tests run: 12, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.121 sec

Results :

Tests run: 12, Failures: 0, Errors: 0, Skipped: 0

[INFO]
[INFO] --- jacoco-maven-plugin:0.8.4:report (post-unit-test) @ coffeemaker ---
[INFO] Loading execution data file C:\Users\mrabb\Documents\github\cs1632\CS1632_Summer2022\deliverables\2\target\jacoco.exec
[INFO] Analyzed bundle 'coffeemaker' with 7 classes
[INFO]
[INFO] --- jacoco-maven-plugin:0.8.4:check (check-unit-test) @ coffeemaker ---
[INFO] Loading execution data file C:\Users\mrabb\Documents\github\cs1632\CS1632_Summer2022\deliverables\2\target\jacoco.exec
[INFO] Analyzed bundle 'coffeemaker' with 7 classes
[WARNING] Rule violated for class edu.pitt.cs.CoffeeMakerQuestImpl: instructions covered ratio is 0.16, but expected minimum is 0.90
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.236 s
[INFO] Finished at: 2022-07-07T02:44:37-04:00
[INFO] ------------------------------------------------------------------------
...
```

Again, don't get too excited about all the tests passing --- that is only
because the test cases are currently empty.  In fact, the test fails in the
code coverage verification phase because the current coverage is only 16% which
is way below our target of 90%.  You will have to fill in all the provided test
cases, and perhaps add a few test cases of your own, to hit 90%.

## Development Methodology

Like Exercise 2, we will try to apply the Test Driven Development (TDD) model
here.  Try writing the test case(s) FIRST before writing the code for a
feature.  This way, you will always have 100% test coverage for the code you
have written and are writing.  Hence, if you break any part of it in the course
of adding a feature or refactoring your code, you will know immediately.

## Expected Outcome

When all is done, you should see the following output when running 'mvn test':

```
...
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running edu.pitt.cs.CoffeeMakerQuestTest
Tests run: 24, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.46 sec

Results :

Tests run: 24, Failures: 0, Errors: 0, Skipped: 0

[INFO]
[INFO] --- jacoco-maven-plugin:0.8.4:report (post-unit-test) @ coffeemaker ---
[INFO] Loading execution data file C:\Users\mrabb\Documents\github\cs1632\CS1632_CoffeeMaker_Solution\target\jacoco.exec
[INFO] Analyzed bundle 'coffeemaker' with 7 classes
[INFO]
[INFO] --- jacoco-maven-plugin:0.8.4:check (check-unit-test) @ coffeemaker ---
[INFO] Loading execution data file C:\Users\mrabb\Documents\github\cs1632\CS1632_CoffeeMaker_Solution\target\jacoco.exec
[INFO] Analyzed bundle 'coffeemaker' with 7 classes
[INFO] All coverage checks have been met.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.949 s
[INFO] Finished at: 2022-07-07T02:52:08-04:00
[INFO] ------------------------------------------------------------------------
...
```

## Verifying the Test Cases

Just like for the exercises, as an extra precaution, we would like to verify
our test cases against a buggy implementation to make sure that they are able
to catch defects.

In order to apply your unit tests to CoffeeMakerQuestBuggy, add the following line to
the beginning of the @Before setUp() method:

```
Config.setBuggyCoffeeMakerQuest(true);
```

If you run 'mvn test' after having done so, you should be detecting lots of defects:

```
Results :

Failed tests:   testAddFirstRoom(edu.pitt.cs.CoffeeMakerQuestTest)
  testAddRoomAtNorthDuplicate(edu.pitt.cs.CoffeeMakerQuestTest)
  testAddRoomAtNorthUnique(edu.pitt.cs.CoffeeMakerQuestTest): (..)
  testGetCurrentRoom(edu.pitt.cs.CoffeeMakerQuestTest): expected null, but was:<edu.pitt.cs.Room@1e8823d2>
  testGetHelpString(edu.pitt.cs.CoffeeMakerQuestTest): java.lang.IllegalArgumentException: object is not an instance of declaring class
  testGetInstructionsString(edu.pitt.cs.CoffeeMakerQuestTest): expected:< INSTRUCTIONS [(N,S,L,I,D,H) ]> > but was:< INSTRUCTIONS []> >
  testIsRoomUnique(edu.pitt.cs.CoffeeMakerQuestTest): java.lang.IllegalArgumentException: object is not an instance of declaring class
  testProcessCommandDLose(edu.pitt.cs.CoffeeMakerQuestTest): expected:<...GAR!(..)
  testProcessCommandDLoseCoffee(edu.pitt.cs.CoffeeMakerQuestTest): expected:<(..)
  testProcessCommandDLoseCoffeeCream(edu.pitt.cs.CoffeeMakerQuestTest): expected:<(..)
  testProcessCommandDLoseCoffeeSugar(edu.pitt.cs.CoffeeMakerQuestTest): expected:<(..)
  testProcessCommandDLoseCream(edu.pitt.cs.CoffeeMakerQuestTest): expected:<(..)
  testProcessCommandDLoseCreamSugar(edu.pitt.cs.CoffeeMakerQuestTest): expected:<(..)
  testProcessCommandDLoseSugar(edu.pitt.cs.CoffeeMakerQuestTest): expected:<(..)
  testProcessCommandDWin(edu.pitt.cs.CoffeeMakerQuestTest): expected:<...gar.(..)
  testProcessCommandI(edu.pitt.cs.CoffeeMakerQuestTest): expected:<[YOU HAVE NO COFFEE!(..)
  testProcessCommandLCream(edu.pitt.cs.CoffeeMakerQuestTest): (..)
  testProcessCommandLSugar(edu.pitt.cs.CoffeeMakerQuestTest): (..)
  testProcessCommandN(edu.pitt.cs.CoffeeMakerQuestTest): expected:<Mock for Room, hashCode: 45416784> but was:<Mock for Room, hashCode: 364480205>
  testProcessCommandS(edu.pitt.cs.CoffeeMakerQuestTest): expected:<Mock for Room, hashCode: 1103505488> but was:<edu.pitt.cs.Room@6b530eb9>
  testSetCurrentRoom(edu.pitt.cs.CoffeeMakerQuestTest)

Tests run: 24, Failures: 21, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  5.552 s
[INFO] Finished at: 2022-07-07T02:57:20-04:00
[INFO] ------------------------------------------------------------------------
...
```

If you don't see all 21 failures, you may want to review the test cases to see which are being too lax.

Again, don't forget to revert your CoffeeMakerQuestTest.java by removing the
'Config.setBuggyCoffeeMakerQuest(true);' line after you are done!

## Additional Requirements

* Code coverage of the class CoffeeMakerQuestImpl when the JUnit TestRunner is
  run should be at an absolute minimum of **90%**.  If coverage falls below
that number, add more unit tests to CoffeeMakerQuestTest.  View the detailed
line-by-line Jacoco coverage report for CoffeeMakerQuestImpl to see which lines
you are missing and come up with test cases that are able to hit those lines.

* For this program, no requirements are given as the requirement is that you
  mimic the output of the given **coffeemaker.jar** file (note that this jar
file is slightly different from the version provided to you for Deliverable 1
as I have fixed most of the bugs!).  If GradeScope gives you a failure because
your output is different canonical output, it will show you where the
difference is between brackets [].  In fact, GradeScope itself uses JUnit
behind the scenes to test your program and showing the difference in brackets
is a JUnit assertEquals feature.

* You are asked to complete CoffeeMakerQuestImpl, but there are other support
  classes as well such as Player and Room.  You are expected to use the methods
provided in those classes and not repeat the code somewhere else.  In fact,
this is an important software testability principle called **DRY (Don't Repeat
Yourself)**.  For example, the Player class has the method
**Player.getInventoryString** that prints out the inventory contents based on
the current items.  You are required to use that method and not implement a
similar method of your own.

* Write at least one **private method** while implementing
  CoffeeMakerQuestImpl.  In general, private methods of a Java class work as
"helper" methods that implement a sub-functionality of a public method.  You
have the freedom to choose what sub-functionality you want to encapsulate
within a private method.  Also, add at least one unit test that directly tests
a private method at the very bottom of CoffeeMakerQuestTest.  Use **Java
reflection** to do this.  If you don't remember what Java reflection is, please review lectures on unit testing.  Here is a tutorial if you need further help:

  https://www.oracle.com/technical-resources/articles/java/javareflection.html

* Coding style is also important for software quality in the long run (even
  though they are not technically defects as we learned).  In particular, a
uniform naming convention greatly improves the readability of your code.  A
widely used convention is called
[lowerCamelCase](https://en.wikipedia.org/wiki/Naming_convention_(programming)#Java)
convention.  That is the convention that was [first adopted when Sun
Microsystems first created the Java
language](https://www.oracle.com/technetwork/java/codeconventions-135099.html).
This is still the convention at the biggest companies using Java like
[Oracle](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/variables.html)
and [Google](https://google.github.io/styleguide/javaguide.html#s5-naming).
Please make sure you follow the lower camel case convention for all your
variables and methods for this project.  There is less agreement on other
formatting issues like indentation and line wrapping, but try to maintain a
uniform convention whatever you choose.

# Grading

* GradeScope autograder: 70% of grade
* Private method added and tested: 5% of grade
* Source code style (lower camel case naming / formatting): 10% of grade
* Report (including coverage stats): 15% of grade

Please review the grading_rubric.txt for details.

# Submission

Each group will do one submissions to GradeScope as usual.

The submission is done in two parts:

1. Submit your GitHub Classroom Deliverable 2 repository to GradeScope at the
   **Deliverable 2 GitHub** link.  Once you submit, GradeScope will run the
autograder to grade you and give feedback.  If you get deductions, fix your
code based on the feedback and resubmit.  Repeat until you don't get
deductions.

1.  Please use the [ReportTemplate.docx](ReportTemplate.docx) file provided in
    this directory to write a short report.  A PDF version of the file is at
[ReportTemplate.pdf](ReportTemplate.pdf).  On the first page introduction,
please describe the division of work between group members and also any
difficulties you faced while using JUnit.  On the second page, paste a
screenshot of code coverage stats **after** having completed the coding.
Please refer to [Exercise 2](/exercises/2#measuring-code-coverage) on how to
create the screenshot.  Submit to GradeScope at the **Deliverable 2 Coverage**
link.  Your screenshot should look like this:

   <img alt="Code Coverage Jacoco" src=code_coverage_jacoco.png width=700>

   Make sure that the coverage of CoffeeMakerQuestImpl is showing and the
overall coverage is above **90%** as shown above.

# GradeScope Feedback

It is encouraged that you submit to GradeScope early and often.  Please use the
feedback you get on each submission to improve your code!

The GradeScope autograder works in 3 phases:

1. CoffeeMakerQuestImpl verification using CoffeeMakerQuestTestSolution:
   CoffeeMakerQuestTestSolution is the solution implementation of
CoffeeMakerQuestTest.  The purpose of this phase is to verify that CoffeeMakerQuestImpl (your CoffeeMakerQuest implementation) does not have any defects.

1. CoffeeMakerQuestTest on CoffeeMakerQuestSolution: CoffeeMakerQuestTest is your submitted JUnit test for CoffeeMakerQuest.  The purpose of this phase is
   to test CoffeeMakerQuestTest itself for defects.  CoffeeMakerQuestSolution is the solution implementation of CoffeeMakerQuest and contains no defects (that I know of).  Hence, all tests in CoffeeMakerQuestTest should pass.

1. CoffeeMakerQuestTest on CoffeeMakerQuestBuggy: CoffeeMakerQuestTest is your submitted JUnit test for CoffeeMakerQuest.  The purpose of this phase is
   to test CoffeeMakerQuestTest against the buggy CoffeeMakerQuestBuggy
implementation.  The class CoffeeMakerQuestBuggy is given to you in the form of
the coffeemaker-buggy.jar file.  Since CoffeeMakerQuestBuggy is buggy, you
expect the tests to fail this time.  If CoffeeMakerQuestTestSolution fails a
test but CoffeeMakerQuestTest passes a test (or vice versa), then this indicates a problem.

# Groupwork Plan

Just like for Exercise 2, I recommend that you divide the list of methods to
implement / test into two halves and working on one half each.  Please document
how you divided the work in your report.

# Resources

These links are the same ones posted at the end of the slides:

* JUnit User manual:  
https://junit.org/junit5/docs/current/user-guide/  
The Writing Tests section is probably the most useful.

* JUnit Reference Javadoc:  
http://junit.sourceforge.net/javadoc/  
For looking up methods only, not a user guide.

* Mockito User Manual:  
https://javadoc.io/static/org.mockito/mockito-core/3.2.4/org/mockito/Mockito.html  
Most useful is the sections about verification and stubbing.

* Jacoco User Manual:  
https://www.jacoco.org/userdoc/index.html
