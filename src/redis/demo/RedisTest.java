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
	 * jedis操作String（字符串）
	 */
	// @Test
	public void stringTest() {

		// 添加数据
		jedis.set("key", "value");
		System.out.println(jedis.get("key"));
		// 修改数据-追加
		jedis.append("key", "升级");
		System.out.println(jedis.get("key"));
		// 覆盖数据
		jedis.set("key", "value");
		System.out.println(jedis.get("key"));
		// 删除数据
		jedis.del("key");
		System.out.println(jedis.get("key"));

		/**
		 * mset相当于 jedis.set("name","meepo"); jedis.set("dota","poofu");
		 */
		jedis.mset("first", "1", "second", "2");
		System.out.println(jedis.get("first") + " " + jedis.get("second"));
	}

	/**
	 * jedis操作Hash(字典）
	 */
	// @Test
	public void setmapTest() {
		// 创建map
		Map<String, String> map = new HashMap<String, String>();
		// 把键值对加到map集合里去
		map.put("key", "value");
		map.put("键", "值");
		// 在redis给map设置一个键去对应
		jedis.hmset("map", map);
		// 第一个参数放redis对应的键，后面的放该键对应的map里面的键
		List<String> maplist = jedis.hmget("map", "键", "key");
		System.out.println(maplist);
		// 删除map中的某个键值
		// jedis.hdel("user","pwd");
		System.out.println(jedis.hmget("map", "键")); // 因为删除了，所以返回的是null
		System.out.println(jedis.hlen("map")); // 返回key为user的键中存放的值的个数1
		System.out.println(jedis.exists("map"));// 是否存在key为user的记录 返回true
		System.out.println(jedis.hkeys("map"));// 返回map对象中的所有key [pwd, name]
		System.out.println(jedis.hvals("map"));// 返回map对象中的所有value [meepo,
												// password]
		// 迭代遍历map所有的键值对
		Iterator<String> iter = jedis.hkeys("map").iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			System.out.println(key + ":" + jedis.hmget("map", key));
		}
	}

	/**
	 * jedis操作list（列表）
	 */
	// @Test
	public void setlistTest() {
		// 清空数据
		// jedis.del("java list");
		// 往list插入数据
		jedis.lpush("java list", "mybatis");
		jedis.lpush("java list", "spring");
		jedis.lpush("java list", "springMvc");
		// 第一个是key，第二个是起始位置，第三个是结束位置，lrange是按范围取出， -1表示取得所有
		System.out.println(jedis.lrange("java list", 0, -1));
		// llen获取长度
		System.out.println(jedis.llen("java list"));
	}

	/**
	 * jedis操作Set（集合）
	 */
//	 @Test
	public void setTest() {
		jedis.del("List");

		// 往集合添加数据
		jedis.sadd("List", "1");
		jedis.sadd("List", "2");
		jedis.sadd("List", "1");
		jedis.sadd("List", "3");
		jedis.sadd("List", "4");
		// 获取集合全部的值
		System.out.println(jedis.smembers("List"));
		// 移除集合某个值
		// jedis.srem("List", "1");
		System.out.println(jedis.smembers("List"));
		// 判断某个值是否是集合元素
		System.out.println(jedis.sismember("List", "4"));
		// 返回集合的一个随机元素
		System.out.println(jedis.srandmember("List"));
		// 返回集合的元素个数
		System.out.println(jedis.scard("List"));
	}

//	 @Test
	public void normalTest() {
		// 获取所有的键
		System.out.println(jedis.keys("*"));
		// 模糊筛选返回相应的键
		System.out.println(jedis.keys("*st"));
		// 返回给定key的有效时间，如果是-1则表示永远
		System.out.println(jedis.ttl("map"));
		// 通过此方法，可以指定key的存活（有效时间） 时间为秒
		// jedis.setex("map", 2000, "min");
		// 睡眠5秒后，剩余时间将为<=5
		// Thread.sleep(5000);
	}

	// jedis 排序
	// 注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的）
	// lpush往当前列表左侧添数据，rpush往当前列表右侧添数据
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
