#!/usr/bin/env bash

./gradlew clean

rm java.zip

rm -rf /tmp/tax_calculator
mkdir -p /tmp/tax_calculator
cp -R . /tmp/tax_calculator

rm -rf /tmp/tax_calculator/.git*
rm -rf /tmp/tax_calculator/.idea
rm -rf /tmp/tax_calculator/.gradle
rm -rf /tmp/tax_calculator/archive.sh

current_dir=`pwd`

cd /tmp
zip -r java.zip tax_calculator

cd $current_dir
mv /tmp/java.zip .

rm -rf /tmp/tax_calculator