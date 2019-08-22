import 'package:flutter/material.dart';
import 'package:job_app/items/StyleSettings.dart';
import "package:ant_icons/ant_icons.dart";
import 'package:job_app/pages/authorizationPage/Authorization.dart';
import 'package:job_app/pages/mainPage/HomeTab.dart';
import 'package:job_app/pages/mainPage/MenuDrawer.dart';
import 'package:job_app/pages/mainPage/AddTab.dart';
import 'package:job_app/pages/mainPage/SummaryTab.dart';
import 'package:job_app/pages/mainPage/MenuFloatingButton.dart';


class MainPage extends StatefulWidget{
  @override
  State<StatefulWidget> createState() => _MainPageState();
}

class _MainPageState extends State<MainPage>{
  int _selectedIndex = 0;
  final GlobalKey<ScaffoldState> scaffoldKey = GlobalKey<ScaffoldState>();
  Authorization _db = new Authorization();
  static const TextStyle optionStyle = TextStyle(fontSize: 30, fontWeight: FontWeight.bold);
  List<Widget> _widgetOptions = <Widget>[
    HomeTab(),        //DATA TABLE TAB
    SummaryTab(),     //SUMMARY TAB
    AddTab(),         //ADD TAB
//    FancyFab(),
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
                icon: Icon(AntIcons.database_outline),
                title: Text('Home'),
              ),
              BottomNavigationBarItem(
                icon: Icon(Icons.assessment),
                title: Text('Summary'),
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
          floatingActionButton: FancyFab(),
          /*floatingActionButton: FloatingActionButton(
              backgroundColor: styleColor,
              child: Center(
                  child:Icon(AntIcons.form)
              ),
              onPressed: () {}
          ),*/
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
  }

  void _signOut() async {
    _db.signOut(context);
  }
}