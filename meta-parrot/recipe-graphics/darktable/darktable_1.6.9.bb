SUMMARY = "An open source photography workflow application and RAW developer"
LICENSE = "GPLv3 "
LIC_FILES_CHKSUM = "file://README.md;md5=e6864c40cae57e45a62556c622f17e7c"

SRC_URI = "https://github.com/darktable-org/darktable/releases/download/release-1.6.9/darktable-1.6.9.tar.xz"

SRC_URI[md5sum] = "d4a52d06c37209afc31c0d0ffdc87ed9"
SRC_URI[sha256sum] = "0f721e9d298a9407f6c0325d9c95b9dc37fa60f3b6a2f2e3b5675ff97c423173"

#S = "${WORKDIR}/git"

DEPENDS = "perl exiv2 lensfun libxml2 jpeg curl librsvg libsoup-2.4 gdk-pixbuf tiff libsdl libxrandr gtk+ pango cairo libglu"
DEPENDS += " sqlite3 lcms"

#DEPENDS = "virtual/libx11 libxrender waffle virtual/libgl libglu python-mako-native python-numpy-native"

inherit cmake pythonnative distro_features_check perlnative

EXTRA_OECMAKE = "-DCMAKE_BUILD_TYPE=Release"

do_install_append(){
    mkdir -p ${D}${libdir}
    mv ${D}/usr/lib/* ${D}${libdir}
    rmdir ${D}/usr/lib/
}

FILES_${PN} += " \
	/usr/share/* \
"
FILES_${PN}-dbg += "\
	${libdir}/darktable/views/.debug/libdarkroom.so \
	${libdir}/darktable/views/.debug/liblighttable.so \
	${libdir}/darktable/views/.debug/libmap.so \
  	${libdir}/darktable/views/.debug/libslideshow.so \
	${libdir}/darktable/plugins/.debug/ \
	${libdir}/darktable/plugins/*/.debug/ \
	${libdir}/darktable/plugins/imageio/format/.debug/ \
	${libdir}/darktable/plugins/imageio/storage/.debug/ \
"
