package com.qingqing.stock_analyse.util.web;

import com.qingqing.common.exception.RequestValidateException;
import com.qingqing.common.util.StringUtils;
import com.qingqing.stock_analyse.exception.StockAnalyseRuntimeException;
import org.codehaus.jackson.*;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class ServletUtil {
    private static final Charset CHARSET = Charset.forName("UTF-8");

    private static Pattern localIpPattern = Pattern.compile("^192\\.168.*|^10\\..*|^172\\.(1[6-9]|2\\d|3[0-1]).*|^127\\..*");
    private static Pattern ipv4Pattern = Pattern.compile("^(1?\\d\\d?|2[0-4]\\d|25[0-5])\\.(1?\\d\\d?|2[0-4]\\d|25[0-5])\\.(1?\\d\\d?|2[0-4]\\d|25[0-5])\\.(1?\\d\\d?|2[0-4]\\d|25[0-5])$");
    private static Logger logger = LoggerFactory.getLogger(ServletUtil.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private static final JsonEncoding encoding = JsonEncoding.UTF8;

    public static String readStringFromRequest(HttpServletRequest request, Charset charset) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream(), charset));

            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RequestValidateException("read request body error.", "read request body error.", e);
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static String readStringFromRequest(HttpServletRequest request) {
        return readStringFromRequest(request, CHARSET);
    }

