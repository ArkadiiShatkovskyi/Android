import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:job_app/pages/SignInSignUpPage.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'WorkDay.dart';

final FirebaseAuth _auth = FirebaseAuth.instance;
final Firestore _db = Firestore.instance;

class DBConnect{

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

  Future<List> getData(user) async{
    print("user: " + user);
    List test = new List();
    _db.collection('hoursDB')
        .where('user', isEqualTo: user)
        .snapshots()
        .listen((data) =>
        data.documents.forEach((doc){
          print("us: " + doc['user'].toString());
          test.add(new WorkDay(doc['strHour'].toDouble(), doc['endHour'].toDouble(), doc['workHours'].toDouble(), doc['date'], doc['rate'].toDouble()));
        }));
    print("list: " + test.toString());
    return test;
  }
}