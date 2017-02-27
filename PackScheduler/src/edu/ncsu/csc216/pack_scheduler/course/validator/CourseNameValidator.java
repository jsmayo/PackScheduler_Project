/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Checks to see if a course name is valid by implementing the 
 * state object pattern for its finite state machine design. 
 * 
 *@author Steven Mayo
 */
public class CourseNameValidator {
	/** Number of text-characters processed. */
	private int letterCount = 0;
	/** Number of digit-characters processed. */
	private int digitCount = 0;
	//private int suffixCount = 0;
	/** Boolean value that represents if a course name is valid or not. */
	private boolean validEndState = false;
	/** Integer that represents the current state of the CourseNameValidator. */
	private int currentState = 0;
	//final integers because they do not need to change. 
	/** Integer that represents the initial state of the CourseNameValidator. */
	private final int INITIAL_STATE = 0;
	/** Integer that represents the letter state of the CourseNameValidator. */
	private final int LETTER_STATE = 1;
	/** Integer that represents the digit state of the CourseNameValidator. */
	private final int DIGIT_STATE = 2;
	/** Integer that represents the digit state of the CourseNameValidator. */
	private final int SUFFIX_STATE = 3;
	/** InitialState object needed for the state pattern. */
	InitialState initialState;
	/** LetterState object needed for the state pattern. */
	LetterState letterState;
	/** DigitState object needed for the state pattern. */
	DigitState digitState;

	/**
	 * Constructor for the CourseNameValidator class. Once called,
	 * The letter and digit state objects are initialized.
	 */
	public CourseNameValidator() {
		letterState = new LetterState();
		digitState = new DigitState();
	}

	/**
	 * Checks to see if the provided course name is valid. Characters are 
	 * analyzed one at a time and passed through a series of state object
	 * comparisons. 
	 * @param courseName The course name to be checked for validity.
	 * @return true if the course name is valid, false otherwise.
	 * @throws InvalidTransitionException if the course name is invalid. 
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException  {
		initialState = new InitialState();
		currentState = INITIAL_STATE;
		
		for(int i = 0; i < courseName.length(); i++) {
			char c = courseName.charAt(i);
			
			if(!Character.isLetter(c) && !Character.isDigit(c)) throw new InvalidTransitionException("Course name can only contain letters and digits.");
			
			//switch based on currentState (int), but let the states be objects that handle their own behaivor.
			switch(currentState) {
			//initial state
			case INITIAL_STATE:
				if(Character.isLetter(c)) initialState.onLetter();
				else if(Character.isDigit(c)) throw new InvalidTransitionException("Course name must start with a letter.");
				break;
			case LETTER_STATE:
				if(Character.isLetter(c) && letterCount >= LetterState.MIN_PREFIX_LETTERS && letterCount <= LetterState.MAX_PREFIX_LETTERS) letterState.onLetter();
				if(Character.isDigit(c)) {
					//Can probably delete this next line, since it shouldn't occur (possibly).
					if(letterCount < LetterState.MIN_PREFIX_LETTERS) throw new InvalidTransitionException("Must have at least 1 letter");
					if(letterCount > LetterState.MAX_PREFIX_LETTERS) throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
					letterState.onDigit();
				}
				break;
			case DIGIT_STATE:
				if(Character.isDigit(c)) {
					if(digitCount < DigitState.REQUIRED_DIGITS) digitState.onDigit();
					else throw new InvalidTransitionException("Course name can only have 3 digits.");
				}
				if(Character.isLetter(c)) {
					if(digitCount != DigitState.REQUIRED_DIGITS) throw new InvalidTransitionException("Course name must have 3 digits.");
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
				
			default:
			}
		}
		if(digitCount == DigitState.REQUIRED_DIGITS) validEndState = true;
		else validEndState = false;
		return validEndState;

	}
	
	/**
	 * Abstract class that declares methods which should be implemented 
	 * by all state objects. State objects are used as an implementation 
	 * of the state pattern, when checking to see if a course name 
	 * is valid.
	 * 
	 * 
	 *@author Steven Mayo
	 */
	public abstract class State {
		
		/**
		 * Abstract method that should handle state validation 
		 * when processing text characters.
		 */
		public abstract void onLetter();
		
		/**
		 * Abstract method that should handle state validation
		 * when processing digit characters. 
		 */
		public abstract void onDigit();
		
		/** While processing input, if a character is encountered that is
		 * not a letter or digit, then a checked exception will be thrown.
		 * @throws InvalidTransitionException Checked exception thrown when
		 * encountering an invalid character while processing input.
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}
	
	/**
	 * State object that represents what the initial state should be for
	 * implementation of the state pattern.
	 * 
	 *@author Steven Mayo
	 */
	public class InitialState extends State {
		
		/**
		 * Constructor for the InitialState object that sets
		 * the letter and digit counts to zero.
		 */
		private InitialState(){
			letterCount = 0;
			digitCount = 0;
		}
		
		/**
		 * Called when encountering text, character input. Afterwards,
		 * the letter count is increased the currentState is
		 * set to LETTER_STATE. 
		 */
		public void onLetter() {
			letterCount++;
			currentState = LETTER_STATE;
		}
		
		/**
		 * Declared, but not defined, since an exception will be thrown if
		 * digits are encountered while still in the INITIAL_STATE.
		 */
		public void onDigit() {
			//digitCount = 0; 
			//currentState = DIGIT_STATE;
		}
	}
	
	/**
	 * State object that represents what the letter state should be for
	 * implementation of the state pattern.
	 * 
	 *@author Steven Mayo
	 */
	public class LetterState extends State {
		/** Maximum number of characters allowed in a course name. */
		private static final int MAX_PREFIX_LETTERS = 4;
		/** Minimum number of characters allowed in a course name. */
		private static final int MIN_PREFIX_LETTERS = 1;
		
		
		/**
		 * Called when encountering textual character input. Afterwards,
		 * the letter count is increased the currentState is
		 * set to LETTER_STATE. 
		 */
		public void onLetter() {
			letterCount++;
		}
		
		/**
		 * Called when encountering character input that is in digit form.
		 * Afterwards, the digit count is increased and the currentState is
		 * set to DIGIT_STATE.
		 */
		public void onDigit() {
			digitCount = 1;
			currentState = DIGIT_STATE;
		}
	}
	
	/**
	 * State object that represents what the digit state should be for
	 * implementation of the state pattern.
	 * 
	 *@author Steven Mayo
	 */
	public class DigitState extends State {
		/** Required number of digits that a course name must have. */
		private static final int REQUIRED_DIGITS = 3;
		
		/**
		 * Called when encountering text, character input. Afterwards,
		 * the letter count is increased the currentState is
		 * set to LETTER_STATE. 
		 */
		public void onLetter() {
			//if(digitCount != MAX_DIGITS_ALLOWED) throw new InvalidTransitionException("Course name must have 3 digits.");
			//else {
				//suffixCount = 1;
				currentState = SUFFIX_STATE;
			}
		
		/**
		 * Called to increase the digit count when numerical/digit characters
		 * are encountered during input processing.
		 */
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
