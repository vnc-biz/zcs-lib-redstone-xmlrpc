
TOPDIR=../../
JAR_FILE_NAME=zcs-lib-redstone-xmlrpc.jar

INSTALL_USER=y
INSTALL_ADMIN=y
INSTALL_SERVICE=y
INSTALL_EXTENSION=y
INSTALL_LIB=y
INSTALL_ZIMLET=y

ZCS_LIB_JARS=\
	servlet-api.jar

JAR_FILE_PREPARE_RULE=jar_file_prepare

include $(TOPDIR)/zcs-java-lib.mk

jar_file_prepare:
	@cp src/redstone/xmlrpc/*.properties classes/redstone/xmlrpc/
	@dos2unix classes/redstone/xmlrpc/*.properties
