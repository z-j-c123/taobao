package util;

import java.util.Random;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class SendCode {

	private static final String product = "Dysmsapi";
	//产品域名,开发者无需替换
	private static final String domain = "dysmsapi.aliyuncs.com";
	// TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
	private static final String accessKeyId = "LTAI4Fk4ZyJEFF8RWtFgSkeq";
	private static final String accessKeySecret = "QeIZWGpIEidX7VmF6lW4Gth7ZSPMG9";
	private static final String templateCode = "SMS_205465776";
	//设置验证码有效时长
	private static final Integer effectiveduration = 1000*60*5;
	 public static SendSmsResponse sendSms(String phone,String code) throws ClientException {
	        //可自助调整超时时间
	        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
	        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
	        //初始化acsClient,暂不支持region化
	        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
	        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
	        IAcsClient acsClient = new DefaultAcsClient(profile);
	        //组装请求对象-具体描述见控制台-文档部分内容
	        SendSmsRequest request = new SendSmsRequest();
	        //必填:待发送手机号
	        request.setPhoneNumbers(phone);
	        //必填:短信签名-可在短信控制台中找到
	        request.setSignName("个人商城");
	        //必填:短信模板-可在短信控制台中找到
	        request.setTemplateCode(templateCode);//短信模板
	        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
	        request.setTemplateParam("{\"code\":\""+code+"\"}");
	        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
	        //request.setSmsUpExtendCode("90997");
	        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
	        request.setOutId("yourOutId");
	        //hint 此处可能会抛出异常，注意catch
	        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
	        return sendSmsResponse;
	    }
	 
	 //产生一个六位数的验证码
	 public static String getCode()
	 {
		 return ((int)(100000+new Random().nextDouble()*(999999-100000+1)))+"";
	 }
	 
	 //判断验证码是否超时						//获取验证码时间			
	 public static Boolean whetherTimeOut(Long acquisitionTime)
	 {
		 //现在时间
		 Long presentTime = System.currentTimeMillis();
		 if(presentTime-acquisitionTime<=effectiveduration)
			 return false;
		 return true;
	 }
}
