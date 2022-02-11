JFLAGS = -g
SERVER = SocServer
CLIENT = SocClient
JC = javac
JVM = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        SocServer.java \
        SocClient.java

default: classes

classes: $(CLASSES:.java=.class)

run:	$(CLIENT).class
	$(JVM) $(CLIENT)

clean:
	$(RM) *.class