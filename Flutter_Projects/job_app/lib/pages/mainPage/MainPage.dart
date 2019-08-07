import 'package:flutter/material.dart';
import 'package:job_app/items/StyleSettings.dart';
import "package:ant_icons/ant_icons.dart";
import 'package:job_app/pages/authorizationPage/Authorization.dart';
import 'package:job_app/pages/mainPage/WorkDataTable.dart';
import 'package:job_app/pages/mainPage/MenuDrawer.dart';
import 'package:job_app/pages/mainPage/BottomSheetWidget.dart';

class NavigationBar extends StatefulWidget{
  @override
  State<StatefulWidget> createState() => _NavigationBarState();
}

class _NavigationBarState extends State<NavigationBar>{
  int _selectedIndex = 0;
  final GlobalKey<ScaffoldState> scaffoldKey = GlobalKey<ScaffoldState>();
  String _user;
  Authorization _db = new Authorization();
  static const TextStyle optionStyle = TextStyle(fontSize: 30, fontWeight: FontWeight.bold);
  static List<Widget> _widgetOptions = <Widget>[
    WorkDataTable(),
    Text(
      'Index 1: Add',
      style: optionStyle,
    ),
  ];

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
          bottomNavigationBar: BottomNavigationBar(
            items: const <BottomNavigationBarItem>[
              BottomNavigationBarItem(
                icon: Icon(AntIcons.calendar_outline),
                title: Text('Home'),
              ),
              BottomNavigationBarItem(
                icon: Icon(AntIcons.file_add_outline),
                title: Text('Add work'),
              ),
            ],
            currentIndex: _selectedIndex,
            selectedItemColor: styleColor,
            onTap: _onItemTapped,
          ),
          appBar: AppBar(
            title: Text("Your work"),
            backgroundColor: styleColor,
            actions: <Widget>[
              new FlatButton(
                  child: new Icon(AntIcons.logout_outline, color: Colors.white),
                  onPressed: _signOut)
            ],
          ),
          body: _widgetOptions.elementAt(_selectedIndex),
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

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
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