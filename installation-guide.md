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

Endpoints:
- /router/v1/logs:
    GET - get all logs
    POST - create new log (body: 
{
  "tripDate": "2022-05-23",
  "regNumber": "10YXY677",
  "ownerName": "not mine",
  "route": "Toshkent-Tbilisi",
  "odometerStart": "145768.1",
  "odometerEnd": "147000.1",
  "description": "trip"
})
- /router/v1/logs/{id}:
    PUT - update log (id - log id, body:
{
  "tripDate": "2022-05-23",
  "regNumber": "10YXY677",
  "ownerName": "not mine",
  "route": "Toshkent-Tbilisi",
  "odometerStart": "145768.1",
  "odometerEnd": "147000.1",
  "description": "trip"
})
    DELETE - delete log (id - log id for deletion)
- /router/v1/logs/report
    GET - get logs report (path params:
    startDate - pattern(yyyy-MM-dd),
    endDate - pattern(yyyy-MM-dd),
    ownerName - string(part of name, case insensitive),
    regNumber - string(part of reg number, case insensitive)
)