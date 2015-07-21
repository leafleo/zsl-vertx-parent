package org.zsl.core;
/**
 *   
 * 项目名称：vertx_web    
 * 类名称：Rectangle    
 * 类描述：    
 * 创建人：liujunqing    
 * 创建时间：2015年7月18日  
 * @version 1.0    
 *
 */
public class Rectangle {
	private float sinA = 0;
	private float sinB = 0;	
	
	public Rectangle() {
		this.sinA = 1.0f;//默认边长1.0
	}

	public float getSinB() {
		return this.sinB;
	}

	public void setSinB(float sinB) {
		this.sinB = sinB;
	}
	
	public Rectangle(float sinA, float sinB) {
		super();
		this.sinA = sinA;
		this.sinB = sinB;
	}

	/**
	 * 计算面积
	 *@return
	 */
	public float caclArea(){
		return sinA * sinB;
	}
	
	
}
