import 'package:job_app/CalendarView.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'StyleSettings.dart';

class LogInView extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        debugShowCheckedModeBanner: false,
        home: WillPopScope(
          onWillPop: () async => false,
          child: Scaffold(
            appBar: AppBar(
              title: Text("Log in"),
              backgroundColor: styleColor,
                leading: IconButton(icon:Icon(Icons.arrow_back),
                  onPressed:() => Navigator.pushReplacement(context, MaterialPageRoute(builder: (context) => TextControl())),
                )
            ),
            body: Center(
              child: Container(
                margin: EdgeInsets.only(top: 40),
                child: ListView(
                  children: <Widget>[
                    Container(
                      width: 250,
                      height: 250,
                      margin: EdgeInsets.only(bottom: 20),
                      child: Image(
                        image: AssetImage("assets/images/undraw_mobile_login_ikmv.png"),
                      ),
                    ),
                    Container(
                      margin: EdgeInsets.only(left: 40.0, right: 40.0),
                      child: TextField(
                        maxLength: 30,
                        obscureText: false,
                        decoration: InputDecoration(
                          border: OutlineInputBorder(borderRadius: BorderRadius.circular(20)),
                          labelText: 'Login',
                        ),
                      ),
                    ),
                    new Divider(
                      color: Colors.transparent,
                      height: 5.0,
                    ),
                    Container(
                      margin: EdgeInsets.only(left: 40.0, right: 40.0),
                      child: TextField(
                        maxLength: 20,
                        obscureText: true,
                        decoration: InputDecoration(
                          border: OutlineInputBorder(borderRadius: BorderRadius.circular(20)),
                          labelText: 'Password',
                        ),
                      ),
                    ),
                    new Divider(
                      color: Colors.transparent,
                      height: 10.0,
                    ),
                    Container(
                      margin: EdgeInsets.only(left: 125, right: 125),
                      child: new RaisedButton(
                        padding: EdgeInsets.all(20.0),
                        shape: new RoundedRectangleBorder(borderRadius: new BorderRadius.circular(30.0)),
                        onPressed: () {},
                        child: const Text('Log in'),
                      ),
                    ),

                  ],
                ),
              ),
            ),
          ),
        )
    );
  }
}