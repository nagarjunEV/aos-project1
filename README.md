# NXE210001 : aos-project1

The project contains three java files
1. SocServer.java being Server - P1
2. SocClient.java being Client - P2
3. Utils.java

Commands to execute the program
1. make -f Makefile.mak clean
2. make -f Makefile.mak

Two files are created by name clientData.txt and serverData.txt which has 300 bytes of randomly generated data.

P1 sends data to P2 in chunks of 100 bytes each for 3 times.
P2 sends data to P1 in chunks of 75 bytes each for 4 times.

