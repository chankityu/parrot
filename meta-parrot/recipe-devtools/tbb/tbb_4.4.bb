SUMMARY = "Threading building blocks"

SRC_URI = "https://www.threadingbuildingblocks.org/sites/default/files/software_releases/source/tbb44_20150728oss_src.tgz"


LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://COPYING;md5=8a588c70f137dbe9585bda6f12f637fa"

SRC_URI[md5sum] = "6bf7b2618955ce2574dbe4a308035eb4"
SRC_URI[sha256sum] = "7ce30e71154e0da70351d423fd34054efd84345c4aafa43824a59340efe99578"

inherit autotools distro_features_check

