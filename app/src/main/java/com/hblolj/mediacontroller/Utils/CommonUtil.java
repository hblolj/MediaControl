package com.hblolj.mediacontroller.Utils;

public class CommonUtil {


    public static void main(String[] args) {

//		byte[] sendData2 = "Hello, World!".getBytes();
//		String hexString = CommonUtil.bytesToHexString(sendData2);
//		System.out.println(hexString);
//
//		byte[] sendData = new byte[15];
//		sendData[0] = (byte) 0xAA;
//		sendData[1] = (byte) 0xAA;
//		sendData[2] = (byte) 0x0d;
//		sendData[3] = (byte) 0x01;
//		sendData[4] = (byte) 0x64;
//		sendData[5] = (byte) 0x00;
//		sendData[6] = (byte) 0x30;
//		sendData[7] = (byte) 0x00;
//		sendData[8] = (byte) 0x02;
//		sendData[9] = (byte) 0x01;
//		sendData[10] = (byte) 0x02;
//		sendData[11] = (byte) 0x01;
//		sendData[12] = (byte) 0x01;
//		sendData[13] = (byte) 0x06;
//		sendData[14] = (byte) 0xC5;
//
//		String hexString3 = "0xAA0xAA0x0d0x010x640x000x300x000x020x010x020x010x010x060xC5";
//		byte[] byteArray = CommonUtil.toByteArray(hexString3);
//		String hexString2 = CommonUtil.bytesToHexString(sendData);
//		System.out.println(hexString2);

        System.out.println(Ten2Hex(10));
    }

    /**
     * byte数组转换成16进制字符串
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * byte数组转换成16进制字符数组
     * @param src
     * @return
     */
    public static String[] bytesToHexStrings(byte[] src){
        if (src == null || src.length <= 0) {
            return null;
        }
        String[] str = new String[src.length];

        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                str[i] = "0";
            }
            str[i] = hv;
        }
        return str;
    }

    /**
     * 16进制的字符串表示转成字节数组
     *
     * @param hexString 16进制格式的字符串
     * @return 转换后的字节数组
     **/
    public static byte[] toByteArray(String hexString) {
        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++) {//因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }

    //十进制转16进制,一位的话，前面补零
    public static String Ten2Hex(int ten){
        String hexString = Integer.toHexString(ten);
        //转换后  是奇数位 需要在前面补零
        if(hexString.length() == 1){
            hexString  = "0" + hexString;
        }

        return hexString;
    }
}
