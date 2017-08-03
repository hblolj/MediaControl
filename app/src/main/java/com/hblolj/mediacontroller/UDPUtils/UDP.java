package com.hblolj.mediacontroller.UDPUtils;

import com.hblolj.mediacontroller.Constant.JTConstant;
import com.hblolj.mediacontroller.Utils.CommonUtil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


/**
 * UDP类，封装UDP的报文发送与接收
 * @author 昀尚智能
 *
 */
public class UDP {

	private static final int TARGET_PORT = 6000;
	private static DatagramSocket receive_socket;
	private static byte[] bb = null;

	/**
	 * 发送报文
	 */
	public static void sendUdpData(byte[] bs, String targetIP, int targetPort){
		try {
			InetAddress address = InetAddress.getByName(targetIP);
			//数据报
			final DatagramPacket packet = new DatagramPacket(bs, bs.length, address, targetPort);
			final DatagramSocket socket = new DatagramSocket();
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						socket.send(packet);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
			socket.close();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 接收报文
	 * 传进的byte[] 的大小怎么处理 45 69 00 00 00 00 00
	 * 拿到有效长度  校验码
	 */
	public static void recriveUdpData(byte[] bs){
		try {
			DatagramPacket packet = new DatagramPacket(bs, bs.length);
			if(receive_socket != null && receive_socket.isBound()){
				receive_socket.receive(packet);
			}
//			receive_socket.close();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void startReceive() throws SocketException{
		receive_socket = new DatagramSocket(TARGET_PORT);
//		receive_socket.setSoTimeout(5000);//设置5秒钟的超时时间  超过五秒没有获取到报文(这里是报文，不是有效报文......)
	}

	public static void finishReceive(){
		if(receive_socket != null){
//			System.out.println("开始停止socket");
			byte[] bb = CommonUtil.toByteArray(JTConstant.LOGO_ORDER + JTConstant.PRE_ORDER + "0C0100037E301001FBF890B5");
			try {
				DatagramPacket p = new DatagramPacket(bb, bb.length, InetAddress.getByName("192.168.1.255"), 6000);
				receive_socket.send(p);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			receive_socket.disconnect();
//			System.out.println("已经disconnect");
			receive_socket.close();
//			receive_socket.getSoTimeout();
//			receive_socket.setSoTimeout(timeout);
//			System.out.println("已经close");
			receive_socket = null;
//			System.out.println("已经设为null");
		}
	}
}
