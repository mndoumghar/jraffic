#!/bin/bash

cd src || exit 1

rm -rf build
mkdir build

javac \
--module-path ~/javafx-sdk/lib \
--add-modules javafx.controls,javafx.graphics \
-d build \
$(find . -name "*.java")

java \
--enable-native-access=javafx.graphics \
--module-path ~/javafx-sdk/lib \
--add-modules javafx.controls,javafx.graphics \
-cp build \
ui.MainApp 2>&1 | grep -v -E "dconf|Unsafe|OffHeapArray"