import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:job_app/items/MenuDrawer.dart';
import 'package:job_app/items/CalendarWidget.dart';
import 'package:ant_icons/ant_icons.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:job_app/StyleSettings.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:job_app/pages/SignInSignUpPage.dart';

final FirebaseAuth _auth = FirebaseAuth.instance;

class CalendarPage  extends StatefulWidget{
  @override
  State<StatefulWidget> createState() => _WidgetState();
}

class _WidgetState extends State<CalendarPage>{
  final String bcgImage = "assets/images/bckg3.jpg";
  final GlobalKey<ScaffoldState> scaffoldKey = GlobalKey<ScaffoldState>();
  DateTime selectedDate = DateTime.now();
  bool _isLoading = false;
  String user = null;

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
          //buttonTheme: ButtonThemeData(textTheme: ButtonTextTheme.accent),
          //dialogTheme: DialogTheme(titleTextStyle: DialogTheme.),
          accentColor: styleColor,
          primaryColor: styleColor),
      debugShowCheckedModeBanner: false,
      home: WillPopScope(
        onWillPop: () async => false,
        child:Scaffold(
                drawer: MenuDrawer(2),
                appBar: AppBar(
                  title: Text("Calendar"),
                  backgroundColor: styleColor,
                  actions: <Widget>[
                    new FlatButton(
                        child: new Icon(AntIcons.logout_outline, color: Colors.white),
                        onPressed: _signOut)
                  ],
                ),
                body: _getData(),
//                ListView(children: <Widget>[
                  /*Container(
                    foregroundDecoration: BoxDecoration(color: Colors.transparent),
                    width: 300,
                    height: 300,
                    margin: EdgeInsets.only(bottom: 20),
                    child: Image(
                      image: AssetImage("assets/images/undraw_calendar_dutt.png"),
                    ),
                  ),*/
//                  Center(child:Text(' CALENDAR PAGE ')),
//                ],
//                ),
                key: scaffoldKey,
                floatingActionButton: FloatingActionButton(
                  backgroundColor: styleColor,
                  child: Center(
                      child:Icon(AntIcons.form)
                  ),
                  onPressed: () {
                    scaffoldKey.currentState
                        .showBottomSheet((context) => _getBottomSheet());
                  }
                ),
              ),
            ),
    );
  }

  @override
  void initState(){
    super.initState();
    _setUser();
  }

  Widget _getData(){
    return StreamBuilder(
        stream: Firestore.instance.collection('hoursDB').snapshots(),
        builder: (context, snapshot){
          if(!snapshot.hasData) return const Text('Loading...');
          return ListView.builder(
            itemCount: snapshot.data.documents.length,
            itemBuilder: (context, index) =>
                _buildListItem(context, snapshot.data.documents[index]),
          );
        });
  }

   void _signOut() async {
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

  Container _getBottomSheet(){
    return Container(
        height: 300,
        alignment: Alignment.center,
        padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 10),
        margin: const EdgeInsets.symmetric(horizontal: 10, vertical: 10),
        decoration: BoxDecoration(
            color: Colors.grey[200],
            borderRadius: BorderRadius.circular(10)
        ),
        child: ListView(
          physics: const NeverScrollableScrollPhysics(),
          children: <Widget>[
            new Divider(
              color: Colors.transparent,
              height: 20.0,
            ),
            TextField(
              obscureText: false,
              decoration: InputDecoration(
                border: OutlineInputBorder(borderRadius: BorderRadius.circular(20)),
                labelText: 'Rate',
              ),
            ),
            new Divider(
              color: Colors.transparent,
              height: 20.0,
            ),
            Container(
              child: Row(
                children: <Widget>[
                  new RaisedButton(
                    padding: EdgeInsets.only(left: 50.0, right: 50.0, top: 20.0, bottom: 20.0),
                    shape: new RoundedRectangleBorder(borderRadius: new BorderRadius.circular(30.0)),
                    onPressed: () => _selectDate(context),
                    child: const Text('Start time'),
                  ),
                  Spacer(flex: 2),
                  new RaisedButton(
                    padding: EdgeInsets.only(left: 50.0, right: 50.0, top: 20.0, bottom: 20.0),
                    shape: new RoundedRectangleBorder(borderRadius: new BorderRadius.circular(30.0)),
                    onPressed: () {},
                    child: const Text('End time'),
                  ),
                ],
              ),
            ),
          ],
        )
    );
  }

  Widget _buildListItem(BuildContext context, DocumentSnapshot document) {
    if(user != document['user']) return ListTile(
      title: Row(
          children: <Widget>[
            Text(""),
          ]
      )
    );
    return ListTile(
      title: Row(
        children: <Widget>[
          Expanded(
            child: Text(
              document['date'].toString(),
            ),
          ),
          Expanded(
            child: Text(
              document['strHour'].toString(),
            ),
          ),
          Expanded(
            child: Text(
              document['endHour'].toString(),
            ),
          ),
          Expanded(
            child: Text(
              document['rate'].toString(),
            ),
          ),
          Expanded(
            child: Text(
              document['workHours'].toString(),
            ),
          ),
        ],
      ),
    );
  }

  Widget _showCircularProgress(){
    if (_isLoading) {
      return Center(child: CircularProgressIndicator(valueColor: new AlwaysStoppedAnimation<Color>(styleColor)));
    } return Container(height: 0.0, width: 0.0,);

  }

  Future<bool> _setUser() async {
    final FirebaseUser user = await FirebaseAuth.instance.currentUser();
    this.user = user.uid;
  }

  Future<Null> _selectDate(BuildContext context) async {
    final DateTime picked = await showDatePicker(
        initialDatePickerMode: DatePickerMode.day,
        context: context,
        initialDate: selectedDate,
        firstDate: DateTime(2015, 8),
        lastDate: DateTime(2050));
    if (picked != null && picked != selectedDate)
      setState(() {
        selectedDate = picked;
      });
  }
}