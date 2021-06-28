package com.youkemm.kcommon.ktx.core.ext

import okhttp3.internal.and

/**
 * Create by harry
 * on 2021/6/28
 *
 */
class TransformUtils {
    companion object{

        fun bytes2Int(bytes: ByteArray): Int {
            return (bytes[0] and 0XFF shl 24
                    or (bytes[1] and 0xFF shl 16)
                    or (bytes[2] and 0xFF shl 8)
                    or (bytes[3] and 0xFF))

        }




        /**
         * int 转 byte[]
         */
        fun int2Bytes(i: Int): ByteArray {
            val bytes = ByteArray(4)
            bytes[0] = (i shr 24 and 0xFF).toByte()
            bytes[1] = (i shr 16 and 0xFF).toByte()
            bytes[2] = (i shr 8 and 0xFF).toByte()
            bytes[3] = (i and 0xFF).toByte()
            return bytes
        }

        /**
         * byte[] 转 short
         */
        fun bytes2Short(bytes: ByteArray): Short {
            return if (bytes.isEmpty()) 0 else (bytes[0] and 0xff or
                    (bytes[1] and 0xff) shl 8).toShort()
        }

        /**
         * short 转 byte[]
         */
        fun short2Bytes(s: Short): ByteArray {
            var temp = s.toInt()
            val b = ByteArray(2)
            for (i in b.indices) {
                b[i] = Integer.valueOf(temp and 0xff).toByte()
                temp = temp shr 8
            }
            return b
        }

        /**
         * byte[] 转 long
         */
        fun bytes2Long(bytes: ByteArray): Long {
            if (bytes.isEmpty()) return 0
            val s0 = (bytes[0] and 0xff).toLong()
            var s1 = (bytes[1] and 0xff).toLong()
            var s2 = (bytes[2] and 0xff).toLong()
            var s3 = (bytes[3] and 0xff).toLong()
            var s4 = (bytes[4] and 0xff).toLong()
            var s5 = (bytes[5] and 0xff).toLong()
            var s6 = (bytes[6] and 0xff).toLong()
            var s7 = (bytes[7] and 0xff).toLong()
            s1 = s1 shl 8
            s2 = s2 shl 16
            s3 = s3 shl 24
            s4 = s4 shl 8 * 4
            s5 = s5 shl 8 * 5
            s6 = s6 shl 8 * 6
            s7 = s7 shl 8 * 7
            return s0 or s1 or s2 or s3 or s4 or s5 or s6 or s7
        }


        /**
         * long 转 byte[]
         */
        fun long2Bytes(l: Long): ByteArray {
            var temp = l
            val b = ByteArray(8)
            for (i in b.indices) {
                b[i] = java.lang.Long.valueOf(temp and 0xff).toByte()
                temp = temp shr 8
            }
            return b
        }

    }


}