

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import simplenlg.features.Feature;
import simplenlg.framework.CoordinatedPhraseElement;
import simplenlg.framework.InflectedWordElement;
import simplenlg.framework.LexicalCategory;
import simplenlg.framework.NLGFactory;
import simplenlg.framework.WordElement;
import simplenlg.lexicon.Lexicon;
import simplenlg.lexicon.XMLLexicon;
import simplenlg.phrasespec.NPPhraseSpec;
import simplenlg.realiser.english.Realiser;

public class All_expln_privacy {
public String algorithm_transparency(int name_index) {
		
		String name="empty";
		if (name_index==0) {name="John";}
		else if (name_index==1) {name="Adam";}
		else if (name_index==2) {name="Mary";}
		
		// compulsory set up
		Lexicon lexicon = new XMLLexicon();                          // default simplenlg lexicon
		NLGFactory nlgFactory = new NLGFactory(lexicon);             // factory based on lexicon
		Realiser realiser = new Realiser(lexicon);
		
		// Target: Dear Adam, the system tends to average out the highest and lowest rating of individual preferences of group members when it is a recommending sequence of places.
		//It recommends places that make sure that no-one is unhappy.
		
		NPPhraseSpec s1 = nlgFactory.createNounPhrase("Dear");
		NPPhraseSpec s2 = nlgFactory.createNounPhrase(name);
		s1.addPostModifier(s2); //s1 = s1+s2
		NPPhraseSpec s3 = nlgFactory.createNounPhrase(", the system tends to average out the highest and lowest rating of individual preferences of group members when it is a recommending sequence of places. It recommends places that make sure that no-one is unhappy");
		s2.addPostModifier(s3); //s1 = s2(s1+s2)+s3

		// create the final Realizer for all the phrases 
       String output = realiser.realiseSentence(s1);
       //System.out.println(output);
       // return final sentence 1 as output of function tesentence1
       return output;
	}
	
	public String plural_string(ArrayList<String> myArray)
	{
		Lexicon lexicon = new XMLLexicon();                          // default simplenlg lexicon
		NLGFactory nlgFactory = new NLGFactory(lexicon);             // factory based on lexicon
		Realiser realiser = new Realiser(lexicon);
		


		WordElement word = (WordElement) nlgFactory.createWord("place", LexicalCategory.NOUN);
		InflectedWordElement inflectedWord = new InflectedWordElement(word);
		inflectedWord.setPlural(true);
		String result = realiser.realise(inflectedWord).getRealisation();
		
//		NPPhraseSpec result = nlgFactory.createNounPhrase("places");
	   // NPPhraseSpec object2 = nlgFactory.createNounPhrase(al[situation].get(1));
	    
	    int size_myarray= myArray.size();
		// create phrase2 :places B,D and H
		
		//System.out.println("myArray "+myArray);
		NPPhraseSpec[] np = new NPPhraseSpec[size_myarray];
		for(int i = 0; i < size_myarray; i++)
		{
			//NPPhraseSpec object1 = nlgFactory.createNounPhrase(al[situation].get(0));
		    np[i] = nlgFactory.createNounPhrase(myArray.get(i));//myArray[i]
		}
	  //System.out.println("size_myarray "+size_myarray);
	  
		CoordinatedPhraseElement obj = null;
		obj = nlgFactory.createCoordinatedPhrase(np[0], np[1]);
		//obj.setFeature(Feature.CONJUNCTION, "and");
		
		for(int j=2;j<size_myarray;j++)
		{
			obj.addCoordinate(np[j]);
		}	
		
	    
		NPPhraseSpec p = nlgFactory.createNounPhrase();
	    p.addComplement(obj);
	   
	    p.addPreModifier(result);
	    
	   
		 
		// create the final Realizer for all the phrases 
       String output = realiser.realiseSentence(p);
  
       String str = output;
		char ch = 'p';
		int pos = 0;

		StringBuilder sb = new StringBuilder(str);
		
		// replace character at the specified position
		sb.setCharAt(pos, ch);
		output = sb.toString();
		
		

		// print the modified string
		//System.out.println("str "+str); 
	    
       // return final sentence 1 as output of function tesentence1
       return output;
	} 

