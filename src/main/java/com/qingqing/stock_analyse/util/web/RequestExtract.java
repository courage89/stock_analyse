package com.qingqing.stock_analyse.util.web;

import com.qingqing.common.exception.UnAuthorizedException;
import com.qingqing.common.util.StringUtils;
import com.qingqing.stock_analyse.exception.StockAnalyseRuntimeException;
import com.qingqing.stock_analyse.util.StockDateUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestExtract {

    public static final String REQUEST_FROM = "from";

    public static final String REQUEST_VERSION = "version";

    public static final String CATALOG_TYPE = "catalog_type";

    public static final String DATE_TIME = "date_time";

    public static final String LIMIT = "limit";

    public static final String DEFAULT_UNKNOWN_REQUEST = "unknown";

    public static final String SESSION = "si";
    public static final String SIGN = "sg";

    public static final String NEED_SESSION = "need_session";

    public static final String HEADER_TIMESTAMP = "Timestamp";
    public static final String HEADER_AUTHKEY = "Authkey";

    public static final String PARAM_TIMESTAMP = "timestamp";
    public static final String PARAM_AUTHKEY = "authkey";

    public static final String PAGED_TAG = "tag";

    public static final String APP_PLATFORM = "appplatform";
    public static final String APP_NAME = "appname";
    public static final String APP_VERSION = "appversion";
    public static final String CALLBACK_URL = "callback_url";

    private static final String USERIP = "UserIp";
    public static final String DATE = "date";

    public static final Date getDate(HttpServletRequest request) {
        Date date = null;
        String dateStr = ParameterTool.getParameterString(request, DATE, null);
        if (!StringUtils.isEmpty(dateStr)) {
            date = StockDateUtil.stringToDate(dateStr);
        }
        return date == null ? StockDateUtil.findLastOpenMarketkDay(new Date()) : date;
    }

    public static String getVersion(HttpServletRequest request, String defaultVersion) {
        return ParameterTool.getParameterString(request, REQUEST_VERSION, defaultVersion);
    }

    public static String getVersion(HttpServletRequest request) {
        return ParameterTool.getParameterString(request, REQUEST_VERSION, DEFAULT_UNKNOWN_REQUEST);
    }

    public static String getFrom(HttpServletRequest request, String defaultFrom) {
        return ParameterTool.getParameterString(request, REQUEST_FROM, defaultFrom);
    }

    public static String getFrom(HttpServletRequest request) {
        return ParameterTool.getParameterString(request, REQUEST_FROM, DEFAULT_UNKNOWN_REQUEST);
    }

    public static Integer getCatalogType(HttpServletRequest request) {
        return ParameterTool.getParameterInteger(request, CATALOG_TYPE, -1);
    }


    public static String getHeaderUserIp(HttpServletRequest request) {
        String value = getHeaderValue(request, USERIP, null);
        if (null == value) {
            throw new UnAuthorizedException("no user ip", "parameter missed");
        }

        return value;
    }


    public static String getDateTime(HttpServletRequest request) {
        return ParameterTool.getParameterString(request, DATE_TIME, "");
    }

    public static Integer getLimit(HttpServletRequest request) {
        return ParameterTool.getParameterInteger(request, LIMIT, 10);
    }

    public static void setAttrNeedSession(HttpServletRequest request, Boolean needSession) {
        request.setAttribute(NEED_SESSION, needSession);
    }

    public static boolean getAttrNeedSession(HttpServletRequest request, Boolean defaultValue) {
        Boolean obj = (Boolean) request.getAttribute(NEED_SESSION);
        if (obj != null) {
            return obj;
        } else {
            return defaultValue;
        }
    }

    public static String getSession(HttpServletRequest request) {
        String session = ParameterTool.getParameterString(request, SESSION, null);
        if (StringUtils.isEmpty(session)) {
            session = request.getHeader(SESSION);
        }
        return session;
    }

    public static String getSign(HttpServletRequest request) {
        String sign = ParameterTool.getParameterString(request, SIGN, null);
        if (StringUtils.isEmpty(sign)) {
            sign = request.getHeader(SIGN);
        }
        return sign;
    }

    public static long getHeaderTimestamp(HttpServletRequest request) {
        long defaultValue = -1l;
        String value = getHeaderValue(request, HEADER_TIMESTAMP, String.valueOf(defaultValue));
        try {
            return Long.parseLong(value);
        } catch (Exception ignore) {
        }
        return defaultValue;
    }

    public static String getHeaderAuthKey(HttpServletRequest request) {
        return getHeaderValue(request, HEADER_AUTHKEY, null);
    }

    public static long getParameterTimestamp(HttpServletRequest request) {
        return ParameterTool.getParameterLong(request, PARAM_TIMESTAMP, -1l);
    }

    public static String getParameterAuthKey(HttpServletRequest request) {
        return ParameterTool.getParameterString(request, PARAM_AUTHKEY, "");
    }

    public static String getPagedTag(HttpServletRequest request) {
        String tag = ParameterTool.getParameterString(request, PAGED_TAG, null);
        return tag;
    }

    public static String getHeaderValue(HttpServletRequest request, String name, String defaultValue) {
        String value = request.getHeader(name);
        if (value == null) {
            return defaultValue;
        }

        return value;
    }

    public static String getAppPlatform(HttpServletRequest request) {
        return ParameterTool.getParameterString(request, APP_PLATFORM, "");
    }

    public static String getAppName(HttpServletRequest request) {
        return ParameterTool.getParameterString(request, APP_NAME, "");
    }

    public static String getAppVersion(HttpServletRequest request) {
        return ParameterTool.getParameterString(request, APP_VERSION, "");
    }

    public static String getRequestContent(HttpServletRequest request) {
        InputStream inputStream = null;
        BufferedReader reader = null;
        StringBuilder sbResp = new StringBuilder();
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                sbResp.append(line);
            }
            return sbResp.toString();
        } catch (Exception ex) {
            throw new StockAnalyseRuntimeException("getRequestContent error", ex);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception ignore) {
                }
            }
        }
    }

    public static String getCallbackUrl(HttpServletRequest httpRequest) {
        return ParameterTool.getParameterString(httpRequest, CALLBACK_URL, "");
    }


    @SuppressWarnings({"unchecked", "rawtypes"})
    public static final Map<String, String> getHttpRequestParameters(HttpServletRequest httpRequest) {
        Map<String, Object> raw_parameters = httpRequest.getParameterMap();
        Map<String, String> map = new HashMap<String, String>(raw_parameters.size());
        for (Map.Entry<String, Object> entry : raw_parameters.entrySet()) {
            String key = entry.getKey();
            Object obj = entry.getValue();
            if (obj instanceof List) {
                List<String> list = (List) obj;
                if (!list.isEmpty()) {
                    map.put(key, list.get(0));
                }
            } else if (obj instanceof String[]) {
                String value = "";
                String[] values = (String[]) obj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
                map.put(key, value);
            }
        }
        return map;
    }
}
