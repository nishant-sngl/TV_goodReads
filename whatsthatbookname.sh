rm test-output/Extent.html
mvn clean test -DsuiteXmlFile=whatsthatbookname.xml -Dinput="$1"