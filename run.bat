if exist "exec" rmdir /S /Q exec
mkdir exec
xcopy *.java exec\*.java
xcopy contest.jar exec\
xcopy testrun.jar exec\
xcopy MainClass.txt exec\
javac -cp exec\contest.jar exec\*.java
cd exec
jar cmf MainClass.txt submission.jar *.class
cd ..
del exec\*.class
xcopy SphereEvaluation.class exec\
xcopy RastriginEvaluation.class exec\
xcopy FletcherPowellEvaluation.class exec\
java -jar exec\testrun.jar -submission=player42 -evaluation=FletcherPowellEvaluation -seed=%1


