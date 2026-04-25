
find . -name "*.class" -delete


javac --module-path ~/javafx-sdk/lib \
--add-modules javafx.controls,javafx.graphics \
-d out \
src/model/*.java \
src/traffic/*.java \
src/simulation/*.java \
src/ui/*.java \
src/input/*.java \
src/utils/*.java


java --module-path ~/javafx-sdk/lib \
--add-modules javafx.controls,javafx.graphics \
-cp out \
ui.MainApp