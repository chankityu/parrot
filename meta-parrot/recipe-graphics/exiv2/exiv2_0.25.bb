LICENSE = "GPL"
SRC_URI = "http://www.exiv2.org/exiv2-${PV}.tar.gz"
LIC_FILES_CHKSUM = "file://COPYING;md5=625f055f41728f84a8d7938acc35bdc2"

SRC_URI[md5sum] = "258d4831b30f75a01e0234065c6c2806"
SRC_URI[sha256sum] = "c80bfc778a15fdb06f71265db2c3d49d8493c382e516cb99b8c9f9cbde36efa4"


DEPENDS = "zlib"

inherit cmake gettext

#B = "${WORKDIR}/exiv2-${PV}"

#EXTRA_OECONF += " --disable-rpath"

do_install_append(){
    mkdir -p ${D}${libdir}
    mv ${D}/usr/lib/* ${D}${libdir}
    rmdir ${D}/usr/lib/
}

FILES_${PN}-doc += " \
  /usr/man \
  /usr/man/man1 \
  /usr/man/man1/exiv2.1 \
"
