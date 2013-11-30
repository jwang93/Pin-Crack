##Shoulder Surfer##

##### About 

It is incredible how powerful the PIN to our smartphones is. For most people, that PIN grants access to one’s email, text messages, social media accounts, photos, contacts list. Yet, our culture is very lax about PIN security, especially with regards to smartphones. 

One of the most popular attacks on PINs is the Observation Attack. One specific type of observation attack is called a shoulder surfing attack, when an attacker peers over the shoulder of a target entering his password. 

The security for a PIN in this case is based on guessing difficulty, observation difficulty, and capture difficulty. In this application, we will focus on guessing difficulty and observation difficulty. Shoulder Surfer is an application that measures (1) how difficult your PIN is to guess and (2) how covertly you enter your PIN. It will then give you an output letting you know how secure your pin is to a shoulder surfing attack.  

Note: We will never store any of your data. Please use responsibly. 


##### How It Works

1. User A enters in his PIN as he normally would, while User B watches. 
2. User B enters in a guess of User A’s PIN, assigning a confidence level to each digit. 
3. The Pin Crack algorithm will calculate the fewest number of guesses it would take a computer to guess User A’s PIN based on User B’s guess. 

The accuracy of the guessing algorithm is heavily contingent on an honest representation of confidence. Ideally, the target is to have at least two digits with a confidence of 4-5. Higher confidence leads to a lower number of guesses. 

For guesses < 100, output will be red for dangerous. 
For 100 < guesses < 1000, output will be yellow for warning. 
For guesses > 1000, output will be green for secure. 


##### Experiment 

Perform three rounds of this operation. 
1. I will enter in a PIN 
2. Subject enters in a guess 
3. I check the answer
4. I record the guess count 


I will shuffle between 10 PINs 

1. 1234 
2. 5555
3. 2001
4. 1470 
5. 2083 
6. 4865
7. 1050
8. 5579 
9. 2140 
10. 7781

The first three PINs were chosen from Data Genetics most popular PINs that represent around 25% of all PINs. The last seven PINs were randomly generated. 

