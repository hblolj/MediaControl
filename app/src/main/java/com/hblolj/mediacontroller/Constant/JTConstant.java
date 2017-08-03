package com.hblolj.mediacontroller.Constant;

import java.util.HashMap;
import java.util.Map;

/***
 * JTControl 常量类
 * @author 昀尚智能
 *
 */
public class JTConstant {

	//类型与设备的对应
	public static final Map<String, String> type2Device = new HashMap<>();

	//表示HDL LOGO
	public static final String LOGO_ORDER = "c0 a8 01 82 48 44 4c 4d 49 52 41 43 4c 45";
	//指令前缀
	public static final String PRE_ORDER = "AAAA";

	//	第四代 4路16A继电器	01BC
	public static final int Four_Loop_16A_Relay = Integer.parseInt("01BC", 16);
	//	6路网络智能数字电表	037E
	public static final int Six_Loop_Smart_WattHourMeter = Integer.parseInt("037E", 16);
	//	第四代1按键触摸面板	080E
	public static final int One_Key_Panel = Integer.parseInt("080E", 16);
	//	"四代八按键多功能面板 音乐 可选 闹钟 抽湿 地热"	00A7
	public static final int Eight_Key_More_Fun_Panel = Integer.parseInt("00A7", 16);
	//	第四代面板大4按键	07EA
	public static final int Big4_Key_Panel = Integer.parseInt("07EA", 16);
	//	1通道带电流检测红外发射模块	013F
	public static final int IR_Emit = Integer.parseInt("013F", 16);
	//	逻辑定时器	         0455
	public static final int Logic_Timer = Integer.parseInt("0455", 16);
	//	八路传感器输入模块        0165
	public static final int Eight_Loop_Sensor = Integer.parseInt("0165", 16);
	//	红外超声波双鉴传感器    013D
	public static final int IR_Ultrasonic_Double_Sensor = Integer.parseInt("013D", 16);
	//  吸顶超声波传感器     0146
	public static final int Top_Ultrasonic_Sensor = Integer.parseInt("0146", 16);
	// 6路2A智能可控硅调光场景控制   0269
	public static final int Six_Loop_2A_Aiming_Model = Integer.parseInt("0269", 16);
	// RS232 <-> HDL-BUS数据交换器    0409
	public static final int Data_Converter232 = Integer.parseInt("0409", 16);
	//------------------------------------------

	public static final int DELAYTIME = 5000;//延迟时间

	public static final int DEFAULT_1_VALUE = 255;//FF ff
	public static final int DEFAULT_2_VALUE = 65535;//ffff FFFF

	public static final String LOOP_OPEN = "64";//回路开
	public static final String LOOP_CLOSE = "00";//回路关

	//设备信息获取
	public static final String DEVICE_ONLINE_DETECTION_ACTION_CODE = "000E";//设备在线检测
	public static final String DEVICE_VERSION_INFO_ACTION_CODE = "EFFD";//获取指定设备版本信息

	//回路信息获取
	public static final String LOOP_NUM_AND_ACTUAL_STATUS_ACTION_CODE = "0038";//回路数量与实际回路状态
	public static final String LOOP_NUM_AND_TARGET_STATUS_ACTION_CODE = "0033";//回路数量与目标回路状态

	public static final String LOOP_REMARK_INFO_ACTION_CODE = "F00E";//回路备注信息

	public static final String LOOP_TURN_ON_DELAY_ACTION_CODE = "F04D";//回路开启延迟
	public static final String LOOP_PROTECT_DELAY_ACTION_CODE = "F03F";//回路保护延迟
	public static final String LOOP_CLOSE_DELAY_ACTION_CODE = "F084";//回路关闭延迟
	public static final String LOOP_CLOSE_PROTECT_DELAY__ACTION_CODE = "F080";//回路关闭保护延迟

	public static final String AIMINGLOOP_HIGH_LOW_LIMIT_ACTION_CODE = "F016";//调光模块的回路  高限和低限   参数01表示高限   参数00表示低限
	public static final String AIMINGLOOP_MAX_LEVEL = "F020";//调光模块的最大水平
	public static final String AIMINGLOOP_DIMMING_CURVE_OUTPUT_TYPE = "1802";//调光模块回路的调光曲线和输出类型

