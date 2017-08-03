package com.hblolj.mediacontroller.Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class China2Unicode16 {

	/**
	 * 汉字转16进制Unicode
	 * 16进制Unicode转汉字
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String s = "37 45 20 33 30 20 33 30 20 33 30 20 33 30 20 32 30 20 33 31 20 30 44 20 0D ";
		s = s.replace(" ", "");
		String as = hex2ASCII(s);
		System.out.println(as);
	}

	/**
	 * 十六进制转中文
	 * 00 -> 空格
	 * @param s
	 * @return
	 */
	public static String hexToStringGBK(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		}
		try {
			s = new String(baKeyword, "GBK");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
			return "";
		}
		return s;
	}

	/**
	 * 中文转16进制
	 * 有空格隔开，需要处理掉空格
	 * @param s
	 * @return
	 */
	public static String toChineseHex(String s)
	{
		String ss = s;
		byte[] bt = ss.getBytes();
		String s1 = "";
		for (int i = 0; i < bt.length; i++)
		{
			String tempStr = Integer.toHexString(bt[i]);
			if (tempStr.length() > 2)
				tempStr = tempStr.substring(tempStr.length() - 2);
			s1 = s1 + tempStr + " ";
		}
		return s1.toUpperCase();
	}

	/**
	 * ASCII转16进制
	 * @param str
	 * @return
	 */
	public static String asciiToHex(String str){

		char[] chars = str.toCharArray();

		StringBuffer hex = new StringBuffer();

		for(int i = 0; i < chars.length; i++){
			hex.append(Integer.toHexString((int)chars[i]));
		}

		return hex.toString();
	}


	/**
	 * 16进制转ASCII码
	 * @param hex
	 * @return
	 */
	public static String hex2ASCII(String hex){

		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();

		for( int i=0; i<hex.length()-1; i+=2 ){

			String output = hex.substring(i, (i + 2));

			int decimal = Integer.parseInt(output, 16);
			sb.append((char)decimal);
			temp.append(decimal);
		}

		return sb.toString();
	}
}
