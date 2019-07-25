import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:job_app/MenuDrawer.dart';
import 'package:job_app/CalendarWidget.dart';
import 'package:ant_icons/ant_icons.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:job_app/StyleSettings.dart';

class CalendarPage  extends StatefulWidget{
  @override
  State<StatefulWidget> createState() => _WidgetState();
}

class _WidgetState extends State<CalendarPage>{
  final String bcgImage = "assets/images/bckg3.jpg";
  final GlobalKey<ScaffoldState> scaffoldKey = GlobalKey<ScaffoldState>();
  DateTime selectedDate = DateTime.now();

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: WillPopScope(
        onWillPop: () async => false,
        child:Scaffold(
                drawer: MenuDrawer(2),
                appBar: AppBar(
                  title: Text("Calendar"),
                  backgroundColor: styleColor,
                ),
                body: ListView(children: <Widget>[
                  Container(
                    foregroundDecoration: BoxDecoration(color: Colors.transparent),
                    width: 300,
                    height: 300,
                    margin: EdgeInsets.only(bottom: 20),
                    child: Image(
                      image: AssetImage("assets/images/undraw_calendar_dutt.png"),
                    ),
                  ),
                ],
                ),
                /*body: StreamBuilder(
                      stream: Firestore.instance.collection('hoursDB').snapshots(),
                      builder: (context, snapshot){
                        if(!snapshot.hasData) return const Text('Loading...');
                        return ListView.builder(
                          itemCount: snapshot.data.documents.length,
                            itemBuilder: (context, index) =>
                              _buildListItem(context, snapshot.data.documents[index]),
                        );
                      }),*/
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

  Future<Null> _selectDate(BuildContext context) async {
    final DateTime picked = await showDatePicker(
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