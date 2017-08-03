package com.hblolj.mediacontroller.Utils;

import com.hblolj.mediacontroller.UDPUtils.UDP;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 网络工具类
 * @author 昀尚智能
 * 封装UDP报文的发送与接收
 *
 */
public class NetUtils {

	private static boolean isOk = true;
	private static boolean isDeviceOK = true;

	/**
	 * 发送一条数据
	 * @param sendData     待发送的指令集合
	 * @param targetIP     目标IP
	 * @param targetPort   目标端口
	 * @param receivePort  接收数据的端口
	 * @param delayTime    超时时间   单位是毫秒
	 * @throws SocketException
	 */
	public static Map<String, String> sendData(final byte[] sendData, byte[] receivedata, final String targetIP,
											   final int targetPort, int receivePort, int delayTime) throws SocketException{

		isOk = true;
		byte[] receiveData = new byte[1024];
		Map<String, String> receive = null;

		//发送指令、接收指令、发一条接收一条
		for(int i=0; i<1; i++){
			//一次发三条,减少掉包的影响
			UDP.sendUdpData(sendData, targetIP, targetPort);
		}
		Map<String, String> send = HandlerUtils.getOrderContent(sendData);
		String send_ac = send.get(HandlerUtils.ACTION_CODE);
		int send_actionCode = Integer.parseInt(send_ac, 16);
		UDP.startReceive();
		//执行一个延迟任务，10秒钟之后执行 会不会有线程安全问题?  堵塞的? 没有问题
		Timer t = new Timer();
		t.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				//延迟10秒执行的动作
				NetUtils.isOk = false;
				UDP.finishReceive();
				this.cancel();
			}
		}, delayTime);
		while(isOk){//这里会阻塞   设置一个超时时间   规定时间内没有接收到有效报文的话 就报错
			UDP.recriveUdpData(receiveData);
			receive = HandlerUtils.getOrderContent(receiveData);
			String receive_ac = receive.get(HandlerUtils.ACTION_CODE);
			String receive_ap = receive.get(HandlerUtils.ADD_PARAM);
			String receive_oc = receive.get(HandlerUtils.ORDER_CONTENT);

			System.out.println("当前发送的指令操作码: " + send_ac);
			System.out.println("接收到指令内容: " + receive_oc);
			System.out.println("接收到指令操作码: " + receive_ac);
			System.out.println("接收到指令附加参数: " + receive_ap);
			System.out.println("----------------------------------");

			int receiver_actionCode = Integer.parseInt(receive_ac, 16);
			if(send_actionCode + 1 == receiver_actionCode){
				//指令匹配，返回指令
				isOk = false;
				UDP.finishReceive();
				t.cancel();
				return receive;
			}
		}
		System.out.println("超时之后，返回的receive");
		t.cancel();
		return receive;
	}

	//发送一条数据，接收一组数据   在线检测
	public static Set<Map<String, String>> sendData(final byte[] sendData, final String targetIP,
													final int targetPort, int receivePort, int delayTime) throws SocketException{

		isDeviceOK = true;

		Set<Map<String, String>> receives = new HashSet();
		Map<String, String> receive = null;
		byte[] res;

		//发送指令、接收指令、发一条接收一条
		for(int i=0; i<1; i++){
			//一次发三条,减少掉包的影响
			System.out.println("第一次执行");
			UDP.sendUdpData(sendData, targetIP, targetPort);
		}
		Map<String, String> send = HandlerUtils.getOrderContent(sendData);
		String send_ac = send.get(HandlerUtils.ACTION_CODE);
		int send_actionCode = Integer.parseInt(send_ac, 16);
		UDP.startReceive();
		//执行一个延迟任务，10秒钟之后执行 会不会有线程安全问题?  堵塞的? 没有问题
		Timer t = new Timer();
		t.schedule(new TimerTask() {

			int i = 0;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(i>1){
					this.cancel();
				}else{
					System.out.println("第" + i+2 + "次执行");
					UDP.sendUdpData(sendData, targetIP, targetPort);
					i++;
				}
			}
		}, 2000, 3000);
		t.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				//延迟10秒执行的动作
				System.out.println("10秒钟到了......");
				NetUtils.isDeviceOK = false;
				UDP.finishReceive();
				System.out.println("准备关闭task");
				this.cancel();
				System.out.println("task已经关闭了");
			}
		}, delayTime);
		while(isDeviceOK){//这里会阻塞   设置一个超时时间   规定时间内没有接收到有效报文的话 就报错
			//加一个十秒钟的超时时间
			res = null;
			res = new byte[1024];
			UDP.recriveUdpData(res);
			receive = HandlerUtils.getOrderContent(res);
			String receive_ac = receive.get(HandlerUtils.ACTION_CODE);
			String receive_ap = receive.get(HandlerUtils.ADD_PARAM);
			String receive_oc = receive.get(HandlerUtils.ORDER_CONTENT);

			System.out.println("当前发送的指令操作码: " + send_ac);
			System.out.println("接收到指令内容: " + receive_oc);
			System.out.println("接收到指令操作码: " + receive_ac);
			System.out.println("接收到指令附加参数: " + receive_ap);
			System.out.println("----------------------------------");

			int receiver_actionCode = Integer.parseInt(receive_ac, 16);
			if(send_actionCode + 1 == receiver_actionCode){
				receives.add(receive);
			}
		}
		System.out.println("超时之后，返回的receive");
		t.cancel();
		return receives;
	}

	/**
	 * 发送一组数据
	 * @param sendDatas
	 * @param receiveDatas
	 * @param targetIP
	 * @param targetPort
	 * @param receivePort
	 * @param delayTime    超时时间    单位是毫秒
	 * @throws SocketException
	 */
	public static List<Map<String, String>> sendDatas(List<byte[]> sendDatas, List<byte[]> receiveDatas, String targetIP,
													  int targetPort, int receivePort, int delayTime) throws SocketException{
		//发送指令、接收指令、发一组接收一组
		List<Map<String, String>> maps = new ArrayList<>();
		for(int i=0; i<sendDatas.size(); i++){
			//拿到每一条指令 发送一条  接收一条
			Map<String, String> map = sendData(sendDatas.get(i), receiveDatas.get(i), targetIP, targetPort, receivePort, delayTime);
			maps.add(map);
		}
		System.out.println("接收完毕");
		return maps;
	}
}
