# Whatsapp-Chat-System
A chatting platform, to send messages and share pictures and videos with contacts.

## Chosen Topic8 Android: 
Android chat app with local disk cache for message and photo (via disk or Room). The chat app can interact with any backend via FireStore for realtime chat.

## Steps to set up this git repository project:
1. This application is set up using firebase, by creating and registering the application on the platform. 
2. Android Studio IDE, to set up the project and build the gradle files with the dependencies.
3. Emulators or devices setup with SDK version kitkat and above to run the appplication on it

Login credentials of existing users:
emma@gmail.com/123456
levi@gmail.com/123456

## Features implemented:
### 1.	Firebase Cloud storage of Chats - 
This table in the Firebase Realtime Database has the message contents, message id, sender id and the receiver id along with the isseen status, true or false.

### 2.	FirebaseCloud storage of Chat list -
This includes conversation between the users. It has the latest messages and the images sent between users identified by a unique id. 

### 3.	Firebase Authentication - 
using email and password user can register and later login with these registered credentials.

### 4.	Firebase notifications -
A user is notified when a new message is received. This notification shows the sender name and the content of the message. This can be heard with a ringtone and is seen upon pulling the notifications drawer on top.

### 5.	User profile picture -  
The user can change his/her profile picture from given the default profile picture. The image chosen is uploaded to the firebase database to be saved. All the references to the user's profile picture fetches this new image now.

### 6.	User cover picture - 
The user can change his/her cover picture from given the default profile picture. The image chosen is uploaded to the firebase database to be saved. All the references to the user's cover picture fetches this new image now.

### 7.	User social media -  
The user facebook, Instagram and website links are saved to the database as well. 

### 8.	Search Contacts - 
User can search his/her contact to filter the names based on the characters being typed in the search box. User can then click send message on the filtered contact to open the chat conversation window to send messages and images.

### 9.	Send message - 
User can send messages to anybody on the contact list. User types the message, and the status of the message is currently sent, when the receiver hasn't viewed it yet.

### 10.	Send Image - 
User can send images to anybody on the contact list. User choose the image from the image picker from the gallery of the phone and clicks on it to be sent. The status of the image is currently sent, when the receiver hasn't viewed it yet.

### 11.	 Delete message - 
Users can delete any message sent to their contacts in the chat window by clicking on it. The pop up has options: a. Delete message b. Cancel. On choosing delete the message gets deleted.

### 12.	Delete Image - 
Users can delete any image sent to their contacts in the chat window by clicking on it. The pop up has options: a. View image b. Delete Image c.Cancel. On choosing delete the image gets deleted.


### 13.	 View Image - 
When the user clicks on an image, the pop up has options: a. View image b. Delete Image c. Cancel. On choosing View image, the image opens up in a different screen with a larger view. 

### 14.	Seen and Sent status - 
When the user types the message and hits send, the status of the message is sent, when the receiver hasn't viewed it yet.

Once the receiver views it on his/her end in the chat window, this status on the sender's end gets updated to seen.

### 15.	View Profile -  
When a user clicks on view profile of a particular user, that user's profile picture, cover picture and social media links are displayed. The user can also click on send message from here to navigate to the chat window.
   
