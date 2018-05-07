# log4j2 + sbt assembly plugin

## Problem

When packaging your application as a "fat jar" with `sbt`'s `assembly` plugin, you may see the following error:
```
[error] java.util.zip.ZipException: duplicate entry: META-INF/MANIFEST.MF
```

You might therefore decide to drop your dependencies' `META-INF` files in an attempt to fix this error.
However, depending on the rules configured in `build.sbt`'s `assemblyMergeStrategy`, and how aggressive you went, critical `log4j2` files might be dropped, resulting in errors like:
```
ERROR StatusLogger Log4j2 could not find a logging implementation. Please add log4j-core to the classpath. Using SimpleLogger to log to the console...
```

## Solution

Configure your `build.sbt` to have:

```scala
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case _ => MergeStrategy.first
}
```

Run:
```bash
$ sbt clean assembly
$ java -jar target/scala-2.12/log4j2-sbt-assembly-assembly-0.1.0-SNAPSHOT.jar
{"thread":"main","level":"INFO","loggerName":"example.Hello$","message":"hello","endOfBatch":false,"loggerFqcn":"org.apache.logging.log4j.scala.Logger$","instant":{"epochSecond":1525709528,"nanoOfSecond":179000000},"threadId":1,"threadPriority":5,"timestamp":"2018-05-07T17:12:08.179+0100"}
```
