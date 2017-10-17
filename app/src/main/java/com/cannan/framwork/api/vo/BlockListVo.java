package com.cannan.framwork.api.vo;

/**
 * Created by Cannan on 2017/9/27 0027.
 *
 * data中的gson解析测试对象
 */


public class BlockListVo {

	private String blockCode;
	private String blockName;

	public String getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	@Override
	public String toString() {
		return "DataBean{" +
				"blockCode='" + blockCode + '\'' +
				", blockName='" + blockName + '\'' +
				'}';
	}
}
