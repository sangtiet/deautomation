appium -p 4733 --allow-insecure chromedriver_autodownload --default-capabilities "{\"appium:newCommandTimeout\": 3000}"
appium -p 4444 --chromedriver-executable C:\Users\MININT-EU2JUTI-local\Downloads\chromedriver.exe
adb shell settings put secure autofill_service null

git checkout master
git pull --rebase
git checkout feature/UM_dev07302020
git rebase master
sau khi fix conflict thì
git add -A
git rebase --continue
esc + ":wq"
sau khi hết conflict
git push -f origin feature/UM_dev07302020