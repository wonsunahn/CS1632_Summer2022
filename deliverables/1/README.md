- [CS 1632 - Software Testing](#cs-1632---software-testing)
  * [Description](#description)
  * [Coffee Maker Quest](#coffee-maker-quest)
  * [Format](#format)
  * [Test Plan / Defect Reporting Tips](#test-plan--defect-reporting-tips)
  * [Grading](#grading)
  * [Submission](#submission)
  * [Groupwork Plan](#groupwork-plan)

# CS 1632 - Software Testing
Summer Semester 2022

* DUE: July 12, 2022 11:30 AM

## Description

For this assignment, your group will determine a test plan for the simple game
Coffee Maker Quest, based on the requirements listed.  There are several known
**defects** in the software; you will need to find and report on **at least three**.
Additionally, a traceability matrix showing the mapping of test cases to
requirements is required.

Each requirement should have AT LEAST one test case associated with it in order
to have good test coverage.  Each test case should have AT LEAST one
requirement associated with it (no orphaned test cases).  One test case may
happen to test more than one requirement using a single set of inputs, and that
is fine.  The above can easily be checked via a traceability matrix (which you
should also deliver). 

Test cases should mention all necessary preconditions, execution steps, and
postconditions.  Please refer to [Exercise 1](../../exercises/1) on how to write
good test cases.

I expect you to test some **edge cases (at least one)** and some **corner cases
(at least one)** as part of the test plan.  If you do this, I'd estimate that
the number of test cases is at least 2x the number of requirements.  If the
number of test cases is more than 3x the number of requirements, you are
probably overtesting.

You are expected to execute the test plan in order to find the defects.  There
are AT LEAST three.  Full credit will be given only to those who properly find
and describe them.  While you are not expected to find all of the defects, a
reasonable test plan should definitely find at least three.  This is an
intentionally target-rich environment.

## Coffee Maker Quest

Coffee Maker Quest is a simple game.  The goal is get coffee, sugar, and cream,
and then drink it so that you can stay up and study.  In order to do so, you
need to visit several rooms in a house and look around.  Once you have obtained
all the necessary elements, you win.  If you decide to drink before getting all
of the necessary elements, you lose.

You can run it downloading the coffeemaker.jar file and running:
```
bash$ java -jar coffeemaker.jar
```

Try playing around with the game a little bit to get a feel of it.

## Create a Test Plan and a Traceability Matrix

Create a test plan and traceability matrix based on The requirements listed in
the file [requirements.md](requirements.md).  Make sure you test each
requirement at least once and each test case is testing some requirement.  Make
sure you add some edge and corner cases to the mix.

**Please apply everything you learned in Exercise 1 here.**

Now, there are a couple of things in the deliverable that you have to watch out
for beyond what you did for the exercise.  They all stem from the fact that
running Coffee Maker Quest typically involves a prolonged interaction between
the program and the player while the player looks for items in the game.  The
GoatGoatCar program for the exercise involved only a single-step interaction
with the user where the user simply ran the program with a given set of
commandline arguments.  These are the things that you need to watch out for
when writing test cases:

1. EXECUTION STEPS: Since the execution steps now involves a prolonged
   interaction, it is even more important that they are crystal clear so all
executions are repeatable.  Make sure that the interaction is divided into the
smallest discrete steps possible and don't forget to **number** them (like in a
recipe).  Also, make sure that each step is **unambiguous**.  For example, on a
step that says "Go north", it is unclear what action the tester should perform.
Instead if it says "Type N and press [Enter]", it is much easier to follow.

2. POSTCONDITIONS: A postcondition is a condition that needs to be in place
   **after** having performed the execution steps (hence the prefix "post").
Now, if you have many execution steps as part of a prolonged interaction, you
may be tempted to check a condition mid-way through the steps (let's call these
"mid-conditions").  In effect, you would be checking multiple things throughout
the steps of a single test case.  This is a clear sign that you are trying to
merge multiple test cases into a single test case.  If you take this to the
extreme, you may end up with just a single jumbo test case that tests
everything about your program in one shot!  This is no way to test your
program.  If that jumbo test case fails, then it is unclear which part of your
program failed and what requirement is not working (compared to many discrete
test cases).  Also, if that jumbo test case fails somewhere in the middle, that
means you will not be testing things later on in the test case, which creates
artifical dependencies between things you would like to test.  We learned the
importance of independence when creating test cases in the lecture.  A test
case should test one behavior at a time and all the preconditions and execution
steps should all be preparation to test that one behavior.

3. PRECONDITIONS: You may be tempted to set as the precondition some state
   after a prolonged interaction with the program.  For example in Coffee Maker
Quest, when testing FUN-DRINK, the outcome of drinking will differ depending on
the state of the inveontory at that point.  So you may be tempted to set as a
precondition "has coffee, cream, and sugar", or "has no items", depending on
what you want to test.  This can cause problems with reproducibility.

   Suppose the precondition is "has coffee, cream, and sugar".  There are a
zillion ways in which the player could have ended up with those items.  The
player could have acquired the items in different orders.  The player could
have taken the shortest path to acquire those items, or he/she could have taken
a detour.  Will drinking reproduce the same outcome regardless?  That depends
on how you wrote the program.  The order in which you acquire the items or what
path you took (or who-knows-what-else) may have an impact.

   Therefore, preconditions are best explained as a **sequence of steps** you
would like the tester to perform to achieve that state.  Instead of the following:

   ```
   PRECONDITIONS:
   The player has gathered coffee, cream, and sugar.

   EXECUTION STEPS:
     1. Type "D [Enter]" at prompt 
   ```

   The following is far superior in terms of reproducibility:

   ```
   PRECONDITIONS:
   Java version "1.8.0_231" is set up on the PATH (verifiable using "java -version").
   The program coffeemaker.jar is installed in the current directory.
   The game has been launched using the commandline: "java -jar coffeemaker.jar".
   The player has gathered coffee, cream, and sugar through the steps:
     1. Type "L [Enter]" at prompt 
     2. Type "N [Enter]" at prompt 
     ...

   EXECUTION STEPS:
     1. Type "D [Enter]" at prompt 
   ```

   The following moves the actions required to gather items from PRECONDITIONS
to the EXECUTION STEPS, but it has the same reproducibility:

   ```
   PRECONDITIONS:
   Java version "1.8.0_231" is set up on the PATH (verifiable using "java -version").
   The program coffeemaker.jar is installed in the current directory.
   The game has been launched using the commandline: "java -jar coffeemaker.jar".

   EXECUTION STEPS:
     1. Type "L [Enter]" at prompt 
     2. Type "N [Enter]" at prompt 
     ...
     N. Type "D [Enter]" at prompt 
   ```

   Where you choose to draw the line between preconditions and execution steps
is your choice.  In general, the steps to set up conditions for the test should
go in the former and the steps to perform the actual test should go to the
latter.  What is important is that the steps are laid out in detail so that the
test case is repeatable.

## Report Defects

Report at least 3 defects found using the test plan.

**Again, please apply everything you learned in Exercise 1 here.**

Also please apply what I said about repeatability and reproducibility while
writing EXECUTION STEPS, PRECONDITIONS, and POSTCONDITIONS when you write the
REPRODUCTION STEPS, EXPECTED BEHAVIOR, and OBSERVED BEHAVIOR for each defect
report.

## Format

The report should start with a cover page with:
* The names of the people in the group

Write a short introduction that includes:
* How you divided the work between members of the group.
* IDENTIFIER of one base case and why you think it is a base case.
* IDENTIFIER of one edge case and why you think it is an edge case.
* IDENTIFIER of one corner case and why you think it is a corner case.

ON A NEW PAGE, a traceability matrix should be provided mapping the test cases
with their associated requirements.  Remember that all requirements should map
to AT LEAST ONE test case, and all test cases should map to AT LEAST ONE
requirement.  

ON A NEW PAGE, a list of the actual test cases should follow.  You may name
them any way you wish, but please be consistent.  Please write them out in this
format -

	IDENTIFIER:
	TEST CASE: 
	PRECONDITIONS:
	EXECUTION STEPS:
	POSTCONDITIONS:

ON A NEW PAGE, list at least three defects found.  The defects should follow
the defect reporting template:

	IDENTIFIER:
	SUMMARY:
	DESCRIPTION:
	REPRODUCTION STEPS:
	EXPECTED BEHAVIOR:
	OBSERVED BEHAVIOR:

## Grading

* Introduction: 10% of grade
* Test Plan: 40% of grade
* Traceability Matrix: 20% of grade
* Defects Found and Described: 30% of grade

Please review the [grading_rubric.txt](grading_rubric.txt) for details.

## Submission

Please use the [ReportTemplate.docx](ReportTemplate.docx) file provided in this
directory to write your report.  If you don't have a .docx compatible word processor,
that's perfectly fine as long as you follow the same organization.  A PDF version of
the file is at [ReportTemplate.pdf](ReportTemplate.pdf).  Please make sure that
the intro, traceability matrix, test cases, and defects are on seperate pages.  You will
be submitting to GradeScope in PDF format.  When you submit, you will be asked
to assign pages in the PDF file to each rubric item: 1. Introduction, 2.
Traceability Matrix, 3. Test Cases, and 4. Defects.

Each pairwise group will do a shared submission to the **Deliverable 1**
GradeScope link in the manner described on Exercise 1.  Make sure that your
partner is added, or he/she will not get a grade!

## Groupwork Plan

Please create a shared online document for the report just like you did for
Exercise 1.  Divide the requirements up between the two of you in a sensible
way as in Exercise 1.  Once you are done, check each other's work, discuss, and
submit!