	//回路信息设置

	//回路信息控制


	//区域信息获取....
	public static final String AREA_REMARK_INFO_ACTION_CODE = "F00A";//区域备注信息

	public static final String AREA_NUM_AND_THE_LOOP_ACTION_CODE = "0004";//区域数量与区域与回路的匹配信息

	//场景信息获取....
	public static final String SCENE_REMARK_INFO_ACTION_CODE = "F024";//场景备注信息

	public static final String SCENE_RUN_TIME_AND_LOOP_STATUS_ACTION_CODE = "0000";//场景运行时间与场景内回路状态

	//场景下 回路号与回路备注 通过该场景所属的区域 来获取

	//序列信息获取....
	public static final String SEQUENCE_REMARK_INFO_ACTION_CODE = "F028";//序列备注信息

	public static final String SEQUENCE_MODEL_AND_STEP_NUM_AND_RUN_TIMES_ACTION_CODE = "0012";//序列基本信息，包括:模式、步骤数、运行次数

	public static final String SEQUENCE_SCENE_NUM_AND_SCENE_RUN_TIME_ACTION_CODE = "0014";//序列下场景号与运行时间

	//FF FF FF 是未初始化过，取默认值，一般都是0

	//-----------------------------------------------------

	//BUS -> 232
	//E420 获取指令类型，开关号，开关状态
	//E428 获取指令备注信息
	//E424 获取指令下指令目标信息
	public static final String INSTRUCTION_TYPE_ADN_SWITCH_ID_SWITCH_STATE_ACTION_CODE = "E420";//232模块获取指令类型，开关号，开关状态
	public static final String INSTRUCTION_REMARK_INFO_ACTION_CODE = "E428";//232模块获取指令备注信息
	public static final String INSTRUCTION_CONTENTS_ACTION_CODE = "E424";//232模块获取指令下具体一个命令目标信息

	//E422  设置指令类型，开关号，开关状态
	//E42A 设置指令备注信息
	//E426  设置指令下指令目标信息
	public static final String SETTING_INSTRUCTION_TYPE_ADN_SWITCH_ID_SWITCH_STATE_ACTION_CODE = "E422";//232模块设置指令类型，开关号，开关状态
	public static final String SETTING_INSTRUCTION_REMARK_INFO_ACTION_CODE = "E42A";//232模块设置指令备注信息
	public static final String SETTING_INSTRUCTION_CONTENTS_ACTION_CODE = "E426";//232模块设置指令下具体一个命令目标信息

	//--------------------------------------------------------

	//232 -> BUS
	//E410 获取命令字符串头是否有效、格式、字符串内容
	//E418 获取命令备注信息
	//E414 获取命令下命令目标信息
	public static final String COMMAND_STRING_HEAD_PATTERN_CONTENT_ACTION_CODE = "E410";//232模块 获取命令字符串头是否有效、格式、字符串内容
	public static final String COMMAND_REMARK_INFO_ACTION_CODE = "E418";//232模块获取命令备注信息
	public static final String COMMAND_CONTENTS_ACTION_CODE = "E414";//232模块获取命令下命令目标信息

	//E412 设置命令字符串头是否有效、格式、字符串内容
	//E41A 设置命令备注信息
	//E416 设置命令下命令目标信息
	public static final String SETTING_COMMAND_STRING_HEAD_PATTERN_CONTENT_ACTION_CODE = "E412";//232模块设置命令字符串头是否有效、格式、字符串内容
	public static final String SETTING_COMMAND_REMARK_INFO_ACTION_CODE = "E41A";//232模块设置命令备注信息
	public static final String SETTING_COMMAND_CONTENTS_ACTION_CODE = "E416";//232模块设置命令下命令目标信息

	//----------------------------------------------------------------
	//E41C 获取串口配置
	//E42C 获取模式
	public static final String SERIAL_PORT_CONFIGURATION_ACTION_CODE = "E41C";//232模块串口配置信息获取
	public static final String RS232_HDL_BUS_MODL_ACTION_CODE = "E42C";//232模块模式获取

}
