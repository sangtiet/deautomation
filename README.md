# DE Automation Framework

Digital Engineer (DE) automation framework helps everyone involve in Automation testing quickly by using Cucumber/BDD.

## Prerequisites

- [Java - JDK 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
- [Java - Environment setup](https://javatutorial.net/set-java-home-windows-10)
- [Apache Maven](https://www.baeldung.com/install-maven-on-windows-linux-mac)
- [Eclipse IDE for Java developers](https://www.eclipse.org/downloads/)
- [TestNG plugin for Eclipse](https://www.lambdatest.com/blog/how-to-install-testng-in-eclipse-step-by-step-guide/)
- [QAF BDD Editor](https://marketplace.eclipse.org/content/qaf-bdd-editors)

## Make the framework in ready state

Open Eclipse IDE:

```sh
Go to File > Import
Select Maven > Existing Maven Projects
Browse to the framework location
Click Finish button
```

Wait for few minutes to load Maven dependencies

## Run

Run from Eclipse:

```sh
Right click on file "/src/test/resources/TestNGRunSmokeTests.xml"
Select Run As > TestNG Suite
```

Run from CommandLine:

```sh
mvn clean install
```

## License

[Vu Bach](www.linkedin.com/in/vu-bach-17448a58)


//test