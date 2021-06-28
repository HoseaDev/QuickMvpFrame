package com.youkemm.kcommon.ktx.core.ext

import java.math.BigDecimal
import java.util.regex.Pattern

/**
 * Created by Harry
 * on 2019/7/23 9:25
 */

/**
 * if [String.isNullOrEmpty], invoke f()
 * otherwise invoke t()
 */
fun <T> String?.notNull(f: () -> T, t: () -> T): T {
    return if (isNullOrEmpty()) f() else t()
}

/**
 * whether string only contains digits
 */
fun String.areDigitsOnly() = matches(Regex("[0-9]+"))


fun String?.isNullOrEmptyOrEqual0():Boolean{
    if (isNullOrEmpty()){
        return true
    }
    if (this=="0"){
        return true
    }
    return false
}

fun String.isNumeric(): Boolean {
    // 该正则表达式可以匹配所有的数字 包括负数
    val pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?")
    val bigStr: String = try {
        BigDecimal(this).toString()
    } catch (e: Exception) {
        return false //异常 说明包含非数字。
    }

    val isNum = pattern.matcher(bigStr) // matcher是全匹配

    return isNum.matches()

}fun String.replacePoint0(): String {
    var str=this
    if ( this.indexOf(".") > 0) {
        str = str.replace("0+?$".toRegex(), "") //去掉多余的0
        str = str.replace("[.]$".toRegex(), "") //如最后一位是.则去掉
    }


    return str

}