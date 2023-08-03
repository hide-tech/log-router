Installation guide(Win):
1) install docker desktop (download exe from internet and start this)
2) install gradle (download and unzip archive, add environment variable)
3) install git (download exe and run it)
4) create a folder and enter this
5) open terminal on this folder
6) git clone https://github.com/hide-tech/log-router.git (terminal command)
7) ./gradlew clean build (terminal command)
8) docker compose up (terminal command)
9) open postman or another tool for http queries
10) hit endpoints "localhost:9000/router/v1/logs | localhost:9000/router/v1/logs/report"
11) for debugger opened port 8000