	@SuppressWarnings("deprecation")
	public String singluar_string(ArrayList<String> myArray) {
		Lexicon lexicon = new XMLLexicon();                          // default simplenlg lexicon
		NLGFactory nlgFactory = new NLGFactory(lexicon);             // factory based on lexicon
		Realiser realiser = new Realiser(lexicon);

		WordElement word = (WordElement) nlgFactory.createWord("place", LexicalCategory.NOUN);
		InflectedWordElement inflectedWord = new InflectedWordElement(word);
		inflectedWord.setPlural(false);
		String result = realiser.realise(inflectedWord).getRealisation();
				
		// create phrase2 : G
		NPPhraseSpec object1 = nlgFactory.createNounPhrase(myArray.get(0));
	    
		// create the final Realizer for all the phrases 
       String output = realiser.realiseSentence(object1);
       
       
      // System.out.println(output);
       return output;
	    
	}
	
	//function 2: to create sentence 2
	// aspect: Repair Related (Transparency)
	// factor 1: Names of people (names of group_members)
	// factor 2: Rating value (low,med,high)
	// value that will change according to user: names of people, rating value of preferences
   public String like_recommended(int name_index)
	{
		
	Lexicon lexicon = new XMLLexicon();                          // default simplenlg lexicon
	NLGFactory nlgFactory = new NLGFactory(lexicon);             // factory based on lexicon
	Realiser realiser = new Realiser(lexicon);

	// Target: "We see that the first few highly rated places (F, H, and D) in the recommended sequence are according to your interests."
	
	// create phrase1 : "This is due to the fact "
	NPPhraseSpec s1 = nlgFactory.createNounPhrase(" We see that the first few highly rated ");   // create an NP
	//call the function that gives the values of places to be inserted
	

	int n=4;
	ArrayList<String>[] al = new ArrayList[n]; 
	al = places_for_situation(name_index);
	
	
	int al0_size = al[0].size(); //number of places in the like_recommended array
	//System.out.println("al0_size "+al0_size);
	
	//variable myarray to pass places computed into singular or plural function
	ArrayList<String>  myArray = new ArrayList<String>();
	myArray=al[0];
	//System.out.println("al[0] "+al[0]);
	String places =null;
	String output=null;
	if(al0_size > 0)
	{
		//singular function call
		if(al0_size==1)
		{
			places = singluar_string(myArray);
			places=places.replace(".", " ");
			//System.out.println("inside al0size==1"+places);
		}
		else if(al0_size>1){
			places = plural_string(myArray);
			places=places.replace(".", " ");
			//System.out.println("inside al0size>1"+places);
		}
	}
	else
	{
	 output= null; //return null;
	}
	
	
	//String places=plural_string(name_index,situation);
	NPPhraseSpec s2 = nlgFactory.createNounPhrase(places);
	//create phrase2 : "in the recommended sequence are according to your interests."
	NPPhraseSpec s3 = nlgFactory.createNounPhrase("in the recommended sequence are according to your interests");
	
	//final output	
	s1.addComplement(s2);
	s1.addComplement(s3);
	
	 output = realiser.realiseSentence(s1);
   //System.out.println(output);
   
   return output;
   }
   
	
	//function 3: to create sentence 3
	// aspect: --
	//headsup
	// factor 1: Complete or Partial information
	// value that will change according to user: Complete algorithm details / partial algorithm details

		
	public String headsup()
	{
		// compulsory set up
		Lexicon lexicon = new XMLLexicon();                          // default simplenlg lexicon
		NLGFactory nlgFactory = new NLGFactory(lexicon);             // factory based on lexicon
		Realiser realiser = new Realiser(lexicon);
				
		// Target :"But there are some situations where you may disagree with other group members."
		NPPhraseSpec object1 = nlgFactory.createNounPhrase("But there are some situations where you may disagree with other group members");
				
		// create the final Realizer for all the phrases 
       String output = realiser.realiseSentence(object1);
      // System.out.println(output);
       // return final sentence 1 as output of function tesentence1
       return output;
	}

