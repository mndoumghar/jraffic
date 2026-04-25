#!/bin/bash

# =========================
# CLEAN OLD BUILD FILES
# =========================
find . -name "*.class" -delete

# =========================
# COMPILE PROJECT
# =========================
javac --module-path ~/javafx-sdk/lib \
--add-modules javafx.controls,javafx.graphics \
-d out \
src/model/*.java \
src/traffic/*.java \
src/simulation/*.java \
src/ui/*.java \
src/input/*.java \
src/utils/*.java

# =========================
# RUN PROJECT
# =========================
java --module-path ~/javafx-sdk/lib \
--add-modules javafx.controls,javafx.graphics \
-cp out \
ui.MainApp