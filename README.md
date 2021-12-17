# Saturn
A tool to analysis the log files from minecraft to scan potential security risks from the CVE-2021-44228 Log4J library exploit. 

## What happened?
In 2021 something incredible was found!

The Java library "Log4J" from Apache has a major security vulnerability in the code that allows people to execute code remotely (RCE (Remote Code Execution))!

A lot of Programs and Games that written in Java uses the library and a lot of these was vulnerable to the exploit like Minecraft.

## What does Saturn?
Saturn scans log files that are located in the .minecraft folder to see if you are got a vicitm of this exploit.
It'll also scan for potential exploit code bypasses, so it's not 100% accurate.

## How I execute this program?
You just need Java 16 and enter in the CMD or terminal the following code:

```
java -jar Saturn.jar
```

#
#
### Stay safe and never give up! <3