//	public static String getRemoteAddress(HttpServletRequest request) {
//		String ipAddress = request.getHeader("x-forwarded-for");
//		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
//			ipAddress = request.getHeader("Proxy-Client-IP");
//		}
//		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
//			ipAddress = request.getHeader("WL-Proxy-Client-IP");
//		}
//		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
//			ipAddress = request.getRemoteAddr();
//			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
//				// 根据网卡取本机配置的IP
//				InetAddress inet = null;
//				try {
//					inet = InetAddress.getLocalHost();
//				} catch (UnknownHostException e) {
//					e.printStackTrace();
//				}
//				ipAddress = inet.getHostAddress();
//			}
//		}
//		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
//		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
//			if (ipAddress.indexOf(",") > 0) {
//				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
//			}
//		}
//		return ipAddress;
//	}

    public static void sendXMLResponse(HttpServletResponse response, int status, String xml) {
        response.setContentType("text/xml; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setStatus(status);
        PrintWriter out;
        try {
            out = response.getWriter();
            out.println(xml);
            out.flush();
        } catch (IOException e) {
            logger.error("send xml error. xml:{}", xml, e);
        }
    }

    public static void sendTextResponse(HttpServletResponse response, int status, String text) {
        response.setContentType("text/plain; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setStatus(status);
        try {
            response.getOutputStream().write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void sendObjectResponseByJson(HttpServletResponse response, Object object) {
        try {
            response.setContentType("text/plain; charset=UTF-8");

            JsonGenerator generator = objectMapper.getJsonFactory().createJsonGenerator(response.getOutputStream(),
                    encoding);
            objectMapper.writeValue(generator, object);
        } catch (Exception ex) {
            throw new StockAnalyseRuntimeException("json convert error", ex);
        }
    }

    public static <T> T getObjectRequestFromJson(HttpServletRequest request, Class<T> cls) {
        try {
            JsonParser parser = objectMapper.getJsonFactory().createJsonParser(request.getInputStream());
            T t = objectMapper.readValue(parser, cls);
            return t;
        } catch (IOException ex) {
            throw new RequestValidateException("parse json error", ex);
        }
    }

    public static <T> List<T> getObjectsFromJson(HttpServletRequest request, Class<T> clsT) {
        JsonParser parser;
        try {
            parser = objectMapper.getJsonFactory().createJsonParser(request.getInputStream());

            JsonNode nodes = parser.readValueAsTree();

            List<T> list = new LinkedList<T>();
            for (JsonNode node : nodes) {
                list.add(objectMapper.readValue(node, clsT));
            }

            return list;
        } catch (IOException e) {
            throw new RequestValidateException("parse json error", e);
        }
    }

    public static String getJsonFromObject(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonGenerationException e) {
            logger.error("get json error", e);
            throw new StockAnalyseRuntimeException("get json error", e);
        } catch (JsonMappingException e) {
            logger.error("get json error", e);
            throw new StockAnalyseRuntimeException("get json error", e);
        } catch (IOException e) {
            logger.error("get json error", e);
            throw new StockAnalyseRuntimeException("get json error", e);
        }
    }

    public static String encodeUrl(String url) throws UnsupportedEncodingException {
        return encodeUrl(url, "utf-8");
    }

    public static String decodeUrl(String url) throws UnsupportedEncodingException {
        return URLDecoder.decode(url, "utf-8");
    }

    public static String encodeUrl(String url, String charset) throws UnsupportedEncodingException {
        return URLEncoder.encode(url, charset);
    }

    public static String decodeUrl(String url, String charset) throws UnsupportedEncodingException {
        return URLDecoder.decode(url, charset);
    }

    public static HttpHeaders getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text", "html", CHARSET));
        return headers;
    }

    public static void printNoView(HttpServletResponse response, Object object, String contentType) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("UTF-8");
        response.setContentType(contentType);
        PrintWriter out;
        try {
            out = response.getWriter();
            out.write(String.valueOf(object));
            out.flush();
        } catch (IOException e) {
            throw new StockAnalyseRuntimeException("", e);
        }
    }

    public static void printNoCharsetView(HttpServletResponse response, Object object, String contentType) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(contentType);
        PrintWriter out;
        try {
            out = response.getWriter();
            out.write(String.valueOf(object));
            out.flush();
        } catch (IOException e) {
            throw new StockAnalyseRuntimeException("", e);
        }
    }

    public static void processException(HttpServletResponse response, Exception e, String errorMsg) {
        logger.error("process exception, errorMsg:" + errorMsg + ", ex:", e);
        PrintWriter out;
        try {
            out = response.getWriter();
            if (e instanceof StockAnalyseRuntimeException) {
                StockAnalyseRuntimeException ex = (StockAnalyseRuntimeException) e;
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                out.write(ex.getMessage());
            } else {
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                out.write(e.getMessage());
            }
        } catch (IOException ioe) {
            throw new StockAnalyseRuntimeException("", ioe);
        }
    }

    public static void printNoView(HttpServletResponse response, Object object) {
        printNoView(response, object, "text/plain");
    }

    /**
     * 设置cdn缓存时间
     */
    public static void setResponseNoCache(HttpServletResponse response) {
        setResponseExpire(response, 0L);
    }

    /**
     * 设置cdn缓存时间
     */
    public static void setResponseExpire(HttpServletResponse response, long seconds) {

        if (seconds <= 0l) {
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
            response.addDateHeader("Expires", 1L);
        } else {
            long now = new Date().getTime();
            response.setDateHeader("Last-Modified", now);
            response.setDateHeader("Expires", now + TimeUnit.SECONDS.toMillis(seconds));
            response.setHeader("Cache-Control", "public, max-age=" + seconds);
        }
    }


    /**
     * 从HTTP请求中取得客户端IP地址
     *
     * @param req
     * @return 客户端IP地址，无法获得则返回空字符串
     */
    public static String getRemoteAddress(HttpServletRequest req) {
        String ip = req.getParameter("x-real-ip");
        if (!StringUtils.isEmpty(ip)) {
            return ip;
        }

        ip = parseIpInHeader(req.getHeader("x-forwarded-for"));
        if (StringUtils.isEmpty(ip)) {
            ip = req.getRemoteAddr();
        }

        return ip.trim();
    }

    private static String parseIpInHeader(String header) {
        if (StringUtils.isEmpty(header)) {
            return "";
        }

        String[] ips = header.split(",\\s*");
        for (String ip : ips) {
            if (isIpV4(ip) && !isLocalIp(ip)) {
                return ip;
            } else {
                continue;
            }
        }
        return "";
    }

    private static boolean isLocalIp(String ip) {
        return localIpPattern.matcher(ip).matches();
    }

    private static boolean isIpV4(String ip) {
        return ipv4Pattern.matcher(ip).matches();
    }

}
