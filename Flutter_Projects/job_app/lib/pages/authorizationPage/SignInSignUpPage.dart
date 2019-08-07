import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:job_app/items/StyleSettings.dart';
import 'package:ant_icons/ant_icons.dart';
import 'package:job_app/pages/mainPage/MainPage.dart';
import 'package:job_app/pages/authorizationPage/Authorization.dart';


class SignInSignUp extends StatefulWidget{
  @override
  State<StatefulWidget> createState() => new _LogInSignUpState();
}

enum FormMode { LOGIN, SIGNUP }

class _LogInSignUpState extends State<SignInSignUp>{

  FormMode _formMode;
  Authorization _db = new Authorization();

  String _pageText = "Sign In";
  String _secondButtonText = "Create an account";
  bool _isLoading = false;

  TextEditingController _emailTextController = new TextEditingController();
  TextEditingController _passwordTextController = new TextEditingController();

  final navigatorKey = GlobalKey<NavigatorState>();

  @override
    Widget build(BuildContext context) => MaterialApp(
          navigatorKey: navigatorKey,
          debugShowCheckedModeBanner: false,
          home: WillPopScope(
            onWillPop: () async => false,
            child: Scaffold(
              appBar: AppBar(
                title: Text(_pageText),
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

  @override
  void initState(){
    super.initState();
    _formMode = FormMode.LOGIN;
    _db.getUser().then((user){
      if(user != null){
        navigatorKey.currentState.push(MaterialPageRoute(builder: (context) => MainPage()));
      }
    });
  }

  Widget _showCircularProgress(){
    if (_isLoading) {
      return Center(child: CircularProgressIndicator(valueColor: new AlwaysStoppedAnimation<Color>(styleColor)));
    } return Container(height: 0.0, width: 0.0,);

  }

  void signUpWithEmail() async{
    setState(() {
      _isLoading = true;
    });
    _db.signUpWithEmail(_emailTextController.text, _passwordTextController.text).then((answer){
      if(answer == true){
        Navigator.push(context ,MaterialPageRoute(builder: (context) => SignInSignUp()));
        _formMode = FormMode.LOGIN;
      }else{
        //show message try again
      }
    });
    setState(() {
      _isLoading = false;
    });
  }

  void signInWithEmail() async{
    _db.setIsLoading();
    setState(() {
      _isLoading = _db.setToLoad();
    });
    _db.signInWithEmail(_emailTextController.text, _passwordTextController.text, navigatorKey).then((answer){
      if(answer == true){
        navigatorKey.currentState.push(MaterialPageRoute(builder: (context) => MainPage()));
      }else{
        //show message try again
        setState(() {
          _isLoading = _db.setToLoad();
        });
      }
    });
    setState(() {
      _isLoading = _db.setToLoad();
    });
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
        _showCircularProgress(),
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
        controller: _emailTextController,
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
        controller: _passwordTextController,
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
        onPressed: () {
          if(_formMode == FormMode.SIGNUP){
            setState(() {
              signUpWithEmail();
            });
          }else{
            setState(() {
              signInWithEmail();
            });
          }
        },
        child: Text(_pageText,
          style: new TextStyle(fontSize:15),
        ),
      ),
    );
  }

  Widget _showSecondButton(){
    return new FlatButton(
      child: new Text(_secondButtonText,
          style:
          new TextStyle(fontSize: 14.0, fontWeight: FontWeight.w300)),
      onPressed: (){
        if(_pageText == "Sign In"){
          setState(() {
            _formMode = FormMode.SIGNUP;
            _pageText = "Sign Up";
            _secondButtonText = "Already have an account? Sign In";
          });
        }else{
          setState(() {
            _formMode = FormMode.LOGIN;
            _pageText = "Sign In";
            _secondButtonText = "Create an account";
          });
        }
      }
    );
  }
}