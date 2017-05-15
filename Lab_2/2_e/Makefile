JETFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Interface.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
