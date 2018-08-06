package com.ybkj.common.encryption;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5 {

	/**
	 * 设置加密
	 * @return
	 */
	public static Object Md5(String mobile,String payPassword){
		//得到加密的的结果
		//1.算法
		String hashAlgorithmName="MD5";
		//2.密码（从数据中的密码）
		Object credentials=payPassword;
		//3.盐值 user为唯一的：我们这里要做成用户名盐值加密
		Object salt=ByteSource.Util.bytes(mobile);
		//4.加密的次数
		int hashIterations =1024;
		Object encryptPassword=new SimpleHash(hashAlgorithmName,credentials,salt,hashIterations );
		return encryptPassword;
		
	}
	
	public static void main(String[] args) {
		//得到加密的的结果
				//1.算法
				String hashAlgorithmName="MD5";
				//2.密码（从数据中的密码）
				Object credentials="15534324353";
				//3.盐值 user为唯一的：我们这里要做成用户名盐值加密
				Object salt=ByteSource.Util.bytes("admin123");
				//4.加密的次数
				int hashIterations =1024;
				Object encryptPassword=new SimpleHash(hashAlgorithmName,credentials,salt,hashIterations );
				System.out.println(encryptPassword);
	}
}
