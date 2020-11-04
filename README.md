### URL for create base project
https://developer.lightbend.com/start/?group=play&project=play-scala-seed

### Related technologies
- Play framework: https://www.playframework.com/
- Akka: https://akka.io/
- Slick: http://scala-slick.org/

### Dependencies for play-slick and sqlite-jdbc searched in
- https://mvnrepository.com/

### Command for updated dependencies added in build.sbt
```
sbt update
```

### Command to compile and be sure that everything compiles correctly
```
sbt compile
```

### Command for start server in development mode
```
sbt run
```
### URL for initialize database
```
Method: GET
URL: http://localhost:9000/dbInit
```

### Commands for export project to Docker

- `sbt` Start sbt console
- `playUpdateSecret` Generate secret key in application.conf (This should be executed in sbt console)
- `start` Start server in production mode (This should be executed in sbt console)
- `sbt dist` Generate .zip file for distribution
- `sbt stage` For put .zip file content into "stage" folder

- `sbt`
- `docker:publishLocal` Export aplication to a new docker image'
