import 'package:flutter/material.dart';
import 'package:ant_icons/ant_icons.dart';
import 'package:job_app/pageElements/MenuDrawer.dart';
import 'package:job_app/items/StyleSettings.dart';
import 'package:job_app/items/Authorization.dart';
import 'package:job_app/pageElements/WorkDataTable.dart';
import 'package:job_app/pageElements/BottomSheetWidget.dart';

class CalendarPage  extends StatefulWidget{
  @override
  State<StatefulWidget> createState() => _WidgetState();
}

class _WidgetState extends State<CalendarPage>{
  final String bcgImage = "assets/images/bckg3.jpg";
  final GlobalKey<ScaffoldState> scaffoldKey = GlobalKey<ScaffoldState>();
  String _user;
  Authorization _db = new Authorization();

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
                        .showBottomSheet((context) => BottomSheetWidget(_user));
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
}