package com.blueberryprojects.xchange.common.extensions

fun Double.formatAmount(): String {
    return String.format("%.2f", this)
}