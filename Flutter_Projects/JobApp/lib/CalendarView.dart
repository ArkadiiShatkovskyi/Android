import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'MenuDrawer.dart';
import 'CalendarWidget.dart';

class TextControl  extends StatefulWidget{
  @override
  State<StatefulWidget> createState() => _WidgetState();
}

class _WidgetState extends State<TextControl>{
  final String bcgImage = "assets/images/bckg3.jpg";
  final GlobalKey<ScaffoldState> scaffoldKey = GlobalKey<ScaffoldState>();

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
          debugShowCheckedModeBanner: false,
          home: WillPopScope(
            onWillPop: () async => false,
            child: Scaffold(
              drawer: MenuDrawer(2),
              appBar: AppBar(
                title: Text("Calendar"),
                backgroundColor: Colors.lightBlueAccent,
              ),
              body: new CalendarViewApp(),
              key: scaffoldKey,
              floatingActionButton: FloatingActionButton(
                child: Center(
                    child:Icon(Icons.add)
                ),
                onPressed: () {
                  scaffoldKey.currentState
                      .showBottomSheet((context) => _getBottomSheet());
                }
              ),
            ),
          )
    );
  }

  Container _getBottomSheet(){
    return Container(
        height: 200,
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
                    padding: EdgeInsets.symmetric(horizontal: 50.0),
                    shape: new RoundedRectangleBorder(borderRadius: new BorderRadius.circular(30.0)),
                    onPressed: () {},
                    child: const Text('Start time'),
                  ),
                  Spacer(flex: 2),
                  new RaisedButton(
                    padding: EdgeInsets.symmetric(horizontal: 50.0),
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
}