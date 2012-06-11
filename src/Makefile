
SUBDIRS=`find -mindepth 1 -maxdepth 1 -type d | sed -e 's~\./~~'`

all:
	@for t in $(SUBDIRS) ; do if [ -f "$$t/Makefile" ] ; then $(MAKE) -C $$t ; fi done

clean:
	@for t in $(SUBDIRS) ; do if [ -f "$$t/Makefile" ] ; then $(MAKE) -C $$t clean ; fi ; done
