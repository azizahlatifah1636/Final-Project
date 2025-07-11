#!/bin/sh
# Gradle wrapper script for Unix-based systems

SCRIPT_DIR=$(dirname "$0")
exec java -Xmx64m -jar "$SCRIPT_DIR/gradle-wrapper.jar" "$@"
