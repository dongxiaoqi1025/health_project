package dxq;

import dxq.constant.RedisConstant;
import dxq.utils.AlibabaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class ClearImgJob {

    @Autowired
    private JedisPool jedisPool;
    public void clearImg()
    {
        Set<String> diff = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_DB_RESOURCES, RedisConstant.SETMEAL_PIC_RESOURCES);
        if(diff!=null) {
            for (String s : diff) {
                AlibabaUtils.deleteFile(s);
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,s);
            }
        }
    }
    public void clearAll()
    {
        Set<String> diff = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_DB_RESOURCES, RedisConstant.SETMEAL_PIC_RESOURCES);
        if(diff!=null) {
            for (String s : diff) {
                AlibabaUtils.deleteFile(s);
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,s);
            }
        }

    }
}
