package org.smileframework.tool.http;

import com.google.common.base.Splitter;
import org.smileframework.tool.common.Default;
import org.smileframework.tool.json.JsonUtils;
import org.smileframework.tool.string.StringTools;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Package: org.smileframework.tool.http
 * @Description: url
 * @author: liuxin
 * @date: 2017/12/8 下午11:24
 */
public class URLTools {

    public static Boolean isUrl(String urlStr) {
        Pattern pattern = Pattern
                .compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/&=])+$");
        return pattern.matcher(urlStr).find();

    }

    public static URL url(String urlStr) {
        URL url = null;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getProtocol(String urlStr) {
        if (!isUrl(urlStr)) {
            return "";
        }
        return url(urlStr).getProtocol();
    }

    public static String getQuery(String urlStr) {
        if (!isUrl(urlStr)) {
            return "";
        }
        return url(urlStr).getQuery();
    }


    public static Integer getPort(String urlStr) {
        if (!isUrl(urlStr)) {
            return -1;
        }
        return url(urlStr).getPort();
    }

    public static String getPath(String urlStr) {
        if (!isUrl(urlStr)) {
            return "";
        }
        return url(urlStr).getPath();
    }

    public static String getUserInfo(String urlStr) {
        if (!isUrl(urlStr)) {
            return "";
        }
        return url(urlStr).getUserInfo();
    }


    public static String getAuthority(String urlStr) {
        if (!isUrl(urlStr)) {
            return "";
        }
        return url(urlStr).getAuthority();
    }

    public static Map<String, Object> getQueryParameter(String urlStr) {
        Map<String, Object> queryParameter = new LinkedHashMap<>();
        if (!isUrl(urlStr)) {
            return null;
        }
        String query = getQuery(urlStr);
        Iterable<String> split = Splitter.on("&").split(query);
        split.forEach(queryParam -> {
            if (StringTools.isEmpty(queryParam)){
                return;
            }
            String[] params = queryParam.split("=");
            params[1] = Default.defaultValue(params[1], "",String.class);
            queryParameter.put(params[0], params[1]);
        });
        return queryParameter;
    }


    public static void main(String[] args) {
        System.out.println(getPort("http://blog.csdn.net:3201/yongh701/article/details/46894417?name=age"));//3201
        System.out.println(getQuery("http://blog.csdn.net:3201/yongh701/article/details/46894417?name=age&sd=23"));////name=age&sd=23
        System.out.println(getAuthority("http://blog.csdn.net:3201/yongh701/article/details/46894417?name=age&sd=23"));//blog.csdn.net:3201
        System.out.println(getPath("http://blog.csdn.net:3201/yongh701/article/details/46894417?name=age&sd=23"));///yongh701/article/details/46894417
        System.out.println(JsonUtils.toJson(getQueryParameter("http://blog.csdn.net:3201/yongh701/article/details/46894417?name=age&")));
    }

}