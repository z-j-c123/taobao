package util;

import java.io.FileWriter;
import java.io.IOException;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101500690716";

	// 商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCu4twF1+FzxoS/0GGLVzVNrYToOtksy7g6lCjJaDJkWdhbZ/4pXnMTVe0QXhXjnV2OZqfGeI5e9+zdvwOgL39x76JQpCHxc6kFr6lnqZPDOY7rK1fHoH3xZ1qF+LV3t1bAtMib3FwtVz02AN5rpy1+IZheFLug99PS3rjnadQO4AGrYLQWAiX3kIyEzHbGqrk0dbHnI3vKMepbK33K+t77Yws+bG1WxWr+bUf1GaW8z5pGTHCo6UYgMd7aXeXRFYPdyLJ0T/JjiwZQaIodtQ/shng2F3RPqBnSKFovbaSJ8lnzT1ilroucD7q50k5lLlI7/1mpOnVPqu97uPRvfZ3PAgMBAAECggEAf1+TSF43Qg9wWBP5N3dIx0594nYZ/tpRT7jhC+/r49LcFZbkMlUsEwUeKDGeV60hmrmBsLfVWa3QwqCAj4aIl/8khP9GCYlnrVCHXYzQpfuh7/4dbLOjhtLzx2ehI5RQhwIkqgAQ0qUbW92+IUIdjCQDyWHoVhCqIoc8vK8DV+Dx20j67SbyuUGUgpUkdv+pOutmGLWgYE3LBUzWqzzaTHAEU11N9KrTyv7fbh+KrM3jHuOVovbR+NNlq1OvS8B1vmUhSVj6ptMZmH/iwsHUST4sti5sepMa2aFYTZJZxwKukT8GE7irsZ/lEQmM6IjwDxEjHQb1J/DEhXZZHDFNgQKBgQDjA16WnictiyZ43hRwsk3vSpO1DIqF4/8vylgLWnnB3ntGbEHKyXX4UBRQXts7g6s5iKhuOLAN67PM73fd/r+yLpzmObLR9BXekgWtx6VIRs0Vf8A5a15WFQQuiU+bNhSxzJSMbxRqIBx2fMM6GbiWxwxxmjQNSJue/XEBTDYhXwKBgQDFN46p/v/6SfAFQV2Potp1t75TwodR/LDa8X6UJFOwcitHFmvuYM/4mmG/+Z7Br4nv9SWVYOdOeavhZcDWlvnMdLIpCxU+IKalNCJl5pwLoyL3o0uGGdvaSDiE5k/wmzdogBLH0nc1UOVktqmd+Du9xhP/N2j+P4kZ7RIsqVOpkQKBgQCtjEflsaaV5MlWocDSnx2BkGv3f0zvKuxN5s0EcPigQ6vOnLh3hSiQ2veMsde8Wd6ur1DEYsYE/mqIg5IJvtViLMx6HSYd6BJIAy4gOn6bELV8eBvGSdTmzk5SHVskd2W+v33ZFppuv3nDA4JkioFZk3Zp6s9cUGPYWjzoFxFS1QKBgH9CD1PQkPq/lRq/CBF0Y4VMcIr53nngFXriE+yh2ELmGNkBNdoXLHvdmq/W8kNG8zoctQjEA93WkzuZfiq6NyxgLMphNW7mgMHfd0bL3OuBdEHZZBV/QCeFLLtNJk46yjErW3MHcxa7QZqQZ7jggiQ92Y95AM32z5AkMvMQvESRAoGBAJfu/HKdCbOSFH9P/cOZssWJDa4NgPJa7MRgBg5usBvmYD07ZunCywppkrTFZxScB+NhuIm7KDeQHkBurnUqPLc0GpuMtMkaPfM+jtuyHbiDQ97BG2XMTXW7yJE6bIjevvvZ080DPQ2suajfkniMAdqYBn8LoIWaFjkfOWkT+s0q";

	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsU1BO/OP8aBwciRY4sNnaV+7pnZhBhheccBYcIiV0jGfIZZK5boaXevfmJYipIvxi59d5XbAYhNVEYkh9R5KCBhOr2yYrWAqDJmd4xnm9EeUzi+Jroza+5dX3vXQmv4MgaIOHFlCfAcrLECNDbimkHffyO16SpQKiMxAR7hpjSin3AjdFzxWrhvuQelORYizBr1dkzzujN/4xp5JOxk88t2NakgBZ4VTUl9SNn5BMVTB3DN5agN+HNW7czl5brS5PBOHV/I6O1EhMYSLRV33FexY+z2jN+VHUEtR9FObpnNfHjvR8Zc1JWn6wc3dYRQz797H1PFPLXzUbJmKGRvF5QIDAQAB";

	// 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/Taobao/paySuccess";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/Taobao/paySuccessJsp";

	// 签名方式
	public static String sign_type = "RSA2";

	// 字符编码格式
	public static String charset = "utf-8";

	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

	// 支付宝网关
	public static String log_path = "C:\\";
	
	// 商户UID
	public static String seller_id = "2088102179656274";

//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	/**
	 * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
	 * 
	 * @param sWord 要写入日志里的文本内容
	 */
	public static void logResult(String sWord) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
			writer.write(sWord);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static AlipayClient getAlipayClient() {
		return new DefaultAlipayClient(gatewayUrl, app_id, merchant_private_key, "json", charset, alipay_public_key, sign_type);
	}
}
