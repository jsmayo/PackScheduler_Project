/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * 
 * 
 *@author Steven Mayo
 */
public class CourseNameValidator {
	
	private int letterCount = 0;
	private int digitCount = 0;
	//private int suffixCount = 0;
	private boolean validEndState = false;
	private int currentState = 0;
	//final integers because they do not need to change. 
	private final int INITIAL_STATE = 0;
	private final int LETTER_STATE = 1;
	private final int DIGIT_STATE = 2;
	private final int SUFFIX_STATE = 3;
	InitialState initialState;
	LetterState letterState;
	DigitState digitState;

	
	public CourseNameValidator() {
		 initialState = new InitialState();
		letterState = new LetterState();
		digitState = new DigitState();
	}

	
	public boolean isValid(String courseName) throws InvalidTransitionException  {
		currentState = INITIAL_STATE;
		
		for(int i = 0; i < courseName.length(); i++) {
			char c = courseName.charAt(i);
			
			if(!Character.isLetter(c) && !Character.isDigit(c)) throw new InvalidTransitionException("Course name can only contain letters and digits.");
			
			//switch based on currentState (int), but let the states be objects that handle their own behaivor.
			switch(currentState) {
			//initial state
			case INITIAL_STATE:
				if(Character.isLetter(c)) letterState.onLetter();
				if(Character.isDigit(c)) throw new InvalidTransitionException("Course name must start with a letter.");
				break;
			case LETTER_STATE:
				if(Character.isLetter(c) && letterCount >= LetterState.MIN_PREFIX_LETTERS && letterCount <= LetterState.MAX_PREFIX_LETTERS) letterState.onLetter();
				if(Character.isDigit(c)) {
					//Can probably delete this next line, since it shouldn't occur (possibly).
					if(letterCount < LetterState.MIN_PREFIX_LETTERS) throw new InvalidTransitionException("Must have at least 1 letter");
					if(letterCount > LetterState.MAX_PREFIX_LETTERS) throw new InvalidTransitionException("Cannot have more than 4 letters");
					letterState.onDigit();
				}
				break;
			case DIGIT_STATE:
				if(Character.isDigit(c)) {
					if(digitCount <= DigitState.MAX_DIGITS_ALLOWED) digitState.onDigit();
					else throw new InvalidTransitionException("Cannot have more than 3 digits.");
				}
				if(Character.isLetter(c)) {
					if(digitCount != DigitState.MAX_DIGITS_ALLOWED) throw new InvalidTransitionException("Must have 3 digits.");
					digitState.onLetter();
				}
				break;
			case SUFFIX_STATE:
				if(Character.isLetter(c)) {
					throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
				}
				else if (Character.isDigit(c)) {
					throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
				}
				break;
			}
		}
		if(digitCount == DigitState.MAX_DIGITS_ALLOWED) validEndState = true;
		else validEndState = false;
		return validEndState;

	}
	
	public abstract class State {
		
		public abstract void onLetter();
		
		public abstract void onDigit();
		
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}
	
	public class InitialState extends State {
		
		private InitialState(){
			letterCount = 0;
			digitCount = 0;
		}
		public void onLetter() {
			letterCount = 1;
			currentState = LETTER_STATE;
		}
		
		public void onDigit() {
			digitCount = 1; //put string in from requirements.
			currentState = DIGIT_STATE;
			//for tests, add if lettercount < digit count, throw an exception
		}
	}
	
	public class LetterState extends State {
		
		private static final int MAX_PREFIX_LETTERS = 4;
		private static final int MIN_PREFIX_LETTERS = 1;
		
		private LetterState() {
			//letter
		}
		
		public void onLetter() {
			letterCount++;
		}
		
		public void onDigit() {
			digitCount = 1;
			currentState = DIGIT_STATE;
		}
	}
	
	public class DigitState extends State {
		private static final int MAX_DIGITS_ALLOWED = 3;
		
		private DigitState() {
			//digit
		}
		
		public void onLetter() {
			//if(digitCount != MAX_DIGITS_ALLOWED) throw new InvalidTransitionException("Course name must have 3 digits.");
			//else {
				//suffixCount = 1;
				currentState = SUFFIX_STATE;
			}
				
		public void onDigit() {
			//if(digitCount < MAX_DIGITS_ALLOWED) 
			digitCount++;
			//else throw new InvalidTransitionException("Course name must have 3 digits.");
		}
	}
	
	//should only throw 2 different types of exceptions while in this state.
//	public class SuffixState extends State {
//		//private static final int MAX_SUFFIX_ALLOWED = 1;
//		
//		private SuffixState() {
//			//suffix constructor
//		}
//		
//		public void onLetter() {
//			//throw new InvalidTransitionException("Course name can only have 1 suffix letter");
//		}
//		
//		public void onDigit() {
//			//throw new InvalidTransitionException("Course name cannot have a digit suffix");
//		}
//	}
		
	
	
}
