package redis.demo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class RedisTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	Jedis jedis = new Jedis("127.0.0.1");

	/**
	 * jedis����String���ַ�����
	 */
	// @Test
	public void stringTest() {

		// �������
		jedis.set("key", "value");
		System.out.println(jedis.get("key"));
		// �޸�����-׷��
		jedis.append("key", "����");
		System.out.println(jedis.get("key"));
		// ��������
		jedis.set("key", "value");
		System.out.println(jedis.get("key"));
		// ɾ������
		jedis.del("key");
		System.out.println(jedis.get("key"));

		/**
		 * mset�൱�� jedis.set("name","meepo"); jedis.set("dota","poofu");
		 */
		jedis.mset("first", "1", "second", "2");
		System.out.println(jedis.get("first") + " " + jedis.get("second"));
	}

	/**
	 * jedis����Hash(�ֵ䣩
	 */
	// @Test
	public void setmapTest() {
		// ����map
		Map<String, String> map = new HashMap<String, String>();
		// �Ѽ�ֵ�Լӵ�map������ȥ
		map.put("key", "value");
		map.put("��", "ֵ");
		// ��redis��map����һ����ȥ��Ӧ
		jedis.hmset("map", map);
		// ��һ��������redis��Ӧ�ļ�������ķŸü���Ӧ��map����ļ�
		List<String> maplist = jedis.hmget("map", "��", "key");
		System.out.println(maplist);
		// ɾ��map�е�ĳ����ֵ
		// jedis.hdel("user","pwd");
		System.out.println(jedis.hmget("map", "��")); // ��Ϊɾ���ˣ����Է��ص���null
		System.out.println(jedis.hlen("map")); // ����keyΪuser�ļ��д�ŵ�ֵ�ĸ���1
		System.out.println(jedis.exists("map"));// �Ƿ����keyΪuser�ļ�¼ ����true
		System.out.println(jedis.hkeys("map"));// ����map�����е�����key [pwd, name]
		System.out.println(jedis.hvals("map"));// ����map�����е�����value [meepo,
												// password]
		// ��������map���еļ�ֵ��
		Iterator<String> iter = jedis.hkeys("map").iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			System.out.println(key + ":" + jedis.hmget("map", key));
		}
	}

	/**
	 * jedis����list���б�
	 */
	// @Test
	public void setlistTest() {
		// �������
		// jedis.del("java list");
		// ��list��������
		jedis.lpush("java list", "mybatis");
		jedis.lpush("java list", "spring");
		jedis.lpush("java list", "springMvc");
		// ��һ����key���ڶ�������ʼλ�ã��������ǽ���λ�ã�lrange�ǰ���Χȡ���� -1��ʾȡ������
		System.out.println(jedis.lrange("java list", 0, -1));
		// llen��ȡ����
		System.out.println(jedis.llen("java list"));
	}

	/**
	 * jedis����Set�����ϣ�
	 */
//	 @Test
	public void setTest() {
		jedis.del("List");

		// �������������
		jedis.sadd("List", "1");
		jedis.sadd("List", "2");
		jedis.sadd("List", "1");
		jedis.sadd("List", "3");
		jedis.sadd("List", "4");
		// ��ȡ����ȫ����ֵ
		System.out.println(jedis.smembers("List"));
		// �Ƴ�����ĳ��ֵ
		// jedis.srem("List", "1");
		System.out.println(jedis.smembers("List"));
		// �ж�ĳ��ֵ�Ƿ��Ǽ���Ԫ��
		System.out.println(jedis.sismember("List", "4"));
		// ���ؼ��ϵ�һ�����Ԫ��
		System.out.println(jedis.srandmember("List"));
		// ���ؼ��ϵ�Ԫ�ظ���
		System.out.println(jedis.scard("List"));
	}

//	 @Test
	public void normalTest() {
		// ��ȡ���еļ�
		System.out.println(jedis.keys("*"));
		// ģ��ɸѡ������Ӧ�ļ�
		System.out.println(jedis.keys("*st"));
		// ���ظ���key����Чʱ�䣬�����-1���ʾ��Զ
		System.out.println(jedis.ttl("map"));
		// ͨ���˷���������ָ��key�Ĵ���Чʱ�䣩 ʱ��Ϊ��
		// jedis.setex("map", 2000, "min");
		// ˯��5���ʣ��ʱ�佫Ϊ<=5
		// Thread.sleep(5000);
	}

	// jedis ����
	// ע�⣬�˴���rpush��lpush��List�Ĳ�������һ��˫���������ӱ��������ģ�
	// lpush����ǰ�б���������ݣ�rpush����ǰ�б��Ҳ�������
//	@Test
	public void sortTest() {
		jedis.del("a");
		jedis.rpush("a", "1");
		jedis.lpush("a", "6");
		jedis.lpush("a", "3");
		jedis.lpush("a", "9");
		jedis.rpush("a", "5");
		System.out.println(jedis.lrange("a", 0, -1));
		System.out.println(jedis.sort("a")); 
		System.out.println(jedis.lrange("a", 0, -1));
	}
	
	@Test
	public void Test(){
//		jedis.flushDB();
		System.out.println(jedis.keys("*"));
		System.out.println(jedis.smembers("Tea"));
	}
}