	public String never_recommended(int name_index)
	{
		
	Lexicon lexicon = new XMLLexicon();                          // default simplenlg lexicon
	NLGFactory nlgFactory = new NLGFactory(lexicon);             // factory based on lexicon
	Realiser realiser = new Realiser(lexicon);

	// Target: "We note that places A and I is rated highly by you, but it has not been included in the sequence. 
	//This is because these places are rated low by Mary and John."
	
	// create phrase1 : "This is due to the fact "
	NPPhraseSpec s1 = nlgFactory.createNounPhrase(" We note that ");   // create an NP
	
	
	//int situation=3; //need to hardcode it here only
	int n=4;
	ArrayList<String>[] al = new ArrayList[n]; 
	al = places_for_situation(name_index); //call the function that gives the values of places to be inserted
	
	//never recommended
	int al3_size = al[3].size(); //number of places in the like_recommended array
	//System.out.println("al3_size "+al3_size);
	
	//variable myarray to pass places computed into singular or plural function
	ArrayList<String>  myArray = new ArrayList<String>();
	myArray = al[3];
	//System.out.println("al[3] "+al[3]);
	 
	String places =null;
	String output=null;
	if(al3_size > 0)
	{
		//singular function call
		if(al3_size==1)
		{
			places = singluar_string(myArray);
			places=places.replace(".", " ");
			//System.out.println("inside al1size==1"+places);
		}
		else if(al3_size>1){
			places = plural_string(myArray);
			places=places.replace(".", " ");
			//System.out.println("inside al1size>1"+places);
		}
	}
	else
	{
	 output= null; //return null;
	}
	
	
	//String places=plural_string(name_index,situation);
	NPPhraseSpec s2 = nlgFactory.createNounPhrase(places);
	
	
	
	s1.addComplement(s2);
	NPPhraseSpec s3 = nlgFactory.createNounPhrase("has/have been rated highly by you, but have not been included in the sequence. This is because the places is liked by only a few members ");
	
	s1.addComplement(s3);
	
	output = realiser.realiseSentence(s1);
    //System.out.println(output);
   
   return output;
   }

	public String recommended_later(int name_index)
	{
		
	Lexicon lexicon = new XMLLexicon();                          // default simplenlg lexicon
	NLGFactory nlgFactory = new NLGFactory(lexicon);             // factory based on lexicon
	Realiser realiser = new Realiser(lexicon);

	// Target: Additionally, we see that places B and J, liked by you, is visited later in the trip.
	//This is due to the fact that this places are rated moderately by Mary and rated highly by John. 
     
	// create phrase1 : "This is due to the fact "
	NPPhraseSpec s1 = nlgFactory.createNounPhrase(" Additionally, we see that  ");   // create an NP
	
	
	//int situation=3; //need to hardcode it here only
	int n=4;
	ArrayList<String>[] al = new ArrayList[n]; 
	al = places_for_situation(name_index); //call the function that gives the values of places to be inserted
	
	//never recommended
	int al2_size = al[2].size(); //number of places in the like_recommended array
	//System.out.println("al2_size "+al2_size);
	
	//variable myarray to pass places computed into singular or plural function
	ArrayList<String>  myArray = new ArrayList<String>();
	myArray = al[2];
	//System.out.println("al[2] "+al[2]);
	 
	String places =null;
	String output=null;
	if(al2_size > 0)
	{
		if(al2_size==1)
		{
			places = singluar_string(myArray); //singular function call
			places=places.replace(".", " ");
			//System.out.println("inside al1size==1"+places);
		}
		else if(al2_size>1){
			places = plural_string(myArray); //plural function call
			places=places.replace(".", " ");
			//System.out.println("inside al1size>1"+places);
		}
	}
	else
	{ places= null; //return null;
	}	
	
	//String places=plural_string(name_index,situation);
	NPPhraseSpec s2 = nlgFactory.createNounPhrase(places);
	
	s1.addComplement(s2);
	NPPhraseSpec s3 = nlgFactory.createNounPhrase("liked by you, will be visited later in the trip. This is due to the fact that these places are(is) rated moderately only by a handful of people.");
	s1.addComplement(s3);
	
	output = realiser.realiseSentence(s1);
    //System.out.println(output);
   
   return output;
   }
	
