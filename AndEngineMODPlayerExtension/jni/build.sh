#! /bin/sh

NDK_DIRECTORY="C:/Android/android-ndk/r8e/"
PROJECT_DIRECTORY="D:/Users/Karl/Documents/NetBeansProjects/AndEngineMODPlayerExtension/"

# Run build:
cd ${PROJECT_DIRECTORY}
${NDK_DIRECTORY}ndk-build

# Clean temporary files:
rm -rf ${PROJECT_DIRECTORY}obj
