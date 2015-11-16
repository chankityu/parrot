LICENSE = "GPLv3"
SRC_URI = "https://github.com/embree/embree/archive/v${PV}.tar.gz"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI[md5sum] = "059ed1e41af564ed7661e817433a38b3"
SRC_URI[sha256sum] = "4620707b7236ec18e843797a1ec7e2734ea23cf5aac43fdcb1f373b09e5cd8fc"

DEPENDS = "tbb"

EXTRA_OECMAKE = " -DENABLE_ISPC_SUPPORT=OFF \
				-DENABLE_TUTORIALS=OFF \
				-DXEON_ISA=SSE2 \
"

inherit cmake distro_features_check 

do_install_append(){
	mkdir -p ${D}${libdir}
	mv ${D}/usr/lib/* ${D}${libdir}
}

FILES_${PN} += "  \
  /usr/lib/
  ${libdir}/cmake \
  ${libdir}/cmake/embree-2.7.1 \
  ${libdir}/cmake/embree-2.7.1/embree-config-version.cmake \
  ${libdir}/cmake/embree-2.7.1/embree-config.cmake  \
"
