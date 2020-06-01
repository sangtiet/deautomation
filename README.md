## Document your bdd test solution here!

### Important folders
* dashboard: has js for displaying QAF results. 
  dashboard.htm will display results of execution locally. Works with Firefox browser only.
* src/main/resources: stores all configuration for tests
* src/main/scenarios: stores all gherkin feature files.
* src/main/java: stores your application customized QAFTestStep files.
* data: stores all your test data. ras.common defines functions to access data.

### Sample Testcases (Local)

| Sample             | JIRA    | Run Type | Configuration      | Test Case                                      | Feature                   |
| ------------------ | ------- | -------- | ------------------ | ---------------------------------------------- | ------------------------  |
| Desktop Web        | RASR-32 | Local    | localWebDataDriven | Desktop Chrome Local Data Driven Google sample | @JsonDataGoogleWebSample  |
| Android Web        | RASR-33 | Local    | localWebDataDriven | Android Chrome Local Data Driven Google Sample | @JsonDataGoogleWebSample  |
| IOS Web            | RASR-34 | Local    | localWebDataDriven | iPhone Chrome Local Data Driven Google Sample  | @JsonDataGoogleWebSample  |
| Android Hybrid     | RASR-36 | Local    | localNativeHybrid  | Android Hybrid Local Oracle Appium             | @AndroidHybridOracleDemo  |
| IOS Hybrid         | RASR-35 | Local    | localNativeHybrid  | IOS Native UICatalog Local Appium              | @IOSHybridDemo            |
| Android Native     | RASR-37 | Local    | localNativeHybrid  | Android Native Flipkart Local Appium           | @FlipkartDemo             |
| IOS Native         | RASR-38 | Local    | localNativeHybrid  | IOS Hybrid UICatalog Local Appium              | @JsonDataGoogleWebSample  |
| REST (Web service) | RASR-39 | Local    | webServiceExample  | Web Service Example                            | @WebServiceExample        |
| Data Driven (JSON) | RASR-85 | Local    | localWebDataDriven | Desktop Chrome Local Data Driven Google sample | @JsonDataGoogleWebSample  |



### Sample Testcases (Perfecto)

| Sample             | JIRA    | Run Type | Configuration         | Test Case                                      | Feature                   |
| ------------------ | ------- | -------- | --------------------- | ---------------------------------------------- | ------------------------  |
| Desktop Web        | RASR-32 | Perfecto | perfectoWebDataDriven | Chrome Desktop Perfecto                        | @JsonDataGoogleWebSample  |
| Android Web        | RASR-33 | Perfecto | perfectoWebDataDriven | Android Chrome Mobile Web Perfecto             | @JsonDataGoogleWebSample  |
| IOS Web            | RASR-34 | Perfecto | perfectoWebDataDriven | iPhone Chrome Mobile Web Perfecto              | @JsonDataGoogleWebSample  |
| Android Hybrid     | RASR-36 | Perfecto | perfectoNativeHybrid  | Android Hybrid Perfecto Appium                 | @AndroidHybridOracleDemo  |
| IOS Hybrid         | RASR-35 | Perfecto | perfectoNativeHybrid  | IOS Hybrid Perfecto Appium                     | @IOSHybridDemo            |
| Android Native     | RASR-37 | Perfecto | perfectoNativeHybrid  | Android Native Local Appium                    | @FlipkartDemo             |
| IOS Native         | RASR-38 | Perfecto | perfectoNativeHybrid  | IOS Native Perfecto Appium                     | @JsonDataGoogleWebSample  |
| Data Driven (JSON) | RASR-85 | Perfecto | perfectoWebDataDriven | Desktop Chrome Local Data Driven Google sample | @JsonDataGoogleWebSample  |
