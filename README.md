# Codex 

Java setup

This project uses JDK 1.8. Be sure to have correct version of Java installed before continuing.

Play setup

To compile and start project, you have to install Play Framework (version 2.2.6). Installation files and instructions are available on https://www.playframework.com/download. Make sure to set up neccessary environment variables. 

Eclipse setup

1) Open Git perspective in Eclipse
2) Choose Clone a Git Repository option
3) For URI, put https://github.com/{username}/quiz.git
4) Enter your username and password
5) Wait for repository to be cloned

When the repository is cloned, position yourself to project in your Git folder and open command line (you should 
be positioned in something like C:\Users\User\git\quiz\Quiz). In command line, execute following orders:

play dependencies clean compile
play eclipse

6) Return to Java view and right click on Project Explorer
7) Import -> Import ... -> Git -> Project from Git -> Existing Local Repository
8) Your project should be visible and editable in Eclipse now.

Project startup

To start a project, besides Java 1.8 and Play 2.2.6, you must have MySQL database up and running. After free registration, you can download MySQL installer here: http://dev.mysql.com/downloads/windows/installer/. After starting up database, with MySQL workbench you have to create schema named quizapp with UTF-8 general encoding.

After setting up database, access conf/application.conf and conf/META-INF/persistence.xml files and set up username and password to match your database credentials.

Position yourself in project folder (as described above), open command line and execute following orders:

play dependencies clean compile
play run (or play debug run to start in debug mode)

Your application is now accessable via browser on localhost:9000.
