# NXE210001 : AOS 6378 - Project1

The project contains three java files
1. SocServer.java being Server - P1 running on dc12.utdallas.edu:8888 machine.
2. SocClient.java being Client - P2 running on dc11.utdallas.edu:8888 machine.
3. Utils.java (for constants and reusable functions).

Commands to execute the program
1. "make -f Makefile.mak clean" on both the machines.
2. "make -f Makefile.mak" on both the machines.
3. "make -f Makefile.mak run" on dc12.utdallas.edu machine (Server).
4. "make -f MakefileClient.mak run" on dc11.utdallas.edu machine (Client).

Two files are created by name clientData.txt and serverData.txt which has 300 bytes of randomly generated characters.

P1 sends data to P2 in chunks of 100 bytes each for 3 times.
P2 sends data to P1 in chunks of 75 bytes each for 4 times.
