package com.hblolj.mediacontroller.Utils;

/**
 * HDL CRC校验类
 * @author hblolj
 *
 */
public class CRCUtils {

	public static void main(String[] args) {
		String hdlOrder = "aaaa0d0cfefffe0000011f0203";
//		System.out.println(hdlOrder.length()/2);
		byte[] bs = CommonUtil.toByteArray(hdlOrder);
		getCRC(bs);
	}

	public static String getCRC(byte[] bytes){

		int crc = 0xaaaa;  //初始值
		for (byte b : bytes) {
			crc = (crc << 8) ^ CRCTab.crc_tab[((crc >>> 8) ^ b) & 0xff];
		}
		byte[] b = new byte[2];
		b[0] = (byte) (crc >> 8);
		b[1] = (byte) (crc >> 0);

		String checkCode = CommonUtil.bytesToHexString(b);
//		System.out.println(checkCode);
		return checkCode;
	}

}
