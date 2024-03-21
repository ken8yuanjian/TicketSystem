package com.ken.common.cache;

/*
cache前缀，用于cache分类管理，前缀避免重复，也便于查看
 */
public class MyCachePrefix {
    //url类，用于防重放缓存
    public static final String url(String url){
        return "url_" + url;
    }
    //sessionid 类，用户sessionid缓存
    public static final String sessionid(String sessionid){
        return "sessionid_"+sessionid;
    }
    //验证码类，用于验证码验证
    public static final String optid(String optid){
        return "optid_"+optid;
    }
}
