package com.dizhejiang.teachin.common;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;


public class RandomUtil {
	private static final Random random =new Random(); 
	
	private static final String[] numbers = {"0","1","2","3","4","5","6","7","8","9"};
	
	private static final String[] characters = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
            "w", "x", "y", "z"};
	
	private static final String[] upperCharacters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};
	
	private static final String[] mixCharacters = {"0","1","2","3","4","5","6","7","8","9","a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
            "w", "x", "y", "z"};
	
	public static String getRandomNumberStr(int length) {
		return getRandomStr(numbers,length);
	}
	
	public static String getRandomCharacterStr(int length) {
		return getRandomStr(characters,length);
	}
	
	public static String getRandomUpperCharacterStr(int length) {
		return getRandomStr(upperCharacters,length);
	}
	
	public static String getMixCharacterStr(int length) {
		return getRandomStr(mixCharacters,length);
	}
	
	public static String getRandomStr(String[] target,int length) {
		String result = "";
		for (int i = 0; i < length; i++) {
			result += target[random.nextInt(target.length)];
		}
		return result;
	}
	
	/**
	 * 
	 * @方法名: generateRandomStr
	 * @方法说明: 产生随机数
	 * @参数 @param length
	 * @参数 @return
	 * @author pengqidi
	 * @创建时间： 2018年6月6日
	 */
	public static String generateRandomStr() {
		String generateSource = "0123456789";
		String rtnStr = "";
		String str="";
		for (int i = 0; i < 4; i++) {
			// 循环随机获得当次字符，并移走选出的字符
			String nowStr = String.valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));

			rtnStr += nowStr;
			generateSource = generateSource.replaceAll(nowStr, "");
				
		}
		String date = DateUtils.dateToString(new Date(), "yyyyMMddHHmmssSSS");
		str=date+rtnStr;
		System.out.println(str);
		return str;
	}

	public static String generateRandomPassword(){
		ArrayList<String> strings = new ArrayList<> ();
		strings.add (RandomUtil.getRandomCharacterStr (2));
		strings.add (RandomUtil.getRandomNumberStr (4));
		strings.add (RandomUtil.getRandomUpperCharacterStr (2));
		Collections.shuffle(strings);
		//joining (",", "{", "}"
		String sysPassword  = strings.stream ().collect (Collectors.joining ());
		return sysPassword;
	}


	public static String generateRandomStr1(int length) {
		// FIXME 这里的String最好改成StringBuilder来提高性能，然后generateSource变成常量
		
		String generateSource = "0123456789";
		String rtnStr = "";
		
		for (int i = 0; i < length; i++) {
			// 循环随机获得当次字符，并移走选出的字符
			String nowStr = String.valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));

			rtnStr += nowStr;
			generateSource = generateSource.replaceAll(nowStr, "");
				
		}
	
		System.out.println(rtnStr);
		return rtnStr;
	}

	/**
	 * 随机产生几位数字
	 */
	public static final String produceNumber(int maxLength){
		return RandomStringUtils.randomNumeric(maxLength);
	}


	/**
	 * 随机产生几位字符串：例：maxLength=3,则结果可能是 aAz
	 * @param maxLength 传入数必须是正数。
	 */
	public static String produceString(int maxLength){
		return RandomStringUtils.randomAlphabetic(maxLength);
	}

	/**
	 * 随机产生随机数字+字母：例：maxLength=3,则结果可能是 1Az
	 * @param maxLength 传入数必须是正数。
	 */
	public static String produceStringAndNumber(int maxLength){
		return RandomStringUtils.randomAlphanumeric(maxLength);
	}

	/**
	 * 随机int 在 min max 之间
	 * @param min
	 * @param max
	 * @return
	 */
	public static Integer randomNumber(int min,int max){
		return new Random().nextInt(max+1-min)+min;
	}


}