	static int min(int[] place_array,int name_index) {

		int[] users= {0,1,2};
		ArrayList<Integer>  others = new ArrayList<Integer>();
		
		//display name of the other 2 people except name index
		for (int i=0;i<users.length;i++)
		{
			if (users[i]!=name_index)
			{
				// create an array without name index
				others.add(users[i]);
				//System.out.println("inside int min function ");
				//System.out.println("others "+others);
			}
		}
		
		int min = place_array[0];
		for(int i=1;i<others.size();i++)
		{
			if(min > place_array[others.get(i)])
			{	//System.out.println("others.get(i) "+others.get(i));
				min = place_array[others.get(i)];
				//System.out.println("min now " +min);
			}
		}
		//System.out.println("min " +min);
		return min;
	}

	//function 4: to create sentence 4
		// aspect: Reassuring aspect
		// factor 1: Compromising information + names of people who compromise
		// value that will change according to user: Complete algorithm details / partial algorithm details
	public String dislike_recommended(int name_index)
    {
	Lexicon lexicon = new XMLLexicon();                          // default simplenlg lexicon
	NLGFactory nlgFactory = new NLGFactory(lexicon);             // factory based on lexicon
	Realiser realiser = new Realiser(lexicon);

	// Target: Finally, place G is rated moderately by other members in the group hence it is recommended later in the sequence.
	NPPhraseSpec object1 = nlgFactory.createNounPhrase("Finally,");
	
	int n=4;
	ArrayList<String>[] al = new ArrayList[n]; 
	al = places_for_situation(name_index);
	int al1_size=al[1].size();
	
	ArrayList<String>  myArray = new ArrayList<String>();
	myArray=al[1];
// System.out.println("al[1] "+al[1]);
	
	String places =null;
	String output=null;
	if(al1_size > 0)
	{
		//singular function call
		if(al1_size==1)
		{
			places = singluar_string(myArray);
			places=places.replace(".", " ");
			//System.out.println("inside al0size==1"+places);
		}
		else if(al1_size>1){
			places = plural_string(myArray);
			places=places.replace(".", " ");
			//System.out.println("inside al0size>1"+places);
		}
	}
	else
	{
	 output= null; //return null;
	}
	
	
	//String places=plural_string(name_index,situation);
	NPPhraseSpec s2 = nlgFactory.createNounPhrase(places);
	object1.addComplement(s2);
	NPPhraseSpec object3 = nlgFactory.createNounPhrase("is(are) rated moderately or low by you or other members in the group hence it is recommended later in the sequence.");
	
	object1.addComplement(object3);
	output = realiser.realiseSentence(object1);
	//System.out.println(output);
	return output;
	}
	
		

	
    public String compromise()
	{
		//target :  Furthermore, Mary is compromising in a similar situation like this
		// compulsory set up
		Lexicon lexicon = new XMLLexicon();                          // default simplenlg lexicon
		NLGFactory nlgFactory = new NLGFactory(lexicon);             // factory based on lexicon
		Realiser realiser = new Realiser(lexicon);
		
		NPPhraseSpec object1 = nlgFactory.createNounPhrase("Although you are recommended the places you don’t like, you get to visit places you love first. Furthermore, almost all group members, are compromising in similar situations of disagreement like these to satisfy the group. Hence this method is the best way to maximize the group's satisfaction");
		String output = realiser.realiseSentence(object1);
		
        //System.out.println(output);
       // return final sentence 1 as output of function tesentence1
       return output; 
	}

