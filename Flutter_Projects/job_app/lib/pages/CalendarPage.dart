import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:job_app/pageElements/MenuDrawer.dart';
import 'package:ant_icons/ant_icons.dart';
import 'package:job_app/items/StyleSettings.dart';
import 'package:job_app/items/Authorization.dart';
import 'package:job_app/pageElements/WorkDataTable.dart';

class CalendarPage  extends StatefulWidget{
  @override
  State<StatefulWidget> createState() => _WidgetState();
}

class _WidgetState extends State<CalendarPage>{
  final String bcgImage = "assets/images/bckg3.jpg";
  final GlobalKey<ScaffoldState> scaffoldKey = GlobalKey<ScaffoldState>();
  DateTime selectedDate = DateTime.now();
  String _user;
  DBConnect _db = new DBConnect();

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
          accentColor: styleColor,
          primaryColor: styleColor),
      debugShowCheckedModeBanner: false,
      home: WillPopScope(
        onWillPop: () async => false,
        child:Scaffold(
                drawer: MenuDrawer(2),
                appBar: AppBar(
                  title: Text("Your work"),
                  backgroundColor: styleColor,
                  actions: <Widget>[
                    new FlatButton(
                        child: new Icon(AntIcons.logout_outline, color: Colors.white),
                        onPressed: _signOut)
                  ],
                ),
                body: WorkDataTable(),
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
    _db.getUser().then((currUser) {this._user = currUser.uid;});
  }

   void _signOut() async {
    _db.signOut(context);
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