package com.hblolj.mediacontroller.Utils;

import com.hblolj.mediacontroller.Constant.JTConstant;

/**
 * 指令工具类
 * @author 昀尚智能
 *
 */
public class OrderUtils {

	/**
	 * 拼装指令
	 * @param actionCode
	 * @param targetChildNetId
	 * @param targetDeviceId
	 * @param param
	 */
	public static byte[] createOrder(String actionCode, String targetChildNetId, String targetDeviceId, String param){
		String order = JTConstant.PRE_ORDER + "长度";

		String content = getSourceChildNetId() + getSourceDeviceId() + getSourceDeviceType() + actionCode +
				targetChildNetId + targetDeviceId + param;
		content = content.replace(" ", "");
		int len = content.length() / 2 + JTConstant.PRE_ORDER.length() / 2 + 1;
		String order_len = CommonUtil.Ten2Hex(len);
		order = JTConstant.PRE_ORDER + order_len + content;//拿到没有CRC的指令
//		System.out.println("order: " + order);
		return createCRCOrder(order);
	}

	/**
	 * 生成包含CRC的指令，并转换为byte[]
	 * @param order
	 * @return
	 */
	public static byte[] createCRCOrder(String order){
		order = order.replace(" ", "");
		byte[] bs = CommonUtil.toByteArray(order);
		String crc = CRCUtils.getCRC(bs);
		order = JTConstant.LOGO_ORDER + order + crc;
		order = order.replace(" ", "");
		System.out.println("完整order: " + order);
		return CommonUtil.toByteArray(order);
	}

	//获取源子网号
	public static String getSourceChildNetId(){
		return "FD";
	}

	//获取源设备号
	public static String getSourceDeviceId(){
		return "FE";
	}

	//获取源设备类型
	public static String getSourceDeviceType(){
		return "FFFE";
	}
}
