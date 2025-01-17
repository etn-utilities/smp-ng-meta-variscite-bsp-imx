# Copyright (C) 2013-2016 Freescale Semiconductor
# Copyright 2017 NXP
# Copyright 2018-2020 Variscite Ltd.
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Linux kernel provided and supported by Variscite"
DESCRIPTION = "Linux kernel provided and supported by Variscite (based on the kernel provided by NXP) \
with focus on i.MX Family SOMs. It includes support for many IPs such as GPU, VPU and IPU."

require recipes-kernel/linux/linux-imx.inc
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

FILES:${KERNEL_PACKAGE_NAME}-base += "${nonarch_base_libdir}/modules/${KERNEL_VERSION}/modules.builtin.modinfo "

DEPENDS += "lzop-native bc-native"

DEFAULT_PREFERENCE = "1"

KERNEL_SRC ?= "git://github.com/varigit/linux-imx;protocol=https"

SRCBRANCH:imx93-var-som = "lf-5.15.y_var01"
SRCREV:imx93-var-som = "ea8935a3f0aabb0a774c7ea7c58be529c15f2af0"
LINUX_VERSION:imx93-var-som = "5.15.71"

SRCBRANCH = "5.15-2.0.x-imx_var01"
SRCREV = "a10598220c20e5b259f03e110edae4489f6df188"
LINUX_VERSION = "5.15.60"

SRC_URI = "${KERNEL_SRC};branch=${SRCBRANCH}"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

LOCALVERSION:imx6ul-var-dart = "-imx6ul"
LOCALVERSION:imx8mp-var-dart = "-imx8mp"
LOCALVERSION:imx8mq-var-dart = "-imx8mq"
LOCALVERSION:imx8mm-var-dart = "-imx8mm"
LOCALVERSION:imx8mn-var-som = "-imx8mn"
LOCALVERSION:imx8qxp-var-som = "-imx8x"
LOCALVERSION:imx8qxpb0-var-som = "-imx8x"
LOCALVERSION:imx8qm-var-som = "-imx8qm"
LOCALVERSION:imx93-var-som = "-imx93"

KBUILD_DEFCONFIG:mx6-nxp-bsp = "imx_v7_var_defconfig"
KBUILD_DEFCONFIG:mx8-nxp-bsp = "imx8_var_defconfig"
KBUILD_DEFCONFIG:mx9-nxp-bsp = "imx8_var_defconfig"
KBUILD_DEFCONFIG:imx8mq-var-dart = "imx8mq_var_dart_defconfig"
DEFAULT_DTB:imx8mq-var-dart = "sd-lvds"
DEFAULT_DTB:imx8qxp-var-som = "sd"
DEFAULT_DTB:imx8qxpb0-var-som = "sd"
DEFAULT_DTB:imx8qm-var-som = "lvds"
DEFAULT_DTB_PREFIX:imx8mq-var-dart = "imx8mq-var-dart-dt8mcustomboard"
DEFAULT_DTB_PREFIX:imx8qxp-var-som = "imx8qxp-var-som-symphony"
DEFAULT_DTB_PREFIX:imx8qxpb0-var-som = "imx8qxp-var-som-symphony"
DEFAULT_DTB_PREFIX:imx8qm-var-som = "imx8qm-var-som-symphony"

pkg_postinst:kernel-devicetree:append () {
   rm -f $D/boot/devicetree-*
}

pkg_postinst:kernel-devicetree:append:imx8mq-var-dart () {
    cd $D/boot
    ln -s ${DEFAULT_DTB_PREFIX}-${DEFAULT_DTB}.dtb ${DEFAULT_DTB_PREFIX}.dtb
    ln -s ${DEFAULT_DTB_PREFIX}-legacy-${DEFAULT_DTB}.dtb ${DEFAULT_DTB_PREFIX}-legacy.dtb
}

pkg_postinst:kernel-devicetree:append:imx8qxp-var-som () {
    cd $D/boot
    ln -s ${DEFAULT_DTB_PREFIX}-${DEFAULT_DTB}.dtb ${DEFAULT_DTB_PREFIX}.dtb
}

pkg_postinst:kernel-devicetree:append:imx8qxpb0-var-som () {
    cd $D/boot
    ln -s ${DEFAULT_DTB_PREFIX}-${DEFAULT_DTB}.dtb ${DEFAULT_DTB_PREFIX}.dtb
}

pkg_postinst:kernel-devicetree:append:imx8qm-var-som () {
    cd $D/boot
    ln -s ${DEFAULT_DTB_PREFIX}-${DEFAULT_DTB}.dtb ${DEFAULT_DTB_PREFIX}.dtb
    ln -s imx8qp-var-som-symphony-${DEFAULT_DTB}.dtb imx8qp-var-som-symphony.dtb
    ln -s imx8qm-var-spear-sp8customboard-${DEFAULT_DTB}.dtb imx8qm-var-spear-sp8customboard.dtb
    ln -s imx8qp-var-spear-sp8customboard-${DEFAULT_DTB}.dtb imx8qp-var-spear-sp8customboard.dtb
}

KERNEL_VERSION_SANITY_SKIP="1"
COMPATIBLE_MACHINE = "(mx8-nxp-bsp|mx9-nxp-bsp)"
