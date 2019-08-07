import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:job_app/pages/authorizationPage/SignInSignUpPage.dart';

final FirebaseAuth _auth = FirebaseAuth.instance;

class Authorization{

  String user;
  bool _isLoading = false;

  Future<bool> signInWithEmail(email, password, navigatorKey) async{
    FirebaseUser user;
    try{
      user = await _auth.signInWithEmailAndPassword(
          email: email,
          password: password,
      );
    }catch(e){
      this._isLoading = false;
      print(e.toString());
      print("wrong pass");
    }
    this._isLoading = false;
    return user != null;
  }

  void signOut(context) async {
    try {
      await FirebaseAuth.instance.signOut();
      //await widget.auth.signOut();
    } catch (e) {
      print(e);
    }finally{
      FirebaseAuth.instance.currentUser().then((firebaseUser){
        if(firebaseUser == null)
        {
          Navigator.pushReplacement(context, MaterialPageRoute(builder: (context) =>  SignInSignUp()));
        }
      });
    }
  }

  Future<bool> signUpWithEmail(email, password) async{
    FirebaseUser user;
    try{
      user = await _auth.createUserWithEmailAndPassword(
          email: email,
          password: password,
      );
    }catch(e){
      print(e.toString());
    }
    return user != null;
  }

  Future<FirebaseUser> getUser() async{
    return await _auth.currentUser();
  }

  bool setToLoad(){
    return _isLoading;
  }

  void setIsLoading(){
    this._isLoading = true;
  }

  /*Future<List<WorkDay>> getData(user) async{
    List<WorkDay> tempList = new List();
    List<DocumentSnapshot> test;
    try{
      Firestore _db = Firestore.instance;
      _db.collection('hoursDB')
          .where('user', isEqualTo: user)
          .snapshots()
          .listen((data) =>
          data.documents.forEach((doc){
            print("us: " + doc['user'].toString());
            t.add(doc['date']);
            tempList.add(new WorkDay(doc['strHour'].toDouble(), doc['endHour'].toDouble(), doc['workHours'].toDouble(), doc['date'], doc['rate'].toDouble()));
          }));
    }catch(e){
      print(e);
    }finally{
      // ignore: control_flow_in_finally
      return tempList;
    }
  }*/
}