package com.sean;

import com.sean.model.TextMessage;
import com.thoughtworks.xstream.XStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WpnApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void resultMessage(){
		Map<String,String> map = new HashMap<>();
		map.put("ToUserName","to");
		map.put("FromUserName","from");
		map.put("MsgType","type");
		TextMessage message = new TextMessage(map,"nihao");
		XStream xStream = new XStream();
		xStream.processAnnotations(TextMessage.class);
		String xml = xStream.toXML(message);
		System.out.println(xml);
	}

}
