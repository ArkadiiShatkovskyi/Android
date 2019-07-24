import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:job_app/StyleSettings.dart';
import 'package:ant_icons/ant_icons.dart';

class LogInPage extends StatefulWidget{
  @override
  State<StatefulWidget> createState() => new _LogInSignUpState();
}

class _LogInSignUpState extends State<LogInPage>{
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        debugShowCheckedModeBanner: false,
        home: WillPopScope(
          onWillPop: () async => false,
          child: Scaffold(
            appBar: AppBar(
              title: Text("Sign in"),
              backgroundColor: styleColor,
            ),
            body: Center(
              child: Container(
                margin: EdgeInsets.only(top: 40),
                child: _showBody(),
              ),
            ),
          ),
        )
    );
  }

  Widget _showBody(){
    return ListView(
      children: <Widget>[
        _showImage(),
        _showEmailField(),
        new Divider(color: Colors.transparent, height: 5.0,),
        _showPasswordField(),
        new Divider(color: Colors.transparent, height: 10.0,),
        _showMainButton(),
        _showSecondButton(),
      ],
    );
  }

  Widget _showImage(){
    return Container(
      width: 250,
      height: 250,
      margin: EdgeInsets.only(bottom: 20),
      child: CircleAvatar(
        backgroundColor: Colors.transparent,
        radius: 48.0,
        child: Image.asset("assets/images/undraw_mobile_login_ikmv.png"),
      ),
    );
  }

  Widget _showEmailField(){
    return Container(
      margin: EdgeInsets.only(left: 40.0, right: 40.0),
      child: TextFormField(
        maxLines: 1,
        maxLength: 30,
        obscureText: false,
        decoration: InputDecoration(
          icon: Icon(AntIcons.mail_outline, color: styleColor),
          border: OutlineInputBorder(borderRadius: BorderRadius.circular(20)),
          labelText: 'Email',
        ),
      ),
    );
  }

  Widget _showPasswordField(){
    return Container(
      margin: EdgeInsets.only(left: 40.0, right: 40.0),
      child: TextFormField(
        maxLines: 1,
        maxLength: 20,
        obscureText: true,
        decoration: InputDecoration(
          icon: Icon(AntIcons.lock_outline, color: styleColor),
          border: OutlineInputBorder(borderRadius: BorderRadius.circular(20)),
          labelText: 'Password',
        ),
      ),
    );
  }

  Widget _showMainButton(){
    return Container(
      margin: EdgeInsets.only(left: 75, right: 75),
      child: new RaisedButton(
        color: styleColor,
        textColor: Colors.white,
        padding: EdgeInsets.all(20.0),
        shape: new RoundedRectangleBorder(borderRadius: new BorderRadius.circular(30.0)),
        onPressed: () {},
        child: Text('Sign in',
          style: new TextStyle(fontSize:15),
        ),
      ),
    );
  }

  Widget _showSecondButton(){
    return new FlatButton(
      child: new Text('Create an account',
          style:
          new TextStyle(fontSize: 14.0, fontWeight: FontWeight.w300)),
      onPressed: (){}
    );
  }
}