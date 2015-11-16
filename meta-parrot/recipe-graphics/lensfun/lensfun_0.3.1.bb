SUMMARY = "Library to correct optical lens defects and lens database"
LICENSE = "LGPLv3"
SRC_URI = "${SOURCEFORGE_MIRROR}/lensfun/${PV}/lensfun-${PV}.tar.gz"
LIC_FILES_CHKSUM = "file://README;md5=8a588c70f137dbe9585bda6f12f637fa"

SRC_URI[md5sum] = "7f577385f98b260ea5384b99c6eb03aa"
SRC_URI[sha256sum] = "216c23754212e051c8b834437e46af3812533bd770c09714e8c06c9d91cdb535"


DEPENDS = "libpng libxrender waffle virtual/libgl"

EXTRA_OECMAKE = " -DCMAKE_BUILD_TYPE=Release"

inherit cmake pythonnative distro_features_check perlnative 

do_pre_configure() {
cat >> ${WORKDIR}/toolchain.cmake << EOF
set(CMAKE_MODULE_PATH ${CMAKE_MODULE_PATH} "${WORKDIR}/lensfun-${PV}/build/CMakeModules/")
EOF 
}

addtask pre_configure before do_configure after do_patch

# Hack for multilib setup
FILES_${PN} += " ${libdir} \
  ${libdir}/liblensfun.so.0 \
  ${libdir}liblensfun.so.0.3.1 \
  ${libdir}pkgconfig \
  ${libdir}/pkgconfig/lensfun.pc  \
  /usr/lib \
"

FILES_${PN}-dev += " ${libdir}/liblensfun.so \
"

FILES_${PN}-dbg += " ${libdir}/.debug \
	 ${libdir}/.debug/liblensfun.so.0.3.1 \
"

do_install_append(){
    mkdir -p ${D}${libdir}
    mv ${D}/usr/lib/* ${D}${libdir}
}

EXPORT_FUNCTIONS do_pre_configure
