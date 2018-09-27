# Proguard-Unscrambler Plugin
![Travis Status image ](https://travis-ci.org/Xovis/Proguard-Unscrambler.svg?branch=master)

The Proguard-Unscrambler Plugin is a plugin to analyse an obfuscated/scrambled stacktrace.

## How to use
1. Open IntelliJ IDEA
2. Click **Analyze Stacktrace** under the **Analyze** menu
3. Copy the stacktrace in the dialog box
4. Select the unscramble tool "Proguard"
5. Specify the location of the proguard mapping file
6. If your stacktrace isn't normalized yet, click **Normalize**
7. Click **OK**, and then you'll get the analysed stacktrace in the console

## Installation
### Automatic Installation

1. Open IntelliJ IDEA
2. Go to Settings » IDE Settings » Plugins » Available
3. Download the plugin from the list

### Manual Installation

1. Download the zip file
2. Copy the content of the zip file to idea.plugins.path (See idea.properties file)
