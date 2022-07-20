In this project , I am using dragger hilt .
Steps I have followed to integrate dragger hitl in my project :
1. Integrate hilt by adding required dependancy, classpath and plugins.
2. Construct hilt android app class
3. Add appname on android manifest file
4.Construct Module and write provides fuction
5. In steps 4 , take care of InstalledIn annotation. ApplicationComponent is absollete and it is replaced by singleton,
6. Inject Constructor in the repository and all rquired class 
7. Annonate the viewmodel class with hiltviewmodel
8. Annotae entry point for activity and fragments
9. add dependancy for the koltin ktx fragment 
10. Inject field in the activity or fragment by viewModels()
