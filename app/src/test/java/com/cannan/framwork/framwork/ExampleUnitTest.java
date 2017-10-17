package com.cannan.framwork.framwork;

import com.cannan.framwork.api.BaseResponse;
import com.cannan.framwork.api.vo.BlockListVo;
import com.google.gson.Gson;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
	@Test
	public void addition_isCorrect() throws Exception {
		//{"code":200,"message":"成功"}

//		TypeToken<BaseResponse<List<BlockListVo>>> token = new TypeToken<BaseResponse<List<BlockListVo>>>(){};

		Gson gson = new Gson();

		BaseResponse<List<BlockListVo>> rs = gson.fromJson(dataStr,BaseResponse.class);

		System.out.print(rs.getData().toString());

		assertNotNull(rs.getData());

	}



	String dataStr = "{\n" +
			"    \"code\": 200,\n" +
			"    \"message\": \"成功\",\n" +
			"    \"data\": [\n" +
			"        {\n" +
			"            \"blockCode\": \"5201030001\",\n" +
			"            \"blockName\": \"贵阳研发中心小区123\"\n" +
			"        },\n" +
			"        {\n" +
			"            \"blockCode\": \"5201030002\",\n" +
			"            \"blockName\": \"贵阳研发中心小区二\"\n" +
			"        },\n" +
			"        {\n" +
			"            \"blockCode\": \"5201030003\",\n" +
			"            \"blockName\": \"好家易贵阳研发中心\"\n" +
			"        },\n" +
			"        {\n" +
			"            \"blockCode\": \"5201030006\",\n" +
			"            \"blockName\": \"永利星座\"\n" +
			"        },\n" +
			"        {\n" +
			"            \"blockCode\": \"5201030007\",\n" +
			"            \"blockName\": \"贵阳研发中心测试区1\"\n" +
			"        },\n" +
			"        {\n" +
			"            \"blockCode\": \"5201030008\",\n" +
			"            \"blockName\": \"自助小区(NO1)\"\n" +
			"        }\n" +
			"    ]\n" +
			"}";



}