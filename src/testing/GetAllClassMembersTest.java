package testing;

public class GetAllClassMembersTest {

	public String str;
	
	public GetAllClassMembersTest(String s){
		str = s;
	}

	public static void main(String[] args) {
		new GetAllClassMembersTest("bob");
		new GetAllClassMembersTest("sal");
		new GetAllClassMembersTest("tuesday");
		new GetAllClassMembersTest("SON OF THE DEVIL");
		new GetAllClassMembersTest("eggma");
		GetAllClassMembersTest t = new GetAllClassMembersTest("germezqa");
		Object[] mems = GetAllClassMembersTest.class.getSigners();
		System.out.println(mems + "\n");
		for (int i = 0; i < mems.length; i++){
			GetAllClassMembersTest obj = (GetAllClassMembersTest) mems[i];
			System.out.println(obj.str);
		}
	}

}
