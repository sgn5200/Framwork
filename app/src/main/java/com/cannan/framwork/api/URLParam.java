package com.cannan.framwork.api;

import android.util.ArrayMap;

/**
 * Created by Cannan on 2017/9/27 0027.
 *
 * http 请求的参数
 * {@link T } 为 网络请求自定义泛型的gson 解析对象
 * 利用 {@link URLParam<T> }  传递泛型类型
 *
 * {"code":200,"message":"成功","data":xxx} T 只关联data
 *
 * 完整解析为 vo =  { com.cannan.framwork.api.BaseResponse<T>}
 * 本类中的T即为BaseResponse 中的 T
 *
 * data:
 * T t = vo.getData();
 */

public class URLParam<T> {
	private String url;
	private ArrayMap<String,String> map;
	private int method;

	/**
	 * 网络请求地址
	 * @param url
	 */
	public URLParam(String url){
		this.url = url ;
		map = new ArrayMap<>();
	}

	/**
	 * 网络请求方法
	 * @return  {@link ApiMethod}
	 */
	public int getMethod() {
		return method;
	}

	/**
	 *  {@link ApiMethod}
	 * 设置网络请求方法 get or post or put...
	 * @param  method  接收参数限定为 {@link ApiMethod}
	 */
	public void setMethod(@ApiMethod.METHOD int method) {
		this.method = method;
	}

	/**
	 * 获取URL 网络请求地址
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 添加参数
	 * @param key
	 * @param value
	 * @return  ArrayMap
	 */
	public ArrayMap<String,String>  put(String key,String value){
		this.map.put(key,value);
		return this.map;
	}

	/**
	 * 添加参数 参数为{@link ArrayMap<String,String> } 小数据使用性能高
	 * 大数据使用{@link java.util.HashMap}
	 * @param map
	 * @return  ArrayMap
	 */
	public ArrayMap<String,String> addParam(ArrayMap<String,String> map){
		this.map.putAll(map);
		return this.map;
	}

	/**
	 * 返回map参数
	 * @return ArrayMap
	 */
	public ArrayMap<String,String> getParam(){
		return this.map;
	}

}
