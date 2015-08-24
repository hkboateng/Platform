/**
 * hkboateng
 */
package com.boateng.abankus.database.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author hkboateng
 *
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{

	 @Bean
	  public RedisConnectionFactory redisConnectionFactory() {
		 JedisConnectionFactory factory = new JedisConnectionFactory();
		 factory.setHostName("localhost");
		 factory.setPort(6379);
		 factory.setUsePool(true);
		 factory.setDatabase(0);
		 return factory;
	 }
	 
	 @Bean
	  public RedisTemplate<String, String> redisTemplate() {
	    RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
	    redisTemplate.setConnectionFactory(redisConnectionFactory());
	    return redisTemplate;
	  }

	  @Bean
	  public CacheManager cacheManager() {
	    RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate());

	    // Number of seconds before expiration. Defaults to unlimited (0)
	    cacheManager.setDefaultExpiration(300);
	    return cacheManager;
	  }	 
}
