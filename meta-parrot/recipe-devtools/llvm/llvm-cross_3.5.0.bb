SUMMARY = "LLVM cross compiler"
LICENSE = "NCSA"
DEPENDS = "libffi zlib"

SRC_URI = "http://llvm.org/releases/${PV}/llvm-${PV}.src.tar.xz;name=llvm"
SRC_URI += "http://llvm.org/releases/${PV}/cfe-${PV}.src.tar.xz;apply=no;name=clang"
SRC_URI[llvm.md5sum] = "d6987305a1a0e58e128c1374cd3b8fef"
SRC_URI[llvm.sha256sum] = "28e199f368ef0a4666708f31c7991ad3bcc3a578342b0306526dd35f07595c03"
SRC_URI[clang.md5sum] = "27718dd13c7df83e15f997116bbb4aef"
SRC_URI[clang.sha256sum] = "fc80992e004b06f6c7afb612de1cdaa9ac9d25811c55f94fcf7331d9b81cdb8b"
S = "${WORKDIR}/llvm-${PV}.src"
LIC_FILES_CHKSUM = "file://${S}/LICENSE.TXT;md5=47e311aa9caedd1b3abf098bd7814d1d"

inherit cmake pkgconfig python-dir pythonnative cross

LLVM_BUILD_DIR = "${S}/build"

OECMAKE_SOURCEPATH = ".."
OECMAKE_BUILDPATH = "build"
do_unpack() {
    tar -xf ${DL_DIR}/llvm-${PV}.src.tar.xz -C ${WORKDIR}
    tar -xf ${DL_DIR}/cfe-${PV}.src.tar.xz -C ${S}/tools
    mv ${S}/tools/cfe-${PV}.src ${S}/tools/clang
}

#For LLVM_TABLEGEN and CLANG_TABLEGEN, you will need to put the path to the native (host) llvm-tblgen and clang-tblgen
#respectively.

#As for LLVM_LIBDIR_SUFFIX, you can omit that if you are using multilib.
#I added 64 because my multilib points to /usr/lib64.

do_configure() {
    mkdir -p ${LLVM_BUILD_DIR}
    cd ${LLVM_BUILD_DIR}
    cmake \
          ../ \
          -DCMAKE_INSTALL_PREFIX=${prefix}\
          -DLLVM_TABLEGEN=/path/to/native/llvm-tblgen \
          -DCLANG_TABLEGEN=/path/to/native/clang-tblgen \
          -DCMAKE_SYSROOT=${STAGING_DIR}\
		  -DCMAKE_CROSSCOMPILING=True \
          -DLLVM_DEFAULT_TARGET_TRIPLE=${TARGET_SYS} \
		  -DLLVM_LIBDIR_SUFFIX=64 \
		  -DCLANG_RESOURCE_DIR=../lib/clang/${PV}/
}

do_compile() {
    cd ${LLVM_BUILD_DIR}
    oe_runmake
}

do_install(){
    cd ${LLVM_BUILD_DIR}
    oe_runmake DESTDIR=${D} install    

    cp -R ${S}/tools/clang/include/clang ${S}/include 
    cp ${LLVM_BUILD_DIR}/tools/clang/include/clang/Basic/DiagnosticCommonKinds.inc  ${S}/include/clang/Basic

}  

FILES_${PN} += "${bindir}/${LLVM_DIR}/\
		${bindir}/${LLVM_DIR}/clang\
		/usr/share/clang/*\
		"

FILES_${PN}-dev += " ${libdir}/BugpointPasses.so \
                     ${libdir}/LLVMHello.so \
                     ${libdir}/clang  \
                   "

