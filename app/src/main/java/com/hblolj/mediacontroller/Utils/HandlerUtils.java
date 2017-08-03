package com.hblolj.mediacontroller.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Handler中公用的方法
 * @author 昀尚智能
 *
 */
public class HandlerUtils {

	public static final String ORDER_CONTENT = "OrderContent";//指令内容 aaaa - CRC(不包括CRC校验码)
	public static final String ACTION_CODE = "ActionCode";//操作码
	public static final String ADD_PARAM = "AddParam";//附加参数

	/**
	 * 从byte[]中获取操作码
	 * @param bs
	 */
	public static Map<String, String> getOrderContent(byte[] bs){
		String s = CommonUtil.bytesToHexString(bs);
		return getOrderContent(s);
	}

	/**
	 * c0 - 000000000000000
	 * 从String中获取操作码
	 * @param order
	 */
	public static Map<String, String> getOrderContent(String order){

		order = order.replace(" ", "");

		int indexOfa = order.indexOf("aaaa");
		int indexOfA = order.indexOf("AAAA");

		Map<String, String> map = new HashMap<>();

		if(indexOfa > 0){
			//aaaa开头的指令
			String content = order.substring(indexOfa);
			String len = content.substring(4, 6);
			//拿到有效指令的长度表示 aaaa - CRC(不包含CRC校验码)
			int length = Integer.parseInt(len, 16);
			length = length * 2;
			content = content.substring(0, length);
			String actionCode = content.substring(14, 18);
			String param = content.substring(22);
			map.put(ORDER_CONTENT, content);
			map.put(ACTION_CODE, actionCode);
			map.put(ADD_PARAM, param);
		}else if(indexOfA > 0){
			//AAAA开头的指令
			String content = order.substring(indexOfA);
			String len = content.substring(4, 6);
			//拿到有效指令的长度表示 aaaa - CRC(不包含CRC校验码)
			int length = Integer.parseInt(len, 16);
			length = length * 2;
			content = content.substring(0, length);
			String actionCode = content.substring(14, 18);
			String param = content.substring(22);
			map.put(ORDER_CONTENT, content);
			map.put(ACTION_CODE, actionCode);
			map.put(ADD_PARAM, param);
		}else{
			System.out.println("不是一个有效的指令!");
		}

		return map;
	}

	/**
	 * String指令转byte指令   动态计算CRC
	 * @param order  aaaa - CRC(不包括CRC校验码)
	 * @return byte[]  包含前缀和后面CRC的完整可直接发送的报文
	 */
	public static byte[] StringOrder2Byte(String order, String preOrder){
		preOrder = preOrder.replace(" ", "");
		order = order.replace(" ", "");
		byte[] bs = CommonUtil.toByteArray(order);
		String crc = CRCUtils.getCRC(bs);
		order = preOrder + order + crc;
		return CommonUtil.toByteArray(order);
	}

//	public static BaseDevice getDeviceByType(int type){
//
//		if(type == JTConstant.Four_Loop_16A_Relay){
//			//四路16A继电器
//			Relay r = new Relay<Loop>();
//			r.setAmpere(16);
//			r.setDescription("第四代 4路16A继电器");
//			r.setType("HDL-MR0416.431");
//			r.setScene_num(12);
//			r.setSequence_num(2);
//			r.setLoop_num(4);
//			r.setSequence_step_num(8);
//			r.initLoop(0);
//			return r;
//		}else if(type == JTConstant.Six_Loop_Smart_WattHourMeter){
//			//六路智能电表
//			SixWaySmartMeter r = new SixWaySmartMeter();
//			r.setDescription("6路网络智能数字电表");
//			r.setType("HDL-MPM1P03.231");
//			return r;
//		}else if(type == JTConstant.One_Key_Panel){
//			//一按键触摸面板
//			OneButtonTouchPanel r = new OneButtonTouchPanel();
//			r.setDescription("第四代1按键触摸面板");
//			r.setType("HDL-MPT1.48");
//			return r;
//		}else if(type == JTConstant.Eight_Key_More_Fun_Panel){
//			//八键多功能面板
//			EightButtonMultifunctionPanel r = new EightButtonMultifunctionPanel();
//			r.setDescription("四代八按键多功能面板 音乐 可选 闹钟 抽湿 地热");
//			r.setType("HDL-MPL8.48-FH");
//			return r;
//		}else if(type == JTConstant.Big4_Key_Panel){
//			//大四键面板
//			BigFourKeyPanel r = new BigFourKeyPanel();
//			r.setDescription("第四代面板大4按键");
//			r.setType("HDL-MP8A.48");
//			return r;
//		}else if(type == JTConstant.IR_Emit){
//			//1通道带电流检测红外发射模块
//			OneChannelIREmitModel r = new OneChannelIREmitModel();
//			r.setDescription("1通道带电流检测红外发射模块");
//			r.setType("暂未收集");
//			return r;
//		}else if(type == JTConstant.Logic_Timer){
//			//逻辑定时器	455
//			LogicTimer r = new LogicTimer();
//			r.setDescription("逻辑定时器");
//			r.setType("SB-DN-Logic960");
//			return r;
//		}else if(type == JTConstant.Eight_Loop_Sensor){
//			//八路传感器输入模块
//			EightWaySensorInputModule r = new EightWaySensorInputModule();
//			r.setDescription("八路传感器输入模块");
//			r.setType("暂未收集");
//			return r;
//		}else if(type == JTConstant.IR_Ultrasonic_Double_Sensor){
//			//红外超声波双鉴传感器
//			InfraredUltrasonicDoubleSensor r = new InfraredUltrasonicDoubleSensor();
//			r.setDescription("红外超声波双鉴传感器");
//			r.setType("暂未收集");
//			return r;
//		}else if(type == JTConstant.Top_Ultrasonic_Sensor){
//			//吸顶超声波传感器
//			SuctionTopUltrasonicSensor r = new SuctionTopUltrasonicSensor();
//			r.setDescription("吸顶超声波传感器");
//			r.setType("HDL-MSPU03.4C");
//			return r;
//		}else if(type == JTConstant.Six_Loop_2A_Aiming_Model){
//			//6路2A智能可控硅调光模块
//			Relay r = new Relay<AimingLoop>();
//			r.setAmpere(2);
//			r.setDescription("6路2A智能可控硅调光场景控制");
//			r.setType("HDL-MD0602.432");
//			r.setScene_num(12);
//			r.setSequence_num(6);
//			r.setSequence_step_num(12);
//			r.setLoop_num(6);
//			r.initLoop(1);
//			return r;
//		}else if(type == JTConstant.Data_Converter232){
//			//RS232<->HDL-BUS数据交换器
//			DataConverter232 r = new DataConverter232();
//			r.setDescription("RS232<->HDL-BUS数据交换器");
//			r.setType("SB-DN-RS232N");
//			return r;
//		}
//		System.out.println("未记录在案的设备: " + Integer.toHexString(type));
//		return null;
//	}



}
