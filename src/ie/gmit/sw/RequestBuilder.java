package ie.gmit.sw;

public class RequestBuilder {
	
	private String Algo, s1, t1, taskNo;
	
	public RequestBuilder(String algo, String s1, String t1, String taskNo) {
		super();
		Algo = algo;
		this.s1 = s1;
		this.t1 = t1;
		this.taskNo = taskNo;
	}

	public String getAlgo() {
		return Algo;
	}

	public void setAlgo(String algo) {
		Algo = algo;
	}

	public String getS1() {
		return s1;
	}

	public void setS1(String s1) {
		this.s1 = s1;
	}

	

	public String getT1() {
		return t1;
	}

	public void setT1(String t1) {
		this.t1 = t1;
	}

	public String getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	
	

}
