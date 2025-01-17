#This file is part of SECONDO.
#
#Copyright (C) 2004, University in Hagen, Department of Computer Science, 
#Database Systems for New Applications.
#
#SECONDO is free software; you can redistribute it and/or modify
#it under the terms of the GNU General Public License as published by
#the Free Software Foundation; either version 2 of the License, or
#(at your option) any later version.
#
#SECONDO is distributed in the hope that it will be useful,
#but WITHOUT ANY WARRANTY; without even the implied warranty of
#MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#GNU General Public License for more details.
#
#You should have received a copy of the GNU General Public License
#along with SECONDO; if not, write to the Free Software
#Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
#
########################################################################
#
# SECONDO makefile for utility modules
#
########################################################################

include ../makefile.env
#LIBXML2 := /opt/local/lib/libxml2.dylib
LIBXML2 := 
CC := g++
LD := g++

APPLOBJECTS := \
	SecondoTTY.$(OBJEXT) \
	DisplayTTY.$(OBJEXT) \
	MainTTY.$(OBJEXT) \
	cmsg.$(OBJEXT) \
	MainTTYCS.$(OBJEXT)

APPLICATIONS := $(BINDIR)/Secondo$(SMIUP) \
		$(BINDIR)/SecondoCS

# some optional objects and applications
ifneq ($(smitype),ora)
APPLOBJECTS += TestRunner.$(OBJEXT)
endif


ifeq ($(readline),true)
CCFLAGS += -DREADLINE
ifneq ($(optimizer),"true")
RL_LD_FLAG = -lreadline -lncurses
endif
endif

# Some optional switches used for providing a prolog
# predicate which maximizes the entropy. For details
# refer to "SecondoPL.cpp" and the subdirectory "Optimizer/Entropy"
ifdef ENTROPY 

MK_ENTROPY := entropy
ENTROPY_OBJ := ../Optimizer/Entropy/Iterative_scaling.$(OBJEXT)
CCFLAGS += -DSECONDO_USE_ENTROPY
endif

ifeq ($(optimizer),"true")
PLOBJ := SecondoPL.$(OBJEXT) SecondoPLMode.$(OBJEXT)
PL_OBJECTS := $(PLOBJ) $(ENTROPY_OBJ)
APPLOBJECTS += $(PLOBJ)
CCFLAGS += $(PLINCLUDEFLAGS) -DSECONDO_PL
endif

BDB_OBJECTS :=  TestRunner.o \
		../ClientServer/SecondoServer.o
		 
ALG_LIST    :=  SecondoInterface.o AlgebraList.o

TTY_OBJECTS :=  cmsg.o \
		SecondoInterfaceGeneral.o \
	  SecondoTTY.o \
		DisplayTTY.o

FLOB := $(SECONDO_BUILD_DIR)/Tools/Flob/FlobManager.o

CS_OBJECTS :=   MainTTYCS.o SecondoInterfaceCS.o


APP_LIB := ../lib/libappCommon.$(LIBEXT)

APPLICATIONS := $(addsuffix $(EXEEXT), $(APPLICATIONS))

#
# TARGET SECTION
#

.PHONY: all
all: $(CODE_DEPENDENCIES) $(MK_ENTROPY) $(APPLOBJECTS) 
	@chmod u+x ../bin/SecondoTTYBDB
	@chmod u+x ../bin/TestRunner
	@chmod u+x ../bin/TestRunnerCS
	@chmod u+x ../bin/SecondoTTYCS

.PHONY: buildapp
buildapp: $(APPLICATIONS)

.PHONY: secondobdb
secondobdb: $(BINDIR)/Secondo$(SMIUP)

.PHONY: TTYDemo
TTYDemo: ../$(SECONDO_DEMO)/SecondoTTY$(SMIUP)$(EXEEXT)

.PHONY: entropy
entropy:
	$(MAKE) -C ../Optimizer/Entropy

.PHONY: clean
clean:
	$(RM) *.dep $(APPLOBJECTS) $(APPLICATIONS) $(ENTROPY_OBJ) $(DEP_FILES)


#
# RULES SECTION
#
# create dependencies from include directives

CCFLAGS += $(DEFAULTCCFLAGS)

# NVK ADDED MA
MEMORY_ALLOCATION := true
ifdef MEMORY_ALLOCATION
APPLOBJECTS += MemoryOptimization.$(OBJEXT) 
TTY_OBJECTS += MemoryOptimization.o 
MAFLAGS := -lnlopt -DSECONDO_USE_MEMORY_ALLOCATION
CCFLAGS += $(MAFLAGS)
MO_OBJECTS := MemoryOptimization.o
MemoryOptimization.$(OBJEXT): MemoryOptimization.$(OBJEXT)
MemoryOptimization.$(OBJEXT): MemoryOptimization.cpp
	$(CC) -c -o $@ $< $(CCFLAGS) $(MAFLAGS)
endif
# NVK ADDED MA END

MainTTYCS.$(OBJEXT): MainTTY.$(OBJEXT)
MainTTYCS.$(OBJEXT): MainTTY.cpp
	$(CC) -c -o $@ $< $(CCFLAGS) -DSEC_TTYCS 

SecondoPL.$(OBJEXT): $(ENTROPY_OBJ) $(MO_OBJECTS)
%.$(OBJEXT): %.cpp
	$(CC) -c -o $@ $< $(CCFLAGS) $(JNIINCLUDE) $(MAFLAGS)


# Rules for linkage
#
# Note: Currently, the SecondoInterface depends on a static function of the relation
# algebra which returns tuple statistics. We should change this in the future.

$(APP_LIB): $(TTY_OBJECTS) $(BDB_OBJECTS) 	
	$(create-lib)

# SecondoBDB, the stand alone application.
$(BINDIR)/Secondo$(SMIUP)$(EXEEXT): $(SECLIBFILES) $(ALGLIBFILES) $(JNILIBFILES) MainTTY.o $(FLOB)
$(BINDIR)/Secondo$(SMIUP)$(EXEEXT): MainTTY.o $(APP_LIB) $(ALG_LIST) $(FLOB) $(PL_OBJECTS)
	$(LD) $(EXEFLAGS) $(LDFLAGS) $(LDJNIFLAGS) $(PROFILERFLAGS) $(LIBXML2) -o $@ $(filter %.o, $^) -lappCommon -lsdbutils $(MAFLAGS) -lm $(LD_LINK_LIBS_ALG) $(RL_LD_FLAG) $(PLLDFLAGS) 


# The TTY-Clients, they use special implementations of SecondoInterface and don't
# need the algebra libs.
# 
$(BINDIR)/SecondoCS$(EXEEXT): $(SECLIBFILES) $(FLOB)
$(BINDIR)/SecondoCS$(EXEEXT): $(CS_OBJECTS) $(APP_LIB) $(PL_OBJECTS) $(FLOB)
	$(LD) $(EXEFLAGS) $(LDFLAGS) $(PROFILERFLAGS) -o $@ $(filter %.o, $^) -lappCommon -lsdbutils $(MAFLAGS) -lm $(OPTLIB_LIBS) $(LD_LINK_LIBS) $(RL_LD_FLAG) $(PLLDFLAGS)


ifneq ($(MAKECMDGOALS),clean)
include $(DEP_FILES:.cpp=.dep)
endif
