package po;

public class VerificationCode {

	private String codeNumber;
	private Long createCodeNumberTime;
	
	public VerificationCode()	{}
	
	public VerificationCode(String codeNumber, Long createCodeNumberTime) {
		super();
		this.codeNumber = codeNumber;
		this.createCodeNumberTime = createCodeNumberTime;
	}

	public String getCodeNumber() {
		return codeNumber;
	}

	public Long getCreateCodeNumberTime() {
		return createCodeNumberTime;
	}

	public void setCodeNumber(String codeNumber) {
		this.codeNumber = codeNumber;
	}

	public void setCreateCodeNumberTime(Long createCodeNumberTime) {
		this.createCodeNumberTime = createCodeNumberTime;
	}

	public String toString() {
		return "VerificationCode [codeNumber=" + codeNumber + ", createCodeNumberTime=" + createCodeNumberTime + "]";
	}
	
}
