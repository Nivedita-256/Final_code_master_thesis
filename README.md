# Final_code_master_thesis
Generation of template based Explanations using simpleNLG
Functions used in All_expln_transparency.java

1.algorithm_transparency(name_index)
2.like_recommended(name_index)
3.headsup()
4.never_recommended(name_index)
5.recommended_later(name_index)
6.dislike_recommended(name_index)
7.compromise()

name_index = 0 is John; 
name_index = 1 is Adam;
name_index = 2 is Mary

Output of transparent explanation for name_index = 1
Output of function 1 :
Dear Adam, the system tends to average out the highest and lowest rating of individual preferences of group members when it is a recommending sequence of places. It recommends places that make sure that no-one is unhappy.

Output of function 2 :
We see that the first few highly rated places D, E, F and H in the recommended sequence are according to your interests.

Output of function 3 :
But there are some situations where you may disagree with other group members.

Output of function 4 :
We note that C has/have been rated highly by you, but have not been included in the sequence. This is because the C is rated low by John C is rated low by Mary.

Output of function 5 :
Additionally, we see that places B and J liked by you, will be visited later in the trip. This is due to the fact that place B is rated low by John and place J is rated moderately by Mary.

Output of function 6 :
Finally, G is(are) rated moderately or low by you or other members in the group hence it is recommended later in the sequence.

Output of function 7 :
Although you are recommended the places you don’t like, you get to visit places you love first. Furthermore, almost all group members, are compromising in similar situations of disagreement like these to satisfy the group. Hence this method is the best way to maximize the group's satisfaction.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Functions used in All_expln_privacy.java

1.algorithm_transparency(name_index)
2.like_recommended(name_index)
3.headsup()
4.never_recommended(name_index)
5.recommended_later(name_index)
6.dislike_recommended(name_index)
7.compromise()

name_index = 0 //John
name_index = 1 //Adam
name_index = 2 //Mary

Output of privacy preserving explanation for name_index = 1
Output of function 1 :
Dear Adam, the system tends to average out the highest and lowest rating of individual preferences of group members when it is a recommending sequence of places. It recommends places that make sure that no-one is unhappy.

Output of function 2 :
We see that the first few highly rated places D, E, F and H in the recommended sequence are according to your interests.

Output of function 3 :
But there are some situations where you may disagree with other group members.

Output of function 4 :
We note that C has/have been rated highly by you, but have not been included in the sequence. This is because the places is liked by only a few members.

Output of function 5 :
Additionally, we see that places B and J liked by you, will be visited later in the trip. This is due to the fact that these places are(is) rated moderately only by a handful of people.

Output of function 6 :
Finally, G is(are) rated moderately or low by you or other members in the group hence it is recommended later in the sequence.

Output of function 7 :
Although you are recommended the places you don’t like, you get to visit places you love first. Furthermore, almost all group members, are compromising in similar situations of disagreement like these to satisfy the group. Hence this method is the best way to maximize the group's satisfaction.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

function
plural_string
place --> places D, E, F and H

singular_string
place --> place C

places_for_situation
Logic
middle = recommended sequence length / 2

1. for the recommended sequence of item, find the rating of adam from ratingnow variable
2. assign the rating found to rating_compare

for the items present in the first half of the sequence
3. if (rating_compare >=7) then assign to like_recommended array
4. if (rating_compare < 7) then assign to dislike_recommended array

for the items present in the second half of the sequence
5. if (rating_compare >=7) then assign to recommended not in preferred order array
6. if (rating_compare < 7) then assign to dislike_recommended array

if the rating value is greater than or equal to 7 and not present in the recommended sequence,
assign it to never recommended array.

Then use these arrays in the functions to generate explanations

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