	static  ArrayList<String>[] places_for_situation(int name_index)
	{
		int [ ] [ ] rating_val = {  
				 {10, 4, 3, 6, 10, 9, 6, 8, 10, 8} ,//john
				 {1, 9, 8, 9, 7, 9, 6, 9, 3, 8}, //adam
				 {10, 5, 2, 7, 9, 8, 5, 6, 7, 6} //mary
				};
		
		String [] name_val = {"John","Adam","Mary"} ;
		String name= name_val[name_index];
		
		
		//lets select the 3 variables for john first
		//rating table of all users
		
		//rating values of john
		int [] ratingnow = rating_val[name_index];
		
		//place names
		String [] all = {"A","B","C","D","E","F","G","H","I","J"}; //allplaces
		String [] rec = {"E","F","H","D","J","B","G"}; //recommended sequence
		
		//length of all places
		int all_places_length=all.length;
		//length of recommended sequence
		int recommended_length = rec.length;
		 
		//finding the middle value, to split 
		int middle = recommended_length / 2; //3
		
		// create like_recommended list array //al[0]
		//create dislike_recommeded list array //al[1]
		//create like_rec_later list array //al[2]
		
		// initializing the 4 listarrays we need to store everyone's places according to situation
		int n=4; //4 situations
		ArrayList<String>[] al = new ArrayList[n]; 
	
	   for (int i = 0; i < n; i++) { 
	       al[i] = new ArrayList<String>(); 
	   } 
		
		int index_all;//create variable to store the index value of all[i] to retrieve john's rating for that i
		int rating_compare;//create variable to store john's current rating value
		
	//LIKE recommended and DISLIKE recommended
		//for loop to check if  the john's rating value given for the recommendation is greater than or equal to 7
		// for loop for j ,0 to middle
		for  (int i=0; i<all_places_length; i++)
		{
			for (int j=0; j<=middle; j++)
			{
				if (all[i] == rec[j])
				{
					index_all=i; //store the index value of all[i] to retrieve john's rating for that i
					rating_compare = ratingnow[index_all]; //variable to store john's current rating value
					//System.out.println(rating_compare);
					if (rating_compare >=7)
					{ al[0].add(all[i]); } //like recommended
					
					else if(rating_compare < 7)
					{ al[1].add(all[i]); } //dislike recommended
				}
			}
		}
		
	//	RECOMMENDED not in preferred order and DISLIKE recommended
		
		//for i --all elements
		// for j -- middle+1 until last recommended item
		for  (int i=0; i<all_places_length; i++)
		{
			for (int j=middle+1; j<recommended_length; j++)
			{
				if (all[i] == rec[j])
				{
					index_all=i; //store the index value of all[i] to retrieve john's rating for that i
					rating_compare = ratingnow[index_all]; //variable to store john's current rating value
					
					if (rating_compare >=7)
					{ al[2].add(all[i]); }  //RECOMMENDED not in preferred order
					
					else if(rating_compare < 7)
					{ al[1].add(all[i]);  }  //DISLIKE recommended
					
				}
				
			}
		}
	
	//NEVER RECOMMENDED		
		
		List<String> list1 =  Arrays.asList("A","B","C","D","E","F","G","H","I","J");
		List<String> list2 =  Arrays.asList("E","F","H","D","J","B","G");
		List<String> never_recommended = list1.stream()
		                          .filter(e -> !list2.contains(e))
		                          .collect (Collectors.toList()); // (3)
		//System.out.println("Never recommended"+never_recommended);
		
		int indexval;
	//	List<String> john_never_rec= new ArrayList<String>(); //al[3]
	
		for  (int i=0; i<all_places_length; i++)
		{
			for(int j=0;j<never_recommended.size();j++)
			{
			  if(never_recommended.get(j)==all[i])
				{
					indexval = i;
					
					if(ratingnow[indexval] >= 7)
					{ al[3].add(all[i]); } //never recommended to the group member
				}
			}
		}
		 
	   
//		System.out.println(name+" like_rec_later"+al[2]);
//		System.out.println(name+" like_recommended"+al[0]);
//		System.out.println(name+ " dislike_recommeded"+al[1]);
//		System.out.println(name+ " never recommended"+al[3]);

		return al;
	
	
	}


	// program starts here.

	public static void main(String[] args)
		{
		All_expln_privacy aep = new All_expln_privacy();
		
		int name_index=1; // change numbers here to choose the user -- 

		String a = aep.algorithm_transparency(name_index);
		String b = aep.like_recommended(name_index);
        String c = aep.headsup();
		String d= aep.never_recommended(name_index);
		String e = aep.recommended_later(name_index);
		String f = aep.dislike_recommended(name_index);
		String g = aep.compromise();
		System.out.println("Privacy Preserved Explanation:  \n"+a+"\n"+b+"\n"+c+"\n"+d+"\n"+e+"\n"+f+"\n"+g);
		
		
		}


}
