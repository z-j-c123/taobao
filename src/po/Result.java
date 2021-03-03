package po;
public class Result{
	private boolean flag;
	private String message;
	private Object data;
	public static Result success()
	{
		return result(true, null, null);
	}
	public static Result success(Object data)
	{
		return result(true, null, data);
	}
	public static Result fail()
	{
		return result(false, null, null);
	}
	public static Result fail(String message)
	{
		return result(false, message, null);
	}
	public static Result result(boolean flag,String message,Object data)
	{
		Result result = new Result();
		result.setFlag(flag);
		result.setMessage(message);
		result.setData(data);
		return result;
	}
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
