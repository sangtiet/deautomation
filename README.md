appium -p 4733 --allow-insecure chromedriver_autodownload --default-capabilities "{\"appium:newCommandTimeout\": 3000}"
adb shell settings put secure autofill_service null
'
6gGJSkUwcDkcyiD_1cuQ

mvn -o -e clean test -Ddevice-names=ss_j7pro -Dcucumber.features="src\test\resources\features\regression_napas_direct_tcb_RERUN.feature" -Datmobile.otp.source=local
mvn -o -e clean test -Ddevice-names=ss_j7pro -Dcucumber.features="src/test/resources/features/BankIntegration.feature" -Dat_bank_name=Vietcombank -Dat_bank_type=account_direct -Dsuite=cucumber

mvn -o -e clean test -Ddevice-names=oppo_F9 -Dcucumber.features="src/test/resources/features/BankIntegration.feature" -Dat_bank_name=BanViet -Dat_bank_type=card_direct

curl -H "Content-Type: multipart/form-data" -u automation-tester:Aa123456 -F "file=@src\test\resources\features\regression_napas_GPBANK.feature" --request POST "https://jira.zalopay.vn/rest/raven/1.0/import/feature?projectKey=TEST"

appium -p 4444 --chromedriver-executable chromedriver.exe
mvn clean test  -Denv="PRODUCTION" -Dsuite="TEST_127"

git checkout master
git pull --rebase
git checkout feature/data-driven
git rebase master
sau khi fix conflict thì
git add -A
git rebase --continue
sau khi hết conflict
git push -f origin feature/data-driven
git push origin feature/data-driven
Access token: b1P-BbEpuZAS6_gyxVaq

https://jira.zalopay.vn/browse/TEST-3065

curl --location --request POST "https://jira.zalopay.vn/rest/raven/1.0/import/feature?projectKey=TEST" --header "Content-Type: multipart/form-data" --header "Authorization: Basic YXV0b21hdGlvbi10ZXN0ZXI6QWExMjM0NTY=" --form "file=@BANK_DATA_DRIVEN.feature"

project = 10800 and issuetype in (10702, 10705) and created >= startOfDay() and labels = Project_BankIntegration

dumpsys window windows | grep -E 'mCurrentFocus'

appium -p 4444 --allow-insecure chromedriver_autodownload --default-capabilities "{\"appium:newCommandTimeout\": 3000}"

appium -p 4725 --allow-insecure chromedriver_autodownload,get_server_logs --default-capabilities '{"newCommandTimeout": 0}'

curl -H "Content-Type: multipart/form-data" -u automation-tester:Aa123456 -F "file=@BankIntegration.feature" https://jira.zalopay.vn/rest/raven/1.0/import/feature?projectKey=TEST


alias adb='/Users/lap13736/Library/Android/sdk/platform-tools/adb'
export JAVA_HOME=$(/usr/libexec/java_home)
export ANDROID_HOME=/Users/lap13736/Library/Android/sdk
export ANDROID_SDK_ROOT=/Users/lap13736/Library/Android/sdk
export ANDROID_AVD_HOME=/Users/lap13736/.android/avd
export PATH=$JAVA_HOME/bin:$PATH
export IDB_HOME=/Users/lap13736/Library/Python/3.8/bin/
export PATH=$IDB_HOME:$PATH
export PATH=$ANDROID_HOME/bundle-tool:$PATH
export PATH=$ANDROID_HOME/tools/bin:$PATH







			
