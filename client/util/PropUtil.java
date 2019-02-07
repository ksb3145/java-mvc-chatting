package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;

/**
 * @Auth K.J.S
 * @date 2019. 2. 7.
 * 별다른 설치없이 사용할수 있는 properties를 사용.
 * properteis를 동적으로 사용가능.
 * 싱글톤 패턴 ::: 싱글톤이 보장되지 않은 분산형 애플리케이션에서는 사용 자제
 */
public class PropUtil {
	private static final String CONFIG_PATH = "/resources/config.properties";
	private Properties prop;
	private PropUtil(){ prop = new Properties(); init();}
	private static class PropUtilholder{static final PropUtil single = new PropUtil();}
	public static PropUtil obj(){return PropUtilholder.single;}
	
	public Properties getProperties(){
		return prop;
	}
	
	private void init() {
		InputStream inputStream = getClass().getResourceAsStream(CONFIG_PATH);
		try {
			prop.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//local test 용
	public void printList(){
		this.getProperties().list(System.out);
	}
	
	//properties 값넣기
	public void put(String key, String value){
		this.getProperties().put(key, value);
	}
	
	//properteis 값빼기
	public String get(String key){
		return (String) this.getProperties().get(key);
	}
	
	//키 있는지 확인
	public boolean isContKey(String key){
		return this.getProperties().containsKey(key);
	}
	
	//값 있는지 확인
	public boolean isContVal(String value){
		return this.getProperties().containsValue(value);
	}
	
	/**
	 * @Auth K.J.S
	 * @date 2019. 2. 7.
	 * Test 코드
	 */
	public static void main(String[] args) {
		//프로퍼티 리스트 확인
		PropUtil.obj().printList();
		
		//프로퍼티 값 넣고  리스트확인
		PropUtil.obj().put("fuck", "you");
		if(!"you".equals(PropUtil.obj().get("fuck"))) throw new RuntimeException("없음");
		
		//프로퍼티 키 오버라이드해서 값 넣기
		PropUtil.obj().put("fuck", "me");
		if(!"me".equals(PropUtil.obj().get("fuck"))) throw new RuntimeException("없음");
		
		//프로퍼티 하나 생성해서 결합
		Properties prop2 = new Properties();
		prop2.put("a", "에이");
		prop2.put("hello", "java");
		PropUtil.obj().getProperties().putAll(prop2);
		
		if(!"에이".equals(PropUtil.obj().get("a"))) throw new RuntimeException("없음");
		if(!"java".equals(PropUtil.obj().get("hello"))) throw new RuntimeException("없음");

		//해당 키가 있는지 확인
		if(PropUtil.obj().isContKey("fuck") == false) throw new RuntimeException("키 없음");
		
		//해당 값이 잇는지 확인
		if(PropUtil.obj().isContVal("me") == false) throw new RuntimeException("값 없음");
		
	}

}