package comp110;

public class Theme {
	
	//Themes are currently setup as String arrays that are used...
	//	in Shell.java to call the corresponding styling methods in the...
	//	Shell, Prompt, and Output classes.
	
	public static final String[] COMP110 = {
			
			//SHELL
			/*Scroll Background*/ "#222", /*Output Background*/ "#222",
			
			//PROMPT
			/*Inactive Border Hint*/ "#FFF", /*Active Border Hint*/ "#FFF", 
			/*Valid Input Border Hint*/ "#0F0", /*Invalid Input Border Hint*/ "#F00", 
			/*Background*/ "#000", /*Data Type Text Fill*/ "#7BAFD4", 
			/*Input Field Background*/ "#222", /*Input Text Fill*/ "#FFF", 
			/*Prompt Text Fill*/ "#FFF", /*Send Button Background*/ "#000", 
			/*Send Button Text Fill*/ "#FFF",
			
			//OUTPUT
			/*Background*/ "#000", /*Output Text Fill*/ "#FFF", 
			/*Data Type Text Fill*/ "#7BAFD4"
			
	};
	
	public static final String[] UNC = {
			
			//SHELL
			/*Scroll Background*/ "#63beff", /*Output Background*/ "#63beff",
			
			//PROMPT
			/*Inactive Border Hint*/ "#FFF", /*Active Border Hint*/ "#FFF", 
			/*Valid Input Border Hint*/ "#63beff", /*Invalid Input Border Hint*/ "#1027bc", 
			/*Background*/ "#FFF", /*Data Type Text Fill*/ "#63beff", 
			/*Input Field Background*/ "#63beff", /*Input Text Fill*/ "#FFF", 
			/*Prompt Text Fill*/ "#63beff", /*Send Button Background*/ "#63beff", 
			/*Send Button Text Fill*/ "#FFF",
			
			//OUTPUT
			/*Background*/ "#FFF", /*Output Text Fill*/ "#63beff", 
			/*Data Type Text Fill*/ "#63beff"
			
	};
	
	public static final String[] CLASSIC = {
			
			//SHELL
			/*Scroll Background*/ "#000", /*Output Background*/ "#000",
			
			//PROMPT
			/*Inactive Border Hint*/ "#000", /*Active Border Hint*/ "#00FF11", 
			/*Valid Input Border Hint*/ "#00FF11", /*Invalid Input Border Hint*/ "#416D44", 
			/*Background*/ "#000", /*Data Type Text Fill*/ "#416D44", 
			/*Input Field Background*/ "#000", /*Input Text Fill*/ "#00FF11", 
			/*Prompt Text Fill*/ "#00FF11", /*Send Button Background*/ "#000", 
			/*Send Button Text Fill*/ "#00FF11",
			
			//OUTPUT
			/*Background*/ "#000", /*Output Text Fill*/ "#00FF11", 
			/*Data Type Text Fill*/ "#416D44"
			
	};

	public static final String[] GOOGLE_LIGHT = {
			
			//SHELL
			/*Scroll Background*/ "#FFF", /*Output Background*/ "#FFF",
			
			//PROMPT
			/*Inactive Border Hint*/ "#FFF", /*Active Border Hint*/ "#FFF", 
			/*Valid Input Border Hint*/ "#000", /*Invalid Input Border Hint*/ "#C4C4C4", 
			/*Background*/ "#FFF", /*Data Type Text Fill*/ "#EEB211", 
			/*Input Field Background*/ "#FFF", /*Input Text Fill*/ "#D50F25", 
			/*Prompt Text Fill*/ "#009925", /*Send Button Background*/ "#FFF", 
			/*Send Button Text Fill*/ "#3369E8",
			
			//OUTPUT
			/*Background*/ "#FFF", /*Output Text Fill*/ "#D50F25", 
			/*Data Type Text Fill*/ "#EEB211"
			
	};
	
}
