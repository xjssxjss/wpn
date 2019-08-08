package com.sean.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.util.TextUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class HttpClientUtil {

	public static String doGet(String url, Map<String, String> param) {
		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String resultString = "";
		CloseableHttpResponse response = null;
		try {
			// 创建uri
			URIBuilder builder = new URIBuilder(url);
			if (param != null) {
				for (String key : param.keySet()) {
					builder.addParameter(key, param.get(key));
				}
			}
			URI uri = builder.build();

			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);

			// 执行请求
			response = httpclient.execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}

	public static String doGet(String url) {
		return doGet(url, null);
	}

	public static String doPost(String url, Map<String, String> param) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建参数列表
			if (param != null) {
				List<NameValuePair> paramList = new ArrayList<>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				// 模拟表单
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
				httpPost.setEntity(entity);
			}
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return resultString;
	}
	
	public static String doPost(String url) {
		return doPost(url, null);
	}

	/**
	 * Java将Unix时间戳转换成指定格式日期字符串
	 * @param timestampString 时间戳 如："1473048265";
	 * @param formats 要格式化的格式 默认："yyyy-MM-dd HH:mm:ss";
	 *
	 * @return 返回结果 如："2016-09-05 16:06:42";
	 */ public static String TimeStamp2Date(String timestampString, String formats) {
	 		if (TextUtils.isEmpty(formats)) formats = "yyyy-MM-dd HH:mm:ss"; Long timestamp = Long.parseLong(timestampString) * 1000;
	 		String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
	 		return date;
	 }

	public static String doPostJson(String url, String json) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建请求内容
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			//设置请求头
			httpPost.setHeader("Content-Type", "application/json");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			long epoch = df.parse("1970-01-01 00:00:00").getTime();
			Date d = new Date();
			String t = df.format(d);
			epoch = df.parse(t).getTime()/1000;
			//httpPost.setHeader("appid",appid);
			//httpPost.setHeader("timespan",epoch+"");
			//String sign = MD5Util.getMd5Value(appid + appkey + epoch).substring(8,24).toUpperCase();

			String signStest = "123456";
			//String MD5Test123456 = MD5Util.getMd5Value(signStest).substring(8,24).toUpperCase();
			httpPost.setEntity(entity);
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}


	/**
	 * 上传微信公众号临时素材
	 * @param url
	 * @param filePath
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String HcUploadFile(String url, String filePath) throws ClientProtocolException, IOException {
		HttpPost post = new HttpPost(url);
		File file = new File(filePath);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpEntity entity = null;
		HttpResponse response = null;
		String BoundaryStr = "------------7da2e536604c8";
		post.addHeader("Connection", "keep-alive");
		post.addHeader("Accept", "*/*");
		post.addHeader("Content-Type", "multipart/form-data;boundary=" + BoundaryStr);
		post.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
		MultipartEntityBuilder meb = MultipartEntityBuilder.create();
		meb.setBoundary(BoundaryStr).setCharset(Charset.forName("utf-8")).setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		meb.addBinaryBody("media", file, ContentType.APPLICATION_OCTET_STREAM, file.getName());
		entity = meb.build();
		post.setEntity(entity);
		response = httpclient.execute(post);
		entity = response.getEntity();
		String result = EntityUtils.toString(entity, "utf-8");
		EntityUtils.consume(entity);// 关闭流

		return result;
	}
}
